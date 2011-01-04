package com.rajasaur.ctfdroid;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CTFDroidMain extends Activity implements OnClickListener {
	public static final String APP = "CTFDroid";
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Button button = (Button) findViewById(R.id.submit);
        button.setClickable(true);
        button.setOnClickListener(this);
    }

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.submit:
			EditText username = (EditText) findViewById(R.id.username);
			EditText password = (EditText) findViewById(R.id.password);
			Log.d(APP, "Username: " + username.getText());
			Log.d(APP, "Password: " + password.getText());
		}
	}
}