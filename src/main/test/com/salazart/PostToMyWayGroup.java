package com.salazart;

import com.social.SocialNetworkFactory;
import com.social.models.SocialNetwork;
import com.social.models.SocialNetworkType;
import com.social.models.requests.Post;
import com.social.utils.PropertyService;

public class PostToMyWayGroup {
	private static String login = PropertyService.getInstance().getValueProperties("okLogin");
	private static String pass = PropertyService.getInstance().getValueProperties("okPass");
	
	public static void main(String[] args) {

		Post post = new Post();
		post.setText("Hello MyWay group!");

		SocialNetwork vkSocialNetwork = new SocialNetwork(SocialNetworkType.VKONTAKTE, "", "");

		post.setId("-97270724");

		//new SocialNetworkFactory().postSocialNetwork(vkSocialNetwork, post);

		SocialNetwork fbSocialNetwork = new SocialNetwork(SocialNetworkType.FACEBOOK, "", "");
		post.setId("863375127077356");

		//new SocialNetworkFactory().postSocialNetwork(fbSocialNetwork, post);
		
		SocialNetwork okSocialNetwork = new SocialNetwork(SocialNetworkType.OK, login, pass);
		
		new SocialNetworkFactory().post(okSocialNetwork, post);

	}

}
