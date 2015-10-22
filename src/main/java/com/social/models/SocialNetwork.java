package com.social.models;
/**
 * SocialNetwork passes login, password and type social network to SocialNetworkFactory 
 * @author home
 *
 */
public class SocialNetwork {
	private SocialNetworkType type = null;
	private String login = "";
	private String pass = "";	
	
	/**
	 * Constructor with parameters
	 * @param type - type social network
	 * @param login - login or username
	 * @param pass - password
	 */
	public SocialNetwork(SocialNetworkType type, String login, String pass) {
		this.type = type;
		this.login = login;
		this.pass = pass;
	}

	public SocialNetworkType getType() {
		return type;
	}

	public String getLogin() {
		return login;
	}

	public String getPass() {
		return pass;
	}
}
