package com.social.models.responses;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Response {
	
	@JsonProperty("post_id")
	private int postId;
	
	public int getPostId(){
		return postId;
	}
}
