package com.salazart;

import java.io.IOException;

import org.codehaus.jackson.map.ObjectMapper;

import com.social.models.Attachment;
import com.social.models.Media;

public class GenerateAttachment {

	public static void main(String[] args) {
		Media media = new Media();
		media.setType("text");
		media.setText("hello");
		
		Attachment attachment = new Attachment();
		attachment.getMedias().add(media);
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			String jsonText = mapper.writeValueAsString(attachment);
			System.out.println(jsonText);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
