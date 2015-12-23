package com.social;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.web.util.UriComponentsBuilder;

import com.social.utils.ParamDic;
import com.social.utils.RuleDic;
import com.social.utils.PropertyService;
import com.social.utils.UrlDic;

public class AccessTokenRequestTest{
	private static final String APP_ID = "okAppId";
	
	@Test
	public void test() {
		String appId = PropertyService.getValueProperties(APP_ID);
		
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(UrlDic.OK_OAUTH_DIALOG)
				.queryParam(ParamDic.CLIENT_ID, appId)
				.queryParam(ParamDic.RESPONSE_TYPE, ParamDic.TOKEN)
				.queryParam(ParamDic.SCOPE, RuleDic.OK_GROUP_CONTENT)
				.queryParam(ParamDic.REDIRECT_URI, UrlDic.OK_REDIRECT_URL);
		
		String urlRequest = builder.build().encode().toUri().toString();
		
		assertTrue(urlRequest.equals("https://connect.ok.ru/oauth/authorize"
				+ "?client_id=1151816192"
				+ "&response_type=token"
				+ "&scope=GROUP_CONTENT"
				+ "&redirect_uri=http://www.i.ua"));
	}
}
