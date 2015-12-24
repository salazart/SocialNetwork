package com.social;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.social.models.SocialNetwork;
import com.social.models.SocialNetworkType;
import com.social.services.FbService;
import com.social.utils.PropertyService;
import com.social.utils.RuleDic;

/**
 * The class test the getting access token of facebook.com and verify it shouldn't be null and empty.
 * @author salazart
 *
 */
public class FbAccessTokenTest {
	
	private static final String fbLogin = PropertyService.getValueProperties("fbLogin");
	private static final String fbPass = PropertyService.getValueProperties("fbPass");
	
	@Test
	public void test() {
		SocialNetwork fbSocialNetwork = new SocialNetwork(SocialNetworkType.FB, fbLogin, fbPass);
		
		FbService fbService = new FbService();
		String fbAccessToken = fbService.generateAccessToken(fbSocialNetwork, RuleDic.FB_PUBLISH_ACTION);
		
		assertNotNull(fbAccessToken);
		
		assertFalse(fbAccessToken.isEmpty());
	}

}
