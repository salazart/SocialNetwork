package com.social;

import com.social.models.Post;
import com.social.services.SocialNetworkService;


public class SocialNetwork {

    public static void main(String[] args) {
    	System.out.println("Hello Social Network world");
    	
    	Post post = new Post();
    	post.setText("Hello_Inna!!!");
    	post.setId(39046828);
    	
    	SocialNetworkService socialNetworkService = new SocialNetworkService();
    	socialNetworkService.postWall(post);
    	
    	
    }
}
