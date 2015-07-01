package com.salazart;

import java.util.List;

import com.social.models.User;
import com.social.services.SocialNetworkService;

public class SelectFriendsById {

    public static void main(String[] args) {
	SocialNetworkService socialNetworkService = new SocialNetworkService();
	List<String> uids = socialNetworkService.friendsById("107275348");
	System.out.println("Count users uids: " + uids.size());

	List<User> users = socialNetworkService.usersById(uids);

	System.out.println("Count users: " + users.size());
	System.out
		.println("uid\tfirst_name\tlast_name\tbdate\tcity\tcountry\tmobile_phone\thome_phone");
	for (int i = 0; i < users.size(); i++) {
	    System.out.println(users.get(i).getId() + "\t"
		    + users.get(i).getFirstName() + "\t"
		    + users.get(i).getLastName() + "\t"
		    + users.get(i).getBirstDay() + "\t"
		    + users.get(i).getCountry() + "\t" + users.get(i).getCity()
		    + "\t" + users.get(i).getHomeTown() + "\t"
		    + users.get(i).getMobilePhone() + "\t"
		    + users.get(i).getHomePhone() + "\t");
	}
    }

}
