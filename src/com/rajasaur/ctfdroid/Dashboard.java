package com.rajasaur.ctfdroid;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;


public class Dashboard extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dashboard);
		TextView usernameField = (TextView) findViewById(R.id.username);
		usernameField.setText(getIntent().getStringExtra("username"));
		TextView passwordField = (TextView) findViewById(R.id.password);
		passwordField.setText(getIntent().getStringExtra("password"));
	}
}
