package com.exa.testscripts;


import org.openqa.selenium.WebDriver;

import com.exa.practice.BaseTestScript;


public class TestScript1 extends BaseTestScript {

	public void helloworld() {
		System.out.println("HelloWorld");
	}
	
	public void ValidateAccounts() {
		System.out.println("ValidateAccounts");		
		WebDriver targetDriver=getDriver();
		targetDriver.get("http://google.com");		
	}
}
