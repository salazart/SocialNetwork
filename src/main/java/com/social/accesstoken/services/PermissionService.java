package com.social.accesstoken.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.NodeList;

import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.social.utils.AuthDic;

/**
 * Service help you automatically login on the website and get rule
 * @author salazart
 *
 */
public class PermissionService {
	private static final Logger log = LogManager.getRootLogger();
	private String formElement;
	private String buttonElement;
	
	public PermissionService(String formElement, String buttonElement){
		this.formElement = formElement;
		this.buttonElement = buttonElement;
	}
	
	public HtmlPage getAccessTokenPage(HtmlPage autorizePage) {
		try {
			log.debug("Get permission form");
			HtmlForm form = autorizePage.getFirstByXPath(formElement);
			
			log.debug("Get permission button");
			HtmlButton button = form.getButtonByName(buttonElement);
			
			log.debug("Click permission button");
			return button.click();
		} catch (Exception e) {
			log.debug(e);
			return null;
		}
	}
		
	public HtmlPage emulatePermissionButtonClickVk(HtmlForm form) {
		try {
			log.debug("Emulating button click for getting rule");
			NodeList inputElements = form
					.getElementsByTagName(AuthDic.NAME_INPUT_FIELD);
			HtmlSubmitInput htmlSubmitInput = getSubmitButton(inputElements);
			return htmlSubmitInput.click();
		} catch (Exception e) {
			log.debug(e);
			return null;
		}
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
