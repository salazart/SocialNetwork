package com.social.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.util.UriComponentsBuilder;

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
		
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(UrlsDictionary.VK_POST_WALL)
				.queryParam(ParametersDictionary.ACCESS_TOKEN, accessToken)
				.queryParam(ParametersDictionary.OWNER_ID, post.getId())
				.queryParam(ParametersDictionary.MESSAGE, post.getText());
		
		log.debug("Post request: " + builder.build().encode().toUri().toString());
		ConnectionService connectionService = new ConnectionService();
		String content = connectionService.createConnection(builder.build().encode().toUri().toString());
		log.debug("Post response: " + content);
		
		log.debug("=Finish process posting to VK.=");
	}

	public String generateAccessToken(String typePermission) {
		String appId = PropertyService.getValueProperties(APP_ID);
		
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(UrlsDictionary.VK_OAUTH_DIALOG)
				.queryParam(ParametersDictionary.CLIENT_ID, appId)
				.queryParam(ParametersDictionary.RESPONSE_TYPE, ParametersDictionary.TOKEN)
				.queryParam(ParametersDictionary.SCOPE, typePermission)
				.queryParam(ParametersDictionary.REDIRECT_URI, UrlsDictionary.VK_REDIRECT_URL)
				.queryParam(ParametersDictionary.DISPLAY, ParametersDictionary.MOBILE);
		
		String urlRequest = builder.build().toUri().toString();
		log.debug("Access token request: " +  urlRequest);
		
		return new VkAccessToken(urlRequest).getAccessToken(socialNetwork);
	}
}