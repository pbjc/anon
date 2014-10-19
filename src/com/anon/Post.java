package com.anon;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;

/*
 * An extension of ParseObject that makes
 * it more convenient to access information
 * about a given Post
 */

@ParseClassName("Post")
public class Post extends ParseObject {
	
	public Post() {}
	
	public String getMessage() {
		return getString("message");
	}
	
	public void setMessage(String message) {
		put("message", message);
	}
	
	public ParseUser getAuthor() {
		return getParseUser("author");
	}
	
	public void setAuthor(ParseUser user) {
		put("author", user);
	}
	
	public ParseFile getPhotoFile() {
		return getParseFile("photo");
	}
	
	public void setPhotoFile(ParseFile file) {
		put("photo", file);
	}
	
}
