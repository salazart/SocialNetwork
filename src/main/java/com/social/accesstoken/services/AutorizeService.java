package com.social.accesstoken.services;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.NodeList;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.social.accesstoken.models.AutorizeEntity;
import com.social.models.SocialNetwork;
import com.social.utils.AutorizeDictionary;

public class AutorizeService {
	private static final Logger log = LogManager.getRootLogger();
	
	protected HtmlPage handleAutorizePage(AutorizeEntity autorizeEntity, SocialNetwork socialNetwork) {
		HtmlPage autorizePage = getAuthorizePage(autorizeEntity);
		
		if(autorizePage != null){
			log.debug("Authorize page is loaded successfully");

			HtmlForm form = emulateUserLogining(autorizeEntity, socialNetwork, autorizePage);
			
			return emulateButtonClick(form);
		} else {
			log.error("Authorize page is not loaded");
			return null;
		}
	}

	private HtmlForm emulateUserLogining(AutorizeEntity autorizeEntity, SocialNetwork socialNetwork,
			HtmlPage autorizePage) {
		try {
			HtmlForm form = autorizePage.getFirstByXPath(autorizeEntity.getTypeAutorizeForm());

			form.getInputByName(autorizeEntity.getEmailField()).setValueAttribute(
					socialNetwork.getLogin());
			form.getInputByName(autorizeEntity.getPassField()).setValueAttribute(
					socialNetwork.getPass()); 
			return form;
		} catch (Exception e) {
			 log.error(e);
			 return null;
		}
	}
	
	private HtmlPage emulateButtonClick(HtmlForm form){
		try {
			NodeList inputElements = form
					.getElementsByTagName(AutorizeDictionary.NAME_INPUT_FIELD);
			HtmlSubmitInput htmlSubmitInput = getSubmitButton(inputElements);
			return htmlSubmitInput.click();
		} catch (IOException e) {
			log.error(e);
			return null;
		}
	}

	private HtmlPage getAuthorizePage(AutorizeEntity autorizeEntity) {
		try {
			WebClient webClient = new WebClient(BrowserVersion.FIREFOX_3);
			webClient.setJavaScriptEnabled(false);
			return webClient.getPage(autorizeEntity.getUrl());
		} catch (FailingHttpStatusCodeException | IOException e) {
			log.error(e);
			return null;
		}
	}
	
	protected HtmlSubmitInput getSubmitButton(NodeList inputElements) {
		HtmlSubmitInput htmlSubmitInput = null;
		for (int i = 0; i < inputElements.getLength(); i++) {
			if (inputElements.item(i) instanceof HtmlSubmitInput) {
				htmlSubmitInput = (HtmlSubmitInput) inputElements.item(i);
			}
		}
		return htmlSubmitInput;
	}
	
	protected boolean isAuthCorrect(SocialNetwork socialNetwork) {
		return socialNetwork.getLogin() != null
				&& !socialNetwork.getLogin().isEmpty()
				&& socialNetwork.getPass() != null
				&& !socialNetwork.getPass().isEmpty();
	}
	
	protected String getRequestUrl(HtmlPage permissionPage, HtmlPage accessTokenPage) {
		if (accessTokenPage != null) {
			log.debug("Permission page return access token page");
			return String.valueOf(accessTokenPage.getWebResponse().getRequestUrl());
		} else if (permissionPage != null){
			log.debug("Authorize page return access token page");
			return String.valueOf(permissionPage.getWebResponse().getRequestUrl());
		} else {
			log.error("Authorize page and permission page return null");
			return "";
		}
	}
}
