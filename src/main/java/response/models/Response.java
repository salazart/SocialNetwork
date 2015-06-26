package response.models;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;


public class Response{
	
	@JsonProperty("response")
	List<User> elements = new ArrayList<User>();
	
	public List<User> getElements() {
		return elements;
	}

	public void setElements(List<User> elements) {
		this.elements = elements;
	}

	@JsonProperty("error")
	private Error error;
	
	public boolean isErrorStatusResponse(){
		return error != null;
	}

	public Error getError() {
		return error;
	}

	public void setError(Error error) {
		this.error = error;
	}
}
