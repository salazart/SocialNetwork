package com.social.services;

import java.util.List;

import com.social.interfaces.ISocialNetwork;
import com.social.models.AccessToken;
import com.social.models.Post;
import com.social.models.VkCity;
import com.social.models.VkUser;
import com.social.utils.ParametersDictionary;
import com.social.utils.PermissionDictionary;
import com.social.utils.UrlsDictionary;

public class FbService implements ISocialNetwork {

    @Override
    public List<String> friendsById(String userId) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public List<Post> getWall(String userId) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public void postWall(Post post, String accessToken) {
	RequestBuilder requestBuilder = 
		new RequestBuilder(UrlsDictionary.FB_GRAPH + post.getId() +UrlsDictionary.FB_POST_WALL);
	
	if (accessToken != null && !accessToken.isEmpty()) {
	    requestBuilder.addParam(ParametersDictionary.ACCESS_TOKEN, accessToken);
	}
	
	requestBuilder.addParam(ParametersDictionary.MESSAGE, post.getText());
	
	System.out.println(requestBuilder.buildRequest());
	ConnectionService connectionService = new ConnectionService(ConnectionService.POST_REQUEST_METHOD);
	String content = connectionService.createConnection(requestBuilder
		.buildRequest());
	System.out.println(content);
    }

    @Override
    public List<VkUser> usersById(List<String> uids, String accessToken) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public List<VkCity> citiesById(List<String> id) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public String getAccessToken() {
	final String FB_CLIENT_ID_APPLICATION = "700011900132723";
	AccessToken accessToken = new AccessToken(UrlsDictionary.FB_OAUTH_DIALOG);
	accessToken.setClientId(FB_CLIENT_ID_APPLICATION);
	accessToken.setResponseType(ParametersDictionary.TOKEN);
	accessToken.setScope(PermissionDictionary.FB_PUBLISH_ACTION);
	accessToken.setRedirectURI(UrlsDictionary.FB_REDIRECT_URL);
	accessToken.setDisplay(ParametersDictionary.POPUP);
	return accessToken.generateAccessToken("" , "");
    }

}
