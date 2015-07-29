package com.social.services;

import java.util.ArrayList;
import java.util.List;

import com.social.interfaces.ISocialNetwork;
import com.social.models.AccessToken;
import com.social.models.Post;
import com.social.models.VkCity;
import com.social.models.VkUser;

public class FbService implements ISocialNetwork{

	@Override
	public List<String> friendsById(String userId) {
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

	@Override
	public List<VkUser> usersById(List<String> uids, String accessToken) {
	    // TODO Auto-generated method stub
	    return null;
	}

	@Override
	public List<VkCity> citiesById(List<String> id) {
	    // TODO Auto-generated method stub
	    return null;
	}

}
