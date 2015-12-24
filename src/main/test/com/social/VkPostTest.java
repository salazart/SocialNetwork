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
 * postId shouldn't be null and empty
 * @author salazart
 *
 */
public class VkPostTest {
	private static final String vkLogin = PropertyService.getValueProperties("vkLogin");
	private static final String vkPass = PropertyService.getValueProperties("vkPass");
	private static final String vkGroupId = PropertyService.getValueProperties("vkGroupId");
	private static final String message = "Hello world number one!";
	
	@Test
	public void test(){
		Post post = new Post();
		post.setText(message);
		
		SocialNetwork vkSocialNetwork = new SocialNetwork(SocialNetworkType.VK, vkLogin, vkPass);
		
		String groupId = PropertyService.getValueProperties(vkGroupId);
		post.setId(groupId);
		
		ISocialNetworkService socialNetworkService = new SocialNetworkFactory().getSocialNetworkService(vkSocialNetwork);
		String postId = socialNetworkService.postToWall(vkSocialNetwork, post);
		
		assertNotNull(postId);
		
		assertFalse(postId.isEmpty());
	}
}
