package com.social.services;

import com.social.models.SocialNetwork;
import com.social.utils.ParametersDictionary;
import com.social.utils.UrlsDictionary;

public class OkService {
	private static final String APP_ID = "1151816192";
	private String accessToken = "";
	
	public void generateAccessToken(SocialNetwork socialNetwork,
			String typePermission) {
		RequestBuilder requestBuilder = new RequestBuilder(
				UrlsDictionary.OK_OAUTH_DIALOG);
		requestBuilder.addParam(ParametersDictionary.CLIENT_ID, APP_ID);
		requestBuilder.addParam(ParametersDictionary.RESPONSE_TYPE,
				ParametersDictionary.TOKEN);
		requestBuilder.addParam(ParametersDictionary.SCOPE, typePermission);
		requestBuilder.addParam(ParametersDictionary.REDIRECT_URI,
				UrlsDictionary.OK_REDIRECT_URL);
		System.out.println(requestBuilder.buildRequest());

		AccessTokenService accessTokenService = new AccessTokenService(
				requestBuilder.buildRequest(), socialNetwork);
		String url = accessTokenService.getAccessTokenUrl();
		
		accessToken = requestBuilder.parseRequest(url,
				ParametersDictionary.ACCESS_TOKEN);
	}

}
