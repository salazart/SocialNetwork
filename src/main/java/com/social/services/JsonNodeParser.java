package com.social.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.ObjectMapper;

import com.social.models.Post;

public class JsonNodeParser{
	private static final String RESPONSE = "response";
	public List<Post> parseJson(String content){
		List<Post> posts = new ArrayList<Post>();
		try {
			JsonFactory jfactory = new JsonFactory();
			JsonParser jParser = jfactory.createJsonParser(content);
			ObjectMapper mapper = new ObjectMapper();
			JsonNode node = mapper.readTree(jParser);
			JsonNode response = node.get(RESPONSE);
			posts = readPosts(response);
		} catch (IOException e1) {
			System.out.println("Error");
		};
		return posts;
	}
	
	private List<Post> readPosts(JsonNode jsonNode){
		List<Post> posts = new ArrayList<Post>();
		for (int i = 0; i < jsonNode.size(); i++) {
			JsonNode node = jsonNode.get(i);
			System.out.println(node);
			Post post = readPost(node);
			if(!post.isEmpty()){
				posts.add(post);
			}
		}
		return posts;
	}
	
	private Post readPost(JsonNode node){
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.readValue(node, Post.class);
		} catch (IOException e) {
			return new Post();
		}
	}
}
