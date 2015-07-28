package com.social.models.responses;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import com.social.models.VkUser;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UsersGet extends JsonResponse{
	
	@JsonProperty("response")
	List<VkUser> users = new ArrayList<VkUser>();

	public List<VkUser> getUsers() {
		return users;
	}

	public void setUsers(List<VkUser> users) {
		this.users = users;
	}

}
