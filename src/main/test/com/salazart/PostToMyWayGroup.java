package com.salazart;

import com.social.SocialNetworkFactory;
import com.social.models.SocialNetwork;
import com.social.models.TypeSN;
import com.social.models.requests.Post;

public class PostToMyWayGroup {
    public static void main(String[] args) {

    Post post = new Post();
    post.setText("Hello MyWay group!");
    	
	SocialNetwork vkSocialNetwork = new SocialNetwork(TypeSN.VKONTAKTE,	"", "");

	post.setId("-97270724");

	new SocialNetworkFactory().postSocialNetwork(vkSocialNetwork, post);

	SocialNetwork fbSocialNetwork = new SocialNetwork(TypeSN.FACEBOOK, "", "");
	post.setId("863375127077356");

	//new SocialNetworkFactory().postSocialNetwork(fbSocialNetwork, post);

    }

}
