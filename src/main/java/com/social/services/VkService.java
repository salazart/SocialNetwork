package com.social.services;

import com.social.models.SocialNetwork;
import com.social.models.requests.Post;
import com.social.utils.ParametersDictionary;
import com.social.utils.PermissionDictionary;
import com.social.utils.UrlsDictionary;

public class VkService {
    private static final String APP_ID = "4517745";
    private static final int COUNT_UIDS = 200;
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

    public void generateAccessToken(SocialNetwork socialNetwork,
	    String typePermission) {
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

	AccessTokenService accessTokenService = new AccessTokenService(
		requestBuilder.buildRequest(), socialNetwork);
	String url = accessTokenService.getAccessTokenResponse();

	System.out.println(url);
	ResponseParser responseParser = new ResponseParser();
	accessToken = responseParser.parseRequest(url,
		ParametersDictionary.ACCESS_TOKEN);
	System.out.println(accessToken);
	
	if (!accessToken.isEmpty()) {
	    System.out.println("Access token obtained successfully");
	} else {
	    System.out.println("Access token is error");
	}
    }
}