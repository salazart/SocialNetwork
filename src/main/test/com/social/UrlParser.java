package com.social;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

public class UrlParser {

	public static void main(String[] args) {
		//String urlText = "https://oauth.vk.com/blank.html?access_token=75381fc9f73336281ed1a2de3fa9f617601600dbb24ee2cfce9ef764142ca9c7c7df3d02d9a3f2431bab7&expires_in=86400&user_id=10262314";
		String urlText = "https://oauth.vk.com/blank.html#access_token=856ac907f0d5d56dce46498d58ca32beca6a44e157a782c763a4514fc9a4134a7b3d8989e5814ce3e9bb6&expires_in=86400&user_id=10262314";
		URI url = null;
		try {
			url = new URI(urlText);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		
		
		UriComponents components = UriComponentsBuilder.fromUri(url).build();
		String myParam = components.getQueryParams().getFirst("access_token");
		System.out.println(myParam);
		
	}
}
