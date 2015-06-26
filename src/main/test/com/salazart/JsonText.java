package com.salazart;

import java.io.File;
import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import response.models.Response;

public class JsonText {

	public static void main(String[] args) {
		ObjectMapper mapper = new ObjectMapper();
    	Response response = new Response();
    	try {
    		response = mapper.readValue(new File("src/main/resources/UsersJsonResponse.txt"), Response.class);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	if(response.isErrorStatusResponse()){
    		System.out.println(response.getError().getErrorCode());
    	} else {
    		System.out.println(response.getElements().size());
    	}
	}

}
