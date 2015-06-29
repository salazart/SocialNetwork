package com.social;

import com.social.models.Post;
import com.social.services.SocialNetworkService;


public class SocialNetwork {

    public static void main(String[] args) {
    	System.out.println("Hello Social Network world");
    	
    	Post post = new Post();
    	post.setText("Hello");
    	post.setId(10262314);
    	
    	SocialNetworkService socialNetworkService = new SocialNetworkService();
    	socialNetworkService.postWall(post);
    	
    	
    }
}
