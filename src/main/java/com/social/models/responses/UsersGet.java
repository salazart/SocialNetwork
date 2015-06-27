package com.social.models.responses;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import com.social.models.User;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UsersGet extends JsonResponse{
	
	@JsonProperty("response")
	List<User> users = new ArrayList<User>();
	
	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
}
