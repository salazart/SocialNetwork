
package com.salazart;

import java.io.IOException;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.social.models.AccessToken;
import com.social.services.ConnectionService;

public class AutoReturnAccessToken {

	public static void main(String[] args) {
		AccessToken oAuthRequest = new AccessToken(
				"oauth.vk.com/authorize");
			oAuthRequest.setClientId("4517745");
			oAuthRequest.setScope("wall,offline");
			oAuthRequest.setRedirectURI("https://oauth.vk.com/blank.html");
			oAuthRequest.setDisplay("mobile");
			oAuthRequest.setResponseType("token");

			System.out.println(oAuthRequest.buildQueryMessage());
			
			//ConnectionService connectionService = new ConnectionService();
			//String content = connectionService.createConnection(oAuthRequest.buildQueryMessage());
			
			//System.out.println(content);
			
		final String INPUT_EMAIL_NAME = "email";
		final String INPUT_PASSWORD_NAME = "pass";
		try {
			WebClient webClient = new WebClient(BrowserVersion.FIREFOX_2);
			webClient.setCssEnabled(false);
			webClient.setJavaScriptEnabled(false);
			HtmlPage loginForm = webClient.getPage("https://" + oAuthRequest.buildQueryMessage());
			if (loginForm != null) {
				System.out.println(loginForm.asXml());
				HtmlForm form = loginForm.getFirstByXPath("//form[@action='https://login.vk.com/?act=login&soft=1&utf8=1']");
				form.getInputByName("email").setValueAttribute("");
				form.getInputByName("pass").setValueAttribute("");
				//HtmlPage page = loginForm.getInputByName(INPUT_LOGIN_NAME).click();
				//HtmlSubmitInput button = form.getInputByName("submitbutton");
				
			}
		} catch (FailingHttpStatusCodeException | IOException e) {
			e.printStackTrace();
		}
	}

}
