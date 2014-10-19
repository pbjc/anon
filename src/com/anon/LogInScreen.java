package com.anon;

import android.app.Activity;
import android.os.Bundle;

import com.parse.ParseAnalytics;
import com.anon.R;

public class LogInScreen extends Activity {
	/** Called when the activity is first created. */
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.log_in_screen);

		ParseAnalytics.trackAppOpened(getIntent());
	}
}
