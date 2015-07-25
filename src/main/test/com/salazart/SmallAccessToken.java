package com.salazart;

import com.social.models.AccessToken;

public class SmallAccessToken {
	private final static String login = "";
	private final static String pass = "";

	public static void main(String[] args) {

		AccessToken accessToken = new AccessToken();
		String accessTokenOut = accessToken.generateAccessToken(login, pass);

		if (accessTokenOut == null) {
			System.out.println("Access token is null");
		} else {
			System.out.println(accessTokenOut);
		}
	}

}
