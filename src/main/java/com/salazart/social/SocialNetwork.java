package com.salazart.social;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;

public class SocialNetwork {

	public static void main(String[] args) {
		System.out.println("Hello Social Network world");
		OAuthClientRequest request = null;
		try {
			request = OAuthClientRequest
					   .authorizationLocation("https://oauth.vk.com/authorize")
					   .setClientId("4517745")
					   .setRedirectURI("https://oauth.vk.com/blank.html")
					   .setScope("audio")
					   .buildQueryMessage();
		} catch (OAuthSystemException e1) {
			e1.printStackTrace();
		}
		
		
		System.out.println(request.getLocationUri());
		
		String textUrl = "https://vk.com/";
		String textUrl2 = "https://oauth.vk.com/authorize?client_id=4517745&scope=audio&redirect_uri=https://oauth.vk.com/blank.html&display=popup&response_type=token";
		URL url;
		try {
			url = new URL(textUrl2);
			HttpsURLConnection con = (HttpsURLConnection)url.openConnection();
			System.out.println(con.getURL());
			BufferedReader br = 
					new BufferedReader(
						new InputStreamReader(con.getInputStream(),"utf-8"));
			 
				   String input;
			 
				   while ((input = br.readLine()) != null){
				      System.out.println(input);
				   }
				   br.close();			
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
