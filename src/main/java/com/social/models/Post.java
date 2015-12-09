package com.social.models;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Post extends Object {

	@JsonProperty("id")
	private String id = "";

	@JsonProperty("owner_id")
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
	private String postType;

	public int getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}

	public int getFromId() {
		return fromId;
	}

	public void setFromId(int fromId) {
		this.fromId = fromId;
	}

	public long getDate() {
		return date;
	}

	public void setDate(long date) {
		this.date = date;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getReplyOwnerId() {
		return replyOwnerId;
	}

	public void setReplyOwnerId(int replyOwnerId) {
		this.replyOwnerId = replyOwnerId;
	}

	public int getReplyPostId() {
		return replyPostId;
	}

	public void setReplyPostId(int replyPostId) {
		this.replyPostId = replyPostId;
	}

	public int getFriendsOnly() {
		return friendsOnly;
	}

	public void setFriendsOnly(int friendsOnly) {
		this.friendsOnly = friendsOnly;
	}

	public String getPostType() {
		return postType;
	}

	public void setPostType(String postType) {
		this.postType = postType;
	}

	public boolean isEmpty() {
		if (id.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
