package com.social.services;

import java.io.IOException;
import java.io.StringReader;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;

public class ResponseParser {
	private static final String GRILLE_SEPARATOR = "#";
	private static final String PROPERTY_SEPARATOR = "&";
	
	public String parseRequest(String url, String parameter) {
		url = StringUtils.substringAfter(url, GRILLE_SEPARATOR);

		List<String> properties = Arrays.asList(url
				.split(PROPERTY_SEPARATOR));
		Properties prop = new Properties();

		for (String string : properties) {
			try {
				prop.load(new StringReader(string));
			} catch (IOException e) {
				continue;
			}
		}
		String textProperty = prop.getProperty(parameter);
		return textProperty != null ? textProperty : "";
	}
}
