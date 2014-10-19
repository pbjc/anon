package com.anon;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.parse.ParseUser;

public class DispatchActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (ParseUser.getCurrentUser() != null) {
			// logged in, go to the groups screen
			startActivity(new Intent(this, GroupsActivity.class));
		} else {
			// go back to the login screen
			startActivity(new Intent(this, LogInScreen.class));
		}
	}
}
