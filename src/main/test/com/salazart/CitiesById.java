package com.salazart;

import java.util.ArrayList;
import java.util.List;

import com.social.interfaces.ISocialNetwork;
import com.social.models.requests.VkCity;
import com.social.services.VkService;

public class CitiesById {

    public static void main(String[] args) {
	List<String> ids = new ArrayList<String>();
	ids.add("2917");
	ids.add("1057");
	ids.add("1158");
	
	ISocialNetwork socialNetwork = new VkService();
	List<VkCity> vkCities = socialNetwork.citiesByIds(ids);
	System.out.println(vkCities.get(0).getTitle());
	System.out.println(vkCities.get(1).getTitle());
	System.out.println(vkCities.get(2).getTitle());
    }

}
