package com.rajasaur.ctfdroid;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.AndroidHttpTransport;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;


public class Dashboard extends Activity {

	private String namespace = "http://schema.open.collab.net/sfee50/soap50/service";
	private String methodName = "login";
	private String soapAction = "";
	private String soapEndpoint = "https://forge.collab.net/ce-soap50/services/CollabNet";
	
	@SuppressWarnings("deprecation")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dashboard);
		TextView usernameField = (TextView) findViewById(R.id.username);
		usernameField.setText(getIntent().getStringExtra("username"));
		TextView passwordField = (TextView) findViewById(R.id.password);
		passwordField.setText(getIntent().getStringExtra("password"));
		TextView statusField = (TextView) findViewById(R.id.status);

		
		// Access SOAP Services
		SoapObject request = new SoapObject(namespace, methodName);
		PropertyInfo loginParam = new PropertyInfo();
		loginParam.setName("userName");
		loginParam.setValue(getIntent().getStringExtra("username"));
		loginParam.setType(String.class);
		request.addProperty(loginParam);
		
		PropertyInfo passwordParam = new PropertyInfo();
		passwordParam.setName("password");
		passwordParam.setValue(getIntent().getStringExtra("password"));
		passwordParam.setType(String.class);
		request.addProperty(passwordParam);
		
		SoapSerializationEnvelope envelope = 
			new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.setOutputSoapObject(request);
		
		AndroidHttpTransport transport = 
			new AndroidHttpTransport(soapEndpoint);
		String status = "";
		try {
			transport.call(soapAction, envelope);
			String response = (String) envelope.getResponse();
			status = "Success: SoapID: " + response;
		} catch(Exception ex) {
			ex.printStackTrace();
			status = "Failed: " + ex.getMessage();
		}

		statusField.setText(status);
	}
}
