package com.salazart;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.social.models.Attachment;
import com.social.models.Media;

import java.io.IOException;

public class AttachmentsTest {
	public static void main(String[] args) throws IOException {
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
