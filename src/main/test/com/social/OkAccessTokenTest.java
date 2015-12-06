package com.social;

import static org.junit.Assert.*;

import org.junit.Test;

import com.social.models.SocialNetwork;
import com.social.models.SocialNetworkType;
import com.social.services.OkService;
import com.social.utils.PermissionDictionary;
import com.social.utils.PropertyService;

public class OkAccessTokenTest{
	
	private String okLogin = PropertyService.getValueProperties("okLogin");
	private String okPass = PropertyService.getValueProperties("okPass");
	
	@Test
	public void test() {
		SocialNetwork okSocialNetwork = new SocialNetwork(SocialNetworkType.OK, okLogin, okPass);
		
		OkService okService = new OkService(okSocialNetwork);
		String okAccessToken = okService.generateAccessToken(PermissionDictionary.OK_GROUP_CONTENT);
		
		assertTrue(okAccessToken != null && okAccessToken != "") ;
	}
}
