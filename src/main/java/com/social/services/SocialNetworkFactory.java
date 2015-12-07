package com.social.services;

import com.social.models.SocialNetwork;
import com.social.models.requests.Post;

public class SocialNetworkFactory {
	public String postToWall(SocialNetwork socialNetwork, Post post) {
		switch (socialNetwork.getType()) {
		case VK:
			return new VkService().postToWall(socialNetwork, post);
		case FB:
			return new FbService().postToWall(socialNetwork, post);
		case OK:
			return new OkService().postToWall(socialNetwork, post);
		default:
			return "";
		}
	}
}
