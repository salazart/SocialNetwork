package com.salazart.social;

public class SocialNetwork {

    public static void main(String[] args) {
	System.out.println("Hello Social Network world");

	OAuthRequest oAuthRequest = new OAuthRequest(
		"https://oauth.vk.com/authorize");
	oAuthRequest.setClientId("4517745");
	oAuthRequest.setScope("audio");
	oAuthRequest.setRedirectURI("https://oauth.vk.com/blank.html");
	oAuthRequest.setDisplay("popup");
	oAuthRequest.setResponseType("token");

	System.out.println(oAuthRequest.buildQueryMessage());
    }

}
