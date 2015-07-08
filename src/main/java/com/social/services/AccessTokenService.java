package com.social.services;

import java.io.IOException;
import java.net.URL;

import org.w3c.dom.NodeList;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.social.models.Parameters;

public class AccessTokenService {
	private String url;

	public AccessTokenService(String url) {
		this.url = url;
	}

	public String generateAccessToken(String login, String pass) {
		HtmlPage htmlPage = autorizePage(login, pass);
		if (htmlPage != null) {
			URL url = htmlPage.getWebResponse().getRequestUrl();
			RequestBuilder requestBuilder = new RequestBuilder();
			System.out.println(requestBuilder.parseRequest(url, Parameters.ACCESS_TOKEN));
		}
		
		return new String();
	}

	private HtmlPage autorizePage(String login, String pass) {
		try {
			WebClient webClient = new WebClient(BrowserVersion.FIREFOX_3);
			webClient.setCssEnabled(false);
			webClient.setJavaScriptEnabled(false);

			HtmlPage loginForm = webClient.getPage(url);
			if (loginForm != null) {
				HtmlForm form = loginForm
						.getFirstByXPath("//form[@action='https://login.vk.com/?act=login&soft=1&utf8=1']");
				form.getInputByName("email").setValueAttribute(login);
				form.getInputByName("pass").setValueAttribute(pass);

				NodeList buttons = form.getElementsByTagName("input");
				HtmlSubmitInput htmlSubmitInput = null;
				for (int i = 0; i < buttons.getLength(); i++) {
					if (buttons.item(i) instanceof HtmlSubmitInput) {
						htmlSubmitInput = (HtmlSubmitInput) buttons.item(i);
					}
				}

				if (htmlSubmitInput != null) {
					return htmlSubmitInput.click();
				}
			}
		} catch (FailingHttpStatusCodeException | IOException e) {
			return null;
		}
		return null;
	}
}
