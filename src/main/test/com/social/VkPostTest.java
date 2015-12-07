package com.social;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.social.models.SocialNetwork;
import com.social.models.SocialNetworkType;
import com.social.models.requests.Post;
import com.social.services.SocialNetworkFactory;
import com.social.utils.PropertyService;

public class VkPostTest {
	private String vkLogin = PropertyService.getValueProperties("vkLogin");
	private String vkPass = PropertyService.getValueProperties("vkPass");
	
	@Test
	public void test(){
		Post post = new Post();
		post.setText("Hello world number one!");
		
		SocialNetwork vkSocialNetwork = new SocialNetwork(SocialNetworkType.VK, vkLogin, vkPass);
		
		String vkGroupId = PropertyService.getValueProperties("vkGroupId");
		post.setId(vkGroupId);
		
		String postId = new SocialNetworkFactory().postToWall(vkSocialNetwork, post);
		assertTrue(postId != null && !postId.isEmpty());
	}
}
