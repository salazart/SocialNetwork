package com.social.accesstoken.services;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.social.accesstoken.models.AutorizeEntity;
import com.social.models.SocialNetwork;
import com.social.utils.AutorizeDictionary;

public class FBAccessToken extends AutorizeService{
	private static final Logger log = LogManager.getRootLogger();
	private AutorizeEntity autorizeEntity;
	
	public FBAccessToken(String url) {
		autorizeEntity = new AutorizeEntity(
				url, 
				AutorizeDictionary.FB_FORM_AUTORIZE, 
				AutorizeDictionary.NAME_EMAIL_FIELD, 
				AutorizeDictionary.NAME_PASS_FIELD);
	}

	public String getAccessTokenResponse(SocialNetwork socialNetwork){
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
		if(autorizePage == null){
			return null;
		}
		HtmlPage permissionPage = null;
		HtmlForm form = autorizePage.getFirstByXPath(AutorizeDictionary.FORM_ELEMENT_PERMISSION);
		if (form != null) {
			HtmlButton button = null;
			try {
				button = form.getButtonByName(AutorizeDictionary.FB_BUTTON_NAME);
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
	
}
