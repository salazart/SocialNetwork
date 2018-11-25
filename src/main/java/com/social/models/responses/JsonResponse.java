package com.social.models.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.social.models.requests.Error;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JsonResponse{

	@JsonProperty("error")
	private Error error;
	
	public boolean isErrorResponse(){
		return error != null;
	}

	public Error getError() {
		return error;
	}

	public void setError(Error error) {
		this.error = error;
	}
}
