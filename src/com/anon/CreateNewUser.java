package com.anon;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class CreateNewUser extends Activity {

	TextView screenTitle, nameClarification;
	EditText userNameInfo, userEmailInfo, userPasswordInfo,
			userPasswordAgainInfo;
	Button cancel, ok;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE); // removes title bar
		setContentView(R.layout.create_new_user);

		overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

		initializeVars();
		setTextFonts();

		// fill in email from login screen
		Bundle accountInfo = getIntent().getExtras();
		userEmailInfo.setText(accountInfo.getString("email"));

		nameClarification.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				alertNameClarification();
			}
		});

		cancel.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish(); // ends activity
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
		String username = userNameInfo.getText().toString().trim();
		String email = userEmailInfo.getText().toString().trim();
		String password = userPasswordInfo.getText().toString().trim();
		String passwordAgain = userPasswordAgainInfo.getText().toString()
				.trim();

		boolean validationError = false;
		StringBuilder validationErrorMessage = new StringBuilder(
				getString(R.string.error_intro));
		if (username.length() == 0) {
			validationError = true;
			validationErrorMessage
					.append(getString(R.string.error_blank_username));
		}
		if (!isValidEmailAddress(email)) {
			if (validationError) {
				validationErrorMessage.append(getString(R.string.error_join));
			}
			validationError = true;
			validationErrorMessage
					.append(getString(R.string.error_invalid_email));
		}
		if (password.length() == 0) {
			if (validationError) {
				validationErrorMessage.append(getString(R.string.error_join));
			}
			validationError = true;
			validationErrorMessage
					.append(getString(R.string.error_blank_password));
		}
		if (!password.equals(passwordAgain)) {
			if (validationError) {
				validationErrorMessage.append(getString(R.string.error_join));
			}
			validationError = true;
			validationErrorMessage
					.append(getString(R.string.error_mismatched_passwords));
		}
		validationErrorMessage.append(getString(R.string.error_end));

		if (validationError) {
			Toast.makeText(CreateNewUser.this,
					validationErrorMessage.toString(), Toast.LENGTH_LONG)
					.show();
			return;
		}

		// set up a progress dialog
		final ProgressDialog dialog = new ProgressDialog(CreateNewUser.this);
		dialog.setMessage(getString(R.string.progress_signup));
		dialog.show();

		ParseUser user = new ParseUser();
		user.put("name", username);
		user.setUsername(email);
		user.setPassword(password);
		user.setEmail(email);

		user.signUpInBackground(new SignUpCallback() {
			@Override
			public void done(ParseException e) {
				dialog.dismiss();
				if (e != null) {
					Toast.makeText(CreateNewUser.this, e.getMessage(),
							Toast.LENGTH_LONG).show();
				} else {
					Intent intent = new Intent(CreateNewUser.this,
							GroupsActivity.class);
					intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK
							| Intent.FLAG_ACTIVITY_NEW_TASK);

					// save login credentials
					SharedPreferences settings = getSharedPreferences(
							DispatchActivity.PREFS_NAME, 0);
					SharedPreferences.Editor editor = settings.edit();
					editor.putString("email",
							userNameInfo.getText().toString().trim());
					editor.putString("password",
							userPasswordInfo.getText().toString().trim());
					editor.commit();

					startActivity(intent);
				}
			}
		});
	}

	private void alertNameClarification() {
		new AlertDialog.Builder(this)
				.setTitle(getString(R.string.alert_name_title))
				.setMessage(getString(R.string.alert_name_text))
				.setPositiveButton(getString(R.string.ok), null).show();
	}

	private boolean isValidEmailAddress(String email) {
		String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
		java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
		java.util.regex.Matcher m = p.matcher(email);
		return m.matches();
	}

	private void initializeVars() {
		screenTitle = (TextView) findViewById(R.id.tvCreateNewUserTitle);
		userNameInfo = (EditText) findViewById(R.id.etNewUserNameInfo);
		nameClarification = (TextView) findViewById(R.id.tvNameClarification);
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
