package com.salazart;

import com.social.models.SocialNetwork;
import com.social.models.SocialNetworkType;
import com.social.models.requests.Post;
import com.social.services.SocialNetworkFactory;
import com.social.utils.PropertyService;

public class PostToMyWayGroup {
	private static String okLogin = PropertyService.getValueProperties("okLogin");
	private static String okPass = PropertyService.getValueProperties("okPass");
	
	private static String fbLogin = PropertyService.getValueProperties("fbLogin");
	private static String fbPass = PropertyService.getValueProperties("fbPass");
	
	private static String vkLogin = PropertyService.getValueProperties("vkLogin");
	private static String vkPass = PropertyService.getValueProperties("vkPass");
	
	public static void main(String[] args) {

		Post post = new Post();
		post.setText("Hello great great great world!");

		SocialNetwork vkSocialNetwork = new SocialNetwork(SocialNetworkType.VK, vkLogin, vkPass);
		SocialNetwork okSocialNetwork = new SocialNetwork(SocialNetworkType.OK, okLogin, okPass);
		SocialNetwork fbSocialNetwork = new SocialNetwork(SocialNetworkType.FB, fbLogin, fbPass);
		
		String vkGroupId = PropertyService.getValueProperties("vkGroupId");
		post.setId(vkGroupId);
		new SocialNetworkFactory().postToWall(vkSocialNetwork, post);
		
		String fbGroupId = PropertyService.getValueProperties("fbGroupId");
		post.setId(fbGroupId);
		new SocialNetworkFactory().postToWall(fbSocialNetwork, post);
		
		String okGroupId = PropertyService.getValueProperties("fbGroupId");
		post.setId(okGroupId);
		new SocialNetworkFactory().postToWall(okSocialNetwork, post);

	}

}
