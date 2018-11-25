package com.salazart;

import com.social.utils.ParametersDictionary;
import com.social.utils.PermissionDictionary;
import com.social.utils.PropertyService;
import com.social.utils.UrlsDictionary;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.util.UriComponentsBuilder;

import com.social.utils.ParametersDictionary;
import com.social.utils.PermissionDictionary;
import com.social.utils.PropertyService;
import com.social.utils.UrlsDictionary;
import org.springframework.boot.autoconfigure.SpringBootApplication;

public class AccessTokenRequest {
	private static final String APP_ID = "okAppId";
	private static final Logger log = LogManager.getRootLogger();
	public static void main(String[] args) {
		//SpringApplication.run(AccessTokenRequest.class);
		String appId = PropertyService.getValueProperties(APP_ID);
		
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(UrlsDictionary.OK_OAUTH_DIALOG)
				.queryParam(ParametersDictionary.CLIENT_ID, appId)
				.queryParam(ParametersDictionary.RESPONSE_TYPE, ParametersDictionary.TOKEN)
				.queryParam(ParametersDictionary.SCOPE, PermissionDictionary.OK_GROUP_CONTENT)
				.queryParam(ParametersDictionary.REDIRECT_URI, UrlsDictionary.OK_REDIRECT_URL);
		String urlRequest = builder.build().encode().toUri().toString();
				
		log.info(urlRequest);
	}

	public void run(String... args) throws Exception {
		String appId = PropertyService.getValueProperties(APP_ID);
		
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(UrlsDictionary.OK_OAUTH_DIALOG)
				.queryParam(ParametersDictionary.CLIENT_ID, appId)
				.queryParam(ParametersDictionary.RESPONSE_TYPE, ParametersDictionary.TOKEN)
				.queryParam(ParametersDictionary.SCOPE, PermissionDictionary.OK_GROUP_CONTENT)
				.queryParam(ParametersDictionary.REDIRECT_URI, UrlsDictionary.OK_REDIRECT_URL);
		String urlRequest = builder.build().encode().toUri().toString();
				
		log.info(urlRequest);
	}

}
