package com.rajasaur.ctfdroid.soap;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.AndroidHttpTransport;

public abstract class SOAPHelper {
	private String namespace = 
		"http://schema.open.collab.net/sfee50/soap50/service";
	private String soapAction = "";
	public static String collabnetSoap = 
		"https://forge.collab.net/ce-soap50/services/CollabNet";
	public static String trackerSoap = 
		"https://forge.collab.net/ce-soap50/services/TrackerApp";
	
	private String endpoint = null;
	private String methodName = null;
	private SoapObject request = null;
	
	public SOAPHelper(String endpoint, 
					  String soapAction,
					  String methodName) {
		this.endpoint = endpoint;
		this.methodName = methodName;
		if (soapAction != null) {
			this.soapAction = soapAction;
		}
	}
	
	/**
	 * Create a SoapObject that is used to hold the PropertyInfo's
	 * for this particular request.
	 * 
	 * Fail if its already created and there is an attempt to create one
	 * more. There is only one SoapObject necessary for a single method
	 * 
	 * @param methodName Name of the SOAP call
	 * @throws Exception if the soap request is already constructed
	 */
	public void createSoapRequest() throws Exception {
		if (request == null) {
			request = new SoapObject(namespace, methodName);
		} else {
			throw new Exception("Soap Request already created");
		}
	}
	
	public void addPropertyInfo(String name, 
										Object value, 
										Class type) {
		PropertyInfo property = new PropertyInfo();
		property.setName(name);
		property.setValue(value);
		property.setType(type);
		request.addProperty(property);
	}
	
	@SuppressWarnings("deprecation")
	public Object invoke() throws Exception {
		Object response = null;
		
		SoapSerializationEnvelope envelope = 
			new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.setOutputSoapObject(request);
		
		AndroidHttpTransport transport = 
			new AndroidHttpTransport(endpoint);
		String status = "";
		try {
			transport.call(soapAction, envelope);
			response = envelope.getResponse();
		} catch(Exception ex) {
			throw ex;
		}
		return response;
	}
	
	public abstract Object execute() throws Exception;
}
