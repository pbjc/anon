package com.anon;

import android.R;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;

public class DispatchActivity extends Activity {
	public static final String PREFS_NAME = "LoginCreds";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
		String email = settings.getString("email", "");
		String password = settings.getString("password", "");
		if (!email.equals("") && !password.equals("")) {
			try {
				ParseUser.logIn(email, password);
				if (ParseUser.getCurrentUser() != null) {
					gotoGroups();
					return;
				}
			} catch (ParseException e) {
				Toast.makeText(DispatchActivity.this,
						"There was an error logging in", Toast.LENGTH_SHORT)
						.show();
			}
		}
		gotoLogin();
	}

	private void gotoGroups() {
		Intent intent = new Intent(DispatchActivity.this, GroupsActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK
				| Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(intent);
	}

	private void gotoLogin() {
		Intent intent = new Intent(DispatchActivity.this, LogInScreen.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK
				| Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(intent);
	}

	private SharedPreferences getPreferences(String prefsName, int i) {
		// TODO Auto-generated method stub
		return null;
	}
}
