package com.salazart;

import com.social.services.OkSessionService;

public class CalculateMD5 {
	private static final String methodName = "users.getCurrentUser";
	private static final String accessToken = "e71f68eb3fe72cceed88a775bc47136cd2b4db350f0c5bdf404561f.d86";
	
	public static void main(String[] args) {
		//SocialNetwork okSocialNetwork = new SocialNetwork(TypeSN.OK, "s.o.w@i.ua", ".sala.zart.");
		
		//OkService okService = new OkService();
		//String accessToken = okService.generateAccessToken(okSocialNetwork, PermissionDictionary.OK_GROUP_CONTENT);
		
		OkSessionService okSessionService = new OkSessionService();
		String sig = okSessionService.generateSesionSignature(accessToken, methodName);
		
		System.out.println(sig);
		
	}
}
