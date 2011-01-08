package com.rajasaur.ctfdroid.soap;

public class Login extends SOAPHelper {

	private String username;
	private String password;
	
	public Login(String username, String password) {
		super(SOAPHelper.collabnetSoap, null, "login");
		this.username = username;
		this.password = password;
	}

	public Object execute() throws Exception {
		String sessionId = null;
		try {
			createSoapRequest();
			addPropertyInfo("userName", this.username, String.class);
			addPropertyInfo("password", this.password, String.class);
			sessionId = (String) invoke();
		} catch(Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return sessionId;
	}
}
