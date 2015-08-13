package com.salazart;

import java.util.ArrayList;
import java.util.List;

import com.social.interfaces.ISocialNetwork;
import com.social.models.SocialNetwork;
import com.social.models.TypeSN;
import com.social.models.requests.VkUser;
import com.social.services.VkService;

public class UsersFromFb {

    public static void main(String[] args) {
	SocialNetwork socialNetwork = new SocialNetwork(TypeSN.VKONTAKTE, "", "");
	
	ISocialNetwork networkService = new VkService();

	List<String> uids = new ArrayList<String>();
	uids.add("135717579");
	uids.add("37837462");
	uids.add("78938531");
	List<VkUser> users = networkService.usersByIds(uids, socialNetwork);
	//System.out.println(users.size());
    }
}
