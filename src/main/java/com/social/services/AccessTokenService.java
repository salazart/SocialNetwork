package com.social.services;

import java.io.IOException;
import java.net.URL;

import org.w3c.dom.NodeList;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;

public class AccessTokenService {
	private String typeSN = "";
	private static final String NAME_EMAIL_FIELD = "email";
	private static final String NAME_PASS_FIELD = "pass";
	private static final String NAME_INPUT_FIELD = "input";
	private static final String FORM_ELEMENT_AUTORIZE = "//form[@action='https://login.vk.com/?act=login&soft=1&utf8=1']";
	private static final String FORM_ELEMENT_AUTORIZE_FB = "//form[@id='login_form']";
	private static final String FORM_ELEMENT_PERMISSION = "//form[@method='post']";
	private String url;

	public AccessTokenService(String url) {
		this.url = url;
		typeSN = getTypeSocialNetwork(url);
	}

	private String getTypeSocialNetwork(String url) {

		if (url.contains("facebook")) {
			return "FB";
		} else {
			return "VK";
		}
	}

	public URL generateAccessToken(String login, String pass) {
		if (isAuthCorrect(login, pass)) {
			HtmlPage permissionPage = null;
			HtmlPage accessTokenPage = null;
			if (typeSN.equals("VK")) {
				permissionPage = autorizePageVk(login, pass);
				accessTokenPage = permissionPageVk(permissionPage);
			} else {
				permissionPage = autorizePageFb(login, pass);
				accessTokenPage = permissionPageFb(permissionPage);
			}

			if (accessTokenPage != null) {
				return accessTokenPage.getWebResponse().getRequestUrl();
			} else {
				return permissionPage.getWebResponse().getRequestUrl();
			}
		} else {
			System.out.println("Login or password is incorrect");
			return null;
		}
	}

	public boolean isAuthCorrect(String login, String pass) {
		return login != null && !login.isEmpty() && pass != null
				&& !pass.isEmpty();
	}

	private HtmlPage permissionPageVk(HtmlPage autorizePage) {
		HtmlPage permissionPage = null;
		HtmlForm form = autorizePage.getFirstByXPath(FORM_ELEMENT_PERMISSION);
		if (form != null) {
			NodeList inputElements = form
					.getElementsByTagName(NAME_INPUT_FIELD);
			HtmlSubmitInput htmlSubmitInput = getSubmitButton(inputElements);

			if (htmlSubmitInput != null) {
				try {
					return htmlSubmitInput.click();
				} catch (IOException e) {
					return null;
				}
			}
		}

		return permissionPage;
	}

	private HtmlPage autorizePageVk(String login, String pass) {
		try {
			WebClient webClient = new WebClient(BrowserVersion.FIREFOX_3);
			webClient.setCssEnabled(false);
			webClient.setJavaScriptEnabled(false);

			HtmlPage autorizePage = webClient.getPage(url);
			if (autorizePage != null) {
				HtmlForm form = autorizePage
						.getFirstByXPath(FORM_ELEMENT_AUTORIZE);
				form.getInputByName(NAME_EMAIL_FIELD).setValueAttribute(login);
				form.getInputByName(NAME_PASS_FIELD).setValueAttribute(pass);

				NodeList inputElements = form
						.getElementsByTagName(NAME_INPUT_FIELD);
				HtmlSubmitInput htmlSubmitInput = getSubmitButton(inputElements);

				if (htmlSubmitInput != null) {
					return htmlSubmitInput.click();
				}
			}
		} catch (IOException e) {
			return null;
		}
		return null;
	}

	private HtmlPage autorizePageFb(String login, String pass) {
		try {
			WebClient webClient = new WebClient(BrowserVersion.FIREFOX_3);
			webClient.setCssEnabled(false);
			webClient.setJavaScriptEnabled(false);
			webClient.setThrowExceptionOnScriptError(false);

			HtmlPage autorizePage = webClient.getPage(url);
			if (autorizePage != null) {
				HtmlForm form = autorizePage
						.getFirstByXPath(FORM_ELEMENT_AUTORIZE_FB);
				form.getInputByName(NAME_EMAIL_FIELD).setValueAttribute(login);
				form.getInputByName(NAME_PASS_FIELD).setValueAttribute(pass);

				NodeList inputElements = form
						.getElementsByTagName(NAME_INPUT_FIELD);
				HtmlSubmitInput htmlSubmitInput = getSubmitButton(inputElements);

				if (htmlSubmitInput != null) {
					return htmlSubmitInput.click();
				}
			}
		} catch (IOException e) {
			return null;
		}
		return null;
	}

	private HtmlPage permissionPageFb(HtmlPage autorizePage) {
		HtmlPage permissionPage = null;
		HtmlForm form = autorizePage.getFirstByXPath(FORM_ELEMENT_PERMISSION);
		if (form != null) {
			HtmlButton button = null;
			try {
				button = form.getButtonByName("__CONFIRM__");
			} catch (Exception e) {
				return null;
			}

			if (button != null) {
				try {
					return button.click();
				} catch (IOException e) {
					return null;
				}
			}
		}

		return permissionPage;
	}

	private HtmlSubmitInput getSubmitButton(NodeList inputElements) {
		HtmlSubmitInput htmlSubmitInput = null;
		for (int i = 0; i < inputElements.getLength(); i++) {
			if (inputElements.item(i) instanceof HtmlSubmitInput) {
				htmlSubmitInput = (HtmlSubmitInput) inputElements.item(i);
			}
		}
		return htmlSubmitInput;
	}
}
