package com.social.services;

import com.social.models.SocialNetwork;
import com.social.models.requests.Post;
import com.social.utils.ParametersDictionary;
import com.social.utils.PermissionDictionary;
import com.social.utils.UrlsDictionary;

public class FbService {
	private static final String APP_ID = "700011900132723";
	private String accessToken = "";

	public void postWall(Post post, SocialNetwork socialNetwork) {
		generateAccessToken(socialNetwork,
				PermissionDictionary.FB_PUBLISH_ACTION);

		RequestBuilder requestBuilder = new RequestBuilder(
				UrlsDictionary.FB_GRAPH + post.getId()
						+ UrlsDictionary.FB_POST_WALL);

		requestBuilder.addParam(ParametersDictionary.ACCESS_TOKEN, accessToken);
		requestBuilder.addParam(ParametersDictionary.MESSAGE, post.getText());

		System.out.println(requestBuilder.buildRequest());
		ConnectionService connectionService = new ConnectionService(
				ConnectionService.POST_REQUEST_METHOD);
		String content = connectionService.createConnection(requestBuilder
				.buildRequest());
		System.out.println(content);
	}

	public void generateAccessToken(SocialNetwork socialNetwork,
			String typePermission) {
		RequestBuilder requestBuilder = new RequestBuilder(
				UrlsDictionary.FB_OAUTH_DIALOG);
		requestBuilder.addParam(ParametersDictionary.CLIENT_ID, APP_ID);
		requestBuilder.addParam(ParametersDictionary.RESPONSE_TYPE,
				ParametersDictionary.TOKEN);
		requestBuilder.addParam(ParametersDictionary.SCOPE, typePermission);
		requestBuilder.addParam(ParametersDictionary.REDIRECT_URI,
				UrlsDictionary.FB_REDIRECT_URL);
		requestBuilder.addParam(ParametersDictionary.DISPLAY,
				ParametersDictionary.POPUP);

		AccessTokenService accessTokenService = new AccessTokenService(
				requestBuilder.buildRequest(), socialNetwork);
		String url = accessTokenService.getAccessTokenResponse();
		accessToken = requestBuilder.parseRequest(url,
				ParametersDictionary.ACCESS_TOKEN);
	}
}
