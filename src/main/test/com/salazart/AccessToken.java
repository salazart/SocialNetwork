package com.salazart;

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
	
	public static void main(String[] args) {
		SocialNetwork okSocialNetwork = new SocialNetwork(SocialNetworkType.OK, okLogin, okPass);
		SocialNetwork vkSocialNetwork = new SocialNetwork(SocialNetworkType.FACEBOOK, fbLogin, fbPass);
		SocialNetwork fbSocialNetwork = new SocialNetwork(SocialNetworkType.VKONTAKTE, vkLogin, vkPass);

		OkService okService = new OkService();
		String okAccessToken = okService.generateAccessToken(okSocialNetwork, PermissionDictionary.OK_GROUP_CONTENT);
		PropertyService.setValueProperties(OK_ACCESS_TOKEN, okAccessToken);
		System.out.println(OK_ACCESS_TOKEN + " = " + okAccessToken);

		FbService fbService = new FbService();
		String fbAccessToken = fbService.generateAccessToken(fbSocialNetwork, PermissionDictionary.FB_PUBLISH_ACTION);
		PropertyService.setValueProperties(FB_ACCESS_TOKEN, fbAccessToken);
		System.out.println(FB_ACCESS_TOKEN + " = " + fbAccessToken);
		
		VkService vkService = new VkService();
		String vkAccessToken = vkService.generateAccessToken(vkSocialNetwork, PermissionDictionary.VK_WALL);
		PropertyService.setValueProperties(VK_ACCESS_TOKEN, vkAccessToken);
		System.out.println(VK_ACCESS_TOKEN + " = " + vkAccessToken);
	}

}
