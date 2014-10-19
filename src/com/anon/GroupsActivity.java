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
    
    private ArrayList<View> loadGroups(){
        ArrayList<View> ret = new ArrayList<View>();
        
        ImageView icons[] = { new ImageView(this), new ImageView(this), new ImageView(this) };
        String names[] = {"askfhaskdjfhaskjdf", "penis", "jacob is a baller"};
        
        for(int a = 0; a < names.length; a++){
            RelativeLayout line = new RelativeLayout(this);
            
            TextView text = new TextView(this);
            text.setText(names[a]);
            text.setTypeface(Typeface.createFromAsset(getAssets(), "Roboto-Light.ttf"));
            text.setTextSize(20);
            
            RelativeLayout.LayoutParams iconParam = new RelativeLayout.LayoutParams(50, 50);
            icons[a].setBackgroundColor(0xffff0000);
            icons[a].setLayoutParams()
        }
        
        return ret;
    }
    
    private void setupGUI(){
        ArrayList<View> groups = loadGroups();
        
        for(View line : groups){
            LinearLayout layout = ((LinearLayout)findViewById(R.id.llGroups));
            
            View split = new View(this);
            LinearLayout.LayoutParams splitParams = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, 2);
            split.setPadding(10, 10, 10, 10);
            split.setLayoutParams(splitParams);
            split.setBackgroundColor(getResources().getColor(R.color.light_purple));

            line.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
            
            layout.addView(line);
            layout.addView(split);
        }
    }
    
}
