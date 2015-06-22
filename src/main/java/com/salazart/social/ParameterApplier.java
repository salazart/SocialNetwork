package com.salazart.social;

import java.util.Map;

import org.apache.oltu.oauth2.common.message.OAuthMessage;
import org.apache.oltu.oauth2.common.utils.OAuthUtils;

public class ParameterApplier {
    private static final String QUESTION_SEPARATOR = "?";
    private static final String EQUAL_SEPARATOR = "=";
    private static final String PROPERTY_SEPARATOR = "&";

    public String applyParameters(String url, Map<String, String> params) {
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
	    return parameterUrl.toString();
	}
    }
}
