package com.anon.backend;

import com.parse.ParseException;
import com.parse.ParseUser;

public class DemoCode {

	public void demo() {
		ParseUser currentUser = ParseUser.getCurrentUser();
		Group group = new Group("Bash Jacob", null, currentUser);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		group.addPost("Jacob sux", currentUser);
		try {
			Thread.sleep(1000);
			group.getAllPosts().get(0).addComment("Yeah I know", currentUser);
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
