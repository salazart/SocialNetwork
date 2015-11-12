package com.social.services;

import com.social.models.SocialNetwork;
import com.social.models.requests.Post;

public class SocialNetworkFactory {
	public void post(SocialNetwork socialNetwork, Post post) {
		switch (socialNetwork.getType()) {
		case VKONTAKTE:
			new VkService().postWall(post, socialNetwork);
			break;
		case FACEBOOK:
			new FbService().postWall(post, socialNetwork);
			break;
		case OK:
			new OkService().postWall(post, socialNetwork);
			break;
		default:
			break;
		}
	}
}
