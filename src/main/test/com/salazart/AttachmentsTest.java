package com.salazart;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.social.models.Attachment;
import com.social.models.Media;

public class AttachmentsTest {
	public static void main(String[] args) throws JsonGenerationException, JsonMappingException, IOException {
		Attachment attachment = createAttachment();
		
		ObjectMapper mapper = new ObjectMapper();
		String attachmentsText = mapper.writeValueAsString(attachment);
		System.out.println(attachmentsText);
	}
	
	private static Attachment createAttachment() {
		Media media = new Media();
		media.setType("text");
		media.setText("hello");
		
		Attachment attachment = new Attachment();
		attachment.getMedias().add(media);
		
		return attachment;
	}
}
