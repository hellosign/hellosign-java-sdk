package com.hellosign.sdk.resource.support;

import org.json.JSONException;

/**
 * The MIT License (MIT)
 * 
 * Copyright (C) 2015 hellosign.com
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
 * This class represents a HelloSign Custom Field. This is a field that is
 * set up during the creation of the signature request or template and contains
 * information that must be filled in by the requester, prior to sending.
 * 
 * Right now, this is only applicable to text fields.
 * 
 * @author "Chris Paul (chris@hellosign.com)"
 */
public class CustomField extends AbstractResource {

    public static final String CUSTOM_FIELD_NAME     = "name";
    public static final String CUSTOM_FIELD_TYPE     = "type";
    public static final String CUSTOM_FIELD_API_ID   = "api_id";
    public static final String CUSTOM_FIELD_VALUE    = "value";
    public static final String CUSTOM_FIELD_EDITOR   = "editor";
    public static final String CUSTOM_FIELD_REQUIRED = "required";

    public CustomField() {
        super();
    }

    public CustomField(JSONObject json) throws HelloSignException {
        super(json, null);
    }

    public String getName() {
        return getString(CUSTOM_FIELD_NAME);
    }
    /**
     * Set the name of this custom field.
     * @param name String
     */
    public void setName(String name) {
        set(CUSTOM_FIELD_NAME, name);
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
    /**
     * Set the type for this custom field.
     * @param type FieldType
     */
    public void setType(FieldType type) {
        set(CUSTOM_FIELD_TYPE, type.toString());
    }
    public String getValue() {
        return getString(CUSTOM_FIELD_VALUE);
    }
    /**
     * Set the value for this field.
     * @param value String
     */
    public void setValue(String value) {
        set(CUSTOM_FIELD_VALUE, value);
    }
    public String getApiId() {
        return getString(CUSTOM_FIELD_API_ID);
    }
    /**
     * Set the API ID for this custom field.
     * @param apiId String
     */
    public void setApiId(String apiId) {
        set(CUSTOM_FIELD_API_ID, apiId);
    }
    public String getEditor() {
        return getString(CUSTOM_FIELD_EDITOR);
    }
    /**
     * Specify what signer role may edit this field.
     * @param editor String
     */
    public void setEditor(String editor) {
        set(CUSTOM_FIELD_EDITOR, editor);
    }
    public Boolean isRequired() {
        return getBoolean(CUSTOM_FIELD_REQUIRED);
    }
    /**
     * Specify whether this field is required.
     * @param isRequired Boolean
     */
    public void setIsRequired(Boolean isRequired) {
        set(CUSTOM_FIELD_REQUIRED, isRequired);
    }
}