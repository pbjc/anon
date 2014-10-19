package com.anon;

import android.app.Activity;
import android.os.Bundle;

import com.anon.backend.Group;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseUser;

public class ParseTestingActivity extends Activity {
	
	String mostRecentId;
	
	/** Called when the activity is first created. */
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		ParseAnalytics.trackAppOpened(getIntent());
		
		ParseUser currentUser = ParseUser.getCurrentUser();
		Group group = new Group("Bash Jacob", null, currentUser);
		try {
			Thread.sleep(1000);
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
		group.addPost("Jacob sux", currentUser);
		try {
			Thread.sleep(1000);
			group.getAllPosts().get(0).addComment("Yeah I know", currentUser);
		} catch(ParseException e) {
			e.printStackTrace();
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
