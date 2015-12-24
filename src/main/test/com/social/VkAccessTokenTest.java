package com.social;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.social.models.SocialNetwork;
import com.social.models.SocialNetworkType;
import com.social.services.VkService;
import com.social.utils.PropertyService;
import com.social.utils.RuleDic;

/**
 * The class test the getting access token. It shouldn't be null and empty.
 * @author salazart
 *
 */
public class VkAccessTokenTest {
	
	private static final String vkLogin = PropertyService.getValueProperties("vkLogin");
	private static final String vkPass = PropertyService.getValueProperties("vkPass");
	
	@Test
	public void test(){
		SocialNetwork vkSocialNetwork = new SocialNetwork(SocialNetworkType.VK, vkLogin, vkPass);
		
		VkService vkService = new VkService();
		String vkAccessToken = vkService.generateAccessToken(vkSocialNetwork, RuleDic.VK_WALL);
		
		assertNotNull(vkAccessToken);
		
		assertFalse(vkAccessToken.isEmpty());
	}
}
