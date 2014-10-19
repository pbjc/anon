package com.anon;

import android.app.Application;

import com.anon.backend.Comment;
import com.anon.backend.Group;
import com.anon.backend.Post;
import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseObject;
import com.parse.ParseUser;

public class Anon extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        ParseObject.registerSubclass(Group.class);
        ParseObject.registerSubclass(Post.class);
        ParseObject.registerSubclass(Comment.class);

        // Add your initialization code here
        Parse.initialize(this, "03BKZLIMEoWsiabvkIpaDWHYCRApQwobb4tOKeBo",
                "A49RaubTM6QJETtEJllPvnCqE4mcWJhTXh4GvplJ");

        ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();

        // If you would like all objects to be private by default, remove this
        // line.
        defaultACL.setPublicReadAccess(true);

        ParseACL.setDefaultACL(defaultACL, true);
    }
}
