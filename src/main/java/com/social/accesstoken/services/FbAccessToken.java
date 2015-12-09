package com.social.accesstoken.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.social.accesstoken.models.AuthorizeEntity;
import com.social.models.SocialNetwork;
import com.social.utils.AutorizeDictionary;

/**
 * Service login on the facebook.com website
 * @author salazart
 */
public class FbAccessToken extends ResponseParser{
	private static final Logger log = LogManager.getRootLogger();
	private AuthorizeEntity autorizeEntity;
	
	public FbAccessToken(String url) {
		autorizeEntity = new AuthorizeEntity(
				url, 
				AutorizeDictionary.FB_FORM_AUTORIZE, 
				AutorizeDictionary.NAME_EMAIL_FIELD, 
				AutorizeDictionary.NAME_PASS_FIELD);
	}

	public String getAccessToken(SocialNetwork socialNetwork){
		if(isAuthCorrect(socialNetwork)){
			log.debug("Login and pass is correct");
			
			AuthorizationService authorizationService = new AuthorizationService();
			HtmlPage permissionPage = authorizationService.handleAutorizePage(autorizeEntity, socialNetwork);
			
			PermissionService permissionService = new PermissionService(
					AutorizeDictionary.FORM_ELEMENT_PERMISSION,
					AutorizeDictionary.FB_BUTTON_NAME);
			HtmlPage accessTokenPage = permissionService.handlePermissionPage(permissionPage);
			
			return getRequestUrl(permissionPage, accessTokenPage);
		} else {
			log.error("Login or pass is incorrect");
			return "";
		}
	}
}
