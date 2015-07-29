package com.social.models.responses;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import com.social.models.VkCity;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CitiesGet  extends JsonResponse{
    @JsonProperty("response")
	List<VkCity> cities = new ArrayList<VkCity>();

    public List<VkCity> getCities() {
        return cities;
    }

    public void setCities(List<VkCity> cities) {
        this.cities = cities;
    }

}
