package com.social;

import com.social.models.SocialNetwork;
import com.social.models.SocialNetworkType;
import com.social.models.requests.Post;
import com.social.services.SocialNetworkFactory;
import com.social.utils.PropertyService;

public class PostToMyWayGroup {
	private static String okLogin = PropertyService.getValueProperties("okLogin");
	private static String okPass = PropertyService.getValueProperties("okPass");
	
	private static String vkLogin = PropertyService.getValueProperties("vkLogin");
	private static String vkPass = PropertyService.getValueProperties("vkPass");
	
	public static void main(String[] args) {

		Post post = new Post();
		post.setText("Hello world number one!");

		SocialNetwork vkSocialNetwork = new SocialNetwork(SocialNetworkType.VK, vkLogin, vkPass);
		SocialNetwork okSocialNetwork = new SocialNetwork(SocialNetworkType.OK, okLogin, okPass);
		
		String vkGroupId = PropertyService.getValueProperties("vkGroupId");
		post.setId(vkGroupId);
		new SocialNetworkFactory().postToWall(vkSocialNetwork, post);
		
		String okGroupId = PropertyService.getValueProperties("fbGroupId");
		post.setId(okGroupId);
		new SocialNetworkFactory().postToWall(okSocialNetwork, post);

	}

}
