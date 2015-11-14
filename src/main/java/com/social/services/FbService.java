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

public class FbService {
	private static final String APP_ID = "fbAppId";
	
	private static final Logger log = LogManager.getRootLogger();
	
	public void postWall(Post post, SocialNetwork socialNetwork) {
		String accessToken = generateAccessToken(socialNetwork,
				PermissionDictionary.FB_PUBLISH_ACTION);

		RequestBuilder requestBuilder = new RequestBuilder(
				UrlsDictionary.FB_GRAPH + post.getId()
						+ UrlsDictionary.FB_POST_WALL);

		requestBuilder.addParam(ParametersDictionary.ACCESS_TOKEN, accessToken);
		requestBuilder.addParam(ParametersDictionary.MESSAGE, post.getText());

		log.debug("Post request: " + requestBuilder.buildRequest());
		ConnectionService connectionService = new ConnectionService(
				ConnectionService.POST_REQUEST_METHOD);
		String content = connectionService.createConnection(requestBuilder
				.buildRequest());
		log.debug("Post response: " + content);
	}

	public String generateAccessToken(SocialNetwork socialNetwork, String typePermission) {
		String urlRequest = createAccessTokenRequest(typePermission);
		log.debug("Access token request: " +  urlRequest);
		
		AccessTokenFactory accessTokenService = new AccessTokenFactory(urlRequest);
		return accessTokenService.getAccessTokenResponse(socialNetwork);
	}
	
	private String createAccessTokenRequest(String typePermission) {
		RequestBuilder requestBuilder = new RequestBuilder(
				UrlsDictionary.FB_OAUTH_DIALOG);
		
		String appId = PropertyService.getValueProperties(APP_ID);
		requestBuilder.addParam(ParametersDictionary.CLIENT_ID, appId);

		requestBuilder.addParam(ParametersDictionary.RESPONSE_TYPE,
				ParametersDictionary.TOKEN);
		requestBuilder.addParam(ParametersDictionary.SCOPE, typePermission);
		requestBuilder.addParam(ParametersDictionary.REDIRECT_URI,
				UrlsDictionary.FB_REDIRECT_URL);
		requestBuilder.addParam(ParametersDictionary.DISPLAY,
				ParametersDictionary.POPUP);
		return requestBuilder.buildRequest();
	}
}
