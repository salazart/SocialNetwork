package com.social.interfaces;

import com.social.models.Post;
import com.social.models.SocialNetwork;

public interface ISocialNetworkService {
	
    String postToWall(SocialNetwork socialNetwork, Post post);
    
    String generateAccessToken(SocialNetwork socialNetwork, String typePermission);
}
