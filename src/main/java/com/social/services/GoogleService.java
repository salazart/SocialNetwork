package com.social.services;

import java.util.ArrayList;
import java.util.List;

import com.google.api.services.plusDomains.PlusDomains;
import com.google.api.services.plusDomains.model.Acl;
import com.google.api.services.plusDomains.model.Activity;
import com.google.api.services.plusDomains.model.PlusDomainsAclentryResource;
import com.social.interfaces.ISocialNetwork;
import com.social.models.SocialNetwork;
import com.social.models.requests.Post;
import com.social.models.requests.VkCity;
import com.social.models.requests.VkUser;

public class GoogleService implements ISocialNetwork {

    @Override
    public void generateAccessToken(SocialNetwork socialNetwork,
	    String typePermission) {
	// TODO Auto-generated method stub
	
    }

    @Override
    public List<VkUser> usersByIds(List<String> uids,
	    SocialNetwork socialNetwork) {
	// TODO Auto-generated method stub
	return null;
    }

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
    public void postWall(Post post, SocialNetwork socialNetwork) {
	
	String msg = "Happy Monday! #caseofthemondays";
	PlusDomainsAclentryResource resource = new PlusDomainsAclentryResource();
	resource.setType("domain");
	
	List<PlusDomainsAclentryResource> aclEntries =
		    new ArrayList<PlusDomainsAclentryResource>();
		aclEntries.add(resource);
		
	Acl acl = new Acl();
	acl.setItems(aclEntries);
	acl.setDomainRestricted(true);
	
	Activity activity = new Activity()
	    .setObject(new Activity.PlusDomainsObject().setOriginalContent(msg))
	    .setAccess(acl);
	//System.out.println(activity.toString());
	//activity = plusDomains.activities().insert("me", activity).execute();
	//PlusDomains plusDomains = new PlusDomains(transport, jsonFactory, httpRequestInitializer)
	
	/*RequestBuilder requestBuilder = new RequestBuilder("accounts.google.com/o/oauth2/auth");
	//requestBuilder.addParam("key", "AIzaSyBIxa2Q09Cvv3ZgZJxkfIvkvIODYPo2vWI");
	//requestBuilder.addParam("userId", "110934328784445676668");
	//requestBuilder.addParam("preview", "false");
	requestBuilder.addParam("client_id", "606521602639-6rt1ib1ms6qp4ml9lm5gdbjosoejrsjg.apps.googleusercontent.com");
	//requestBuilder.addParam("client_secret", "OXurOssBb-2V33w68MCn5bq6");
	requestBuilder.addParam("redirect_uris", UrlsDictionary.FB_REDIRECT_URL);
	//requestBuilder.addParam("auth_uri", "https://accounts.google.com/o/oauth2/auth");
	//requestBuilder.addParam("token_uri", "https://accounts.google.com/o/oauth2/token");
	
	System.out.println(requestBuilder.buildRequest());
	
	ConnectionService connectionService = new ConnectionService();//ConnectionService.POST_REQUEST_METHOD);
	String content = connectionService.createConnection(requestBuilder.buildRequest());
	System.out.println(content);*/
    }

    @Override
    public List<VkCity> citiesByIds(List<String> id) {
	// TODO Auto-generated method stub
	return null;
    }

}
