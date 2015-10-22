package com.salazart;

import com.social.models.SocialNetwork;
import com.social.models.SocialNetworkType;
import com.social.services.OkService;
import com.social.utils.PermissionDictionary;
import com.social.utils.PropertyService;

public class AccessToken {
	private static final String ACCESS_TOKEN = "okAccessToken";
	private static String okLogin = PropertyService.getValueProperties("okLogin");
	private static String okPass = PropertyService.getValueProperties("okPass");
	
	public static void main(String[] args) {
		SocialNetwork okSocialNetwork = new SocialNetwork(SocialNetworkType.OK, "", "");
		SocialNetwork vkSocialNetwork = new SocialNetwork(SocialNetworkType.VKONTAKTE, "", "");
		SocialNetwork fbSocialNetwork = new SocialNetwork(SocialNetworkType.FACEBOOK, "", "");

		OkService okService = new OkService();
		String accessToken = okService.generateAccessToken(okSocialNetwork, PermissionDictionary.OK_GROUP_CONTENT);
		PropertyService.setValueProperties(ACCESS_TOKEN, accessToken);
		System.out.println(accessToken);
		
		
		//VkService vkService = new VkService();
		//vkService.generateAccessToken(vkSocialNetwork, PermissionDictionary.VK_WALL);
		
		//FbService fbService = new FbService();
		//fbService.generateAccessToken(fbSocialNetwork, PermissionDictionary.FB_PUBLISH_ACTION);
	}

}
