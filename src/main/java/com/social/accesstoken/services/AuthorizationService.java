package com.social.accesstoken.services;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.NodeList;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.social.accesstoken.models.AuthorizeEntity;
import com.social.models.SocialNetwork;
import com.social.utils.AutorizeDictionary;

/**
 * Service login on websites of social networks
 * @author salazart
 *
 */
public class AuthorizationService {
	private static final Logger log = LogManager.getRootLogger();
	
	public HtmlPage handleAutorizePage(AuthorizeEntity autorizeEntity, SocialNetwork socialNetwork) {
		HtmlPage autorizePage = getAuthorizePage(autorizeEntity);
		
		HtmlForm form = enterAuthorizationData(autorizeEntity, socialNetwork, autorizePage);
			
		return emulateAutorizeButtonClick(form);
	}
	
	private HtmlPage getAuthorizePage(AuthorizeEntity autorizeEntity) {
		try {
			log.debug("Getting authorization page");
			WebClient webClient = new WebClient(BrowserVersion.FIREFOX_3);
			webClient.setJavaScriptEnabled(false);
			return webClient.getPage(autorizeEntity.getUrl());
		} catch (FailingHttpStatusCodeException | IOException e) {
			log.error(e);
			return null;
		}
	}

	private HtmlForm enterAuthorizationData(AuthorizeEntity autorizeEntity, SocialNetwork socialNetwork,
			HtmlPage autorizePage) {
		try {
			log.debug("Emulate a login and pass on the form");
			HtmlForm form = autorizePage.getFirstByXPath(autorizeEntity.getTypeAutorizeForm());

			form.getInputByName(autorizeEntity.getEmailField()).setValueAttribute(
					socialNetwork.getLogin());
			form.getInputByName(autorizeEntity.getPassField()).setValueAttribute(
					socialNetwork.getPass());
			return form;
		} catch (Exception e) {
			 log.error(e);
			 return null;
		}
	}
	
	private HtmlPage emulateAutorizeButtonClick(HtmlForm form){
		try {
			log.debug("Emulate a click in button log in");
			NodeList inputElements = form
					.getElementsByTagName(AutorizeDictionary.NAME_INPUT_FIELD);
			HtmlSubmitInput htmlSubmitInput = getSubmitButton(inputElements);
			return htmlSubmitInput.click();
		} catch (IOException e) {
			log.error(e);
			return null;
		}
	}

	protected HtmlSubmitInput getSubmitButton(NodeList inputElements) {
		HtmlSubmitInput htmlSubmitInput = null;
		for (int i = 0; i < inputElements.getLength(); i++) {
			if (inputElements.item(i) instanceof HtmlSubmitInput) {
				htmlSubmitInput = (HtmlSubmitInput) inputElements.item(i);
			}
		}
		return htmlSubmitInput;
	}
}
