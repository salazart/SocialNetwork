package com.social.services;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

public class ConnectionService {
    public static final String GET_REQUEST_METHOD = "GET";
    public static final String POST_REQUEST_METHOD = "POST";
    private static final String PROTOCOL = "https://";
    private static final String SPACE_SYMBOL = "%20";
    private static final int MAX_CONNECTION = 5;
    private int counterConnection;
    private String methodRequest = "";
    
    public ConnectionService(){
	
    }
    
    public ConnectionService(String methodRequest) {
	this.methodRequest = methodRequest;
    }

    public String createConnection(String link) {
	URL url = modifyUrl(link);

	HttpsURLConnection conn = tryGetConnection(url,MAX_CONNECTION);

	if(!methodRequest.isEmpty()){
	    try {
		conn.setRequestMethod(methodRequest);
	    } catch (ProtocolException e) {
		System.out.println(e.getMessage());
	    }
	}
	
	return readContent(conn);
    }

    private String readContent(HttpsURLConnection conn) {
	String content = "";
	if (conn != null) {
	    System.out.println("Reading from connection...");
	    try {
		content = readInputStream(conn.getInputStream());
	    } catch (IOException e) {
		System.out.println(e.getMessage());
	    }
	}
	return content;
    }

    private HttpsURLConnection tryGetConnection(URL url, int countTry) {
	HttpsURLConnection conn = null;
	while (conn == null && counterConnection <= countTry) {
	    conn = getConnection(url);
	    counterConnection++;
	}
	return conn;
    }

    private URL modifyUrl(String link) {
	URL url = null;
	try {
	    link = link.replace(" ", SPACE_SYMBOL);
	    url = new URL(PROTOCOL + link);
	} catch (MalformedURLException e) {
	    e.printStackTrace();
	}
	return url;
    }

    private HttpsURLConnection getConnection(URL url) {
	System.out.println("Wait connection to...");
	try {
	    return (HttpsURLConnection) url.openConnection();
	} catch (IOException e) {
	    System.out.println(e.getMessage());
	    return null;
	}
    }

    private String readInputStream(InputStream is) {
	String content = "";
	Scanner s = new Scanner(is).useDelimiter("\\A");
	while (s.hasNext()) {
	    content = content + s.next();
	}
	if (is != null) {
	    try {
		is.close();
		System.out.println("Connection is closed.");
	    } catch (IOException e) {
		System.out.println(e.getMessage());
		return content;
	    }
	}
	return content;
    }
}
