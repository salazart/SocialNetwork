package com.social.services;

import java.net.URL;
import java.util.List;

import com.social.interfaces.ISocialNetwork;
import com.social.models.Post;
import com.social.models.SocialNetwork;
import com.social.models.VkCity;
import com.social.models.VkUser;
import com.social.utils.ParametersDictionary;
import com.social.utils.PermissionDictionary;
import com.social.utils.UrlsDictionary;

public class FbService implements ISocialNetwork {
	private static final String APP_ID = "700011900132723";
	@Override
	public List<String> friendsById(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Post> getWall(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void postWall(Post post, SocialNetwork socialNetwork) {
		String accessToken = getAccessToken(socialNetwork);
		
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

	@Override
	public List<VkUser> usersById(List<String> uids, String accessToken) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<VkCity> citiesById(List<String> id) {
		return null;
	}

	@Override
	public String getAccessToken(SocialNetwork socialNetwork) {
		RequestBuilder requestBuilder = new RequestBuilder(UrlsDictionary.FB_OAUTH_DIALOG);
		requestBuilder.addParam(ParametersDictionary.CLIENT_ID, APP_ID);
		requestBuilder.addParam(ParametersDictionary.RESPONSE_TYPE, ParametersDictionary.TOKEN);
		requestBuilder.addParam(ParametersDictionary.SCOPE, PermissionDictionary.FB_PUBLISH_ACTION);
		requestBuilder.addParam(ParametersDictionary.REDIRECT_URI, UrlsDictionary.FB_REDIRECT_URL);
		requestBuilder.addParam(ParametersDictionary.DISPLAY, ParametersDictionary.POPUP);
		
		AccessTokenService accessTokenService = new AccessTokenService(requestBuilder.buildRequest());
		URL url = accessTokenService.generateAccessToken(socialNetwork);
		return requestBuilder.parseRequest(url, ParametersDictionary.ACCESS_TOKEN);
	}
}
