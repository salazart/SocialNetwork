package com.social.models.responses;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class VkResponse {
	
	@JsonProperty("response")
	private Response response;
	
	public Response getResponse(){
		return response;
	}
}
//{"response":{"post_id":32}}