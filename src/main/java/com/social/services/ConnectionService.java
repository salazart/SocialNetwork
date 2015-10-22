package com.social.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;

import javax.net.ssl.HttpsURLConnection;

import org.apache.commons.lang.StringUtils;

public class ConnectionService {
	private static final String UTF_8_ENCODING = "UTF-8";
	public static final String GET_REQUEST_METHOD = "GET";
	public static final String POST_REQUEST_METHOD = "POST";
	private static final String HTTPS = "https";
	private static final String HTTP = "http";
	private static final String SPACE_SYMBOL = "%20";
	private String methodRequest;

	public ConnectionService() {
		this.methodRequest = GET_REQUEST_METHOD;
	}

	public ConnectionService(String methodRequest) {
		this.methodRequest = methodRequest;
	}

	public String createConnection(String link) {
		try {
			return getContent(link);
		} catch (IOException e) {
			System.out.println(e);
			return "";
		}
	}

	private String getContent(String link) throws IOException, ProtocolException {
		String content = "";

		if (link.startsWith(HTTPS)) {
			URL url = modifyUrl(link);
			HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
			conn.setRequestMethod(methodRequest);
			content = readContent(conn.getInputStream());
		} else if (link.startsWith(HTTP)) {
			URL url = modifyUrl(link);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod(methodRequest);
			content = readContent(conn.getInputStream());
		}

		return content;
	}

	private String readContent(InputStream inputStream) throws IOException{
		String content = "";
		BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, UTF_8_ENCODING));

		String lineContent = "";
		while ((lineContent = in.readLine()) != null) {
			content += lineContent;
		}

		if (in != null) {
			in.close();
		}
		return content;
	}

	private URL modifyUrl(String link) throws MalformedURLException {
		if (!StringUtils.startsWith(link, "http")) {
			link = HTTPS + link;
		}

		link = link.replace(" ", SPACE_SYMBOL);
		return new URL(link);
	}
}
