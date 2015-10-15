package com.salazart;

import com.social.models.SocialNetwork;
import com.social.models.TypeSN;
import com.social.services.OkService;
import com.social.utils.PermissionDictionary;
import com.social.utils.PropertyService;

public class AccessToken {
	private static final String ACCESS_TOKEN = "okAccessToken";
	private static String okLogin = PropertyService.getValueProperties("okLogin");
	private static String okPass = PropertyService.getValueProperties("okPass");
	
	public static void main(String[] args) {
		SocialNetwork okSocialNetwork = new SocialNetwork(TypeSN.OK, okLogin, okPass);
		SocialNetwork vkSocialNetwork = new SocialNetwork(TypeSN.VKONTAKTE, "", "");
		SocialNetwork fbSocialNetwork = new SocialNetwork(TypeSN.FACEBOOK, "", "");
		
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
