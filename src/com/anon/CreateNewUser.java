package com.anon;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CreateNewUser extends Activity{

	TextView screenTitle;
	EditText userNameInfo, userEmailInfo, userPasswordInfo, userPasswordAgainInfo;
	Button cancel, ok;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);		//removes title bar
		setContentView(R.layout.create_new_user);

		overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
		
		initializeVars();
		setTextFonts();
		
		cancel.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();	//ends activity
			}
		});
		
		ok.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				signup();
			}
		});
	}
	
	private void signup() {
		
	}
	
	private void initializeVars() {
		screenTitle = (TextView) findViewById(R.id.tvCreateNewUserTitle);
		userNameInfo = (EditText) findViewById(R.id.etNewUserNameInfo);
		userEmailInfo = (EditText) findViewById(R.id.etNewUserEmailAddressInfo);
		userPasswordInfo = (EditText) findViewById(R.id.etNewUserPasswordInfo);
		userPasswordAgainInfo = (EditText) findViewById(R.id.etNewUserPasswordAgainInfo);
		cancel = (Button) findViewById(R.id.bCancelCreateNewUser);
		ok = (Button) findViewById(R.id.bCreateNewUser);
	}
	
	private void setTextFonts() {
		Typeface tf = Typeface.createFromAsset(getAssets(), "Roboto-Light.ttf");
		screenTitle.setTypeface(tf);
		userNameInfo.setTypeface(tf);
		userEmailInfo.setTypeface(tf);
		userPasswordInfo.setTypeface(tf);
		userPasswordAgainInfo.setTypeface(tf);
		cancel.setTypeface(tf);
		ok.setTypeface(tf);
	}

}
