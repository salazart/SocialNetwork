package com.social.interfaces;

import com.social.models.SocialNetwork;
import com.social.models.requests.Post;

public interface ISocialNetworkService {
	
    String postToWall(SocialNetwork socialNetwork, Post post);
    
    String generateAccessToken(SocialNetwork socialNetwork, String typePermission);
}
