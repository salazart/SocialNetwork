package com.social.accesstoken.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.social.accesstoken.models.AuthorizeEntity;
import com.social.models.SocialNetwork;
import com.social.utils.AutorizeDictionary;

public class OkAccessToken extends AutorizeService{
	private static final Logger log = LogManager.getRootLogger();
	private AuthorizeEntity autorizeEntity;
	
	public OkAccessToken(String url) {
		autorizeEntity = new AuthorizeEntity(
				url, 
				AutorizeDictionary.FORM_AUTORIZE_OK, 
				AutorizeDictionary.OK_EMAIL_FIELD, 
				AutorizeDictionary.OK_PASS_FIELD);
	}
	
	public String getAccessToken(SocialNetwork socialNetwork){
		if(isAuthCorrect(socialNetwork)){
			log.debug("Login and pass is correct");
			HtmlPage permissionPage = handleAutorizePage(autorizeEntity, socialNetwork);
			HtmlPage accessTokenPage = handlePermissionPage(permissionPage);
			
			return getRequestUrl(permissionPage, accessTokenPage);
		} else {
			log.error("Login or pass is incorrect");
			return "";
		}
	}
	
	private HtmlPage handlePermissionPage(HtmlPage autorizePage) {
		HtmlForm form = getPermissionForm(autorizePage);
		
		return emulatePermissionButtonClick(form);
	}
	
	private HtmlForm getPermissionForm(HtmlPage autorizePage) {
		if(autorizePage != null){
			log.debug("Gettign permission form");
			return autorizePage.getFirstByXPath(AutorizeDictionary.OK_FORM_ELEMENT_PERMISSION);
		} else {
			log.debug("Authorize page is null");
			return null;
		}
	}
	
	private HtmlPage emulatePermissionButtonClick(HtmlForm form) {
		try {
			log.debug("Emulating button click for getting rule");
			HtmlButton button = form.getButtonByName(AutorizeDictionary.OK_BUTTON_NAME);
			return button.click();
		} catch (Exception e) {
			log.debug(e);
			return null;
		}
	}
}
