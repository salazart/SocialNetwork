package com.social.accesstoken.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.NodeList;

import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.social.utils.AutorizeDictionary;

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
	
	public HtmlPage handlePermissionPage(HtmlPage autorizePage) {
		HtmlForm form = getPermissionForm(autorizePage);
		
		return emulatePermissionButtonClick(form);
	}
	
	public HtmlForm getPermissionForm(HtmlPage autorizePage) {
		if(autorizePage != null){
			log.debug("Gettign permission form");
			return autorizePage.getFirstByXPath(formElement);
		} else {
			log.debug("Authorize page is null");
			return null;
		}
	}
	
	private HtmlPage emulatePermissionButtonClick(HtmlForm form) {
		try {
			log.debug("Emulating button click for getting rule");
			HtmlButton button = form.getButtonByName(buttonElement);
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
					.getElementsByTagName(AutorizeDictionary.NAME_INPUT_FIELD);
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
