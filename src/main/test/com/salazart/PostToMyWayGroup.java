package com.salazart;

import com.social.models.Post;
import com.social.models.SocialNetwork;
import com.social.models.TypeSN;
import com.social.services.SocialNetworkFactory;

public class PostToMyWayGroup {
	public static void main(String[] args) {
		
		SocialNetwork vkSocialNetwork = new SocialNetwork(TypeSN.VKONTAKTE, "", "");
		
		Post post = new Post();
		post.setText("Hello MyWay group!");
		post.setId("-97270724");

		//new SocialNetworkFactory().postSocialNetwork(vkSocialNetwork, post);

		SocialNetwork fbSocialNetwork = new SocialNetwork(TypeSN.FACEBOOK, "", "");
		post.setId("863375127077356");
		
		new SocialNetworkFactory().postSocialNetwork(fbSocialNetwork, post);
		
	}

}
