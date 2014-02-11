package com.hellosign.sdk.resource.support;

import org.json.JSONObject;

import com.hellosign.sdk.HelloSignException;
import com.hellosign.sdk.resource.AbstractResource;

public class OauthData extends AbstractResource {

	private static final String OAUTH_DATA = "oauth_data";
	private static final String OAUTH_ACCESS_TOKEN = "access_token";
	private static final String OAUTH_TOKEN_TYPE = "token_type";
	private static final String OAUTH_REFRESH_TOKEN = "refresh_token";
	private static final String OAUTH_EXPIRES_IN = "expires_in";
	
	public OauthData(JSONObject json) throws HelloSignException {
		super(json, OAUTH_DATA);
	}
	
	public String getAccessToken() {
		return getString(OAUTH_ACCESS_TOKEN);
	}
	public String getTokenType() {
		return getString(OAUTH_TOKEN_TYPE);
	}
	public String getRefreshToken() {
		return getString(OAUTH_REFRESH_TOKEN);
	}
	public Integer getExpiresIn() {
		return getInteger(OAUTH_EXPIRES_IN);
	}
}
