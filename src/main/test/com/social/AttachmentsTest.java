package com.social;

import static org.junit.Assert.*;

import java.io.IOException;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;

import com.social.models.Attachment;
import com.social.models.Media;

public class AttachmentsTest {
	
	@Test
	public void test() {
		Attachment attachment = createAttachment();
		
		ObjectMapper mapper = new ObjectMapper();
		String attachmentsText = "";
		try {
			attachmentsText = mapper.writeValueAsString(attachment);
		} catch (IOException e) {
			attachmentsText = "";
		}
		
		assertTrue(attachmentsText.equals("{\"media\":[{\"type\":\"text\",\"text\":\"hello\"}]}"));
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
