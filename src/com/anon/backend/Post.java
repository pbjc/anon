package com.anon.backend;

import java.util.List;

import com.parse.ParseClassName;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

/*
 * An extension of ParseObject that makes
 * it more convenient to access information
 * about a given Post
 */

@ParseClassName("Post")
public class Post extends ParseObject {
	
	public static Post getPostFromID(String ID) throws ParseException {
		ParseQuery<Post> postQuery = ParseQuery.getQuery("Post");
		postQuery.setCachePolicy(ParseQuery.CachePolicy.CACHE_ELSE_NETWORK);
		Post post = postQuery.get(ID);
		post.pin();
		return post;
	}
	
	public Post() {} 
	
	public Post(String text, Group parentGroup, ParseUser author) {
		setMessage(text);
		setParentGroup(parentGroup);
		setScore(0);
		setAuthor(author);
		this.saveInBackground();
	}
	
	private void setMessage(String message) {
		put("message", message);
	}
	
	public String getMessage() {
		return getString("message");
	}
	
	private void setScore(int score) {
		put("score", score);
	}
	
	public void incrementScore(int score) {
		put("score", getScore() + 1);
	}
	
	public void decrementScore(int score) {
		put("score", getScore() - 1);
	}
	
	public int getScore() {
		return getInt("score");
	}
	
	private void setPhotoFile(ParseFile file) {
		put("photo", file);
	}
	
	public ParseFile getPhotoFile() {
		return getParseFile("photo");
	}
	
	private void setParentGroup(Group group) {
		put("parentGroup", group);
	}
	
	public Group getParentGroup() {
		return (Group) getParseObject("parentGroup");
	}
	
	private void setAuthor(ParseUser user) {
		put("author", user);
	}
	
	public ParseUser getAuthor() {
		return getParseUser("author");
	}
	
	public void addComment(String commentText, ParseUser author) {
		new Comment(commentText, this, author);
	}
	
	public List<Comment> getAllComments() throws ParseException {
		ParseQuery<Comment> comments = ParseQuery.getQuery("Comment");
		comments.whereEqualTo("parentPost", this);
		return comments.find();
	}
}
