package com.rajasaur.ctfdroid.soap;

public class Login extends SOAPHelper {

	private String username;
	private String password;
	private static final String endPointPath = "/ce-soap60/services/CollabNet"; 
	
	public Login(String username, String password) {
		super(endPointPath, null, "login");
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
