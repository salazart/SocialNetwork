package com.social.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

public class ConnectionService {
	private static final int MAX_CONNECTION = 5;
	private int counterConnection;
	
	public String createConnection(String link){
		URL url = null;
		try {
			url = new URL(link);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		HttpsURLConnection conn = null;
		while(conn == null && counterConnection <= MAX_CONNECTION){
			conn = getConnection(url);
			counterConnection++;
		}
		
		String content = "";
		if(conn != null){
			System.out.println("Reading from connection...");
			try {
				content = readInputStream(conn.getInputStream());
			} catch (IOException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		}
		return content;
	}
		
	private HttpsURLConnection getConnection(URL url){
			System.out.println("Wait connection to...");
			try {
				return (HttpsURLConnection) url.openConnection();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
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
				System.out.println("Connection is closed.");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return content;
	}
	
	private static String getStringFromInputStream(InputStream is) {
		 
		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();
 
		String line;
		try {
 
			br = new BufferedReader(new InputStreamReader(is));
			while ((line = br.readLine()) != null) {
				System.out.println(line);
				sb.append(line);
			}
 
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
 
		return sb.toString();
 
	}
}

	