package com.social.oauth;

import java.util.HashMap;
import java.util.Map;

import org.apache.oltu.oauth2.common.message.OAuthMessage;
import org.apache.oltu.oauth2.common.utils.OAuthUtils;

public class RequestBuilder {
    private static final String QUESTION_SEPARATOR = "?";
    private static final String EQUAL_SEPARATOR = "=";
    private static final String PROPERTY_SEPARATOR = "&";
    private static final String COMMA_SEPARATOR = ",";
    
    private String url;
    private Map<String, String> params;

    public RequestBuilder(){};
    
    public RequestBuilder(String url){
    	this.url = url;
    	this.params = new HashMap<String, String>();
    }
    
    public void addParam(String nameParam, String valueParam){
    	if(params.get(nameParam) != null){
    		params.put(nameParam, params.get(nameParam) + COMMA_SEPARATOR + valueParam);
    	} else {
    		params.put(nameParam, valueParam);
    	}
    }
    
    public void addParam(String nameParam, int valueParam){
    	addParam(nameParam, String.valueOf(valueParam));
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
    	    return parameterUrl.toString();
    	}
    }
    
    public String buildRequest(){
    	return buildRequest(this.url, this.params);
    }
}
