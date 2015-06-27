package com.social;

import com.social.services.SocialNetworkService;

public class SocialNetwork {

    public static void main(String[] args) {
    	System.out.println("Hello Social Network world");
    	
    	SocialNetworkService socialNetworkService = new SocialNetworkService();
    	socialNetworkService.getWall("10262314");
    	
    }
}
