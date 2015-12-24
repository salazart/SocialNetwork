package com.social;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.social.interfaces.ISocialNetworkService;
import com.social.models.Post;
import com.social.models.SocialNetwork;
import com.social.models.SocialNetworkType;
import com.social.services.SocialNetworkFactory;
import com.social.utils.PropertyService;

/**
 * The class test the posting message to wall of social network and response shouldn't be null and empty.
 * @author salazart
 *
 */
public class OkPostTest {
	private static final String okLogin = PropertyService.getValueProperties("okLogin");
	private static final String okPass = PropertyService.getValueProperties("okPass");
	private static final String okGroupId = PropertyService.getValueProperties("fbGroupId");
	
	@Test
	public void test(){
		Post post = new Post();
		post.setText("This message is test!");
		post.setId(okGroupId);
		
		SocialNetwork okSocialNetwork = new SocialNetwork(SocialNetworkType.OK, okLogin, okPass);
		
		ISocialNetworkService socialNetworkService = new SocialNetworkFactory().getSocialNetworkService(okSocialNetwork);
		String postId = socialNetworkService.postToWall(okSocialNetwork, post);
		
		assertNotNull(postId);
		
		assertFalse(postId.isEmpty());
	}
}
