package com.social.models;

public class Media {
	public static final String TYPE_TEXT = "text";
	
	private String type;
	private String text;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
