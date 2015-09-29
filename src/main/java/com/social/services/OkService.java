package com.social.services;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

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
	private static final String APP_KEY = "okAppKey";
	private static final String APP_ID = "1151816192";
	private static final String GROUP_ID = "52753880514749";
	
	private String accessToken = "";

	public void postWall(Post post, SocialNetwork socialNetwork) {
		
		if (accessToken.isEmpty()) {
			generateAccessToken(socialNetwork, PermissionDictionary.OK_GROUP_CONTENT);
		}
		
		String postRequest =  createPostRequest();
		System.out.println(postRequest);

		//ConnectionService connectionService = new ConnectionService();
		//String content = connectionService.createConnection(url);
		
		//System.out.println(content);
		Attachment attachment = createAttachment();
		try {
			URL url = new URL(postRequest);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			
			conn.setRequestMethod("POST");
			
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("Authorization", "attachment=");
			//conn.setRequestProperty("uid", "241932594621");
			//conn.setRequestProperty("type", "GROUP_THEME");
			//conn.setRequestProperty("gid", GROUP_ID);
			
			ObjectMapper mapper = new ObjectMapper();
			//String jsonText = mapper.writeValueAsString(attachment);
			//conn.setRequestProperty("attachment", jsonText);
			conn.setDoOutput(true);
			
			
			DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
			mapper.writeValue(wr, attachment);
			
			wr.flush();
			wr.close();
			
			int responseCode = conn.getResponseCode();
            System.out.println("\nSending 'POST' request to URL : " + url);
            System.out.println("Response Code : " + responseCode);
            
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
 
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
 
            System.out.println(response.toString());
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		
	}

	private Attachment createAttachment() {
		Media media = new Media();
		media.setType("text");
		media.setText("hello");
		
		Attachment attachment = new Attachment();
		attachment.getMedias().add(media);
		
		return attachment;
	}

	private String createPostRequest() {
		final String methodName = "mediatopic.post";
		String sig = generateSesionSignature(accessToken, methodName);
		System.out.println(sig);
		
		RequestBuilder requestBuilder = new RequestBuilder(
				UrlsDictionary.OK_URL_REQUEST);

		String appKey = PropertyService.getInstance().getValueProperties(APP_KEY);
		requestBuilder.addParam(ParametersDictionary.APP_KEY, appKey);
		requestBuilder.addParam(ParametersDictionary.METHOD, methodName);
		requestBuilder.addParam(ParametersDictionary.ACCESS_TOKEN, accessToken);
		requestBuilder.addParam(ParametersDictionary.SIG, sig);
		
		//requestBuilder.addParam(ParametersDictionary.UID, "241932594621");
		//requestBuilder.addParam(ParametersDictionary.TYPE, "GROUP_THEME");
		//requestBuilder.addParam(ParametersDictionary.GID, GROUP_ID);
		
		return requestBuilder.buildRequest();
	}

	public String generateAccessToken(SocialNetwork socialNetwork, String typePermission) {

		String accessTokenRequest = createAccessTokenRequest(typePermission);

		System.out.println(accessTokenRequest);

		AccessTokenService accessTokenService = new AccessTokenService(accessTokenRequest, socialNetwork);
		String url = accessTokenService.getAccessTokenResponse();

		System.out.println(url);

		ResponseParser responseParser = new ResponseParser();
		accessToken = responseParser.parseRequest(url, ParametersDictionary.ACCESS_TOKEN);
		
		System.out.println(accessToken);
		
		return accessToken;
	}

	private String createAccessTokenRequest(String typePermission) {
		RequestBuilder requestBuilder = new RequestBuilder(UrlsDictionary.OK_OAUTH_DIALOG);
		requestBuilder.addParam(ParametersDictionary.CLIENT_ID, APP_ID);
		requestBuilder.addParam(ParametersDictionary.RESPONSE_TYPE, ParametersDictionary.TOKEN);
		requestBuilder.addParam(ParametersDictionary.SCOPE, typePermission);
		requestBuilder.addParam(ParametersDictionary.REDIRECT_URI, UrlsDictionary.OK_REDIRECT_URL);
		return requestBuilder.buildRequest();
	}

}
