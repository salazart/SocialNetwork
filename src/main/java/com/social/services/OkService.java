package com.social.services;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.social.accesstoken.services.OkAccessToken;
import com.social.interfaces.ISocialNetworkService;
import com.social.models.Attachment;
import com.social.models.Media;
import com.social.models.SocialNetwork;
import com.social.models.requests.Post;
import com.social.utils.ParametersDictionary;
import com.social.utils.PermissionDictionary;
import com.social.utils.PropertyService;
import com.social.utils.UrlsDictionary;

public class OkService extends OkSessionService implements ISocialNetworkService{
	private static final String APP_ID = "okAppId";
	private static final String METHOD = "okMethod";
	private static final String POST_TYPE = "okPostType";
	private static final String APP_KEY = "okAppKey";
	private static final String GROUP_ID = "okGroupId";
	
	private static final Logger log = LogManager.getRootLogger();
	
	public String postToWall(SocialNetwork socialNetwork, Post post) {
		log.debug("=Start process posting to OK...=");
		String accessToken = generateAccessToken(socialNetwork, PermissionDictionary.OK_GROUP_CONTENT);
		log.debug("Access token: " + accessToken);
		
		MultiValueMap<String, String> headers = getHeaders(post, accessToken);
		log.debug("Headers: " + headers.toString());
		
		String response = sendRequest(post, headers);
		log.debug("=Finish process posting to OK...=");
		return getResponse(response);
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

	private String getAttachmentsText(Post post) {
		Media media = new Media();
		media.setType(Media.TYPE_TEXT);
		media.setText(post.getText());
		
		Attachment attachment = new Attachment();
		attachment.getMedias().add(media);
		
		try {
			ObjectMapper mapper = new ObjectMapper();
			return mapper.writeValueAsString(attachment);
		} catch (IOException e) {
			return "";
		}
	}

	private MultiValueMap<String, String> getHeaders(Post post, String accessToken) {
		String attachmentsText = getAttachmentsText(post);
		String sig = generateSesionSignature(accessToken, attachmentsText);
		String appKey = PropertyService.getValueProperties(APP_KEY);
		String methodName = PropertyService.getValueProperties(METHOD);
		String groupId = PropertyService.getValueProperties(GROUP_ID);
		String type = PropertyService.getValueProperties(POST_TYPE);
		
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
		headers.add(ParametersDictionary.ATTACHMENT, attachmentsText);
		headers.add(ParametersDictionary.APP_KEY, appKey);
		headers.add(ParametersDictionary.METHOD, methodName);
		headers.add(ParametersDictionary.ACCESS_TOKEN, accessToken);
		headers.add(ParametersDictionary.SIG, sig);
		headers.add(ParametersDictionary.GID, groupId);
		headers.add(ParametersDictionary.TYPE, type);
		
		return headers;
	}
	
	private String sendRequest(Post post, MultiValueMap<String, String> map) {
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate.postForObject(
				UrlsDictionary.OK_URL_REQUEST, 
				map, 
				String.class);
	}
	
	public String generateAccessToken(SocialNetwork socialNetwork, String typePermission) {
		String appId = PropertyService.getValueProperties(APP_ID);
		
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(UrlsDictionary.OK_OAUTH_DIALOG)
				.queryParam(ParametersDictionary.CLIENT_ID, appId)
				.queryParam(ParametersDictionary.RESPONSE_TYPE, ParametersDictionary.TOKEN)
				.queryParam(ParametersDictionary.SCOPE, PermissionDictionary.OK_GROUP_CONTENT)
				.queryParam(ParametersDictionary.REDIRECT_URI, UrlsDictionary.OK_REDIRECT_URL);
		String urlRequest = builder.build().encode().toUri().toString();
		
		log.debug("Access token request: " +  urlRequest);
		
		return new OkAccessToken(urlRequest).getAccessToken(socialNetwork);
	}
}
