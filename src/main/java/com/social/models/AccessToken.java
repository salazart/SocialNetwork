package com.social.models;

import java.util.HashMap;
import java.util.Map;

import com.social.services.RequestBuilder;

public class AccessToken {
    private String url;
    private Map<String, String> headers;

    public AccessToken(String url) {
	this.url = url;
	this.headers = new HashMap<String, String>();
    }

    public void setClientId(String clientId) {
	this.headers.put(Parameters.CLIENT_ID, clientId);
    }

    public void setScope(String scope) {
	this.headers.put(Parameters.SCOPE, scope);
    }

    public void setRedirectURI(String redirectUrl) {
	this.headers.put(Parameters.REDIRECT_URI, redirectUrl);
    }

    public void setDisplay(String display) {
	this.headers.put(Parameters.DISPLAY, display);
    }

    public void setResponseType(String responseType) {
	this.headers.put(Parameters.RESPONSE_TYPE, responseType);
    }

    public String buildQueryMessage() {
	RequestBuilder parameterApplier = new RequestBuilder();
	return parameterApplier.buildRequest(url, headers);
    }

}
