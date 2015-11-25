package com.salazart;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.social.models.SocialNetwork;
import com.social.models.SocialNetworkType;
import com.social.services.FbService;
import com.social.services.OkService;
import com.social.services.VkService;
import com.social.utils.PermissionDictionary;
import com.social.utils.PropertyService;

public class AccessToken {
	private static final String OK_ACCESS_TOKEN = "okAccessToken";
	private static final String FB_ACCESS_TOKEN = "fbAccessToken";
	private static final String VK_ACCESS_TOKEN = "vkAccessToken";
	
	private static String okLogin = PropertyService.getValueProperties("okLogin");
	private static String okPass = PropertyService.getValueProperties("okPass");
	
	private static String fbLogin = PropertyService.getValueProperties("fbLogin");
	private static String fbPass = PropertyService.getValueProperties("fbPass");
	
	private static String vkLogin = PropertyService.getValueProperties("vkLogin");
	private static String vkPass = PropertyService.getValueProperties("vkPass");
	
	private static final Logger log = LogManager.getRootLogger();
	
	public static void main(String[] args) {
		SocialNetwork okSocialNetwork = new SocialNetwork(SocialNetworkType.OK, okLogin, okPass);
		SocialNetwork fbSocialNetwork = new SocialNetwork(SocialNetworkType.FB, fbLogin, fbPass);
		SocialNetwork vkSocialNetwork = new SocialNetwork(SocialNetworkType.VK, vkLogin, vkPass);

		OkService okService = new OkService(okSocialNetwork);
		String okAccessToken = okService.generateAccessToken(PermissionDictionary.OK_GROUP_CONTENT);
		log.debug(OK_ACCESS_TOKEN + " = " + okAccessToken);

		FbService fbService = new FbService(fbSocialNetwork);
		String fbAccessToken = fbService.generateAccessToken(PermissionDictionary.FB_PUBLISH_ACTION);
		log.debug(FB_ACCESS_TOKEN + " = " + fbAccessToken);
		
		VkService vkService = new VkService(vkSocialNetwork);
		String vkAccessToken = vkService.generateAccessToken(PermissionDictionary.VK_WALL);
		log.debug(VK_ACCESS_TOKEN + " = " + vkAccessToken);
	}

}
