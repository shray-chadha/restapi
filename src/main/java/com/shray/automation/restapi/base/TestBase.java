package com.shray.automation.restapi.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;


public class TestBase {
	
	public Properties prop;	
	
	public TestBase() {		
		prop = new Properties();
			try {
				prop.load(new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\com\\shray\\automation\\restapi\\config\\config.properties"));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
	}
	
}
