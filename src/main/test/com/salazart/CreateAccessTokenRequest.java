package com.salazart;

import com.social.models.AccessToken;

public class CreateAccessTokenRequest {
    private static final String VK_CLIENT_ID_APPLICATION = "4517745";
    private static final String FB_CLIENT_ID_APPLICATION = "700011900132723";

    public static void main(String[] args) {
	AccessToken accessToken = new AccessToken("www.facebook.com/dialog/oauth");
	accessToken.setClientId(FB_CLIENT_ID_APPLICATION);
	accessToken.setResponseType("token");
	accessToken.setScope("email,user_posts,publish_actions,user_managed_groups,user_friends");
	accessToken.setRedirectURI("http://www.i.ua");
	accessToken.setDisplay("popup");
	String accessTokenText = accessToken.generateAccessToken("", "");
	System.out.println(accessTokenText);
	/*AccessToken oAuthRequest = new AccessToken("oauth.vk.com/authorize");
	oAuthRequest.setClientId(CLIENT_ID_APPLICATION);
	oAuthRequest.setScope("wall,offline");
	oAuthRequest.setRedirectURI("https://oauth.vk.com/blank.html");
	oAuthRequest.setDisplay("mobile");
	oAuthRequest.setResponseType("token");*/
	

	//System.out.println(accessToken.buildQueryMessage());
    }

}
