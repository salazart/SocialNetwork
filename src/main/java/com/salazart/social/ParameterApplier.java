package com.salazart.social;

import java.util.Map;

import org.apache.oltu.oauth2.common.message.OAuthMessage;
import org.apache.oltu.oauth2.common.utils.OAuthUtils;

public class ParameterApplier {
	public String applyParameters(String url, Map<String, String> params) {
        if (url != null) {
            boolean containsQuestionMark = url.contains("?");
            StringBuffer parameterUrl = new StringBuffer(url);
            //StringBuffer query = new StringBuffer(OAuthUtils.format(params.entrySet(), "UTF-8"));
            for(int i = 0; i < params.size(); i++){
            	if(!params.get(i).isEmpty()){
            		if (containsQuestionMark) {
            			//parameterUrl.append("&").append(params.);
                    } else {
                    	//parameterUrl.append("?").append(query);
                    }
            	}
            }
                if (containsQuestionMark) {
                    //url.append("&").append(query);
                } else {
                    //url.append("?").append(query);
                }
            //message.setLocationUri(url.toString());
        }
		return new String();
	}
}
