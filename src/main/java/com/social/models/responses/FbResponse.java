package com.social.models.responses;

public class FbResponse {
	private String id;
	
	public boolean isEmpty(){
		return (id == null || id.isEmpty());
	}
	
	public String getId() {
		return id;
	}
}
