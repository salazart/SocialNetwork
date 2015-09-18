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
import com.social.models.SocialNetwork;

public class AccessTokenService {
	private static final String NAME_EMAIL_FIELD = "email";
	private static final String NAME_PASS_FIELD = "pass";
	private static final String OK_EMAIL_FIELD = "fr.email";
	private static final String OK_PASS_FIELD = "fr.password";
	private static final String NAME_INPUT_FIELD = "input";
	private static final String FORM_AUTORIZE_VK = "//form[@action='https://login.vk.com/?act=login&soft=1&utf8=1']";
	private static final String FORM_AUTORIZE_FB = "//form[@id='login_form']";
	private static final String FORM_AUTORIZE_OK = "//form[@method='post']";
	private static final String FORM_ELEMENT_PERMISSION = "//form[@method='post']";
	private static final String FORM_ELEMENT_PERMISSION_2 = "//form[@method='POST']";
	private static final String FB_BUTTON_NAME = "__CONFIRM__";
	private static final String OK_BUTTON_NAME = "button_accept_request";
	private String url;
	private String typeAutorizeForm = "";
	private String emailField = "";
	private String passField = "";
	private SocialNetwork socialNetwork = null;

	public AccessTokenService(String url, SocialNetwork socialNetwork) {
		this.url = url;
		this.socialNetwork = socialNetwork;
		switch (socialNetwork.getTypeSN()) {
		case VKONTAKTE:
			typeAutorizeForm = FORM_AUTORIZE_VK;
			emailField = NAME_EMAIL_FIELD;
			passField = NAME_PASS_FIELD;
			break;
		case FACEBOOK:
			typeAutorizeForm = FORM_AUTORIZE_FB;
			emailField = NAME_EMAIL_FIELD;
			passField = NAME_PASS_FIELD;
			break;
		case OK:
			typeAutorizeForm = FORM_AUTORIZE_OK;
			emailField = OK_EMAIL_FIELD;
			passField = OK_PASS_FIELD;
			break;
		default:
			break;
		}

	}

	public String getAccessTokenResponse() {
		if (isAuthCorrect(socialNetwork)) {
			HtmlPage permissionPage = null;
			HtmlPage accessTokenPage = null;
			switch (socialNetwork.getTypeSN()) {
			case VKONTAKTE:
				permissionPage = autorizePage(socialNetwork);
				accessTokenPage = permissionPageVk(permissionPage);
				break;
			case FACEBOOK:
				permissionPage = autorizePage(socialNetwork);
				accessTokenPage = permissionPageFb(permissionPage);
				break;
			case OK:
				permissionPage = autorizePage(socialNetwork);
				accessTokenPage = permissionPageOk(permissionPage);
				break;
			default:
				break;
			}

			if (accessTokenPage != null) {
				return String.valueOf(accessTokenPage.getWebResponse().getRequestUrl());
			} else if (permissionPage != null){
				return String.valueOf(permissionPage.getWebResponse().getRequestUrl());
			} else {
				return "";
			}
		} else {
			System.out.println("Login or password is incorrect");
			return "";
		}
	}

	public boolean isAuthCorrect(SocialNetwork socialNetwork) {
		return socialNetwork.getLogin() != null
				&& !socialNetwork.getLogin().isEmpty()
				&& socialNetwork.getPass() != null
				&& !socialNetwork.getPass().isEmpty();
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

	private HtmlPage permissionPageFb(HtmlPage autorizePage) {
		HtmlPage permissionPage = null;
		HtmlForm form = autorizePage.getFirstByXPath(FORM_ELEMENT_PERMISSION);
		if (form != null) {
			HtmlButton button = null;
			try {
				button = form.getButtonByName(FB_BUTTON_NAME);
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
	
	private HtmlPage permissionPageOk(HtmlPage autorizePage) {
		HtmlPage permissionPage = null;
		HtmlForm form = autorizePage.getFirstByXPath(FORM_ELEMENT_PERMISSION_2);
		if (form != null) {
			HtmlButton button = null;
			try {
				button = form.getButtonByName(OK_BUTTON_NAME);
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

	private HtmlPage autorizePage(SocialNetwork socialNetwork) {
		try {
			WebClient webClient = new WebClient(BrowserVersion.FIREFOX_3);
			webClient.setJavaScriptEnabled(false);

			HtmlPage autorizePage = webClient.getPage(url);
			if (autorizePage != null) {
				HtmlForm form = autorizePage.getFirstByXPath(typeAutorizeForm);

				form.getInputByName(emailField).setValueAttribute(
						socialNetwork.getLogin());
				form.getInputByName(passField).setValueAttribute(
						socialNetwork.getPass());
				
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
