package com.salazart;

import java.util.ArrayList;
import java.util.List;

import com.social.interfaces.ISocialNetwork;
import com.social.models.AccessToken;
import com.social.models.VkUser;
import com.social.services.VkService;

public class UsersFromVk {
    private final static String login = "";
    private final static String pass = "";

    public static void main(String[] args) {
	ISocialNetwork socialNetwork = new VkService();

	AccessToken accessToken = new AccessToken();
	accessToken.generateAccessToken(login, pass);

	List<String> uids = new ArrayList<String>();
	uids.add("135717579");
	uids.add("37837462");
	uids.add("78938531");
	List<VkUser> users = socialNetwork.usersById(uids, accessToken);

	System.out.println(users.size());
	System.out.println("uid\tfirst_name\tlast_name\tbdate\tcountry"
		+ "\tcity\thome_town\tmobile_phone\thome_phone");

	for (int i = 0; i < users.size(); i++) {
	    System.out.println(users.get(i).toString());
	}
    }

}
