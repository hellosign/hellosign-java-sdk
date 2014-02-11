package com.hellosign.sdk.resource;

import org.json.JSONObject;

import com.hellosign.sdk.HelloSignException;
import com.hellosign.sdk.resource.support.OauthData;
import com.hellosign.sdk.resource.support.types.RoleType;

/**
 * Stores HelloSign Account information.
 * 
 * @author "Chris Paul (chris@hellosign.com)"
 */
public class Account extends AbstractResource {
	
	public static final String ACCOUNT_KEY = "account";
	public static final String ACCOUNT_ID = "account_id";
	public static final String ACCOUNT_EMAIL_ADDRESS = "email_address";
	public static final String ACCOUNT_IS_PAID_HS = "is_paid_hs";
	public static final String ACCOUNT_IS_PAID_HF = "is_paid_hf";
	public static final String ACCOUNT_TEMPLATES_LEFT = "templates_left";
	public static final String ACCOUNT_API_SIG_REQS_LEFT = "api_signature_requests_left";
	public static final String ACCOUNT_CALLBACK_URL = "callback_url";
	public static final String ACCOUNT_ROLE_CODE = "role_code";
	public static final String OAUTH_DATA = "oauth_data";
	public static final String ACCOUNT_PASSWORD = "password";
	
	public Account() {
		super();
	}
	
	public Account(JSONObject json) throws HelloSignException {
		super(json, ACCOUNT_KEY);
	}

	public String getId() {
		return getString(ACCOUNT_ID);
	}
	
	public boolean hasId() {
		return has(ACCOUNT_ID);
	}
	
	public String getEmail() {
		return getString(ACCOUNT_EMAIL_ADDRESS);
	}
	
	public boolean hasEmail() {
		return has(ACCOUNT_EMAIL_ADDRESS);
	}
	
	public boolean isPaidHS() {
		return getBoolean(ACCOUNT_IS_PAID_HS);
	}
	
	public boolean isPaidHF() {
		return getBoolean(ACCOUNT_IS_PAID_HF);
	}
	
	public Integer getTemplatesLeft() {
		return getInteger(ACCOUNT_TEMPLATES_LEFT);
	}
	
	public Integer getApiSigReqsLeft() {
		return getInteger(ACCOUNT_API_SIG_REQS_LEFT);
	}
	
	public String getCallbackUrl() {
		return getString(ACCOUNT_CALLBACK_URL);
	}
	
	public boolean hasCallbackUrl() {
		return has(ACCOUNT_CALLBACK_URL);
	}
	
	public RoleType getRoleCode() throws HelloSignException {
		String code = getString(ACCOUNT_ROLE_CODE);
		for (RoleType type : RoleType.values()) {
			if (type.toString().equalsIgnoreCase(code)) {
				return type;
			}
		}
		throw new HelloSignException("Unknown role code: " + code);
	}
	
	public boolean hasRoleCode() {
		return has(ACCOUNT_ROLE_CODE);
	}
	
	public OauthData getOauthData() throws HelloSignException {
		return new OauthData((JSONObject) get(OAUTH_DATA));
	}
	
}
