package com.salazart;
import com.social.models.AccessToken;
import com.social.services.ConnectionService;


public class CreateAccessTokenRequest {

	public static void main(String[] args) {
		AccessToken oAuthRequest = new AccessToken(
				"https://oauth.vk.com/authorize");
			oAuthRequest.setClientId("4517745");
			oAuthRequest.setScope("wall,offline");
			oAuthRequest.setRedirectURI("https://oauth.vk.com/blank.html");
			oAuthRequest.setDisplay("popup");
			oAuthRequest.setResponseType("token");

			System.out.println(oAuthRequest.buildQueryMessage());
			
			//ConnectionService connectionService = new ConnectionService();
			//String content = connectionService.createConnection(oAuthRequest.buildQueryMessage());
			
			//System.out.println(content);
	}

}
