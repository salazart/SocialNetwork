package com.salazart;

import java.util.ArrayList;
import java.util.List;

import com.social.interfaces.ISocialNetwork;
import com.social.models.User;
import com.social.services.FbService;

public class UsersFromFb {

    public static void main(String[] args) {
	ISocialNetwork socialNetwork = new FbService();

	List<String> uids = new ArrayList<String>();
	uids.add("135717579");
	uids.add("37837462");
	uids.add("78938531");
	List<User> users = socialNetwork.usersById(uids, null);
	//System.out.println(users.size());
    }
}
