package com.hellosign.sdk.resource.support;

import org.json.JSONObject;

import com.hellosign.sdk.HelloSignException;
import com.hellosign.sdk.resource.AbstractResource;
import com.hellosign.sdk.resource.support.types.FieldType;

/**
 * This class represents a HelloSign Form Field object. 
 * 
 * @author "Chris Paul (chris@hellosign.com)"
 */
public class FormField extends AbstractResource {

	public FormField() {
		super();
	}
	
	public FormField(JSONObject json) throws HelloSignException {
		super(json, null);
	}
	
	public String getName() {
		return getString("name");
	}
	public void setName(String name) {
		set("name", name);
	}
	public boolean hasName() {
		return has("name");
	}
	public FieldType getType() {
		return FieldType.valueOf(getString("type"));
	}
	public void setType(FieldType type) {
		set("type", type.toString());
	}
	public String getTypeString() {
		if (has("type")) {
			return getType().toString();
		}
		return null;
	}
	public Integer getX() {
		return getInteger("x");
	}
	public void setX(Integer x) {
		set("x", x);
	}
	public Integer getY() {
		return getInteger("y");
	}
	public void setY(Integer y) {
		set("y", y);
	}
	public Integer getWidth() {
		return getInteger("width");
	}
	public void setWidth(Integer width) {
		set("width", width);
	}
	public Integer getHeight() {
		return getInteger("height");
	}
	public void setHeight(Integer height) {
		set("height", height);
	}
	public Integer getSigner() {
		return getInteger("signer");
	}
	public void setSigner(Integer signer) {
		set("signer", signer);
	}
	public Boolean getRequired() {
		return getBoolean("required");
	}
	public void setRequired(Boolean required) {
		set("required", required);
	}

}
