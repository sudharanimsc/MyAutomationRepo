package com.exa.practice;

import java.io.IOException;
import java.util.Properties;

//import com.esc.nihb.utility.framework.FrameworkUtility;

public class FrameworkUtility {
	Properties conf_properties = new Properties();

	public FrameworkUtility() {
		try {
			conf_properties.load(FrameworkUtility.class.getClassLoader().getResourceAsStream("Config.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getConfigProperties(String key) throws IOException {

		String value = conf_properties.getProperty(key);
		return value;

	}

}
