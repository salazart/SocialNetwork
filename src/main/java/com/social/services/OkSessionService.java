package com.social.services;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.social.utils.PropertyService;

public class OkSessionService {
	
	private static final String APP_SECRET_KEY = "okAppSecretKey";
	private static final String APP_KEY = "okAppKey";
	private static final String METHOD = "okMethod";
	private static final String GROUP_ID = "okGroupId";
	private static final String POST_TYPE = "okPostType";
	
	protected String generateSesionSignature(String accessToken, String attachmentsText) {
		String appSecretKey = PropertyService.getValueProperties(APP_SECRET_KEY);
		String hash = generateMD5(accessToken + appSecretKey);
		
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(getAppKey());
		stringBuilder.append(getAttachmentsText(attachmentsText));
		stringBuilder.append(getGroupId());
		stringBuilder.append(getMethod());
		stringBuilder.append(getType());
		stringBuilder.append(hash);
		
		return generateMD5(stringBuilder.toString());
	}
	
	private String getAttachmentsText(String attachmentsText){
		final String attachment = "attachment";
		return !attachmentsText.isEmpty() ? attachment + "=" + attachmentsText : "";
	}
	
	private String getType(){
		final String type = "type";
		String groupId = PropertyService.getValueProperties(POST_TYPE);
		return !groupId.isEmpty() ? type + "=" + groupId : "";
	}
	
	private String getGroupId(){
		final String gid = "gid";
		String groupId = PropertyService.getValueProperties(GROUP_ID);
		return !groupId.isEmpty() ? gid + "=" + groupId : "";
	}
	
	private String getAppKey(){
		final String applicationKey = "application_key";
		String appKey = PropertyService.getValueProperties(APP_KEY);
		return !appKey.isEmpty() ? applicationKey + "=" + appKey : "";
	}
	
	private String getMethod(){
		final String method = "method";
		String methodName = PropertyService.getValueProperties(METHOD);
		return !methodName.isEmpty() ? method + "=" + methodName : "";
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
