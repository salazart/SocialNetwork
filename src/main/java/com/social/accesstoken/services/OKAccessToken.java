package com.social.accesstoken.services;

import java.io.IOException;

import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.social.accesstoken.models.AutorizeEntity;
import com.social.models.SocialNetwork;
import com.social.utils.AutorizeDictionary;

public class OKAccessToken extends AutorizeService{
	
	private AutorizeEntity autorizeEntity;
	
	public OKAccessToken(String url) {
		autorizeEntity = new AutorizeEntity(
				url, 
				AutorizeDictionary.FORM_AUTORIZE_OK, 
				AutorizeDictionary.OK_EMAIL_FIELD, 
				AutorizeDictionary.OK_PASS_FIELD);
	}
	
	public String getAccessTokenResponse(SocialNetwork socialNetwork){
		HtmlPage permissionPage = handleAutorizePage(autorizeEntity, socialNetwork);
		HtmlPage accessTokenPage = permissionPage(permissionPage);
		
		if (accessTokenPage != null) {
			return String.valueOf(accessTokenPage.getWebResponse().getRequestUrl());
		} else if (permissionPage != null){
			return String.valueOf(permissionPage.getWebResponse().getRequestUrl());
		} else {
			return "";
		}
	}
	
	private HtmlPage permissionPage(HtmlPage autorizePage) {
		if(autorizePage == null){
			return null;
		}
		HtmlPage permissionPage = null;
		HtmlForm form = autorizePage.getFirstByXPath(AutorizeDictionary.OK_FORM_ELEMENT_PERMISSION_2);
		if (form != null) {
			HtmlButton button = null;
			try {
				button = form.getButtonByName(AutorizeDictionary.OK_BUTTON_NAME);
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
