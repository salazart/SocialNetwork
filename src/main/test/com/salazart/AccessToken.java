package com.salazart;

import com.social.models.SocialNetwork;
import com.social.models.TypeSN;
import com.social.services.OkService;
import com.social.utils.PermissionDictionary;

public class AccessToken {

	public static void main(String[] args) {
		SocialNetwork okSocialNetwork = new SocialNetwork(TypeSN.OK, "", "");
		SocialNetwork vkSocialNetwork = new SocialNetwork(TypeSN.VKONTAKTE, "", "");
		SocialNetwork fbSocialNetwork = new SocialNetwork(TypeSN.FACEBOOK, "", "");
		
		OkService okService = new OkService();
		okService.generateAccessToken(okSocialNetwork, PermissionDictionary.OK_GROUP_CONTENT);
		
		//VkService vkService = new VkService();
		//vkService.generateAccessToken(vkSocialNetwork, PermissionDictionary.VK_WALL);
		
		//FbService fbService = new FbService();
		//fbService.generateAccessToken(fbSocialNetwork, PermissionDictionary.FB_PUBLISH_ACTION);
	}

}
