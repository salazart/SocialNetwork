package com.social.services;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.social.utils.PropertyService;

public class OkSessionService {
	
	private static final String APP_SECRET_KEY = "okAppSecretKey";
	private static final String APP_KEY = "okAppKey";
	
	private String appSecretKey = PropertyService.getInstance().getValueProperties(APP_SECRET_KEY);
	private String appKey = PropertyService.getInstance().getValueProperties(APP_KEY);
	
	public String generateSesionSignature(String accessToken, String methodName) {
		//String hashAccessTokenAndSectetKey = generateMD5(accessToken + appSecretKey);
		System.out.println(accessToken + appSecretKey);
		String sig1 = generateMD5(accessToken + appSecretKey);
		System.out.println(sig1);
		
		System.out.println(getAppKey()+getMethod(methodName)+sig1);
		String sig2 = generateMD5(getAppKey()+getMethod(methodName)+sig1);
		
		return sig2;
	}

	private String generateSessionSignature(String methodName, String hashAccessTokenAndSectetKey) {
		if(!hashAccessTokenAndSectetKey.isEmpty()){
			return generateMD5(getAppKey() 
					+ getMethod(methodName) 
					+ hashAccessTokenAndSectetKey);
		} else {
			return "";
		}
	}
	
	private String getAppKey(){
		final String applicationKey = "application_key";
		String appKey = PropertyService.getInstance().getValueProperties(APP_KEY);
		return !appKey.isEmpty() ? applicationKey + "=" + appKey : "";
	}
	
	private String getMethod(String methodName){
		final String method = "method";
		return !methodName.isEmpty() ? method + "=" + methodName : "";
	}
	
	private String getAccessToken(String accessTokenValue){
		final String accessToken = "access_token";
		return !accessTokenValue.isEmpty() ? accessToken + "=" + accessTokenValue : "";
	}
	
	private String generateMD5(String text) {
		try {
			MessageDigest m = MessageDigest.getInstance("MD5");
			m.reset();
			m.update(text.getBytes());
			byte[] digest = m.digest();
			BigInteger bigInt = new BigInteger(1, digest);
			String hashtext = bigInt.toString(16);
			return hashtext;
		} catch (NoSuchAlgorithmException e) {
			return "";
		}
	}
}
