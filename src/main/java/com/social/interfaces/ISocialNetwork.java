package com.social.interfaces;

import java.util.List;

import com.social.models.User;

public interface ISocialNetwork {
	List<User> usersById(List<String> uids);
	
	List<String> friendsById(String userId);
	
	String getAccessToken();
}
