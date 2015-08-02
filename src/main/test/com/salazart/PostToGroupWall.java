package com.salazart;

import com.social.interfaces.ISocialNetwork;
import com.social.models.Post;
import com.social.services.FbService;

public class PostToGroupWall {
    private static final String groupId = "863375127077356";
    public static void main(String[] args) {
	String accessToken = "CAAJ8qDilXXMBAJ61kGJNM6fv3JgGrZCgVdIw5laR9ZBZBHMwQoErmYninXfdAyHYRJ756Gz3mX6MSw4M0RZBwMGhf0LiZCTTEFgzhs5hqzxBc4ZBZAMi5leszvAFBKxGOT6GdjassCkQc8uMG9g0qyGc9mDCJcWkT0SJy6Oj9N76Sw5HRywPDxSDkaqqZCeRR3gZA5iWEX08FVukq4ubwFfUF";

	Post post = new Post();
	post.setText("Hello");
	post.setId(groupId);

	ISocialNetwork socialNetwork = new FbService();
	socialNetwork.postWall(post, accessToken);
    }
}
