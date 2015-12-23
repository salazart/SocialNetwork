package com.social.accesstoken.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.social.accesstoken.models.AuthEntity;
import com.social.models.SocialNetwork;
import com.social.utils.AuthDic;

/**
 * Service login on the facebook.com website
 * @author salazart
 */
public class FbAccessToken extends ResponseParser{
	private static final Logger log = LogManager.getRootLogger();
	private AuthEntity autorizeEntity;
	
	public FbAccessToken(String url) {
		autorizeEntity = new AuthEntity(
				url, 
				AuthDic.FB_FORM_AUTORIZE, 
				AuthDic.NAME_EMAIL_FIELD, 
				AuthDic.NAME_PASS_FIELD);
	}

	public String getAccessToken(SocialNetwork socialNetwork){
		if(isAuthCorrect(socialNetwork)){
			log.debug("Login and pass is correct");
			
			AuthService authorizationService = new AuthService();
			HtmlPage permissionPage = authorizationService.getPermissionPage(autorizeEntity, socialNetwork);
			
			PermissionService permissionService = new PermissionService(
					AuthDic.FORM_ELEMENT_PERMISSION,
					AuthDic.FB_BUTTON_NAME);
			HtmlPage accessTokenPage = permissionService.getAccessTokenPage(permissionPage);
			
			return getRequestUrl(permissionPage, accessTokenPage);
		} else {
			log.error("Login or pass is incorrect");
			return "";
		}
	}
}
