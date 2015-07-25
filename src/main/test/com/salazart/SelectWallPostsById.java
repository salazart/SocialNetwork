package com.salazart;

import java.util.List;

import com.social.models.Post;
import com.social.services.VkService;

public class SelectWallPostsById {

	public static void main(String[] args) {
		VkService socialNetworkService = new VkService();
    	List<Post> posts = socialNetworkService.getWall("10262314");

    	for (int i = 0; i < posts.size(); i++) {
    		System.out.println(i + "\t" + posts.get(i).getId() + "\t" + posts.get(i).getText());
		}
	}

}
