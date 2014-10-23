package com.anon;

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

public class CreateNewComment extends DialogFragment implements OnEditorActionListener{

	public interface EditNameDialogListenerNewComments {
        void onFinishEditDialogNewComments(String inputText);
    }
 
    private EditText etCommentText;
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.create_new_comment, container);
        etCommentText = (EditText) view.findViewById(R.id.etCreateNewCommentSetText);
        getDialog().setTitle("Create New Comment");
 
        // Show soft keyboard automatically
        etCommentText.requestFocus();
        etCommentText.setOnEditorActionListener(this);
 
        return view;
    }

	@Override
	public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
		if (EditorInfo.IME_ACTION_DONE == actionId) {
            // Return input text to activity
            EditNameDialogListenerNewComments activity = (EditNameDialogListenerNewComments) getActivity();
            activity.onFinishEditDialogNewComments(etCommentText.getText().toString());
            this.dismiss();
            return true;
        }
        return false;
	}

}
