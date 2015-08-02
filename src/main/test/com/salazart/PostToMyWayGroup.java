package com.salazart;

import com.social.interfaces.ISocialNetwork;
import com.social.models.AccessToken;
import com.social.models.Post;
import com.social.services.VkService;

public class PostToMyWayGroup {
    private final static String login = "";
    private final static String pass = "";

    public static void main(String[] args) {
	AccessToken accessToken = new AccessToken();
	String textAT = accessToken.generateAccessToken(login, pass);
	
	Post post = new Post();
	post.setText("Hello MyWay group!");
	post.setId("-97270724");
	
	ISocialNetwork socialNetwork = new VkService();
	socialNetwork.postWall(post, textAT);
    }

}
