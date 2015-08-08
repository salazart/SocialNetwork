package com.social.interfaces;

import java.util.List;

import com.social.models.Post;
import com.social.models.SocialNetwork;
import com.social.models.VkCity;
import com.social.models.VkUser;

public interface ISocialNetwork {
    String getAccessToken(String login, String pass);
    
    List<VkUser> usersById(List<String> uids, String accessToken);

    List<String> friendsById(String userId);

    List<Post> getWall(String userId);

    void postWall(Post post, SocialNetwork socialNetwork);
    
    List<VkCity> citiesById(List<String> id);
}
