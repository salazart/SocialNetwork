package com.social.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.ObjectMapper;

import com.social.interfaces.ISocialNetwork;
import com.social.models.User;
import com.social.models.responses.FriendsGet;
import com.social.models.responses.UsersGet;
import com.social.oauth.RequestBuilder;

public class SocialNetworkService implements ISocialNetwork{
	public List<User> usersById(List<String> uids) {
		final int COUNT_UIDS = 200;
		
		String url = "https://api.vk.com/method/users.get";
		
		List<User> users = new ArrayList<User>();
		RequestBuilder requestBuilder = null;
		for(int i = 0; i < uids.size(); i += COUNT_UIDS){
			requestBuilder = new RequestBuilder(url);
			//requestBuilder.addParam("access_token", getAccessToken());
			requestBuilder.addParam("fields", "bdate,city,country,contacts");
			
			int j = i;
			while(j < uids.size() && j < i + COUNT_UIDS){
				requestBuilder.addParam("uids", String.valueOf(uids.get(j)));
				j++;
			}
			
			ConnectionService connectionService = new ConnectionService();
			String content = connectionService.createConnection(requestBuilder.buildRequest());

			UsersGet usersGet = new UsersGet();
			ObjectMapper mapper = new ObjectMapper();
			try {
				usersGet = mapper.readValue(content, UsersGet.class);
			} catch (IOException e) {
				e.printStackTrace();
			}

			users.addAll(usersGet.getUsers());
		}
		return users;
	}
	
	@Override
	public String getAccessToken() {
		String fileName = "src/main/resources/accessToken.txt";
		String content = null;
		try {
			content = new Scanner(new File(fileName)).useDelimiter("\\Z").next();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		if(content == null || content.isEmpty()){
			System.out.println("Not found access token");
			return "";
		} else {
			return content;
		}
	}

	public List<String> friendsById(String userId) {
		String url = "https://api.vk.com/method/friends.get";
		
		RequestBuilder requestBuilder = new RequestBuilder(url);
		//requestBuilder.addParam("access_token", getAccessToken());
		requestBuilder.addParam("user_id", userId);
		
		ConnectionService connectionService = new ConnectionService();
		String content = connectionService.createConnection(requestBuilder.buildRequest());
		
		ObjectMapper mapper = new ObjectMapper();
		FriendsGet friendsGet = new FriendsGet();
		try {
			friendsGet = mapper.readValue(content, FriendsGet.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return friendsGet.getUids();
	}
	
	public void getWall(String userId){
		String url = "https://api.vk.com/method/wall.get.xml";
		
		RequestBuilder requestBuilder = new RequestBuilder(url);
		//requestBuilder.addParam("access_token", getAccessToken());
		requestBuilder.addParam("owner_id", userId);
		requestBuilder.addParam("offset", "0");
		requestBuilder.addParam("count", "2");
		//requestBuilder.addParam("filter", "owner");
		//requestBuilder.addParam("version", "5.34");
		//requestBuilder.addParam("extended", "1");
		
		System.out.println(requestBuilder.buildRequest());
		ConnectionService connectionService = new ConnectionService();
		String content = connectionService.createConnection(requestBuilder.buildRequest());
		System.out.println(content);
		
		
		try {
			JsonFactory jfactory = new JsonFactory();
			JsonParser jParser = jfactory.createJsonParser(content);
			ObjectMapper mapper = new ObjectMapper();
			JsonNode input = mapper.readTree(jParser);
			
			System.out.println(input.size());
			JsonNode results = input.get("response");
			
			System.out.println(results.size());
			
		    //JsonNode results2 = results.get("id");
		    //System.out.println(fieldname.toString());
		    
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*ObjectMapper mapper = new ObjectMapper();
		WallGet wallGet = new WallGet();
		try {
			wallGet = mapper.readValue(content, WallGet.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(wallGet.getPosts().size());
		Post post = (Post) wallGet.getPosts().get(2);
		System.out.println(post.getId());*/
		//return wallGet.getPosts().size();
		
	}
}