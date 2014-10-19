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
	
	
	private void populate() {
		for(int k = 0; k < 20; k++) {
			ParseUser currentUser = ParseUser.getCurrentUser();
			Group group = new Group("Jacob " + k, null, ParseUser.getCurrentUser());
			try {
				Thread.sleep(500);
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
			for(int i = 0; i < 7; i++) {
				group.addPost("Jacob sux " + i, currentUser);
				try {
					for(int j = 0; j < 7; j++) {
						Thread.sleep(500);
						group.getAllPosts().get(i).addComment("Yeah I know " + j, currentUser);
					}
				} catch(ParseException e) {
					e.printStackTrace();
				} catch(InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
