package com.social;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.social.models.SocialNetwork;
import com.social.models.SocialNetworkType;
import com.social.services.FbService;
import com.social.utils.PermissionDictionary;
import com.social.utils.PropertyService;

public class FbAccessTokenTest {
	
	private String fbLogin = PropertyService.getValueProperties("fbLogin");
	private String fbPass = PropertyService.getValueProperties("fbPass");
	
	@Test
	public void test() {
		SocialNetwork fbSocialNetwork = new SocialNetwork(SocialNetworkType.FB, fbLogin, fbPass);
		
		FbService fbService = new FbService();
		String fbAccessToken = fbService.generateAccessToken(fbSocialNetwork, PermissionDictionary.FB_PUBLISH_ACTION);
		
		assertTrue(fbAccessToken != null && !fbAccessToken.isEmpty()) ;
	}

}
