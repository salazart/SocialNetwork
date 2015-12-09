package com.social.accesstoken.services;

import java.io.IOException;
import java.io.StringReader;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.social.models.SocialNetwork;
import com.social.utils.ParametersDictionary;

/**
 * Class parse url response from access token page and return value entity by parameter
 * @author salazart
 *
 */

public class ResponseParser {
    private static final String GRILLE_SEPARATOR = "#";
    private static final String PROPERTY_SEPARATOR = "&";
    
    private static final Logger log = LogManager.getRootLogger();
    
	protected boolean isAuthCorrect(SocialNetwork socialNetwork) {
		return socialNetwork.getLogin() != null
				&& !socialNetwork.getLogin().isEmpty()
				&& socialNetwork.getPass() != null
				&& !socialNetwork.getPass().isEmpty();
	}
    
	protected String getRequestUrl(HtmlPage permissionPage, HtmlPage accessTokenPage) {
		String requestUrl = "";
		if (accessTokenPage != null) {
			log.debug("Permission page return access token page");
			requestUrl = String.valueOf(accessTokenPage.getWebResponse().getRequestUrl());
		} else if (permissionPage != null){
			log.debug("Authorize page return access token page");
			requestUrl =  String.valueOf(permissionPage.getWebResponse().getRequestUrl());
		} else {
			log.debug("Authorize page and permission page return null");
		}
		
		log.debug("Access token response: " + requestUrl);
		
		return parseRequest(requestUrl, ParametersDictionary.ACCESS_TOKEN);
	}
    
    private String parseRequest(String url, String parameter) {
    	log.debug("Url for parsing: " + url);
    	String urlsEntity = StringUtils.substringAfter(url, GRILLE_SEPARATOR);

    	List<String> properties = Arrays.asList(urlsEntity.split(PROPERTY_SEPARATOR));
	
    	Properties prop = parsePropertyText(properties);
	
    	String textProperty = prop.getProperty(parameter);
    	log.debug(parameter + " = " + textProperty);
    	return textProperty != null ? textProperty : "";
    }

	private Properties parsePropertyText(List<String> properties) {
		Properties prop = new Properties();
		for (String property : properties) {
		    try {
		    	prop.load(new StringReader(property));
		    } catch (IOException e) {
		    	log.debug(e);
		    	continue;
		    }
		}
		return prop;
	}
}
