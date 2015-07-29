package com.social.interfaces;

import java.util.List;

import com.social.models.Post;
import com.social.models.VkCity;
import com.social.models.VkUser;

public interface ISocialNetwork {
    List<VkUser> usersById(List<String> uids, String accessToken);

    List<String> friendsById(String userId);

    List<Post> getWall(String userId);

    void postWall(Post post);
    
    List<VkCity> citiesById(List<String> id);
}
