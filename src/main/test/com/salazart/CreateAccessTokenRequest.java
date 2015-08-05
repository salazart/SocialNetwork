package com.salazart;

import com.social.models.AccessToken;

public class CreateAccessTokenRequest {
    private static final String VK_CLIENT_ID_APPLICATION = "4517745";
    private static final String FB_CLIENT_ID_APPLICATION = "700011900132723";
    
    private static final String login = "";
    private static final String pass = "";

    public static void main(String[] args) {
	AccessToken accessToken = new AccessToken("www.facebook.com/dialog/oauth");
	accessToken.setClientId(FB_CLIENT_ID_APPLICATION);
	accessToken.setResponseType("token");
	accessToken.setScope("email,user_posts,publish_actions,user_managed_groups,user_friends");
	accessToken.setRedirectURI("http://www.i.ua");
	accessToken.setDisplay("popup");
	String accessTokenText = accessToken.generateAccessToken(login , pass);
	System.out.println(accessTokenText);
	/*AccessToken oAuthRequest = new AccessToken("oauth.vk.com/authorize");
	oAuthRequest.setClientId(VK_CLIENT_ID_APPLICATION);
	oAuthRequest.setScope("wall,offline");
	oAuthRequest.setRedirectURI("https://oauth.vk.com/blank.html");
	oAuthRequest.setDisplay("mobile");
	oAuthRequest.setResponseType("token");
	//oAuthRequest.generateAccessToken("s.o.w@i.ua", ".salazart.1989...");
	System.out.println(oAuthRequest.buildQueryMessage());*/
	//System.out.println(accessToken.buildQueryMessage());
    }

}
