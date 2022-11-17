package com.nopcommerce.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {

	public Properties pro;
	public ReadConfig() {

		File src = new File("./Configuration/config.properties");
		
		try {
			FileInputStream fis = new FileInputStream(src);
			pro = new Properties();
			pro.load(fis);
		} catch (Exception e) {
			System.out.println("Exception is -" + e.getMessage());
		}
	}

	public String getApplicationUrl() {
		String url=pro.getProperty("baseUrl");
		return url;
		
	}
	public String getChromepath() {
		String chrome=pro.getProperty("chromepath");
		return chrome;
		
	}
	public String getEdgepath() {
		String edge=pro.getProperty("edgepath");
		return edge;
		
	}
	public String getFirefoxpath() {
		String firefox=pro.getProperty("forefoxpath");
		return firefox;
		
	}



}
