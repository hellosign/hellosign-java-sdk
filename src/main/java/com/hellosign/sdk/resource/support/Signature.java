package com.hellosign.sdk.resource.support;

import java.util.Date;

import org.json.JSONObject;

import com.hellosign.sdk.HelloSignException;
import com.hellosign.sdk.resource.AbstractResource;
import com.hellosign.sdk.resource.support.types.SignatureStatus;

/**
 * POJO that stores information related to a signature captured on a 
 * HelloSign SignatureRequest.
 *  
 * @author "Chris Paul (chris@hellosign.com)"
 */
public class Signature extends AbstractResource {
	
	private static final String SIGNATURE_KEY = "signature";
	private static final String SIGNATURE_ID = "signature_id";
	private static final String SIGNER_EMAIL_ADDRESS = "signer_email_address";
	private static final String SIGNER_NAME = "signer_name";
	private static final String SIGNER_ORDER = "order";
	private static final String SIGNER_STATUS_CODE = "status_code";
	private static final String SIGNER_SIGNED_AT = "signed_at";
	private static final String SIGNER_LAST_VIEWED_AT = "last_viewed_at";
	private static final String SIGNER_LAST_REMINDED_AT = "last_reminded_at";
	
	public Signature() {
		super();
	}
	
	public Signature(JSONObject json) throws HelloSignException {
		super(json, SIGNATURE_KEY);
	}
	
	public String getId() {
		return getString(SIGNATURE_ID);
	}
	public boolean hasId() {
		return has(SIGNATURE_ID);
	}
	public String getEmail() {
		return getString(SIGNER_EMAIL_ADDRESS);
	}
	public boolean hasEmail() {
		return has(SIGNER_EMAIL_ADDRESS);
	}
	public String getName() {
		return getString(SIGNER_NAME);
	}
	public boolean hasName() {
		return has(SIGNER_NAME);
	}
	public Integer getOrder() {
		return getInteger(SIGNER_ORDER);
	}
	public SignatureStatus getStatus() {
		return SignatureStatus.valueOf(getString(SIGNER_STATUS_CODE));
	}
	public String getStatusString() {
		return getStatus().toString();
	}
	public Date getDateSigned() {
		return getDate(SIGNER_SIGNED_AT);
	}
	public Date getLastViewed() {
		return getDate(SIGNER_LAST_VIEWED_AT);
	}
	public Date getLastReminded() {
		return getDate(SIGNER_LAST_REMINDED_AT);
	}	
}
