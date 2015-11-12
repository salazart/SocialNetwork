package com.salazart;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;

public class ClassPath {

	public static void main(String[] args) {
		String classpath = System.getProperty("java.class.path");
		String[] classpathEntries = classpath.split(File.pathSeparator);
		for (String string : classpathEntries) {
			System.out.println(string);
		}
		
		ClassPath s = new ClassPath();
		
		System.out.println(s.getClass().getResource("/config.properties"));
		InputStream input = s.getClass().getResourceAsStream("/config.properties");
		Properties prop = new Properties();
		try {
			prop.load(input);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Enumeration<?> e = prop.propertyNames();
		while (e.hasMoreElements()) {
			String key = (String) e.nextElement();
			String value = prop.getProperty(key);
			System.out.println("Key : " + key + ", Value : " + value);
		}
	}

}
