package com.petstore.api.endpoints;

import com.petstore.api.baseAPI.Routes;
import com.petstore.api.payloads.UserPojo;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;

public class UserEndPoints {


	
	public static Response Createuser(UserPojo payload)	{

		Response response = given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(payload)
				.when().post(Routes.postUser);
		return response;
	}
	

	public static Response UpdateUser(String userName,UserPojo payload)	{

		Response response = given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.pathParam("username", userName)
				.body(payload)
				.when().put(Routes.putuser);
		return response;
	}
	
	
	
	public static Response GetUser(String userName){

		Response response = given().when().pathParam("username", userName)
				.get(Routes.getUser);
		return response;
	}
	
	

	public static Response DeleteUser(String userName)	{

		Response response = given().when().pathParam("username", userName)
				.delete(Routes.deleteUser);
		return response;
	}
	

}
