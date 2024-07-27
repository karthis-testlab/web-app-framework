package com.web.app.framework.utlis.properties.objects;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ObjectRepositoriesController {
	
	public static String getDomValue(String fileName, String key) {
		Properties properties = new Properties();
		String value = null;
		try {
			FileInputStream file = new FileInputStream("./src/test/resources/ObjectsRepositories/"+fileName+".properties");
			properties.load(file);
			value = properties.getProperty(key);
		} catch (FileNotFoundException e) {			
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return value;
	}

}
