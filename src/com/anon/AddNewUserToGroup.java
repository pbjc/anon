package com.anon;

import com.anon.CreateNewPost.EditNameDialogListenerNewPosts;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

public class AddNewUserToGroup extends DialogFragment implements OnEditorActionListener{

	public interface EditNameDialogListenerAddUsers {
        void onFinishEditDialogAddUsers(String inputText);
    }
 
    private EditText etNewUserEmail;
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_new_user_to_group, container);
        etNewUserEmail = (EditText) view.findViewById(R.id.etAddNewUserByEmail);
        getDialog().setTitle("Add New User To Group");
 
        // Show soft keyboard automatically
        etNewUserEmail.requestFocus();
        etNewUserEmail.setOnEditorActionListener(this);
 
        return view;
    }

	@Override
	public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
		if (EditorInfo.IME_ACTION_DONE == actionId) {
            // Return input text to activity
            EditNameDialogListenerAddUsers activity = (EditNameDialogListenerAddUsers) getActivity();
            activity.onFinishEditDialogAddUsers(etNewUserEmail.getText().toString());
            this.dismiss();
            return true;
        }
        return false;
	}

}
