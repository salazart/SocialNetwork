package com.social.accesstoken.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.social.accesstoken.models.AuthorizeEntity;
import com.social.models.SocialNetwork;
import com.social.utils.AutorizeDictionary;

/**
 * Service login on the OK.RU website
 * @author salazart
 */
public class OkAccessToken extends ResponseParser{
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
			
			AuthorizationService authorizationService = new AuthorizationService();
			HtmlPage permissionPage = authorizationService.handleAutorizePage(autorizeEntity, socialNetwork);
			
			PermissionService permissionService = new PermissionService(
					AutorizeDictionary.OK_FORM_ELEMENT_PERMISSION, 
					AutorizeDictionary.OK_BUTTON_NAME);
			HtmlPage accessTokenPage = permissionService.handlePermissionPage(permissionPage);
			
			return getRequestUrl(permissionPage, accessTokenPage);
		} else {
			log.error("Login or pass is incorrect");
			return "";
		}
	}
}
