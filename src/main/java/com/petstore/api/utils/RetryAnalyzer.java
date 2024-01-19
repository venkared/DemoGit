package com.petstore.api.utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {

	int counter = 0;
	int retryLimit = 2;
	public boolean retry(ITestResult result) {
		
		
		if (result.getStatus () == ITestResult.FAILURE) {
			result.getTestContext().getSkippedTests().removeResult(result.getMethod());

			if (counter < retryLimit) {
				System.err.println ("Retrying...");
				try {
					Thread.sleep (5000);
				}
				catch (InterruptedException e) {
					e.printStackTrace ();
				}
				counter++;
				return true;
			}
				
		}
		return false;	

		
	}
		
}
