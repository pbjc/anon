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
            text.setTextColor(0xffffffff);
            text.setPadding(40, 20, 40, 20);
            
            textParams.setMargins(40, 40, 40, 40);
            text.setLayoutParams(textParams);
            
            ret.add(text);
        }
        
        return ret;
    }
    
    private void setupGUI(){
        ArrayList<View> posts = loadPosts();
        
        for(View line : posts){
            LinearLayout layout = ((LinearLayout)findViewById(R.id.llPosts));
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            params.setMargins(0, 40, 0, 40);

            line.setLayoutParams(params);
            line.setClickable(true);
            line.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    Log.wtf("Arjun", "Sucks");
                }
            });
            line.setBackgroundColor(getResources().getColor(R.color.dark_blue));
            
            layout.addView(line);
        }
    }
    
}
