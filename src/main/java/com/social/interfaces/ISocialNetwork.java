package com.social.interfaces;

import com.social.models.SocialNetwork;
import com.social.models.requests.Post;

public interface ISocialNetwork {
    String postWall(SocialNetwork socialNetwork, Post post);
    
    String generateAccessToken(SocialNetwork socialNetwork, String typePermission);
}
