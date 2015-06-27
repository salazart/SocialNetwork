package com.social.models.responses;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class FriendsGet extends JsonResponse{
	
	@JsonProperty("response")
	List<String> uids = new ArrayList<String>();

	public List<String> getUids() {
		return uids;
	}

	public void setUids(List<String> uids) {
		this.uids = uids;
	}

}
