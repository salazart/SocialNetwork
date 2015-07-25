package com.salazart;

import com.social.models.Post;
import com.social.services.VkService;

public class PostToMyWayGroup {

    public static void main(String[] args) {
	Post post = new Post();
	post.setText("Hello MyWay group!");
	post.setId(-97270724);

	VkService socialNetworkService = new VkService();
	socialNetworkService.postWall(post);
    }

}
