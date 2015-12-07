package com.social;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.social.models.SocialNetwork;
import com.social.models.SocialNetworkType;
import com.social.models.requests.Post;
import com.social.services.SocialNetworkFactory;
import com.social.utils.PropertyService;

public class OkPostTest {
	private static String okLogin = PropertyService.getValueProperties("okLogin");
	private static String okPass = PropertyService.getValueProperties("okPass");
	
	@Test
	public void test(){
		Post post = new Post();
		post.setText("Hello world number one!");
		
		SocialNetwork okSocialNetwork = new SocialNetwork(SocialNetworkType.OK, okLogin, okPass);
		
		String okGroupId = PropertyService.getValueProperties("fbGroupId");
		post.setId(okGroupId);
		
		String postId = new SocialNetworkFactory().postToWall(okSocialNetwork, post);
		assertTrue(postId != null && !postId.isEmpty());
	}

}
