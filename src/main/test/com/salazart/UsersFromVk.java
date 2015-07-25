package com.salazart;

import java.util.ArrayList;
import java.util.List;

import com.social.interfaces.ISocialNetwork;
import com.social.models.User;
import com.social.services.VkService;

public class UsersFromVk {

	public static void main(String[] args) {
    	ISocialNetwork socialNetwork = new VkService();
    	
    	List<String> uids = new ArrayList<String>();
    	uids.add("135717579");
    	uids.add("37837462");
    	uids.add("78938531");
    	List<User> users = socialNetwork.usersById(uids);
    	
    	System.out.println(users.size());
    	System.out.println("uid\tfirst_name\tlast_name\tbdate\tcity\tcountry\tmobile_phone\thome_phone");
    	for(int i = 0; i < users.size(); i++){
    		System.out.println(users.get(i).getId() + "\t"
    				+ users.get(i).getFirstName() + "\t"
    				+ users.get(i).getLastName() + "\t"
    				+ users.get(i).getBirstDay() + "\t"
    				+ users.get(i).getCity() + "\t"
    				+ users.get(i).getCountry() + "\t"
    				+ users.get(i).getMobilePhone() + "\t"
    				+ users.get(i).getHomeTown() + "\t"
    				+ users.get(i).getHomePhone() + "\t");
    	}
	}

}
