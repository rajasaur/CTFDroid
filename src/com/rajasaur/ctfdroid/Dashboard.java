package com.rajasaur.ctfdroid;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.AndroidHttpTransport;

import com.rajasaur.ctfdroid.soap.Login;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;


public class Dashboard extends Activity {

	private String username;
	private String password;
	private String server;
	private String protocol;
	TextView statusField;
	
	@SuppressWarnings("deprecation")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dashboard);
		
		username = getIntent().getStringExtra("username");
		password = getIntent().getStringExtra("password");
		
		TextView usernameField = (TextView) findViewById(R.id.username);
		usernameField.setText(username);
		TextView passwordField = (TextView) findViewById(R.id.password);
		passwordField.setText(password);
		statusField = (TextView) findViewById(R.id.status);

		String protocol = getIntent().getBooleanExtra("protocol", true) ? "https"
				: "http";
		String server = protocol + "://" + getIntent().getStringExtra("server");

		CTFApplication app = (CTFApplication) getApplication();
		app.setCtfServer(server);
		LoginTask task = new LoginTask();
		task.execute(null);
	}
	
	private class LoginTask extends AsyncTask<String, String, String> {
		@Override
		protected String doInBackground(String... urls) {
			String soapSessionId = null;
			publishProgress("Authenticating...");
			Login login = new Login(username, password);
			
			try {
				soapSessionId = (String) login.execute();
			} catch (Exception e) {
				e.printStackTrace();
				soapSessionId = "Error";
			}
			return soapSessionId;
		}
		
		@Override
		protected void onProgressUpdate(String... progress) {
			statusField.setText(progress[0]);
		}
		
		@Override
		protected void onPostExecute(String message) {
			String status = "";
			if (!"Error".equals(message)) {
				CTFApplication.getInstance().setSoapSessionId(message);
				status = "Success: SoapID: " + message;
			} else {
				status = message;
			}
			statusField.setText(status);
		}
	}
}
