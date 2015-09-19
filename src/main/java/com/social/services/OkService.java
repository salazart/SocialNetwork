package com.social.services;

import com.social.models.SocialNetwork;
import com.social.models.requests.Post;
import com.social.utils.ParametersDictionary;
import com.social.utils.PermissionDictionary;
import com.social.utils.PropertyService;
import com.social.utils.UrlsDictionary;

public class OkService extends OkSessionService{
	private static final String APP_KEY = "okAppKey";
	private static final String APP_ID = "1151816192";
	private String accessToken = "";

	public void postWall(Post post, SocialNetwork socialNetwork) {
		final String methodName = "users.getCurrentUser";
		
		accessToken = "17b7d264a726981246d6a0b425a55444a9c36344fd809a9174.e1c36191";
		
		if (accessToken.isEmpty()) {
			generateAccessToken(socialNetwork, PermissionDictionary.OK_GROUP_CONTENT);
		}
		
		String sig = generateSesionSignature(accessToken, methodName);
		System.out.println(sig);
		
		RequestBuilder requestBuilder = new RequestBuilder(
				UrlsDictionary.OK_URL_REQUEST);

		String appKey = PropertyService.getInstance().getValueProperties(APP_KEY);
		requestBuilder.addParam(ParametersDictionary.APP_KEY, appKey);
		requestBuilder.addParam(ParametersDictionary.METHOD, methodName);
		requestBuilder.addParam(ParametersDictionary.ACCESS_TOKEN, accessToken);
		requestBuilder.addParam(ParametersDictionary.SIG, sig);
		
		System.out.println(requestBuilder.buildRequest());
		ConnectionService connectionService = new ConnectionService();
		//String content = connectionService.createConnection(requestBuilder
		//		.buildRequest());
		//System.out.println(content);
		
		
	}

	public String generateAccessToken(SocialNetwork socialNetwork, String typePermission) {

		String accessTokenRequest = createAccessTokenRequest(typePermission);

		System.out.println(accessTokenRequest);

		AccessTokenService accessTokenService = new AccessTokenService(accessTokenRequest, socialNetwork);
		String url = accessTokenService.getAccessTokenResponse();

		System.out.println(url);

		ResponseParser responseParser = new ResponseParser();
		accessToken = responseParser.parseRequest(url, ParametersDictionary.ACCESS_TOKEN);
		
		System.out.println(accessToken);
		
		return accessToken;
	}

	private String createAccessTokenRequest(String typePermission) {
		RequestBuilder requestBuilder = new RequestBuilder(UrlsDictionary.OK_OAUTH_DIALOG);
		requestBuilder.addParam(ParametersDictionary.CLIENT_ID, APP_ID);
		requestBuilder.addParam(ParametersDictionary.RESPONSE_TYPE, ParametersDictionary.TOKEN);
		requestBuilder.addParam(ParametersDictionary.SCOPE, typePermission);
		requestBuilder.addParam(ParametersDictionary.REDIRECT_URI, UrlsDictionary.OK_REDIRECT_URL);
		return requestBuilder.buildRequest();
	}

}
