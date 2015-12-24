package com.social;

import static org.junit.Assert.*;

import org.junit.Test;

import com.social.interfaces.ISocialNetworkService;
import com.social.models.Post;
import com.social.models.SocialNetwork;
import com.social.models.SocialNetworkType;
import com.social.services.SocialNetworkFactory;
import com.social.utils.PropertyService;

/**
 * The class testing to posting message to wall of social network and response shouldn't be null and empty.
 * @author salazart
 *
 */
public class FbPostTest {
	private static final String fbLogin = PropertyService.getValueProperties("fbLogin");
	private static final String fbPass = PropertyService.getValueProperties("fbPass");
	private static final String fbGroupId = PropertyService.getValueProperties("fbGroupId");
	
	@Test
	public void test(){
		Post post = new Post();
		post.setText("Test message!");
		post.setId(fbGroupId);
		
		SocialNetwork fbSocialNetwork = new SocialNetwork(SocialNetworkType.FB, fbLogin, fbPass);
		
		ISocialNetworkService socialNetworkService = new SocialNetworkFactory().getSocialNetworkService(fbSocialNetwork);
		String postId = socialNetworkService.postToWall(fbSocialNetwork, post);
		
		assertNotNull(postId);
		
		assertFalse(postId.isEmpty());
	}
}
