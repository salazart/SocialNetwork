package com.social;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.web.util.UriComponentsBuilder;

import com.social.utils.ParametersDictionary;
import com.social.utils.PermissionDictionary;
import com.social.utils.PropertyService;
import com.social.utils.UrlsDictionary;

public class AccessTokenRequestTest{
	private static final String APP_ID = "okAppId";
	
	@Test
	public void test() {
		String appId = PropertyService.getValueProperties(APP_ID);
		
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(UrlsDictionary.OK_OAUTH_DIALOG)
				.queryParam(ParametersDictionary.CLIENT_ID, appId)
				.queryParam(ParametersDictionary.RESPONSE_TYPE, ParametersDictionary.TOKEN)
				.queryParam(ParametersDictionary.SCOPE, PermissionDictionary.OK_GROUP_CONTENT)
				.queryParam(ParametersDictionary.REDIRECT_URI, UrlsDictionary.OK_REDIRECT_URL);
		
		String urlRequest = builder.build().encode().toUri().toString();
		
		assertTrue(urlRequest.equals("https://connect.ok.ru/oauth/authorize"
				+ "?client_id=1151816192"
				+ "&response_type=token"
				+ "&scope=GROUP_CONTENT"
				+ "&redirect_uri=http://www.i.ua"));
	}
}
