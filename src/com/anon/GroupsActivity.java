package com.anon;

import java.util.ArrayList;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class GroupsActivity extends Activity{

    @Override
    public void onCreate(Bundle b){
        super.onCreate(b);
        setContentView(R.layout.groups);
        setupGUI();
    }
    
    private ArrayList<RelativeLayout> loadGroups(){
        ArrayList<RelativeLayout> ret = new ArrayList<RelativeLayout>();
        
        ImageView icons[] = { new ImageView(this), new ImageView(this), new ImageView(this) };
        String names[] = {"askfhaskdjfhaskjdf", "penis", "jacob is a baller"};
        
        for(int a = 0; a < names.length; a++){
            RelativeLayout line = new RelativeLayout(this);
            
            TextView text = new TextView(this);
            text.setText(names[a]);
            text.setTypeface(Typeface.createFromAsset(getAssets(), "Roboto-Light.ttf"));
            text.setTextSize(20);
        }
        
        return ret;
    }
    
    private void setupGUI(){
        ArrayList<RelativeLayout> groups = loadGroups();
        
        for(RelativeLayout rl : groups){
            LinearLayout layout = ((LinearLayout)findViewById(R.id.llGroups));
            
            
            View split = new View(this);
            LinearLayout.LayoutParams splitParams = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, 2);
            split.setPadding(10, 10, 10, 10);
            split.setLayoutParams(splitParams);
            split.setBackgroundColor(getResources().getColor(R.color.light_purple));

            layout.addView(rl);
            layout.addView(split);
        }
    }
    
}
