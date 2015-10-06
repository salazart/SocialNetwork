package com.salazart;

import com.social.models.SocialNetwork;
import com.social.models.TypeSN;
import com.social.services.OkService;
import com.social.utils.PropertyService;

public class FriendsByUser {
	private static String login = PropertyService.getInstance().getValueProperties("okLogin");
	private static String pass = PropertyService.getInstance().getValueProperties("okPass");
	
	public static void main(String[] args) {
		SocialNetwork okSocialNetwork = new SocialNetwork(TypeSN.OK, login, pass);
		
		OkService okService = new OkService();
		okService.getFriends(okSocialNetwork);
	}
}
