
package com.salazart;

import java.net.URL;

import com.social.models.AccessToken;
import com.social.models.Parameters;
import com.social.services.AccessTokenService;
import com.social.services.RequestBuilder;

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
			URL url = accessTokenService.generateAccessToken("s.o.w@i.ua", ".salazart.1989...");
			
			RequestBuilder requestBuilder = new RequestBuilder();
			String accessTokenOut = requestBuilder.parseRequest(url, Parameters.ACCESS_TOKEN);
			
			System.out.println(accessTokenOut);
			
	}
}
