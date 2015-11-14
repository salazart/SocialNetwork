package com.social.accesstoken.services;

import com.social.models.SocialNetwork;

public class AccessTokenFactory {
	
	private String url;

	public AccessTokenFactory(String url) {
		this.url = url;
	}

	public String getAccessTokenResponse(SocialNetwork socialNetwork) {
		switch (socialNetwork.getType()) {
		case VK:
			return new VKAccessToken(url).getAccessTokenResponse(socialNetwork);
		case FB:
			return new FBAccessToken(url).getAccessTokenResponse(socialNetwork);
		case OK:
			return new OKAccessToken(url).getAccessTokenResponse(socialNetwork);
		default:
			return "";
		}
	}
}
