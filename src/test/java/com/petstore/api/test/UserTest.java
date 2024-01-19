package com.petstore.api.test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItems;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;
import com.petstore.api.endpoints.UserEndPoints;
import com.petstore.api.payloads.UserPojo;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;


public class UserTest {
	
		
	Faker faker;
	UserPojo userpayload;
	public static Logger log = LogManager.getLogger(UserTest.class);
	
	
	
	@BeforeClass
	public void setup()
	{
		faker= new Faker();
		userpayload = new UserPojo();

	    userpayload.setId(faker.idNumber().hashCode());
		userpayload.setUsername(faker.name().username());
		userpayload.setFirstName(faker.name().name());
		userpayload.setLastName(faker.name().lastName());
		userpayload.setEmail(faker.internet().safeEmailAddress());
		userpayload.setPassword(faker.internet().password());
		userpayload.setPhone(faker.phoneNumber().cellPhone());
		
	}

	@Test (priority=1)
	public void PostUserTest(){
		Response response =UserEndPoints.Createuser(userpayload);
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
	}
	
	
	@Test (priority=2)
	public void PutUserTest(){
		
		userpayload.setFirstName(faker.name().name());
		userpayload.setLastName(faker.name().lastName());
		
		Response response =UserEndPoints.UpdateUser(this.userpayload.getUsername(),userpayload);
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
	}
	
	@Test (priority=3)
	public void GetUserTest(){
		Response response =UserEndPoints.GetUser(this.userpayload.getUsername());
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200, "User not found");
		JsonPath jsonpath = response.jsonPath();

		log.info("firstName and lastName verification" );	
		Assert.assertEquals(jsonpath.get("firstName"),this.userpayload.getFirstName()); 
		Assert.assertEquals(jsonpath.get("lastName"),this.userpayload.getLastName());
		
		
	}	
	

	@Test (priority=4)
	public void DeleteUserTest(){
		Response response =UserEndPoints.DeleteUser(this.userpayload.getUsername());
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200,"User not found to delete");
		log.info("======UserName has been delete successfully=======");
		
	}
	

}
