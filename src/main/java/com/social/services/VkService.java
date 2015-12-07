package com.social.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.social.accesstoken.services.VkAccessToken;
import com.social.models.SocialNetwork;
import com.social.models.requests.Post;
import com.social.models.responses.VkResponse;
import com.social.utils.ParametersDictionary;
import com.social.utils.PermissionDictionary;
import com.social.utils.PropertyService;
import com.social.utils.UrlsDictionary;

public class VkService{
	private static final String APP_ID = "vkAppId";
	
	private static final Logger log = LogManager.getRootLogger();
	
	public String postToWall(SocialNetwork socialNetwork, Post post) {
		log.debug("=Start process posting to VK...=");
		String accessToken = generateAccessToken(socialNetwork, PermissionDictionary.VK_WALL);
		log.debug("Access token: " + accessToken);
		
		MultiValueMap<String, String> headers = getHeaders(post, accessToken);
		log.debug("Headers: " + headers.toString());
		
		//String response = sendRequest(post, headers);
		VkResponse vkResponse = sendRequest(post, headers);
		
		log.debug("=Finish process posting to VK.=");
		return getResponse(vkResponse);
	}
	
	private String getResponse(String response) {
		if (response != null && !response.isEmpty()){
			log.debug("Post id: " + response);
			return response;
		} else {
			log.error("Error posting");
			return "";
		}
	}
	
	private String getResponse(VkResponse vkResponse) {
		if (vkResponse != null){
			log.debug("Post id: " + vkResponse.getResponse().getPostId());
			return String.valueOf(vkResponse.getResponse().getPostId());
		} else {
			log.error("Error posting");
			return "";
		}
	}
	
	private VkResponse sendRequest(Post post, MultiValueMap<String, String> map) {
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate.postForObject(
				UrlsDictionary.VK_POST_WALL, 
				map, 
				VkResponse.class);
	}
	
	private MultiValueMap<String, String> getHeaders(Post post, String accessToken) {
		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add(ParametersDictionary.ACCESS_TOKEN, accessToken);
		map.add(ParametersDictionary.OWNER_ID, post.getId());
		map.add(ParametersDictionary.MESSAGE, post.getText());
		return map;
	}

	public String generateAccessToken(SocialNetwork socialNetwork, String typePermission) {
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