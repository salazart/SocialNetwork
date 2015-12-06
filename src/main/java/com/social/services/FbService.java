package com.social.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.social.accesstoken.services.FbAccessToken;
import com.social.models.SocialNetwork;
import com.social.models.requests.Post;
import com.social.models.responses.FbResponse;
import com.social.utils.ParametersDictionary;
import com.social.utils.PermissionDictionary;
import com.social.utils.PropertyService;
import com.social.utils.UrlsDictionary;

public class FbService {
	private static final String APP_ID = "fbAppId";
	
	private static final Logger log = LogManager.getRootLogger();
	
	private SocialNetwork socialNetwork;
	
	public FbService(SocialNetwork socialNetwork) {
		this.socialNetwork = socialNetwork;
	}
	
	public void postToWall(Post post) {
		log.debug("=Start process posting to FB...=");
		String accessToken = generateAccessToken(PermissionDictionary.FB_PUBLISH_ACTION);

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(
				UrlsDictionary.FB_GRAPH + post.getId() + UrlsDictionary.FB_POST_WALL)
				.queryParam(ParametersDictionary.ACCESS_TOKEN, accessToken)
				.queryParam(ParametersDictionary.MESSAGE, post.getText());
		
		log.debug("Post request: " + builder.build().encode().toUri().toString());
		RestTemplate restTemplate = new RestTemplate();
		FbResponse fbResponse = restTemplate.getForObject(builder.build().encode().toUri(), FbResponse.class);
		ConnectionService connectionService = new ConnectionService(
				ConnectionService.POST_REQUEST_METHOD);
		String content = connectionService.createConnection(
				builder.build().encode().toUri().toString());
		log.debug("Post response: " + content);
		log.debug("=Finish process posting to FB...=");
	}

	public String generateAccessToken(String typePermission) {
		String appId = PropertyService.getValueProperties(APP_ID);
		
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(UrlsDictionary.FB_OAUTH_DIALOG)
				.queryParam(ParametersDictionary.CLIENT_ID, appId)
				.queryParam(ParametersDictionary.RESPONSE_TYPE,	ParametersDictionary.TOKEN)
				.queryParam(ParametersDictionary.SCOPE, typePermission)
				.queryParam(ParametersDictionary.REDIRECT_URI, UrlsDictionary.FB_REDIRECT_URL)
				.queryParam(ParametersDictionary.DISPLAY, ParametersDictionary.POPUP);
		
		String urlRequest = builder.build().encode().toUri().toString();
		log.debug("Access token request: " +  urlRequest);
		
		return new FbAccessToken(urlRequest).getAccessToken(socialNetwork);
	}
}
