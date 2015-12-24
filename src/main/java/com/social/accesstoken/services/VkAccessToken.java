package com.social.accesstoken.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.social.accesstoken.models.AuthEntity;
import com.social.models.SocialNetwork;
import com.social.utils.AuthDic;

/**
 * Service login of vk.com website
 * @author salazart
 */
public class VkAccessToken extends ResponseParser{
	private static final Logger log = LogManager.getRootLogger();
	private AuthEntity authEntity;
	
	public VkAccessToken(String url) {
		authEntity = new AuthEntity(
				url, 
				AuthDic.VK_FORM, 
				AuthDic.VK_LOGIN, 
				AuthDic.VK_PASS);
	}

	public String getAccessToken(SocialNetwork socialNetwork){
		if(isAuthCorrect(socialNetwork)){
			log.debug("Login and pass is correct");
			
			AuthService authService = new AuthService();
			HtmlPage permissionPage = authService.getPermissionPage(authEntity, socialNetwork);
			System.out.println(authEntity.getUrl());
			PermissionService permissionService = new PermissionService(
					AuthDic.FORM_ELEMENT_PERMISSION, 
					AuthDic.NAME_INPUT_FIELD);
			HtmlForm permissionForm = null;//permissionService.getPermissionForm(permissionPage);
			HtmlPage accessTokenPage = permissionService.emulatePermissionButtonClickVk(permissionForm);
			
			return getRequestUrl(permissionPage, accessTokenPage);
		} else {
			log.error("Login or pass is incorrect");
			return "";
		}
	}


}
