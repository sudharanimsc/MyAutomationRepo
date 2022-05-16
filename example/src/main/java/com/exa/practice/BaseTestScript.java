package com.exa.practice;

import org.openqa.selenium.WebDriver;

public class BaseTestScript {
public static WebDriver driver;

public WebDriver getDriver() {
	return driver;
}

public void setDriver(WebDriver driver) {
	this.driver = driver;
}
}

