package com.social.services;

import com.social.models.SocialNetwork;
import com.social.models.requests.Post;
import com.social.utils.ParametersDictionary;
import com.social.utils.PermissionDictionary;
import com.social.utils.UrlsDictionary;

public class VkService{
	private static final String APP_ID = "4517745";
	private String accessToken = "";

	public void postWall(Post post, SocialNetwork socialNetwork) {
		RequestBuilder requestBuilder = new RequestBuilder(
				UrlsDictionary.VK_POST_WALL);

		if (accessToken.isEmpty()) {
			generateAccessToken(socialNetwork, PermissionDictionary.VK_WALL);
		}

		requestBuilder.addParam(ParametersDictionary.ACCESS_TOKEN, accessToken);
		requestBuilder.addParam(ParametersDictionary.OWNER_ID, post.getId());
		requestBuilder.addParam(ParametersDictionary.MESSAGE, post.getText());

		System.out.println(requestBuilder.buildRequest());
		ConnectionService connectionService = new ConnectionService();
		String content = connectionService.createConnection(requestBuilder
				.buildRequest());
		System.out.println(content);
	}

	public String generateAccessToken(SocialNetwork socialNetwork,
			String typePermission) {
		
		String accessTokenRequest = createAccessTokenRequest(typePermission);
		
		AccessTokenService accessTokenService = new AccessTokenService(
				accessTokenRequest, socialNetwork);
		String url = accessTokenService.getAccessTokenResponse();
		
		ResponseParser responseParser = new ResponseParser();
		return responseParser.parseRequest(url,
				ParametersDictionary.ACCESS_TOKEN);
		
	}
	
	private String createAccessTokenRequest(String typePermission) {
		RequestBuilder requestBuilder = new RequestBuilder(
				UrlsDictionary.VK_OAUTH_DIALOG);
		requestBuilder.addParam(ParametersDictionary.CLIENT_ID, APP_ID);
		requestBuilder.addParam(ParametersDictionary.RESPONSE_TYPE,
				ParametersDictionary.TOKEN);
		requestBuilder.addParam(ParametersDictionary.SCOPE, typePermission);
		requestBuilder.addParam(ParametersDictionary.REDIRECT_URI,
				UrlsDictionary.VK_REDIRECT_URL);
		requestBuilder.addParam(ParametersDictionary.DISPLAY,
				ParametersDictionary.MOBILE);
		return requestBuilder.buildRequest();
    }
}