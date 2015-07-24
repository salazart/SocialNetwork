package com.salazart;

import com.social.models.AccessToken;

public class CreateAccessTokenRequest {
    private static final String CLIENT_ID_APPLICATION = "4517745";

    public static void main(String[] args) {
	AccessToken oAuthRequest = new AccessToken("oauth.vk.com/authorize");
	oAuthRequest.setClientId(CLIENT_ID_APPLICATION);
	oAuthRequest.setScope("wall,offline");
	oAuthRequest.setRedirectURI("https://oauth.vk.com/blank.html");
	oAuthRequest.setDisplay("mobile");
	oAuthRequest.setResponseType("token");

	System.out.println(oAuthRequest.buildQueryMessage());
    }

}
