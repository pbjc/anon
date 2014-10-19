package com.anon;

import java.util.LinkedHashMap;
import java.util.List;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.anon.CreateNewGroup.EditNameDialogListener;
import com.anon.CreateNewPost.EditNameDialogListenerNewPosts;
import com.anon.backend.Comment;
import com.anon.backend.Group;
import com.anon.backend.Post;
import com.parse.ParseException;
import com.parse.ParseUser;

public class PostsActivity extends Activity implements EditNameDialogListenerNewPosts {

	@Override
	public void onCreate(Bundle b) {
		super.onCreate(b);
		setContentView(R.layout.posts);
		setupGUI(getIntent().getExtras().getString("parentGroupID"));
	}

	private LinkedHashMap<View, String> loadPosts(String parentGroupID) {
		LinkedHashMap<View, String> ret = new LinkedHashMap<View, String>();

		List<Post> posts = null;
		try {
			posts = Group.getGroupFromID(parentGroupID).getAllPosts();
		} catch (ParseException e) {
			e.printStackTrace();
		}

		for (Post post : posts) {
			RelativeLayout.LayoutParams textParams = new RelativeLayout.LayoutParams(
					LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

			TextView text = new TextView(this);
			text.setText(post.getMessage());
			text.setTypeface(Typeface.createFromAsset(getAssets(),
					"Roboto-Light.ttf"));
			text.setTextSize(30);
			text.setTextColor(0xff000000);
			text.setPadding(40, 20, 40, 20);

			textParams.setMargins(40, 40, 40, 40);
			text.setLayoutParams(textParams);

			ret.put(text, post.getObjectId());
		}

		return ret;
	}

	private void setupGUI(String parentGroupID) {
		LinkedHashMap<View, String> posts = loadPosts(parentGroupID);

		for (View line : posts.keySet()) {
			LinearLayout layout = ((LinearLayout) findViewById(R.id.llPosts));
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
					LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
			params.setMargins(0, 40, 0, 40);

			line.setLayoutParams(params);
			line.setClickable(true);
			line.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					Log.wtf("Arjun", "Sucks");
				}
			});
			line.setBackgroundColor(getResources().getColor(R.color.white));

			layout.addView(line);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.specific_group_page_activity_menu, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.mbGroupPageCreateNewPost:
			FragmentManager fm = getFragmentManager();
			CreateNewPost CreateNewPostDialog = new CreateNewPost();
			CreateNewPostDialog.show(fm, "CreateNewPostDialog");
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	public void onFinishEditDialogNewPosts(String inputText) {
		// TODO Auto-generated method stub
		String postText = inputText;
	}

}
