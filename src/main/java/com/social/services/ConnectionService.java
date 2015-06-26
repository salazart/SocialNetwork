package com.social.services;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

public class ConnectionService {
	
	public String createConnection(String url){
		String content = "";
		try {
			HttpsURLConnection con = (HttpsURLConnection)new URL(url).openConnection();
			if(con != null){
				content = readInputStream(con.getInputStream());
			} else {
				System.out.println("Error connection to URL: " + url);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return content;
	}
	
	private String readInputStream(InputStream is){
		String content = "";
		Scanner s = new Scanner(is).useDelimiter("\\A");
		while(s.hasNext()){
			content = content + s.next();
		}
		if(is != null){
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return content;
	}
}

	