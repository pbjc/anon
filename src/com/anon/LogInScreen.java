package com.anon;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.parse.ParseAnalytics;

public class LogInScreen extends Activity {

	TextView screenTitle;
	EditText userEmailAddressInfo, userPasswordInfo;
	Button createNewUser, logInUser;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE); // removes title bar
		setContentView(R.layout.log_in_screen);
		
		initializeVars();
		setTextFonts();
		
		
		logInUser.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
			}
		});

		createNewUser.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(LogInScreen.this, CreateNewUser.class));
			}
		});

		ParseAnalytics.trackAppOpened(getIntent());
	}

	private void initializeVars() {
		screenTitle = (TextView) findViewById(R.id.tvLogInScreenTitle);
		userEmailAddressInfo = (EditText) findViewById(R.id.etLoginEmailAddress);
		userPasswordInfo = (EditText) findViewById(R.id.etLoginPassword);
		createNewUser = (Button) findViewById(R.id.bCreateNewUserFromMain);
		logInUser = (Button) findViewById(R.id.bLogInUser);
	}
	
	private void setTextFonts() {
		Typeface tf = Typeface.createFromAsset(getAssets(), "Roboto-Light.ttf");
		screenTitle.setTypeface(tf);
		userEmailAddressInfo.setTypeface(tf);
		userPasswordInfo.setTypeface(tf);
		createNewUser.setTypeface(tf);
		logInUser.setTypeface(tf);
	}

}
