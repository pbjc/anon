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

public class CreateGroup extends DialogFragment implements OnEditorActionListener{

	public interface EditNameDialogListener {
        void onFinishEditDialog(String inputText);
    }
 
    private EditText etGroupName;
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.create_new_group, container);
        etGroupName = (EditText) view.findViewById(R.id.etCreateNewGroupSetName);
        getDialog().setTitle("Create New Group");
 
        // Show soft keyboard automatically
        etGroupName.requestFocus();
        etGroupName.setOnEditorActionListener(this);
 
        return view;
    }

	@Override
	public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
		if (EditorInfo.IME_ACTION_DONE == actionId) {
            // Return input text to activity
            EditNameDialogListener activity = (EditNameDialogListener) getActivity();
            activity.onFinishEditDialog(etGroupName.getText().toString());
            this.dismiss();
            return true;
        }
        return false;
	}

}
