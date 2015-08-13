package com.social.models;
/**
 * SocialNetwork passes login, password and type social network to SocialNetworkFactory 
 * @author home
 *
 */
public class SocialNetwork {
	private TypeSN typeSN = null;
	private String login = "";
	private String pass = "";	
	
	/**
	 * Constructor with parameters
	 * @param typeSN - type social network
	 * @param login - login or username
	 * @param pass - password
	 */
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
