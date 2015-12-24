package com.social.models.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class VkResponse {
	
	@JsonProperty("response")
	private Response response;
	
	public Response getResponse(){
		return response;
	}
	
	public void setResponse(Response response){
		this.response = response;
	}
}
