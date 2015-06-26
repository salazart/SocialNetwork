package com.social.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import org.codehaus.jackson.map.ObjectMapper;

import response.models.Response;
import response.models.User;

import com.social.interfaces.ISocialNetwork;
import com.social.oauth.RequestBuilder;

public class SocialNetworkService implements ISocialNetwork{
	public List<User> usersById(List<String> uids) {
		
		String url = "https://api.vk.com/method/users.get";
		
		RequestBuilder requestBuilder = new RequestBuilder(url);
		requestBuilder.addParam("access_token", getAccessToken());
		requestBuilder.addParam("fields", "bdate");
		requestBuilder.addParam("fields", "city");
		requestBuilder.addParam("fields", "country");
		requestBuilder.addParam("fields", "contacts");
		
		for(int i = 0; i < uids.size(); i++){
			requestBuilder.addParam("uids", String.valueOf(uids.get(i)));
		}
		
		ConnectionService connectionService = new ConnectionService();
		System.out.println(requestBuilder.buildRequest());
		String content = connectionService.createConnection(requestBuilder.buildRequest());
		System.out.println(content);
		
		ObjectMapper mapper = new ObjectMapper();
		Response response = new Response();
		try {
			response = mapper.readValue(content, Response.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return (List<User>) response.getElements();
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

	public void friendsById(String userId) {
		String url = "https://api.vk.com/method/friends.get";
		RequestBuilder requestBuilder = new RequestBuilder(url);
		requestBuilder.addParam("access_token", getAccessToken());
		requestBuilder.addParam("user_id", userId);
		
		ConnectionService connectionService = new ConnectionService();
		String content = connectionService.createConnection(requestBuilder.buildRequest());
		
		ObjectMapper mapper = new ObjectMapper();
		Response response = new Response();
		try {
			response = mapper.readValue(content, Response.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
}