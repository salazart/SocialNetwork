package com.social.interfaces;

import java.util.List;

import response.models.User;

public interface ISocialNetwork {
	List<User> usersById(List<String> uids);
	
	String getAccessToken();
}
