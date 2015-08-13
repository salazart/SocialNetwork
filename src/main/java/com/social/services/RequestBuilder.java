package com.social.services;

import java.io.IOException;
import java.io.StringReader;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;

/**
 * Class RequestBuilder build url with parameters for connection (Build api
 * stream)
 * 
 * @author Salazart
 *
 */
public class RequestBuilder {
    private static final String HTTPS = "https://";
    private static final String QUESTION_SEPARATOR = "?";
    private static final String EQUAL_SEPARATOR = "=";
    private static final String PROPERTY_SEPARATOR = "&";
    private static final String COMMA_SEPARATOR = ",";
    private static final String GRILLE_SEPARATOR = "#";

    private String url;
    private Map<String, String> params;

    public RequestBuilder() {
    };

    public RequestBuilder(String url) {
	this.url = url;
	this.params = new HashMap<String, String>();
    }

    public void addParam(String nameParam, String valueParam) {
	if (valueParam != null && !valueParam.isEmpty()) {
	    if (params.get(nameParam) != null) {
		params.put(nameParam, params.get(nameParam) + COMMA_SEPARATOR
			+ valueParam);
	    } else {
		params.put(nameParam, valueParam);
	    }
	}
    }

    public void addParam(String nameParam, int valueParam) {
	addParam(nameParam, String.valueOf(valueParam));
    }

    public void addParam(String nameParam, String... valuesParam) {
	if (valuesParam != null) {
	    for (int i = 0; i < valuesParam.length; i++) {
		addParam(nameParam, valuesParam[i]);
	    }
	}

    }

    public String buildRequest(String url, Map<String, String> params) {
	if (url == null) {
	    return "";
	} else {
	    StringBuffer parameterUrl = new StringBuffer(url);
	    for (Map.Entry<String, String> entry : params.entrySet()) {
		if (parameterUrl.toString().contains(QUESTION_SEPARATOR)) {
		    parameterUrl.append(PROPERTY_SEPARATOR)
			    .append(entry.getKey()).append(EQUAL_SEPARATOR)
			    .append(entry.getValue());
		} else {
		    parameterUrl.append(QUESTION_SEPARATOR)
			    .append(entry.getKey()).append(EQUAL_SEPARATOR)
			    .append(entry.getValue());
		}

	    }
	    return HTTPS + parameterUrl.toString();
	}
    }

    public String buildRequest() {
	return buildRequest(this.url, this.params);
    }

    public String parseRequest(URL url, String parameter) {
	String textUrl = String.valueOf(url);
	textUrl = StringUtils.substringAfter(textUrl, GRILLE_SEPARATOR);

	List<String> properties = Arrays.asList(textUrl
		.split(PROPERTY_SEPARATOR));
	Properties prop = new Properties();

	for (String string : properties) {
	    try {
		prop.load(new StringReader(string));
	    } catch (IOException e) {
		continue;
	    }
	}

	return prop.getProperty(parameter);
    }
}
