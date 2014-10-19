package com.anon;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

public class CreateNewUser extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);		//removes title bar
		setContentView(R.layout.create_new_user);
	}

}
