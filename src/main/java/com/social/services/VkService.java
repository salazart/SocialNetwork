package com.social.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.social.accesstoken.services.VkAccessToken;
import com.social.models.SocialNetwork;
import com.social.models.requests.Post;
import com.social.utils.ParametersDictionary;
import com.social.utils.PermissionDictionary;
import com.social.utils.PropertyService;
import com.social.utils.UrlsDictionary;

public class VkService{
	private static final String APP_ID = "vkAppId";
	
	private static final Logger log = LogManager.getRootLogger();
	
	private SocialNetwork socialNetwork;
	
	public VkService(SocialNetwork socialNetwork) {
		this.socialNetwork = socialNetwork;
	}
	
	public void postToWall(Post post) {
		log.debug("=Start process posting to VK...=");
		String accessToken = generateAccessToken(PermissionDictionary.VK_WALL);
		
		RequestBuilder requestBuilder = new RequestBuilder(
				UrlsDictionary.VK_POST_WALL);

		requestBuilder.addParam(ParametersDictionary.ACCESS_TOKEN, accessToken);
		requestBuilder.addParam(ParametersDictionary.OWNER_ID, post.getId());
		requestBuilder.addParam(ParametersDictionary.MESSAGE, post.getText());

		log.debug("Post request: " + requestBuilder.buildRequest());
		ConnectionService connectionService = new ConnectionService();
		String content = connectionService.createConnection(requestBuilder
				.buildRequest());
		log.debug("Post response: " + content);
		
		log.debug("=Finish process posting to VK.=");
	}

	public String generateAccessToken(String typePermission) {
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
		
		String urlRequest = requestBuilder.buildRequest();
		log.debug("Access token request: " +  urlRequest);
		
		return new VkAccessToken(urlRequest).getAccessToken(socialNetwork);
	}
}