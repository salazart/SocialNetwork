package com.salazart.social;

import java.util.HashMap;
import java.util.Map;

public class OAuthRequest {
	private String url;
	private Map<String, String> headers;
	
	public OAuthRequest(String url) {
		this.url = url;
		this.headers = new HashMap<String, String>();
	}
	
	public void setClientId(String clientId){
		this.headers.put(OAuth.OAUTH_CLIENT_ID, clientId);
	}
	
	
	
	
}
