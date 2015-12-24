package com.social;

import static org.junit.Assert.*;

import org.junit.Test;

import com.social.models.SocialNetwork;
import com.social.models.SocialNetworkType;
import com.social.services.OkService;
import com.social.utils.RuleDic;
import com.social.utils.PropertyService;

/**
 * The class testing to obtain access token of social network and is shouldn't be null and empty.
 * @author salazart
 *
 */
public class OkAccessTokenTest{
	
	private static final String okLogin = PropertyService.getValueProperties("okLogin");
	private static final String okPass = PropertyService.getValueProperties("okPass");
	
	@Test
	public void test() {
		SocialNetwork okSocialNetwork = new SocialNetwork(SocialNetworkType.OK, okLogin, okPass);
		
		OkService okService = new OkService();
		String okAccessToken = okService.generateAccessToken(okSocialNetwork, RuleDic.OK_GROUP_CONTENT);
		
		assertTrue(okAccessToken != null && !okAccessToken.isEmpty()) ;
	}
}
