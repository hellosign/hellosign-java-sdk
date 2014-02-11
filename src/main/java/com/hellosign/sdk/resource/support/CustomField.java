package com.hellosign.sdk.resource.support;

import org.json.JSONObject;

import com.hellosign.sdk.HelloSignException;
import com.hellosign.sdk.resource.AbstractResource;
import com.hellosign.sdk.resource.support.types.FieldType;

/**
 * This class represents a HelloSign Custom Field. This is a field that is
 * set up during the creation of the signature request or template and contains
 * information that must be filled in by the requester, prior to sending.
 * 
 * Right now, this is only applicable to text fields.
 * 
 * @author "Chris Paul (chris@hellosign.com)"
 */
public class CustomField extends AbstractResource {

	public static final String CUSTOM_FIELD_NAME = "name";
	public static final String CUSTOM_FIELD_TYPE = "type";
	public static final String CUSTOM_FIELD_API_ID = "api_id";
	
	public CustomField() {
		super();
	}
	
	public CustomField(JSONObject json) throws HelloSignException {
		super(json, null);
	}
	
	public String getName() {
		return getString(CUSTOM_FIELD_NAME);
	}
	public FieldType getType() {
		return FieldType.valueOf(getString(CUSTOM_FIELD_TYPE));
	}
	public String getTypeString() {
		if (has(CUSTOM_FIELD_TYPE)) {
			return getType().toString();
		}
		return null;
	}
	public String getApiId() {
		return getString(CUSTOM_FIELD_API_ID);
	}
}
