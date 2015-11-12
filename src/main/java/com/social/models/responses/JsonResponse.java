package com.social.models.responses;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

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
