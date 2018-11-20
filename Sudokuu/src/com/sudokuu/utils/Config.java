package com.sudokuu.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class Config {
	private static Properties configFile=null;

	private Config() {
		configFile = new java.util.Properties();
		InputStream input = null;
		try {
			input = this.getClass().getClassLoader()
					.getResourceAsStream("/config.properties");
			configFile.load(input);
		} catch (Exception eta) {
			eta.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static Properties getConfigProperty(){
		if(configFile==null){
			new Config();
		}
		
		return configFile;
		
	}
}
