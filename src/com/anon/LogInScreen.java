package com.anon;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class LogInScreen extends Activity {

	TextView screenTitle;
	EditText userEmailAddressInfo, userPasswordInfo;
	Button createNewUser, logInUser;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE); // removes title bar
		setContentView(R.layout.log_in_screen);

		overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

		initializeVars();
		setTextFonts();

		userPasswordInfo
				.setOnEditorActionListener(new TextView.OnEditorActionListener() {
					@Override
					public boolean onEditorAction(TextView v, int actionId,
							KeyEvent event) {
						if (actionId == EditorInfo.IME_ACTION_DONE) {
							login();
							return true;
						}
						return false;
					}
				});

		logInUser.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				login();
			}
		});

		createNewUser.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(LogInScreen.this,
						CreateNewUser.class);

				// if the user has entered email, send it to
				// the account creation screen
				Bundle accountInfo = new Bundle();
				if (userEmailAddressInfo.getText().length() > 0) {
					accountInfo.putString("email", userEmailAddressInfo
							.getText().toString().trim());
				}

				intent.putExtras(accountInfo);
				startActivity(intent);
			}
		});
	}

	private void login() {
		String email = userEmailAddressInfo.getText().toString().trim();
		String password = userPasswordInfo.getText().toString().trim();

		boolean validationError = false;
		StringBuilder validationErrorMessage = new StringBuilder(
				getString(R.string.error_intro));
		if (email.length() == 0) {
			validationError = true;
			validationErrorMessage
					.append(getString(R.string.error_blank_email));
		}
		if (password.length() == 0) {
			if (validationError) {
				validationErrorMessage.append(getString(R.string.error_join));
			}
			validationError = true;
			validationErrorMessage
					.append(getString(R.string.error_blank_password));
		}
		validationErrorMessage.append(getString(R.string.error_end));

		if (validationError) {
			Toast.makeText(LogInScreen.this, validationErrorMessage.toString(),
					Toast.LENGTH_LONG).show();
			return;
		}

		// set up a progress dialog
		final ProgressDialog dialog = new ProgressDialog(LogInScreen.this);
		dialog.setMessage(getString(R.string.progress_signup));
		dialog.show();

		ParseUser.logInInBackground(email, password, new LogInCallback() {
			@Override
			public void done(ParseUser user, ParseException e) {
				dialog.dismiss();
				if (e != null) {
					Toast.makeText(LogInScreen.this, e.getMessage(),
							Toast.LENGTH_LONG).show();
				} else {
					Intent intent = new Intent(LogInScreen.this,
							GroupsActivity.class);
					intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK
							| Intent.FLAG_ACTIVITY_NEW_TASK);

					// save login credentials
					SharedPreferences settings = getSharedPreferences(
							DispatchActivity.PREFS_NAME, 0);
					SharedPreferences.Editor editor = settings.edit();
					editor.putString("email", user.getUsername());
					editor.putString("password",
							userPasswordInfo.getText().toString().trim());
					editor.commit();

					startActivity(intent);
				}
			}

			private SharedPreferences getPreferences(String prefsName, int i) {
				// TODO Auto-generated method stub
				return null;
			}
		});
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
