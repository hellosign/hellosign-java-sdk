package com.hellosign.sdk.resource.support;

import org.json.JSONObject;

import com.hellosign.sdk.HelloSignException;
import com.hellosign.sdk.resource.AbstractResource;
import com.hellosign.sdk.resource.support.types.FieldType;

/**
 * This class represents the "response_data" portion of 
 * SignatureRequest objects.
 * 
 * @author "Chris Paul (chris@hellosign.com)"
 */
public class ResponseData extends AbstractResource {
	
	private static final String RESPONSE_DATA_TYPE = "type";
	private static final String RESPONSE_DATA_VALUE = "value";
	private static final String RESPONSE_DATA_NAME = "name";
	private static final String RESPONSE_DATA_SIGNATURE_ID = "signature_id";
	private static final String RESPONSE_DATA_API_ID = "api_id";

	public ResponseData() {
		super();
	}
	
	public ResponseData(JSONObject json) throws HelloSignException {
		super(json, null);
	}
	
	public String getSignatureId() {
		return getString(RESPONSE_DATA_SIGNATURE_ID);
	}
	public void setSignatureId(String signatureId) {
		set(RESPONSE_DATA_SIGNATURE_ID, signatureId);
	}
	public String getName() {
		return getString(RESPONSE_DATA_NAME);
	}
	public void setName(String name) {
		set(RESPONSE_DATA_NAME, name);
	}
	public Object getValue() {
		return get(RESPONSE_DATA_VALUE);
	}
	public void setValue(Object value) {
		set(RESPONSE_DATA_VALUE, value);
	}
	public FieldType getType() {
		return FieldType.valueOf(getString(RESPONSE_DATA_TYPE));
	}
	public void setType(FieldType type) {
		set(RESPONSE_DATA_TYPE, type.toString());
	}
	public String getTypeString() {
		if (has(RESPONSE_DATA_TYPE)) {
			return getType().toString();
		}
		return null;
	}
	public String getApiId() {
		return getString(RESPONSE_DATA_API_ID);
	}
	public void setApiId(String apiId) {
		set(RESPONSE_DATA_API_ID, apiId);
	}
}
