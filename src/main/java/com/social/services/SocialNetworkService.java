package com.social.services;

import java.util.ArrayList;
import java.util.List;

import com.social.interfaces.ISocialNetwork;
import com.social.models.Parameters;
import com.social.models.Post;
import com.social.models.User;
import com.social.models.responses.FriendsGet;
import com.social.models.responses.UsersGet;
import com.social.models.responses.WallGet;

public class SocialNetworkService implements ISocialNetwork {

    @Override
    public String getAccessToken() {
	return null;
    }

    public List<User> usersById(List<String> uids) {
	final int COUNT_UIDS = 200;

	String url = "api.vk.com/method/users.get";

	List<User> users = new ArrayList<User>();
	RequestBuilder requestBuilder = null;
	for (int i = 0; i < uids.size(); i += COUNT_UIDS) {
	    requestBuilder = new RequestBuilder(url);
	    requestBuilder.addParam(Parameters.ACCESS_TOKEN, getAccessToken());
	    requestBuilder.addParam(Parameters.FIELDS, Parameters.BDATE,
		    Parameters.CITY, Parameters.COUNTRY, Parameters.CONTACTS);

	    int j = i;
	    while (j < uids.size() && j < i + COUNT_UIDS) {
		requestBuilder.addParam(Parameters.UIDS, uids.get(j));
		j++;
	    }

	    ConnectionService connectionService = new ConnectionService();
	    String content = connectionService.createConnection(requestBuilder
		    .buildRequest());

	    ResponseParser jsonNodeParser = new ResponseParser();
	    UsersGet usersGet = jsonNodeParser.parseJson(content,
		    new UsersGet());

	    users.addAll(usersGet.getUsers());
	}
	return users;
    }

    public List<String> friendsById(String userId) {
	String url = "api.vk.com/method/friends.get";

	RequestBuilder requestBuilder = new RequestBuilder(url);
	requestBuilder.addParam(Parameters.ACCESS_TOKEN, getAccessToken());
	requestBuilder.addParam(Parameters.USER_ID, userId);

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
	requestBuilder.addParam(Parameters.ACCESS_TOKEN, getAccessToken());
	requestBuilder.addParam(Parameters.OWNER_ID, userId);
	requestBuilder.addParam(Parameters.OFFSET, "0");
	requestBuilder.addParam(Parameters.COUNT, "20");

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
	requestBuilder.addParam(Parameters.ACCESS_TOKEN, getAccessToken());
	requestBuilder.addParam(Parameters.OWNER_ID, post.getId());
	requestBuilder.addParam(Parameters.MESSAGE, post.getText());

	System.out.println(requestBuilder.buildRequest());
	ConnectionService connectionService = new ConnectionService();
	String content = connectionService.createConnection(requestBuilder
		.buildRequest());
	System.out.println(content);

    }
}