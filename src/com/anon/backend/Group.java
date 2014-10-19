package com.anon.backend;

import java.util.List;

import com.parse.ParseClassName;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseRelation;
import com.parse.ParseUser;

@ParseClassName("Group")
public class Group extends ParseObject {
	
	public static Group getGroupFromID(String ID) throws ParseException {
		ParseQuery<Group> groupQuery = ParseQuery.getQuery("Group");
//		groupQuery.setCachePolicy(ParseQuery.CachePolicy.CACHE_ELSE_NETWORK);
		Group group = groupQuery.get(ID);
		group.pin();
		return group;
	}
	
	public static List<Group> getGroupsOfUser(ParseUser user) throws ParseException {
		ParseQuery<Group> query = ParseQuery.getQuery("Group");
		query.whereEqualTo("members", user);
		
		return query.find();
	}
	
	public Group() {}
	
	public Group(String name, ParseFile icon, ParseUser admin) {
		setName(name);
		if(icon != null)
			setIcon(icon);
		setAdmin(admin);
		addMember(admin);
		this.saveInBackground();
	}
	
	private void setName(String name) {
		put("name", name);
	}
	
	public String getName() {
		return getString("name");
	}
	
	private void setIcon(ParseFile icon) {
		put("icon", icon);
	}
	
	public ParseFile getIcon() {
		return getParseFile("icon");
	}
	
	public void addMember(ParseUser member) {
		ParseRelation<ParseUser> members = this.getRelation("members");
		members.add(member);
		this.saveInBackground();
	}
	
	public List<ParseUser> getMembers() throws ParseException {
		ParseRelation<ParseUser> members = this.getRelation("members");
		ParseQuery<ParseUser> membersQuery = members.getQuery();
		return membersQuery.find();
	}
	
	private void setAdmin(ParseUser admin) {
		put("admin", admin);
	}
	
	public ParseUser getAdmin() {
		return getParseUser("admin");
	}
	
	public void addPost(String text, ParseUser author) {
		new Post(text, this, author);
	}
	
	public List<Post> getAllPosts() throws ParseException {
		ParseQuery<Post> posts = ParseQuery.getQuery("Post");
		posts.whereEqualTo("parentGroup", this);
		return posts.find();
	}
	
}
