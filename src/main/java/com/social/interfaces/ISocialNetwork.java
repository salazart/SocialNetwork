package com.social.interfaces;

import com.social.models.SocialNetwork;
import com.social.models.requests.Post;

public interface ISocialNetwork {
    void generateAccessToken(SocialNetwork socialNetwork, String typePermission);
    
    void postWall(Post post, SocialNetwork socialNetwork);
}
