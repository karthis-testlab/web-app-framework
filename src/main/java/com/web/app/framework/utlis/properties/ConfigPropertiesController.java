package com.web.app.framework.utlis.properties;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ConfigPropertiesController {
	
	public static String getConfigValue(String key) {
		Properties properties = new Properties();
		String value = null;
		try {
			FileInputStream file = new FileInputStream("./waf-config.properties");
			properties.load(file);
			value = properties.getProperty(key);
		} catch (FileNotFoundException e) {			
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return value;
	}
	
	public static void main(String[] args) {
		List<String> arguments = new ArrayList<String>();
		System.out.println(getConfigValue("waf.default.browser.name"));
		System.out.println(Boolean.valueOf(getConfigValue("waf.headless.browser")));
		System.out.println(Long.valueOf(getConfigValue("waf.webdriver.implicit.wait.seconds")));
		String[] strings = getConfigValue("waf.chromium.browser.options").split(",");
		for (String string : strings) {
			arguments.add(string);
		}
		System.out.println(arguments);
	}

}