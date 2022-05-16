package com.exa.practice;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

public class DriverScript {
	
public static void main(String[] args) throws InvalidFormatException, IOException {
	ExecuteScript executeScript=new ExecuteScript();
	executeScript.run();
}

}
