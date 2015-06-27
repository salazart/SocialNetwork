package com.social.models.responses;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import com.social.models.Post;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WallGet extends JsonResponse{
	
	@JsonProperty("response")
	List<Post> posts = new ArrayList<Post>();

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

}
