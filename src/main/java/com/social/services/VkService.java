package com.social.services;

import java.util.ArrayList;
import java.util.List;

import com.social.interfaces.ISocialNetwork;
import com.social.models.AccessToken;
import com.social.models.Post;
import com.social.models.VkUser;
import com.social.models.responses.FriendsGet;
import com.social.models.responses.UsersGet;
import com.social.models.responses.WallGet;
import com.social.utils.ParametersDictionary;
import com.social.utils.UrlsDictionary;

public class VkService implements ISocialNetwork {
    private static final int COUNT_UIDS = 200;

    @Override
    public List<VkUser> usersById(List<String> uids, AccessToken accessToken) {

	List<VkUser> users = new ArrayList<VkUser>();
	for (int i = 0; i < uids.size(); i += COUNT_UIDS) {
	    String request = createRequest(uids, i, accessToken);

	    ConnectionService connectionService = new ConnectionService();
	    String content = connectionService.createConnection(request);
	    System.out.println(content);
	    ResponseParser jsonNodeParser = new ResponseParser();
	    UsersGet usersGet = jsonNodeParser.parseJson(content,
		    new UsersGet());

	    if (!usersGet.isErrorResponse()) {
		users.addAll(usersGet.getUsers());
	    } else {
		System.out.println(usersGet.getError().getErrorMsg());
		return users;
	    }
	}
	return users;
    }

    private String createRequest(List<String> uids, int i, AccessToken accessToken) {
	RequestBuilder requestBuilder = new RequestBuilder(
		UrlsDictionary.VK_USERS_GET);

	requestBuilder.addParam(ParametersDictionary.FIELDS, ParametersDictionary.BDATE,
		ParametersDictionary.CITY, ParametersDictionary.COUNTRY, ParametersDictionary.CONTACTS);

	int j = i;
	while (j < uids.size() && j < i + COUNT_UIDS) {
	    requestBuilder.addParam(ParametersDictionary.UIDS, uids.get(j));
	    j++;
	}
	
	if(accessToken != null && accessToken.isValidAccessToken()){
	    requestBuilder.addParam(ParametersDictionary.ACCESS_TOKEN, accessToken.getAccessToken());
	} 
	
	return requestBuilder.buildRequest();
    }

    public List<String> friendsById(String userId) {
	RequestBuilder requestBuilder = new RequestBuilder(UrlsDictionary.VK_FRIENDS_GET);
	requestBuilder.addParam(ParametersDictionary.USER_ID, userId);

	ConnectionService connectionService = new ConnectionService();
	String content = connectionService.createConnection(requestBuilder
		.buildRequest());

	ResponseParser jsonNodeParser = new ResponseParser();
	FriendsGet friendsGet = jsonNodeParser.parseJson(content,
		new FriendsGet());

	return friendsGet.getUids();
    }

    public List<Post> getWall(String userId) {
	String url = "api.vk.com/method/wall.get";

	RequestBuilder requestBuilder = new RequestBuilder(url);
	// requestBuilder.addParam(Parameters.ACCESS_TOKEN, getAccessToken());
	requestBuilder.addParam(ParametersDictionary.OWNER_ID, userId);
	requestBuilder.addParam(ParametersDictionary.OFFSET, "0");
	requestBuilder.addParam(ParametersDictionary.COUNT, "20");

	System.out.println(requestBuilder.buildRequest());
	ConnectionService connectionService = new ConnectionService();
	String content = connectionService.createConnection(requestBuilder
		.buildRequest());
	System.out.println(content);

	ResponseParser jsonNodeParser = new ResponseParser();
	WallGet wallGet = jsonNodeParser.parseJson(content, new WallGet());

	if (wallGet.isErrorResponse()) {
	    System.out.println(wallGet.getError().getErrorMsg());
	    return new ArrayList<Post>();
	} else {
	    return wallGet.getPosts();
	}
    }

    public void postWall(Post post) {
	String url = "api.vk.com/method/wall.post";

	RequestBuilder requestBuilder = new RequestBuilder(url);
	//requestBuilder.addParam(Parameters.ACCESS_TOKEN, getAccessToken());
	requestBuilder.addParam(ParametersDictionary.OWNER_ID, post.getId());
	requestBuilder.addParam(ParametersDictionary.MESSAGE, post.getText());

	System.out.println(requestBuilder.buildRequest());
	ConnectionService connectionService = new ConnectionService();
	String content = connectionService.createConnection(requestBuilder
		.buildRequest());
	System.out.println(content);

    }
}