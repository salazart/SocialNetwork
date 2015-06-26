package com.social.oauth;

import java.util.HashMap;
import java.util.Map;

public class OAuthRequest {
    private String url;
    private Map<String, String> headers;

    public OAuthRequest(String url) {
	this.url = url;
	this.headers = new HashMap<String, String>();
    }

    public void setClientId(String clientId) {
	this.headers.put(OAuth.OAUTH_CLIENT_ID, clientId);
    }

    public void setScope(String scope) {
	this.headers.put(OAuth.OAUTH_SCOPE, scope);
    }

    public void setRedirectURI(String redirectUrl) {
	this.headers.put(OAuth.OAUTH_REDIRECT_URI, redirectUrl);
    }

    public void setDisplay(String display) {
	this.headers.put(OAuth.OAUTH_DISPLAY, display);
    }

    public void setResponseType(String responseType) {
	this.headers.put(OAuth.OAUTH_RESPONSE_TYPE, responseType);
    }

    public String buildQueryMessage() {
	RequestBuilder parameterApplier = new RequestBuilder();
	return parameterApplier.buildRequest(url, headers);
    }

}
