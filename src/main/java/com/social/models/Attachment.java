package com.social.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

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
