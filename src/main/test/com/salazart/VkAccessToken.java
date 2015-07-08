
package com.salazart;

import com.social.models.AccessToken;
import com.social.services.AccessTokenService;

public class VkAccessToken {

	public static void main(String[] args) {
		AccessToken accessToken = new AccessToken(
				"oauth.vk.com/authorize");
			accessToken.setClientId("4517745");
			accessToken.setScope("wall,offline");
			accessToken.setRedirectURI("https://oauth.vk.com/blank.html");
			accessToken.setDisplay("mobile");
			accessToken.setResponseType("token");

			AccessTokenService accessTokenService = new AccessTokenService(accessToken.buildQueryMessage());
			accessTokenService.generateAccessToken("", "");
	}
}
