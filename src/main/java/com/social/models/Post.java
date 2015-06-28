package com.social.models;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Post extends Object{
	
	@JsonProperty("id")
	private int id;
	
	/*@JsonProperty("owner_id")
	private int ownerId;
	
	@JsonProperty("from_id")
	private int fromId;	
	
	@JsonProperty("date")
	private long date;
	
	@JsonProperty("text")
	private String text;
	
	@JsonProperty("reply_owner_id")
	private int replyOwnerId;
	
	@JsonProperty("reply_post_id")
	private int replyPostId;
	
	@JsonProperty("friends_only")
	private int friendsOnly;
	
	@JsonProperty("post_type")
	private String postType;*/
	
	public boolean isEmpty(){
		if(id == 0){
			return true;
		} else {
			return false;
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
