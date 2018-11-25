package com.social.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Place {
	private String lat = "";
	private String lng = "";
	
	@JsonProperty("place_name")
	private String placeName = "";
	
	private String category = "";

	@JsonProperty("city_town_id")
	private String cityTownId = "";
	
	private String city = "";

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public String getPlaceName() {
		return placeName;
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getCityTownId() {
		return cityTownId;
	}

	public void setCityTownId(String cityTownId) {
		this.cityTownId = cityTownId;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
}
