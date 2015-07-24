package com.social.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class PropertyService {
	private final static String PATH_PROPERTIES = "src/main/resources/config.properties";
	private String propFileName = PATH_PROPERTIES;

	private static PropertyService instance;
	
	private PropertyService(){
	}
	
    public static PropertyService getInstance() {
        if (instance == null) {
        	synchronized(PropertyService.class) {
        		if(instance == null) {
        			instance = new PropertyService();
        		}
        	}
        }
        return instance;
    }
	
	public void setValueProperties(String typeProperties, String valueProperties) {
		Properties prop = new PropertyService().getProperties();
		prop.setProperty(typeProperties, valueProperties);
		OutputStream out = null;
		try {
			out = new FileOutputStream( propFileName );
			prop.store(out, null);
		} catch (IOException e) {
			System.out.println(this.getClass().toString()+" "+e.getMessage()+" Error read properties from file: " + propFileName);
			if(createPath(propFileName)){
				setValueProperties(typeProperties, valueProperties);
			}
		}finally{
			try {
				if(out != null){
					out.close();
				}
			} catch (IOException e) {
				System.out.println(this.getClass().toString()+" "+e.getMessage()+" Error read properties");
			}
		}
	}
	
	private boolean createPath(String path) {
		File f = new File(propFileName);
		f.getParentFile().mkdirs(); 
		try {
			f.createNewFile();
			System.out.println("File: " + propFileName + " created successfully");
			return true;
		} catch (IOException e) {
			System.out.println(e.getMessage() + " Can't create file: " + path);
			return false;
		}
	}
	
	//Read properties from file
	public Properties getProperties(){
		Properties prop = new Properties();
		if(new File(propFileName).isFile()){
			InputStream inputStream = null;
			try {
				inputStream = new FileInputStream(propFileName);
				prop.load(inputStream);
				inputStream.close();
			} catch (Exception e) {
				System.out.println(this.getClass().toString()+ " " +e.getMessage() + " Error read properties");
			}
		} else {
			System.out.println(this.getClass().toString() +" Don't found propertyfile " + PATH_PROPERTIES);
		}
		return prop;
	}

	public String getValueProperties(String typeProperties) {
		Properties prop = new PropertyService().getProperties();
		String valueProperties = prop.getProperty(typeProperties);
		if(valueProperties != null){
			return valueProperties;
		} else {
			return "";
		}
	}
}
