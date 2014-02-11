package com.hellosign.sdk.resource;

import java.util.Date;

import org.json.JSONObject;

import com.hellosign.sdk.HelloSignException;

/**
 * Class to hold an embedded signature request response.
 * 
 * @author "Chris Paul (chris@hellosign.com)"
 */
public class EmbeddedResponse extends AbstractResource {

	private static final String EMBEDDED_KEY = "embedded";
	private static final String EMBEDDED_SIGN_URL = "sign_url";
	private static final String EMBEDDED_EXPIRES_AT = "expires_at";

	public EmbeddedResponse(JSONObject json) throws HelloSignException {
		super(json, EMBEDDED_KEY);
	}
	
	public String getSignUrl() {
		return getString(EMBEDDED_SIGN_URL);
	}
	
	public Date getExpiresAt() {
		return getDate(EMBEDDED_EXPIRES_AT);
	}
}
