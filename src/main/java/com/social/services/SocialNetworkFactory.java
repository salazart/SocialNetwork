package com.social.services;

import com.social.models.SocialNetwork;
import com.social.models.requests.Post;

public class SocialNetworkFactory {
	public void postToWall(SocialNetwork socialNetwork, Post post) {
		switch (socialNetwork.getType()) {
		case VK:
			new VkService(socialNetwork).postToWall(post);
			break;
		case FB:
			new FbService(socialNetwork).postToWall(post);
			break;
		case OK:
			new OkService(socialNetwork).postToWall(post);
			break;
		default:
			break;
		}
	}
}
