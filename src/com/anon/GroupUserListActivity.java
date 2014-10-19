package com.anon;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.anon.backend.Group;
import com.parse.ParseException;
import com.parse.ParseUser;

public class GroupUserListActivity extends Activity {

	ListView userList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE); // removes title bar
		setContentView(R.layout.group_users_list_activity);
		initializeVars();
		
		Bundle b = getIntent().getExtras();
		String groupID = b.getString("groupID");
		
		Group group = null;
		List<ParseUser> groupMemberList = null;
		List<String> userArrayList = new ArrayList<String>();
		
		try {
			group = Group.getGroupFromID(groupID);
			groupMemberList = group.getMembers();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for (ParseUser user : groupMemberList) {
			userArrayList.add(user.getString("name"));
		}
		
		
		ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, userArrayList);
		
		userList.setAdapter(arrayAdapter);
		
	}
	
	private void initializeVars() {
		userList = (ListView) findViewById(R.id.lvGroupUserList);
	}

}
