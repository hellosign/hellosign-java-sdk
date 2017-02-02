package com.hellosign.sdk.resource.support;

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
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
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
 * This class represents the "response_data" portion of SignatureRequest
 * objects.
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
