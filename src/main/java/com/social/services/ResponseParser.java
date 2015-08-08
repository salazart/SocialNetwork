package com.social.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.ObjectMapper;

import com.social.models.Post;
import com.social.models.responses.CitiesGet;
import com.social.models.responses.FriendsGet;
import com.social.models.responses.UsersGet;
import com.social.models.responses.WallGet;

public class ResponseParser {
	private static final String RESPONSE = "response";

	public WallGet parseJson(String content, WallGet wallGet) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.readValue(content, WallGet.class);
		} catch (IOException e) {
			wallGet.setPosts(parseJsonNode(content));
			return wallGet;
		}
	}

	public FriendsGet parseJson(String content, FriendsGet friendsGet) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.readValue(content, friendsGet.getClass());
		} catch (IOException e) {
			return new FriendsGet();
		}
	}

	public UsersGet parseJson(String content, UsersGet usersGet) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.readValue(content, usersGet.getClass());
		} catch (IOException e) {
			return new UsersGet();
		}
	}

	public CitiesGet parseJson(String content, CitiesGet citiesGet) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.readValue(content, citiesGet.getClass());
		} catch (IOException e) {
			return new CitiesGet();
		}
	}

	private List<Post> parseJsonNode(String content) {
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
		}
		return posts;
	}

	private List<Post> readPosts(JsonNode jsonNode) {
		List<Post> posts = new ArrayList<Post>();
		for (int i = 0; i < jsonNode.size(); i++) {
			JsonNode node = jsonNode.get(i);
			Post post = readPost(node);
			if (!post.isEmpty()) {
				posts.add(post);
			}
		}
		return posts;
	}

	private Post readPost(JsonNode node) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.readValue(node, Post.class);
		} catch (IOException e) {
			return new Post();
		}
	}
}
