package com.social.interfaces;

import java.util.List;

import com.social.models.SocialNetwork;
import com.social.models.requests.Post;
import com.social.models.requests.VkCity;
import com.social.models.requests.VkUser;

public interface ISocialNetwork {
    void generateAccessToken(SocialNetwork socialNetwork, String typePermission);
    
    List<VkUser> usersByIds(List<String> uids, SocialNetwork socialNetwork);

    List<String> friendsById(String userId);

    List<Post> getWall(String userId);

    void postWall(Post post, SocialNetwork socialNetwork);
    
    List<VkCity> citiesByIds(List<String> id);
}
