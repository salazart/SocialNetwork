package com.salazart;

import com.social.models.SocialNetwork;
import com.social.models.TypeSN;
import com.social.services.GoogleService;

public class PostToGooglePlus {

    public static void main(String[] args) {
	SocialNetwork socialNetwork = new SocialNetwork(TypeSN.GOOGLE, "", "");
	
	GoogleService googleService = new GoogleService();
	googleService.postWall(null, socialNetwork);
    }

}
