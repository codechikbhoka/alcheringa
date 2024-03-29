package com.iitg.alcheringa.fragments;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.iitg.alcheringa.R;

@SuppressLint("NewApi")
public class QuestionFragment extends Fragment {
	Button buttonSend;
	Button buttonClear;
	EditText textMessage;

	public QuestionFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_questions,
				container, false);
		buttonSend = (Button) rootView.findViewById(R.id.button1);
		buttonClear = (Button) rootView.findViewById(R.id.button2);
		textMessage = (EditText) rootView.findViewById(R.id.editText1);
		textMessage.setHint("You will receive answer via notification.");
		
		
		buttonSend.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				String to = "alcheroid@gmail.com";
				String subject = "Alchering'15 : Query";
				String message = textMessage.getText().toString();

				Intent email = new Intent(Intent.ACTION_SEND);
				email.putExtra(Intent.EXTRA_EMAIL, new String[] { to });
				email.putExtra(Intent.EXTRA_SUBJECT, subject);
				email.putExtra(Intent.EXTRA_TEXT, message);

				// need this to prompts email client only
				email.setType("message/rfc822");
				startActivity(Intent.createChooser(email,
						"Choose an Email client :"));
				textMessage.setText("");
			}
		});

		buttonClear.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				textMessage.setText("");

			}

		});

		return rootView;
	}
}