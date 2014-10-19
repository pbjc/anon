package com.anon;

import java.util.ArrayList;
import java.util.List;

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

import com.anon.backend.Group;
import com.parse.ParseException;
import com.parse.ParseUser;

public class GroupsActivity extends Activity {

    @Override
    public void onCreate(Bundle b){
        super.onCreate(b);
        setContentView(R.layout.groups);
        setupGUI();
    }
    
    private ArrayList<View> loadGroups(){
        ArrayList<View> ret = new ArrayList<View>();
        
        List<Group> usersGroups = null;
        try {
			usersGroups = Group.getGroupsOfUser(ParseUser.getCurrentUser());
		} catch(ParseException e) {
			e.printStackTrace();
		}
        
        String[] names = new String[usersGroups.size()];
        for(int i=0;i<names.length;i++){
        	System.out.println(usersGroups.get(i));
        	names[i] = usersGroups.get(i).getName();
        }
        
        ImageView icons[] = { new ImageView(this), new ImageView(this), new ImageView(this) };
        
        for(int a = 0; a < names.length; a++){
            RelativeLayout.LayoutParams iconParams = new RelativeLayout.LayoutParams(150, 150),
                    textParams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

            RelativeLayout line = new RelativeLayout(this);
            
            TextView text = new TextView(this);
            text.setText(names[a]);
            text.setTypeface(Typeface.createFromAsset(getAssets(), "Roboto-Light.ttf"));
            text.setTextSize(30);
            text.setTextColor(0xff000000);
            textParams.setMargins(20, 20, 20, 20);
            textParams.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
            textParams.addRule(RelativeLayout.RIGHT_OF, a+10);
            
            icons[a].setBackgroundColor(0xffff0000);
            icons[a].setId(a+10);
            iconParams.setMargins(10, 10, 10, 30);
            iconParams.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);

            text.setLayoutParams(textParams);
            icons[a].setLayoutParams(iconParams);
            
            line.addView(icons[a]);
            line.addView(text);
            
            ret.add(line);
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

            line.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, 200));
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
