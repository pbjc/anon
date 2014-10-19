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

import com.anon.CreateNewPost.EditNameDialogListenerNewPosts;
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

        
		int count = 0;
        for(Post post : posts){
            RelativeLayout layout = new RelativeLayout(this);
            
            RelativeLayout.LayoutParams  textParams =
                    new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT),
                    numParams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            
            TextView text = new TextView(this), numPosts = new TextView(this);
            text.setText(post.getMessage());
            text.setTypeface(Typeface.createFromAsset(getAssets(), "Roboto-Light.ttf"));
            text.setTextSize(30);
            text.setTextColor(0xff000000);
            text.setId(10 + count);
            text.setLayoutParams(textParams);
            
            try {
                numPosts.setText(post.getAllComments().size()+" comments");
            } catch (ParseException e) {}
            numPosts.setTypeface(Typeface.createFromAsset(getAssets(), "Roboto-Light.ttf"));
            numPosts.setTextSize(15);
            numPosts.setTextColor(0xff7f7f7f);
            numParams.addRule(RelativeLayout.BELOW, 10 + count);
            numPosts.setLayoutParams(numParams);
            
            layout.addView(text);
            layout.addView(numPosts);
            layout.setPadding(40, 40, 40, 40);
            
            ret.put(layout, post.getObjectId());
            count++;
        }
        
        return ret;
    }
    
    private void setupGUI(String parentGroupID){
        try {
            TextView title = ((TextView)findViewById(R.id.tvGroup));
            title.setText(Group.getGroupFromID(parentGroupID).getName());
            title.setTypeface(Typeface.createFromAsset(getAssets(), "Roboto-Light.ttf"));
        } catch (ParseException e) {
            Log.wtf("Shit", e+"");
        }
        
        final LinkedHashMap<View, String> posts = loadPosts(parentGroupID);
        
        for(final View line : posts.keySet()){
            LinearLayout layout = ((LinearLayout)findViewById(R.id.llPosts));
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
            params.setMargins(0, 20, 0, 20);


            line.setLayoutParams(params);
            line.setClickable(true);
            line.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(PostsActivity.this,
                            CommentsActivity.class);
                    Bundle groupInfo = new Bundle();
                    groupInfo.putString("parentGroupID", posts.get(line));
                    intent.putExtras(groupInfo);
                    startActivity(intent);
                }
            });
            line.setBackground(getResources().getDrawable(R.drawable.post_background));
            
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
		String postText = inputText;
		Group group = null;
		try {
			group = Group.getGroupFromID(getIntent().getExtras().getString("parentGroupID"));
		} catch(ParseException e) {
			e.printStackTrace();
		}
		new Post(postText, group, ParseUser.getCurrentUser());
	}
}
