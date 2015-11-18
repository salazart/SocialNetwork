package com.social.accesstoken.models;

public class AuthorizeEntity {
	private String url;
	private String typeAutorizeForm = "";
	private String emailField = "";
	private String passField = "";
	
	public AuthorizeEntity(String url, String typeAutorizeForm, String emailField, String passField) {
		setUrl(url);
		setTypeAutorizeForm(typeAutorizeForm);
		setEmailField(emailField);
		setPassField(passField);
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTypeAutorizeForm() {
		return typeAutorizeForm;
	}

	public void setTypeAutorizeForm(String typeAutorizeForm) {
		this.typeAutorizeForm = typeAutorizeForm;
	}

	public String getEmailField() {
		return emailField;
	}

	public void setEmailField(String emailField) {
		this.emailField = emailField;
	}

	public String getPassField() {
		return passField;
	}

	public void setPassField(String passField) {
		this.passField = passField;
	}
}
