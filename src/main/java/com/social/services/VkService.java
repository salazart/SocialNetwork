package com.social.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.social.accesstoken.services.AccessTokenFactory;
import com.social.models.SocialNetwork;
import com.social.models.requests.Post;
import com.social.utils.ParametersDictionary;
import com.social.utils.PermissionDictionary;
import com.social.utils.PropertyService;
import com.social.utils.UrlsDictionary;

public class VkService{
	private static final Logger log = LogManager.getRootLogger();
	private static final String APP_ID = "vkAppId";
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

		log.debug("Post request: " + requestBuilder.buildRequest());
		ConnectionService connectionService = new ConnectionService();
		String content = connectionService.createConnection(requestBuilder
				.buildRequest());
		log.debug("Post response: " + content);
	}

	public String generateAccessToken(SocialNetwork socialNetwork,
			String typePermission) {
		
		String urlRequest = createAccessTokenRequest(typePermission);
		log.debug("Access token request: " +  urlRequest);
		
		AccessTokenFactory accessTokenService = new AccessTokenFactory(urlRequest);
		String accessTokenResponse = accessTokenService.getAccessTokenResponse(socialNetwork);
		log.debug("Access token response: " + accessTokenResponse);
		
		ResponseParser responseParser = new ResponseParser();
		return responseParser.parseRequest(accessTokenResponse,
				ParametersDictionary.ACCESS_TOKEN);
		
	}
	
	private String createAccessTokenRequest(String typePermission) {
		RequestBuilder requestBuilder = new RequestBuilder(
				UrlsDictionary.VK_OAUTH_DIALOG);
		
		String appId = PropertyService.getValueProperties(APP_ID);
		requestBuilder.addParam(ParametersDictionary.CLIENT_ID, appId);
		
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