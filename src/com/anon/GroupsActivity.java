package com.anon;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.anon.CreateNewGroup.EditNameDialogListener;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.FirebaseError;

public class GroupsActivity extends Activity implements EditNameDialogListener {
	
	LinearLayout layout;
	
	@Override
	public void onCreate(Bundle b) {
		super.onCreate(b);
		setContentView(R.layout.groups);
		
		layout = ((LinearLayout) findViewById(R.id.llGroups));
		
		Flame.ref.child("groups").addChildEventListener(new ChildEventListener() {
			
			@Override
			public void onChildRemoved(DataSnapshot arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onChildMoved(DataSnapshot arg0, String arg1) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onChildChanged(DataSnapshot arg0, String arg1) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onChildAdded(DataSnapshot data, String arg1) {
				String groupName = (String) data.getValue();
				View line = getLine(groupName);
				addLine(line, groupName);
			}
			
			@Override
			public void onCancelled(FirebaseError arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		/*
		if(groups.size() == 0) {
			LinearLayout.LayoutParams textParams = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			TextView text = new TextView(this);
			text.setTypeface(Typeface.createFromAsset(getAssets(), "Roboto-Light.ttf"));
			text.setTextSize(35);
			text.setTextColor(getResources().getColor(R.color.light_purple));
			textParams.setMargins(20, 20, 20, 20);
			text.setText(getResources().getString(R.string.loser));
			text.setGravity(Gravity.CENTER);
			layout.addView(text);
		}
		*/
	}
	
	private View getLine(String groupName) {
		ImageView icon = new ImageView(this);
		
		RelativeLayout.LayoutParams iconParams = new RelativeLayout.LayoutParams(150, 150), textParams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		
		RelativeLayout line = new RelativeLayout(this);
		
		icon.setBackgroundColor(getResources().getColor(R.color.light_purple));
		icon.setId(icon.hashCode());
		iconParams.setMargins(10, 10, 10, 30);
		iconParams.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
		
		TextView text = new TextView(this);
		text.setText(groupName);
		text.setTypeface(Typeface.createFromAsset(getAssets(), "Roboto-Light.ttf"));
		text.setTextSize(30);
		text.setTextColor(0xff000000);
		textParams.setMargins(20, 20, 20, 20);
		textParams.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
		textParams.addRule(RelativeLayout.RIGHT_OF, icon.hashCode());
		
		text.setLayoutParams(textParams);
		icon.setLayoutParams(iconParams);
		
		line.addView(icon);
		line.addView(text);
		
		return line;
	}
	
	private void addLine(View line, final String groupName) {
		View split = new View(this);
		
		LinearLayout.LayoutParams splitParams = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, 2);
		split.setPadding(10, 10, 10, 10);
		split.setLayoutParams(splitParams);
		split.setBackgroundColor(getResources().getColor(R.color.light_purple));
		
		line.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		line.setBackground(getResources().getDrawable(R.drawable.group_background));
		line.setClickable(true);
		line.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				Intent intent = new Intent(GroupsActivity.this, PostsActivity.class);
				Bundle groupInfo = new Bundle();
				groupInfo.putString("parentGroupID", groupName);
				intent.putExtras(groupInfo);
				startActivity(intent);
			}
		});
		
		layout.addView(line);
		layout.addView(split);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.groups_activity_menu, menu);
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch(item.getItemId()) {
		case R.id.mbGroupsSearchGroups:
			Toast.makeText(GroupsActivity.this, "Not implemented yet", Toast.LENGTH_SHORT).show();
			return true;
		case R.id.mbGroupsCreateNewGroup:
			FragmentManager fm = getFragmentManager();
			CreateNewGroup createNewGroupDialog = new CreateNewGroup();
			createNewGroupDialog.show(fm, "createNewGroupDialog");
			return true;
		case R.id.mbGroupsSettings:
			Toast.makeText(GroupsActivity.this, "Not implemented yet", Toast.LENGTH_SHORT).show();
			return true;
		case R.id.mbGroupsSignOut:
			Flame.ref.unauth();
			Intent i = new Intent(GroupsActivity.this, LogInScreen.class);
			i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(i);
			
			SharedPreferences settings = getSharedPreferences(DispatchActivity.PREFS_NAME, 0);
			SharedPreferences.Editor editor = settings.edit();
			editor.putString("email", "");
			editor.putString("password", "");
			editor.commit();
			
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
	@Override
	public void onFinishEditDialog(String inputText) {
		String groupName = inputText;
		Flame.ref.child("groups").push().setValue(groupName);
		finish();
		startActivity(getIntent());
	}
}
