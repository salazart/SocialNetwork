package com.social.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.apache.commons.lang.StringUtils;

public class ConnectionService {
	public static final String GET_REQUEST_METHOD = "GET";
	public static final String POST_REQUEST_METHOD = "POST";
	private static final String PROTOCOL = "https://";
	private static final String SPACE_SYMBOL = "%20";
	private static final int MAX_CONNECTION = 5;
	private int counterConnection;
	private String methodRequest = "";

	public ConnectionService() {

	}

	public ConnectionService(String methodRequest) {
		this.methodRequest = methodRequest;
	}

	public String createConnection(String link) {
		URL url = modifyUrl(link);
		
		HttpsURLConnection conn = tryGetConnection(url, MAX_CONNECTION);

		if (!methodRequest.isEmpty()) {
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
				BufferedReader in = new BufferedReader(new InputStreamReader(
						conn.getInputStream(), "UTF-8"));

				String lineContent = "";
				while ((lineContent = in.readLine()) != null) {
					content += lineContent;
				}

				if (in != null) {
					in.close();
				}

				System.out.println("Connection is closed.");
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
		if(!StringUtils.startsWith(link, "http")){
			link = PROTOCOL + link;
		}
		URL url = null;
		try {
			link = link.replace(" ", SPACE_SYMBOL);
			url = new URL(link);
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
}
