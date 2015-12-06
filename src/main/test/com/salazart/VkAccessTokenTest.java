package com.salazart;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.social.models.SocialNetwork;
import com.social.models.SocialNetworkType;
import com.social.services.VkService;
import com.social.utils.PermissionDictionary;
import com.social.utils.PropertyService;

public class VkAccessTokenTest {
	
	private String vkLogin = PropertyService.getValueProperties("vkLogin");
	private String vkPass = PropertyService.getValueProperties("vkPass");
	
	@Test
	public void test(){
		SocialNetwork vkSocialNetwork = new SocialNetwork(SocialNetworkType.VK, vkLogin, vkPass);
		
		VkService vkService = new VkService(vkSocialNetwork);
		String vkAccessToken = vkService.generateAccessToken(PermissionDictionary.VK_WALL);
		
		assertTrue(vkAccessToken != null && vkAccessToken != "") ;
	}
}
