package com.social.services;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.social.accesstoken.services.VkAccessToken;
import com.social.interfaces.ISocialNetworkService;
import com.social.models.SocialNetwork;
import com.social.models.requests.Post;
import com.social.models.responses.VkResponse;
import com.social.utils.ParametersDictionary;
import com.social.utils.PermissionDictionary;
import com.social.utils.PropertyService;
import com.social.utils.UrlsDictionary;

public class VkService implements ISocialNetworkService{
	private static final String APP_ID = "vkAppId";
	
	private Logger log = LogManager.getRootLogger();
	
	public String postToWall(SocialNetwork socialNetwork, Post post) {
		log.debug("=Start process posting to VK...=");
		String accessToken = generateAccessToken(socialNetwork, PermissionDictionary.VK_WALL);
		log.debug("Access token: " + accessToken);
		
		URI url = getUrl(post, accessToken);
		log.debug("Post request: " + url.toString());
		
		VkResponse vkResponse = sendRequest(url);
		
		log.debug("=Finish process posting to VK.=");
		return getResponse(vkResponse);
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
	
	private VkResponse sendRequest(URI url) {
		RestTemplate restTemplate = new RestTemplate();
		
		List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
	    messageConverters.add(new MappingJackson2HttpMessageConverter());
	    restTemplate.setMessageConverters(messageConverters);
		
		return restTemplate.getForObject(
				url,
				VkResponse.class);
	}
	
	private URI getUrl(Post post, String accessToken) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(UrlsDictionary.VK_POST_WALL)
				.queryParam(ParametersDictionary.ACCESS_TOKEN, accessToken)
				.queryParam(ParametersDictionary.OWNER_ID, post.getId())
				.queryParam(ParametersDictionary.MESSAGE, post.getText());
		return builder.build().encode().toUri();
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