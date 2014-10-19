package com.anon.backend;

import com.parse.ParseClassName;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

@ParseClassName("Comment")
public class Comment extends ParseObject {
	
	public static Comment getCommentFromID(String ID) throws ParseException {
		ParseQuery<Comment> commentQuery = ParseQuery.getQuery("Comment");
//		commentQuery.setCachePolicy(ParseQuery.CachePolicy.CACHE_ELSE_NETWORK);
		Comment comment = commentQuery.get(ID);
//		comment.pin();
		return comment;
	}
	
	public Comment() {}
	
	public Comment(String text, Post parentPost, ParseUser author) {
		setMessage(text);
		setParentPost(parentPost);
		setAuthor(author);
		this.saveInBackground();
	}
	
	private void setMessage(String message) {
		put("message", message);
	}
	
	public String getMessage() {
		return getString("message");
	}
	
	private void setParentPost(Post post) {
		put("parentPost", post);
	}
	
	public Post getParentPost() {
		return (Post) getParseObject("parentPost");
	}
	
	private void setAuthor(ParseUser author) {
		put("author", author);
	}
	
	public ParseUser getAuthor() {
		return getParseUser("author");
	}
	
}
