package com.anon;

import java.util.LinkedHashMap;
import java.util.List;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
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

import com.anon.CreateGroup.EditNameDialogListener;
import com.anon.backend.Group;
import com.parse.ParseException;
import com.parse.ParseUser;

public class GroupsActivity extends Activity implements EditNameDialogListener {
	
	@Override
	public void onCreate(Bundle b) {
		super.onCreate(b);
		setContentView(R.layout.groups);
		setupGUI();
	}
	
	private LinkedHashMap<View, String> loadGroups() {
		LinkedHashMap<View, String> ret = new LinkedHashMap<View, String>();
		
		List<Group> usersGroups = null;
		try {
			usersGroups = Group.getGroupsOfUser(ParseUser.getCurrentUser());
		} catch(ParseException e) {
			e.printStackTrace();
		}

		for (int i = 0; i < usersGroups.size(); i++) {
		    ImageView icon = new ImageView(this);
            Group group = usersGroups.get(i);
            
            RelativeLayout.LayoutParams iconParams = new RelativeLayout.LayoutParams(150, 150),
                    textParams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            
            RelativeLayout line = new RelativeLayout(this);
            
            TextView text = new TextView(this);
            text.setText(group.getName());
            text.setTypeface(Typeface.createFromAsset(getAssets(), "Roboto-Light.ttf"));
            text.setTextSize(30);
            text.setTextColor(0xff000000);
            textParams.setMargins(20, 20, 20, 20);
            textParams.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
            textParams.addRule(RelativeLayout.RIGHT_OF, i+10);
            
            icon.setBackgroundColor(getResources().getColor(R.color.light_purple));
            icon.setId(i+10);
            iconParams.setMargins(10, 10, 10, 30);
            iconParams.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);

            text.setLayoutParams(textParams);
            icon.setLayoutParams(iconParams);
            
            line.addView(icon);
            line.addView(text);
            
            ret.put(line, group.getObjectId());
        }

		return ret;
	}
	
	private void setupGUI() {
		final LinkedHashMap<View, String> groups = loadGroups();
		LinearLayout layout = ((LinearLayout) findViewById(R.id.llGroups));
		
		for(final View line : groups.keySet()) {
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
					groupInfo.putString("parentGroupID", groups.get(line));
					intent.putExtras(groupInfo);
					startActivity(intent);
				}
			});
			
			layout.addView(line);
			layout.addView(split);
		}
		
		if(groups.size() == 0){
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
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.groups_activity_menu, menu);
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()) {
		case R.id.mbGroupsSearchGroups:
			Toast.makeText(GroupsActivity.this, "Not implemented yet",
					Toast.LENGTH_SHORT).show();
			return true;
		case R.id.mbGroupsCreateNewGroup:
			FragmentManager fm = getFragmentManager();
			CreateGroup createNewGroupDialog = new CreateGroup();
			createNewGroupDialog.show(fm, "createNewGroupDialog");
			return true;
		case R.id.mbGroupsSettings:
			Toast.makeText(GroupsActivity.this, "Not implemented yet",
					Toast.LENGTH_SHORT).show();
			return true;
		case R.id.mbGroupsSignOut:
			ParseUser.logOut();
			Intent i = new Intent(GroupsActivity.this, LoginActivity.class);
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
        new Group(groupName, null, ParseUser.getCurrentUser());
        finish();
        startActivity(getIntent());
    }
}
