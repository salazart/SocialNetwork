package com.social.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.social.accesstoken.services.FbAccessToken;
import com.social.interfaces.ISocialNetworkService;
import com.social.models.Post;
import com.social.models.SocialNetwork;
import com.social.models.responses.FbResponse;
import com.social.utils.ParamDic;
import com.social.utils.RuleDic;
import com.social.utils.PropertyService;
import com.social.utils.UrlDic;

public class FbService implements ISocialNetworkService{
	private static final String APP_ID = "fbAppId";
	
	private static final Logger log = LogManager.getRootLogger();
	
	public String postToWall(SocialNetwork socialNetwork, Post post) {
		log.debug("=Start process posting to FB...=");
		String accessToken = generateAccessToken(socialNetwork, RuleDic.FB_PUBLISH_ACTION);
		log.debug("Access token: " + accessToken);
		
		MultiValueMap<String, String> headers = getHeaders(post, accessToken);
		log.debug("Headers: " + headers.toString());
		
		FbResponse fbResponse = sendRequest(post, headers);

		log.debug("=Finish process posting to FB...=");
		return getResponse(fbResponse);
	}

	private String getResponse(FbResponse fbResponse) {
		if (fbResponse != null && !fbResponse.isEmpty()){
			log.debug("Post id: " + fbResponse.getId());
			return fbResponse.getId();
		} else {
			log.error("Error posting");
			return "";
		}
	}
	
	/**
	 * Send request through Spring RestTemplate and get FbResponse object
	 * @param post - post object
	 * @param map - headers for post
	 * @return
	 */
	private FbResponse sendRequest(Post post, MultiValueMap<String, String> map) {
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate.postForObject(
				UrlDic.FB_GRAPH + post.getId() + UrlDic.FB_POST_WALL, 
				map, 
				FbResponse.class);
	}

	private MultiValueMap<String, String> getHeaders(Post post, String accessToken) {
		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add(ParamDic.ACCESS_TOKEN, accessToken);
		map.add(ParamDic.MESSAGE, post.getText());
		return map;
	}

	public String generateAccessToken(SocialNetwork socialNetwork, String typePermission) {
		String appId = PropertyService.getValueProperties(APP_ID);
		
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(UrlDic.FB_OAUTH_DIALOG)
				.queryParam(ParamDic.CLIENT_ID, appId)
				.queryParam(ParamDic.RESPONSE_TYPE,	ParamDic.TOKEN)
				.queryParam(ParamDic.SCOPE, typePermission)
				.queryParam(ParamDic.REDIRECT_URI, UrlDic.FB_REDIRECT_URL)
				.queryParam(ParamDic.DISPLAY, ParamDic.POPUP);
		
		String urlRequest = builder.build().encode().toUri().toString();
		log.debug("Access token request: " +  urlRequest);
		
		return new FbAccessToken(urlRequest).getAccessToken(socialNetwork);
	}
}
