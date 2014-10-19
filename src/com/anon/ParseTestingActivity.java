package com.anon;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;

public class ParseTestingActivity extends Activity {
	
	/** Called when the activity is first created. */
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		ParseAnalytics.trackAppOpened(getIntent());
	}
	
	
	/*
	 * Sample sendMessage!
	 * Copy/paste this into a main application
	 */
	public void sendMessage(View view) {
		EditText editText = (EditText) findViewById(R.id.message);
		String message = editText.getText().toString();
		
		Post post = new Post();
		post.setMessage(message);
		post.setAuthor(ParseUser.getCurrentUser());
		post.saveInBackground(new SaveCallback() {
			
			@Override
			public void done(ParseException e) {
				if(e == null) {
					setResult(Activity.RESULT_OK);
					finish();
				} else {
					Toast.makeText(getApplicationContext(), "Error saving: " + e.getMessage(), Toast.LENGTH_SHORT).show();
				}
			}
			
		});
	}
}
