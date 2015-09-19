package com.social;

import com.social.models.SocialNetwork;
import com.social.models.requests.Post;
import com.social.services.FbService;
import com.social.services.OkService;
import com.social.services.VkService;

public class SocialNetworkFactory {
    public void postSocialNetwork(SocialNetwork socialNetwork, Post post) {
	switch (socialNetwork.getTypeSN()) {
	case VKONTAKTE:
	    new VkService().postWall(post, socialNetwork);
	    break;
	case FACEBOOK:
	    new FbService().postWall(post, socialNetwork);
	    break;
	case OK:
	    new OkService().postWall(post, socialNetwork);
	    break;
	default:
	    break;
	}
    }
}
