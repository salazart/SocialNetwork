package com.social.interfaces;

import java.util.List;

import com.social.models.AccessToken;
import com.social.models.Post;
import com.social.models.VkUser;

public interface ISocialNetwork {
    List<VkUser> usersById(List<String> uids, AccessToken accessToken);

    List<String> friendsById(String userId);

    List<Post> getWall(String userId);

    void postWall(Post post);
}
