package com.social.services;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.web.util.UriComponentsBuilder;

import com.social.accesstoken.services.OkAccessToken;
import com.social.models.Attachment;
import com.social.models.Media;
import com.social.models.SocialNetwork;
import com.social.models.requests.Post;
import com.social.utils.ParametersDictionary;
import com.social.utils.PermissionDictionary;
import com.social.utils.PropertyService;
import com.social.utils.UrlsDictionary;

public class OkService extends OkSessionService{
	private static final String APP_ID = "okAppId";
	private static final String METHOD = "okMethod";
	private static final String POST_TYPE = "okPostType";
	private static final String APP_KEY = "okAppKey";
	private static final String GROUP_ID = "okGroupId";
	
	private static final Logger log = LogManager.getRootLogger();
	
	private SocialNetwork socialNetwork;
	
	public OkService(SocialNetwork socialNetwork) {
		this.socialNetwork = socialNetwork;
	}
	
	public void postToWall(Post post) {
		log.debug("=Start process posting to OK...=");
		String attachmentsText = getAttachmentsText(post);
		log.debug("Attachment text: " + attachmentsText);
		
		String postRequest =  createPostRequest(attachmentsText);
		log.debug("Post request: " + postRequest);
		
		ConnectionService connectionService = new ConnectionService(ConnectionService.POST_REQUEST_METHOD);
		String content = connectionService.createConnection(postRequest);
		log.debug("Post response: " + content);
		log.debug("=Finish process posting to OK...=");
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

	private String createPostRequest(String attachmentsText) {
		String accessToken = generateAccessToken(PermissionDictionary.OK_GROUP_CONTENT);
		String sig = generateSesionSignature(accessToken, attachmentsText);
		String appKey = PropertyService.getValueProperties(APP_KEY);
		String methodName = PropertyService.getValueProperties(METHOD);
		String groupId = PropertyService.getValueProperties(GROUP_ID);
		String type = PropertyService.getValueProperties(POST_TYPE);
		
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(UrlsDictionary.OK_URL_REQUEST)
				.queryParam(ParametersDictionary.ATTACHMENT, attachmentsText)
				.queryParam(ParametersDictionary.APP_KEY, appKey)
				.queryParam(ParametersDictionary.METHOD, methodName)
				.queryParam(ParametersDictionary.ACCESS_TOKEN, accessToken)
				.queryParam(ParametersDictionary.SIG, sig)
				.queryParam(ParametersDictionary.GID, groupId)
				.queryParam(ParametersDictionary.TYPE, type);
		
		return builder.build().encode().toUri().toString();
	}

	public String generateAccessToken(String typePermission) {
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
