package com.social.services;

import com.social.interfaces.ISocialNetworkService;
import com.social.models.SocialNetwork;

public class SocialNetworkFactory {
	
	public ISocialNetworkService getSocialNetworkService(SocialNetwork socialNetwork) {
		switch (socialNetwork.getType()) {
		case VK:
			return new VkService();
		case FB:
			return new FbService();
		case OK:
			return new OkService();
		default:
			return null;
		}
	}
}
