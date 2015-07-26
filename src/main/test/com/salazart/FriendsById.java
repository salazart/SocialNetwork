package com.salazart;

import java.util.List;

import com.social.models.User;
import com.social.services.VkService;

public class FriendsById {

    public static void main(String[] args) {
	VkService socialNetworkService = new VkService();
	List<String> uids = socialNetworkService.friendsById("107275348");
	System.out.println("Count users uids: " + uids.size());

	List<User> users = socialNetworkService.usersById(uids, null);

	System.out.println("Count users: " + users.size());
	System.out
		.println("uid\tfirst_name\tlast_name\tbdate\tcity\tcountry\tmobile_phone\thome_phone");
	
	for (int i = 0; i < users.size(); i++) {
	    System.out.println(users.get(i).toString());
	}
    }

}
