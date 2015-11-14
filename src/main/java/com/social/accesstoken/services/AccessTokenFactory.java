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
			return new VkAccessToken(url).getAccessToken(socialNetwork);
		case FB:
			return new FbAccessToken(url).getAccessToken(socialNetwork);
		case OK:
			return new OkAccessToken(url).getAccessToken(socialNetwork);
		default:
			return "";
		}
	}
}
