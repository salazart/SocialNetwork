package com.social.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class created for work with file config.properties
 * @author salazart
 *
 */
public class PropertyService {
	private final static String PATH_PROPERTIES = "/src/main/resources/config.properties";
	private static String fileName = System.getProperty("user.dir") + PATH_PROPERTIES;
	
	private static final Logger log = LogManager.getRootLogger();

	public static void setValueProperties(String typeProperties, String valueProperties) {
		Properties properties = readFromFile();
		properties.setProperty(typeProperties, valueProperties);
		writeToFile(properties);
	}

	private static void writeToFile(Properties properties) {
		createDirectory();
			
		try {
			properties.store(new FileOutputStream(fileName), null);
			log.debug("File saved successfully: " + fileName);
		} catch (IOException e) {
			log.error(e);
		} 
	}

	private static void createDirectory() {
		File parentFolder = new File(new File(fileName).getParent());
		if(!parentFolder.isDirectory()){
			if(parentFolder.mkdirs()){
				log.debug("Folder created successfully: " + parentFolder);
			};
		}
	}

	private static Properties readFromFile() {
		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream(fileName));
		} catch (Exception e) {
			log.error(e);
		}
		return properties;
	}

	public static String getValueProperties(String typeProperties) {
		String valueProperties = readFromFile().getProperty(typeProperties);
		if (valueProperties == null) {
			setValueProperties(typeProperties, "");
			return "";
		} else {
			return valueProperties;
		}
	}
}
