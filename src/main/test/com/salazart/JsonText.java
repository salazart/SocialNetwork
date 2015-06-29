package com.salazart;

import java.io.File;
import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.social.models.responses.JsonResponse;
import com.social.models.responses.UsersGet;

public class JsonText {

	public static void main(String[] args) {
		ObjectMapper mapper = new ObjectMapper();
    	UsersGet usersGet = new UsersGet();
    	try {
    		usersGet = mapper.readValue(new File("src/main/resources/UsersJsonResponse.txt"), UsersGet.class);
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
    	
    	if(usersGet.isErrorResponse()){
    		System.out.println(usersGet.getError().getErrorCode());
    	} else {
    		System.out.println(usersGet.getUsers().size());
    	}
	}

}
