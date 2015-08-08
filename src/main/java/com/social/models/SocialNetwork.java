package com.social.models;

public class SocialNetwork {
	private TypeSN typeSN = null;
	private String login = "";
	private String pass = "";	
	
	public SocialNetwork(TypeSN typeSN, String login, String pass) {
		this.typeSN = typeSN;
		this.login = login;
		this.pass = pass;
	}

	public TypeSN getTypeSN() {
		return typeSN;
	}

	public String getLogin() {
		return login;
	}

	public String getPass() {
		return pass;
	}
}
