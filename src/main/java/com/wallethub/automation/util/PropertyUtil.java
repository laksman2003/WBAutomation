package com.wallethub.automation.util;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class PropertyUtil {
    
	public  static String getProperty(String key)
	{
		Properties prop = null; 
		try
		{
		    prop = new Properties();
			prop.load(new FileInputStream(System.getProperty("user.dir")+File.separator+"config.properties"));
			
		}
		catch(Exception e) {}
		return prop.getProperty(key);
	}
	
}
