package com.anon;

import java.util.LinkedHashMap;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
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

import com.anon.backend.Group;
import com.parse.ParseException;
import com.parse.ParseUser;

public class GroupsActivity extends Activity {

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
		} catch (ParseException e) {
			e.printStackTrace();
		}

		ImageView icons[] = { new ImageView(this), new ImageView(this),
				new ImageView(this) };

		for (int i = 0; i < usersGroups.size(); i++) {
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
            
            icons[i].setBackgroundColor(0xffff0000);
            icons[i].setId(i+10);
            iconParams.setMargins(10, 10, 10, 30);
            iconParams.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);

            text.setLayoutParams(textParams);
            icons[i].setLayoutParams(iconParams);
            
            line.addView(icons[i]);
            line.addView(text);
            
            ret.put(line, group.getObjectId());
        }

		return ret;
	}

	private void setupGUI() {
		final LinkedHashMap<View, String> groups = loadGroups();

		for (final View line : groups.keySet()) {
			LinearLayout layout = ((LinearLayout) findViewById(R.id.llGroups));

			View split = new View(this);
			LinearLayout.LayoutParams splitParams = new LinearLayout.LayoutParams(
					LayoutParams.MATCH_PARENT, 2);
			split.setPadding(10, 10, 10, 10);
			split.setLayoutParams(splitParams);
			split.setBackgroundColor(getResources().getColor(R.color.light_purple));

            line.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
			line.setBackground(getResources().getDrawable(
					R.drawable.group_background));
			line.setClickable(true);
			line.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					Intent intent = new Intent(GroupsActivity.this,
							PostsActivity.class);
					Bundle groupInfo = new Bundle();
					groupInfo.putString("parentGroupID", groups.get(line));
					intent.putExtras(groupInfo);
					startActivity(intent);
				}
			});

			layout.addView(line);
			layout.addView(split);
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
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.mbGroupsSearchGroups:
			Toast.makeText(GroupsActivity.this, "Not implemented yet", Toast.LENGTH_SHORT).show();
			return true;
		case R.id.mbGroupsCreateNewGroup:
			Intent i = new Intent(GroupsActivity.this,
					CreateNewGroupActivity.class);
			startActivity(i);
			return true;
		case R.id.mbGroupsSettings:
			Toast.makeText(GroupsActivity.this, "Not implemented yet", Toast.LENGTH_SHORT).show();
			return true;
		case R.id.mbGroupsSignOut:
			ParseUser.logOut();
			i = new Intent(GroupsActivity.this, LogInScreen.class);
			i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK
					| Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(i);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

}
