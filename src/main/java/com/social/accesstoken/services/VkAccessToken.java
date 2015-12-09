package com.social.accesstoken.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.social.accesstoken.models.AuthorizeEntity;
import com.social.models.SocialNetwork;
import com.social.utils.AutorizeDictionary;

/**
 * Service login on the vk.com website
 * @author salazart
 */
public class VkAccessToken extends ResponseParser{
	private static final Logger log = LogManager.getRootLogger();
	private AuthorizeEntity autorizeEntity;
	
	public VkAccessToken(String url) {
		autorizeEntity = new AuthorizeEntity(
				url, 
				AutorizeDictionary.FORM_AUTORIZE_VK, 
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
					AutorizeDictionary.NAME_INPUT_FIELD);
			HtmlForm permissionForm = permissionService.getPermissionForm(permissionPage);
			HtmlPage accessTokenPage = permissionService.emulatePermissionButtonClickVk(permissionForm);
			
			return getRequestUrl(permissionPage, accessTokenPage);
		} else {
			log.error("Login or pass is incorrect");
			return "";
		}
	}


}
