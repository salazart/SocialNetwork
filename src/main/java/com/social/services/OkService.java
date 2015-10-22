package com.social.services;

import java.io.IOException;

import org.codehaus.jackson.map.ObjectMapper;

import com.social.models.Attachment;
import com.social.models.Media;
import com.social.models.SocialNetwork;
import com.social.models.requests.Post;
import com.social.utils.ParametersDictionary;
import com.social.utils.PermissionDictionary;
import com.social.utils.PropertyService;
import com.social.utils.UrlsDictionary;

public class OkService extends OkSessionService{
	private static final String ACCESS_TOKEN = "okAccessToken";
	private static final String APP_ID = "okAppId";
	private static final String METHOD = "okMethod";
	private static final String POST_TYPE = "okPostType";
	
	private static final String APP_KEY = "okAppKey";
	private static final String GROUP_ID = "okGroupId";
	
	private String accessToken = "";

	public void postWall(Post post, SocialNetwork socialNetwork) {
		accessToken = PropertyService.getValueProperties(ACCESS_TOKEN);
		if (accessToken.isEmpty()) {
			accessToken = generateAccessToken(socialNetwork, PermissionDictionary.OK_GROUP_CONTENT);
			PropertyService.setValueProperties(ACCESS_TOKEN,accessToken);
		}
		
		String attachmentsText = getAttachmentsText(post);
		
		String postRequest =  createPostRequest(attachmentsText);
		
		ConnectionService connectionService = new ConnectionService(ConnectionService.POST_REQUEST_METHOD);
		String content = connectionService.createConnection(postRequest);
		System.out.println(content);
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
		String sig = generateSesionSignature(accessToken, attachmentsText);
		
		RequestBuilder requestBuilder = new RequestBuilder(
				UrlsDictionary.OK_URL_REQUEST);
		
		requestBuilder.addParam(ParametersDictionary.ATTACHMENT, attachmentsText);

		String appKey = PropertyService.getValueProperties(APP_KEY);
		requestBuilder.addParam(ParametersDictionary.APP_KEY, appKey);
		
		String methodName = PropertyService.getValueProperties(METHOD);
		requestBuilder.addParam(ParametersDictionary.METHOD, methodName);
		
		requestBuilder.addParam(ParametersDictionary.ACCESS_TOKEN, accessToken);
		requestBuilder.addParam(ParametersDictionary.SIG, sig);
		
		String groupId = PropertyService.getValueProperties(GROUP_ID);
		requestBuilder.addParam(ParametersDictionary.GID, groupId);
		
		String type = PropertyService.getValueProperties(POST_TYPE);
		requestBuilder.addParam(ParametersDictionary.TYPE, type);
		
		return requestBuilder.buildRequest();
	}

	public String generateAccessToken(SocialNetwork socialNetwork, String typePermission) {
		String accessTokenRequest = createAccessTokenRequest(typePermission);

		AccessTokenService accessTokenService = new AccessTokenService(accessTokenRequest, socialNetwork);
		String url = accessTokenService.getAccessTokenResponse();

		ResponseParser responseParser = new ResponseParser();
		return responseParser.parseRequest(url, ParametersDictionary.ACCESS_TOKEN);
	}

	private String createAccessTokenRequest(String typePermission) {
		RequestBuilder requestBuilder = new RequestBuilder(UrlsDictionary.OK_OAUTH_DIALOG);
		
		String appId = PropertyService.getValueProperties(APP_ID);
		requestBuilder.addParam(ParametersDictionary.CLIENT_ID, appId);
		
		requestBuilder.addParam(ParametersDictionary.RESPONSE_TYPE, ParametersDictionary.TOKEN);
		requestBuilder.addParam(ParametersDictionary.SCOPE, typePermission);
		requestBuilder.addParam(ParametersDictionary.REDIRECT_URI, UrlsDictionary.OK_REDIRECT_URL);
		return requestBuilder.buildRequest();
	}
}
