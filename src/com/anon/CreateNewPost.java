package com.anon;

import com.anon.CreateNewGroup.EditNameDialogListener;

import android.app.DialogFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

public class CreateNewPost extends DialogFragment implements OnEditorActionListener{

	public interface EditNameDialogListenerNewPosts {
        void onFinishEditDialogNewPosts(String inputText);
    }
 
    private EditText etPostText;
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.create_new_post, container);
        etPostText = (EditText) view.findViewById(R.id.etCreateNewPostSetText);
        getDialog().setTitle("Create New Post");
 
        // Show soft keyboard automatically
        etPostText.requestFocus();
        etPostText.setOnEditorActionListener(this);
 
        return view;
    }

	@Override
	public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
		if (EditorInfo.IME_ACTION_DONE == actionId) {
            // Return input text to activity
            EditNameDialogListenerNewPosts activity = (EditNameDialogListenerNewPosts) getActivity();
            activity.onFinishEditDialogNewPosts(etPostText.getText().toString());
            this.dismiss();
            return true;
        }
        return false;
	}

}
