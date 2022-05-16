package com.exa.practice;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class LogGenerator {
	
	public static Logger log = Logger.getLogger(LogGenerator.class.getName());
	public void configureLog4j() throws FileNotFoundException, IOException {
		Properties props = new Properties();
		//InputStream confStream= new FileInputStream("C:\\Users\\ragha\\Desktop\\NIHB\\example\\src\\main\\resources");
		InputStream confStream = LogGenerator.class.getClassLoader().getResourceAsStream("Log4j.properties");
		//props.load(LogGenerator.class.getClassLoader().getResourceAsStream("Log4j.properties"));
		props.load(confStream);
		confStream.close();
		props.setProperty("log4j.appender.R.File", "C:\\Users\\ragha\\Desktop\\practice\\Log4j\\errorlog.log");
		props.setProperty("log4j.appender.HTML.File", "C:\\Users\\ragha\\Desktop\\practice\\Log4j\\log4j.html");
		LogManager.resetConfiguration();
		PropertyConfigurator.configure(props);
	}

}
