package com.anon;

import java.util.LinkedHashMap;
import java.util.List;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.anon.backend.Comment;
import com.anon.backend.Group;
import com.anon.backend.Post;
import com.parse.ParseException;

public class CommentsActivity extends Activity {

    @Override
    public void onCreate(Bundle b){
        super.onCreate(b);
        setContentView(R.layout.comments);
        setupGUI(getIntent().getExtras().getString("parentGroupID"));
    }
    

    private LinkedHashMap<View, String> loadPosts(String parentPostID){
        LinkedHashMap<View, String> ret = new LinkedHashMap<View, String>();
        
        List<Comment> comments = null;
        try {
            comments = Post.getPostFromID(parentPostID).getAllComments();
        } catch(ParseException e) {
            e.printStackTrace();
        }
        
        for(Comment comment : comments){
            TextView text = new TextView(this);
            
            text.setText(comment.getMessage());
            text.setTypeface(Typeface.createFromAsset(getAssets(), "Roboto-Light.ttf"));
            text.setTextSize(25);
            text.setTextColor(0xff000000);
            text.setPadding(20, 20, 20, 20);
            
            ret.put(text, comment.getObjectId());
        }
        
        return ret;
    }
    
    private void setupGUI(String parentPostID){
        try {
            TextView title = ((TextView)findViewById(R.id.tvPost));
            title.setText(Post.getPostFromID(parentPostID).getMessage());
            title.setTypeface(Typeface.createFromAsset(getAssets(), "Roboto-Light.ttf"));
        } catch (ParseException e) {
            Log.wtf("Shit", e+"");
        }
        
        LinkedHashMap<View, String> posts = loadPosts(parentPostID);
        
        for(View line : posts.keySet()){
            LinearLayout layout = ((LinearLayout)findViewById(R.id.llComments));
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            params.setMargins(0, 40, 0, 40);
            params.gravity = Gravity.RIGHT;
            
            line.setLayoutParams(params);
            line.setClickable(true);
            line.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    Log.wtf("Arjun", "Sucks");
                }
            });
            line.setBackground(getResources().getDrawable(R.drawable.rounded_edittext));
            
            layout.addView(line);
        }
    }
    
}
