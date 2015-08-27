package com.salazart;

import com.social.models.SocialNetwork;
import com.social.models.TypeSN;
import com.social.services.OkService;
import com.social.utils.PermissionDictionary;

public class OkAccessToken {

	public static void main(String[] args) {
		SocialNetwork vkSocialNetwork = new SocialNetwork(TypeSN.OK,
				"", "");
		
		OkService okService = new OkService();
		okService.generateAccessToken(vkSocialNetwork, PermissionDictionary.OK_GROUP_CONTENT);
	}

}
