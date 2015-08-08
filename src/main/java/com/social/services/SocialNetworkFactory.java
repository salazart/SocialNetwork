package com.social.services;

import com.social.interfaces.ISocialNetwork;
import com.social.models.Post;
import com.social.models.SocialNetwork;

public class SocialNetworkFactory {
	public void postSocialNetwork(SocialNetwork socialNetwork, Post post) {
		switch (socialNetwork.getTypeSN()) {
		case VKONTAKTE:
			ISocialNetwork vkService = new VkService();
			vkService.postWall(post, socialNetwork);
			break;
		case FACEBOOK:
			ISocialNetwork fbService = new FbService();
			fbService.postWall(post, socialNetwork);
			break;
		default:
			break;
		}
	}
}
