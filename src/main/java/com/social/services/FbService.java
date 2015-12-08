package com.social.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.social.accesstoken.services.FbAccessToken;
import com.social.interfaces.ISocialNetworkService;
import com.social.models.SocialNetwork;
import com.social.models.requests.Post;
import com.social.models.responses.FbResponse;
import com.social.utils.ParametersDictionary;
import com.social.utils.PermissionDictionary;
import com.social.utils.PropertyService;
import com.social.utils.UrlsDictionary;

public class FbService implements ISocialNetworkService{
	private static final String APP_ID = "fbAppId";
	
	private static final Logger log = LogManager.getRootLogger();
	
	public String postToWall(SocialNetwork socialNetwork, Post post) {
		log.debug("=Start process posting to FB...=");
		String accessToken = generateAccessToken(socialNetwork, PermissionDictionary.FB_PUBLISH_ACTION);
		log.debug("Access token: " + accessToken);
		
		MultiValueMap<String, String> headers = getHeaders(post, accessToken);
		log.debug("Headers: " + headers.toString());
		
		FbResponse fbResponse = sendRequest(post, headers);

		log.debug("=Finish process posting to FB...=");
		return getResponse(fbResponse);
	}

	private String getResponse(FbResponse fbResponse) {
		if (fbResponse != null && !fbResponse.isEmpty()){
			log.debug("Post id: " + fbResponse.getId());
			return fbResponse.getId();
		} else {
			log.error("Error posting");
			return "";
		}
	}

	private FbResponse sendRequest(Post post, MultiValueMap<String, String> map) {
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate.postForObject(
				UrlsDictionary.FB_GRAPH + post.getId() + UrlsDictionary.FB_POST_WALL, 
				map, 
				FbResponse.class);
	}

	private MultiValueMap<String, String> getHeaders(Post post, String accessToken) {
		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add(ParametersDictionary.ACCESS_TOKEN, accessToken);
		map.add(ParametersDictionary.MESSAGE, post.getText());
		return map;
	}

	public String generateAccessToken(SocialNetwork socialNetwork, String typePermission) {
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
