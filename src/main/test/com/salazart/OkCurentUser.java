package com.salazart;

import com.social.services.OkSessionService;
import com.social.utils.PropertyService;

public class OkCurentUser {
	private static final String ACCESS_TOKEN = "okAccessToken";
	private static final String APP_SECRET_KEY = "okAppSecretKey";
	private static final String accessToken = PropertyService.getValueProperties(ACCESS_TOKEN);
	private static final String appSecretKey = PropertyService.getValueProperties(APP_SECRET_KEY);
	
	private static final String METHOD_NAME = "friends.get";

	public static void main(String[] args) {
		System.out.println(accessToken);
		System.out.println(appSecretKey);
		
		//OkSessionService okSessionService = new OkSessionService();
		//String hash1 = okSessionService.generateMD5(accessToken + appSecretKey);
		///System.out.println(hash1);
		
		//String methodValue = okSessionService.getMethod(METHOD_NAME);
		//System.out.println(methodValue);
		
		//String appKey = okSessionService.getAppKey();
		//System.out.println(appKey);
		
		//String concatValue = appKey + methodValue + hash1;
		//String hash2 = okSessionService.generateMD5(concatValue);
		//System.out.println(hash2);
	}

}
