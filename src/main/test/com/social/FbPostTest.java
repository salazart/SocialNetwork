package com.social;

import static org.junit.Assert.*;

import org.junit.Test;

import com.social.models.SocialNetwork;
import com.social.models.SocialNetworkType;
import com.social.models.requests.Post;
import com.social.services.SocialNetworkFactory;
import com.social.utils.PropertyService;

public class FbPostTest {
	private String fbLogin = PropertyService.getValueProperties("fbLogin");
	private String fbPass = PropertyService.getValueProperties("fbPass");
	
	@Test
	public void test(){
		Post post = new Post();
		post.setText("Hello world number one!");
		
		SocialNetwork fbSocialNetwork = new SocialNetwork(SocialNetworkType.FB, fbLogin, fbPass);
		
		String fbGroupId = PropertyService.getValueProperties("fbGroupId");
		post.setId(fbGroupId);
		
		String id = new SocialNetworkFactory().postToWall(fbSocialNetwork, post);
		assertTrue(id != null && !id.isEmpty());
	}
}
