package com.anon;

import java.util.ArrayList;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class PostsActivity extends Activity {

    @Override
    public void onCreate(Bundle b){
        super.onCreate(b);
        setContentView(R.layout.posts);
        setupGUI();
    }
    
    private ArrayList<View> loadPosts(){
        ArrayList<View> ret = new ArrayList<View>();
        
        String posts[] = {"Shit", "Jacob is da' bomb.com", "gfgfskjdHFBAJHKDSFGAKSHDFGAKSJDHFG ASD FASDGF LAJKSHDFG KADSKL ASDGFDSLKASJD AS"};
        
        for(int a = 0; a < posts.length; a++){
            RelativeLayout.LayoutParams  textParams =
                    new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            
            TextView text = new TextView(this);
            text.setText(posts[a]);
            text.setTypeface(Typeface.createFromAsset(getAssets(), "Roboto-Light.ttf"));
            text.setTextSize(30);
            text.setTextColor(getResources().getColor(R.color.dark_blue));
            
            text.setLayoutParams(textParams);
            
            ret.add(text);
        }
        
        return ret;
    }
    
    private void setupGUI(){
        ArrayList<View> posts = loadPosts();
        
        for(View line : posts){
            LinearLayout layout = ((LinearLayout)findViewById(R.id.llPosts));
            
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
                    Log.wtf("Arjun", "Sucks");
                }
            });
            
            layout.addView(line);
            layout.addView(split);
        }
    }
    
}
