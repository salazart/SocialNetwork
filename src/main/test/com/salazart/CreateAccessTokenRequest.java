package com.salazart;

import com.social.models.AccessToken;

public class CreateAccessTokenRequest {
    private static final String CLIENT_ID_APPLICATION = "4517745";

    public static void main(String[] args) {
	AccessToken accessToken = new AccessToken("www.facebook.com/dialog/oauth");
	accessToken.setClientId("700011900132723");
	accessToken.setResponseType("token");
	accessToken.setScope("email,user_posts,publish_actions,user_managed_groups");
	accessToken.setRedirectURI("https://oauth.vk.com/authorize");
	accessToken.generateAccessToken("", "");
	
	/*AccessToken oAuthRequest = new AccessToken("oauth.vk.com/authorize");
	oAuthRequest.setClientId(CLIENT_ID_APPLICATION);
	oAuthRequest.setScope("wall,offline");
	oAuthRequest.setRedirectURI("https://oauth.vk.com/blank.html");
	oAuthRequest.setDisplay("mobile");
	oAuthRequest.setResponseType("token");*/
	

	//System.out.println(accessToken.buildQueryMessage());
    }

}
