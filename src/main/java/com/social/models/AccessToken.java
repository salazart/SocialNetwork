package com.social.models;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import com.social.services.AccessTokenService;
import com.social.services.RequestBuilder;
import com.social.utils.ParametersDictionary;

public class AccessToken {
    private static final String HTTPS = "https://";
    private static final String defaultIdApplication = "4517745";
    private static final String defaultScope = "wall,offline";
    private static final String defaultUrl = "oauth.vk.com/authorize";
    private static final String defaultDisplay = "mobile";
    private static final String defaultResponseType = "token";

    private String url;
    private Map<String, String> headers;
    private String accessToken = "";

    private RequestBuilder requestBuilder = new RequestBuilder();

    public AccessToken() {
	this(defaultUrl);
	setClientId(defaultIdApplication);
	setScope(defaultScope);
	setRedirectURI("https://oauth.vk.com/blank.html");
	setDisplay(defaultDisplay);
	setResponseType(defaultResponseType);
    }

    public AccessToken(String url) {
	this.url = url;
	this.headers = new HashMap<String, String>();
    }

    public void setClientId(String clientId) {
	this.headers.put(ParametersDictionary.CLIENT_ID, clientId);
    }

    public void setScope(String scope) {
	this.headers.put(ParametersDictionary.SCOPE, scope);
    }

    public void setRedirectURI(String redirectUrl) {
	this.headers.put(ParametersDictionary.REDIRECT_URI, redirectUrl);
    }

    public void setDisplay(String display) {
	this.headers.put(ParametersDictionary.DISPLAY, display);
    }

    public void setResponseType(String responseType) {
	this.headers.put(ParametersDictionary.RESPONSE_TYPE, responseType);
    }

    public String buildQueryMessage() {
	return requestBuilder.buildRequest(HTTPS + url, headers);
    }

    public String generateAccessToken(String login, String pass) {
	if (this.accessToken == null || this.accessToken.isEmpty()) {
	    String queryMessage = buildQueryMessage();
	    AccessTokenService accessTokenService = new AccessTokenService(
		    queryMessage);

	    URL url = accessTokenService.generateAccessToken(login, pass);

	    this.accessToken = requestBuilder.parseRequest(url,
		    ParametersDictionary.ACCESS_TOKEN);

	    return this.accessToken;
	} else {
	    return this.accessToken;
	}
    }

    public String getAccessToken() {
	return this.accessToken;
    }
    
    public boolean isValidAccessToken(){
	return getAccessToken() != null && !getAccessToken().isEmpty();
    }
}
