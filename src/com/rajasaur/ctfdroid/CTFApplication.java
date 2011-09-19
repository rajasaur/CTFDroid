package com.rajasaur.ctfdroid;

import android.app.Application;

/**
 * Class that maintains the details that are common for the entire CTFDroid app
 * 
 * @author raja
 *
 */
public class CTFApplication extends Application {
	private static CTFApplication instance;
	private String soapSessionId;
	private String ctfServer;
	
	public CTFApplication() {
		super();
	}

	@Override
	public void onCreate() {
		super.onCreate();
		instance = this;
	}
	
	public static CTFApplication getInstance() {
		return instance;
	}
	
	public String getSoapSessionId() {
		return soapSessionId;
	}

	public void setSoapSessionId(String soapSessionId) {
		this.soapSessionId = soapSessionId;
	}

	public String getCtfServer() {
		return ctfServer;
	}

	public void setCtfServer(String ctfServer) {
		this.ctfServer = ctfServer;
	}
	
	
}
