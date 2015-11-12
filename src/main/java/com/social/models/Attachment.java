package com.social.models;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class Attachment {
	@JsonProperty("media")
	private List<Media> medias = new ArrayList<Media>();
	//private Place place = new Place();

	public List<Media> getMedias() {
		return medias;
	}

	public void setMedias(List<Media> medias) {
		this.medias = medias;
	}

	//public Place getPlace() {
		//return place;
	//}

	//public void setPlace(Place place) {
	//	this.place = place;
	//}

}
