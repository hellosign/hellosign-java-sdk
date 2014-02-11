package com.hellosign.sdk.resource;

import java.io.Serializable;
import java.util.Map;

import org.json.JSONObject;

import com.hellosign.sdk.HelloSignException;

/**
 * Requests to HelloSign will have common fields such as a 
 * request title, subject, and message. This class centralizes
 * those fields. 
 * 
 * @author "Chris Paul (chris@hellosign.com)"
 */
public abstract class AbstractRequest extends AbstractResource {
	
	public static final String REQUEST_TITLE = "title";
	public static final String REQUEST_SUBJECT = "subject";
	public static final String REQUEST_MESSAGE = "message";
	public static final String REQUEST_REDIRECT_URL = "signing_redirect_url";
	public static final String REQUEST_TEST_MODE = "test_mode";

	public AbstractRequest() {
		super();
	}
	
	public AbstractRequest(JSONObject json, String optionalKey) 
			throws HelloSignException {
		super(json, optionalKey);
	}

	protected abstract Map<String, Serializable> getPostFields() throws HelloSignException;
	
	public abstract String getId();

	public String getTitle() {
		return getString(REQUEST_TITLE);
	}
	
	public void setTitle(String title) {
		set(REQUEST_TITLE, title);
	}
	
	public boolean hasTitle() {
		return has(REQUEST_TITLE);
	}
	
	public String getSubject() {
		return getString(REQUEST_SUBJECT);
	}
	public void setSubject(String subject) {
		set(REQUEST_SUBJECT, subject);
	}
	
	public boolean hasSubject() {
		return has(REQUEST_SUBJECT);
	}
	
	public String getMessage() {
		return getString(REQUEST_MESSAGE);
	}
	
	public void setMessage(String message) {
		set(REQUEST_MESSAGE, message);
	}	
	
	public boolean hasMessage() {
		return has(REQUEST_MESSAGE);
	}
	
	public void setTestMode(boolean testMode) {
		set(REQUEST_TEST_MODE, testMode);
	}
	
	public boolean isTestMode() {
		if (has(REQUEST_TEST_MODE)) {
			return getBoolean(REQUEST_TEST_MODE);
		}
		return false;
	}
	
	public String getRedirectUrl() {
		return getString(REQUEST_REDIRECT_URL);
	}
	
	public void setRedirectUrl(String url) {
		set(REQUEST_REDIRECT_URL, url);
	}
	
	public boolean hasRedirectUrl() {
		return has(REQUEST_REDIRECT_URL);
	}
}
