package com.salazart;
import java.io.IOException;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.social.models.AccessToken;
import com.social.services.ConnectionService;


public class CreateAccessTokenRequest {

	public static void main(String[] args) {
		AccessToken oAuthRequest = new AccessToken(
				"oauth.vk.com/authorize");
			oAuthRequest.setClientId("4517745");
			oAuthRequest.setScope("wall,offline");
			oAuthRequest.setRedirectURI("https://oauth.vk.com/blank.html");
			oAuthRequest.setDisplay("mobile");
			oAuthRequest.setResponseType("token");

			System.out.println(oAuthRequest.buildQueryMessage());
			
			ConnectionService connectionService = new ConnectionService();
			String content = connectionService.createConnection(oAuthRequest.buildQueryMessage());
			
			System.out.println(content);
	}

}
