package com.social.services;

import com.social.models.SocialNetwork;
import com.social.models.requests.Post;
import com.social.utils.ParametersDictionary;
import com.social.utils.UrlsDictionary;

public class OkService {
	private static final String APP_ID = "1151816192";
	private String accessToken = "";
	
	public void postWall(Post post, SocialNetwork socialNetwork) {
		
	}
	
	public void generateAccessToken(SocialNetwork socialNetwork,
			String typePermission) {
		String accessTokenRequest = createAccessTokenRequest(typePermission);
System.out.println(accessTokenRequest);
		AccessTokenService accessTokenService = new AccessTokenService(
				accessTokenRequest, socialNetwork);
		String url = accessTokenService.getAccessTokenResponse();
		System.out.println(url);
		ResponseParser responseParser = new ResponseParser();
		accessToken = responseParser.parseRequest(url, ParametersDictionary.ACCESS_TOKEN);
		System.out.println(accessToken);
	}

	private String createAccessTokenRequest(String typePermission) {
		RequestBuilder requestBuilder = new RequestBuilder(
				UrlsDictionary.OK_OAUTH_DIALOG);
		requestBuilder.addParam(ParametersDictionary.CLIENT_ID, APP_ID);
		requestBuilder.addParam(ParametersDictionary.RESPONSE_TYPE,
				ParametersDictionary.TOKEN);
		requestBuilder.addParam(ParametersDictionary.SCOPE, typePermission);
		requestBuilder.addParam(ParametersDictionary.REDIRECT_URI,
				UrlsDictionary.OK_REDIRECT_URL);
		return requestBuilder.buildRequest();
	}

}
