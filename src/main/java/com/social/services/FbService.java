package com.social.services;

import java.util.ArrayList;
import java.util.List;

import com.social.interfaces.ISocialNetwork;
import com.social.models.Post;
import com.social.models.User;

public class FbService implements ISocialNetwork{

	public List<User> usersById(List<String> uids) {
		List<User> users = new ArrayList<User>();
		
		return users;
	}

	@Override
	public List<String> friendsById(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAccessToken() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Post> getWall(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void postWall(Post post) {
		// TODO Auto-generated method stub
		
	}

}
