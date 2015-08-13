package com.social.models.requests;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class VkUser {
	
	@JsonProperty("uid")
	private int socialId;
	
	@JsonProperty("first_name")
	private String firstName;
	
	@JsonProperty("last_name")
	private String lastName;
	
	@JsonProperty("bdate")
	private String birstDay;
	
	@JsonProperty("city")
	private String city;

	@JsonProperty("country")
	private String country;
	
	@JsonProperty("mobile_phone")
	private String mobilePhone;

	@JsonProperty("home_phone")
	private String homePhone;
	
	@JsonProperty("home_town")
	private String homeTown;
	
	public String toString(){
	    StringBuilder builder = new StringBuilder();
	    builder.append(socialId + "\t");
	    builder.append(firstName + "\t");
	    builder.append(lastName + "\t");
	    builder.append(birstDay + "\t");
	    builder.append(country + "\t");
	    builder.append(city + "\t");
	    builder.append(homeTown + "\t");
	    builder.append(mobilePhone + "\t");
	    builder.append(homePhone);
	    return builder.toString();
	}
	
	public String getBirstDay() {
		return birstDay;
	}

	public void setBirstDay(String birstDay) {
		this.birstDay = birstDay;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getHomePhone() {
		return homePhone;
	}

	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	public String getHomeTown() {
		return homeTown;
	}

	public void setHomeTown(String homeTown) {
		this.homeTown = homeTown;
	}

	public int getId() {
		return socialId;
	}

	public void setId(int id) {
		this.socialId = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	

}
