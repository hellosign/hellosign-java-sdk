package com.hellosign.sdk.resource.support;

/**
 * The MIT License (MIT)
 * 
 * Copyright (C) 2014 hellosign.com
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

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
