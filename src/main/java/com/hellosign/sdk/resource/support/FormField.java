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
import com.hellosign.sdk.resource.support.types.ValidationType;

/**
 * This class represents a HelloSign Form Field object. 
 * 
 * @author "Chris Paul (chris@hellosign.com)"
 */
public class FormField extends AbstractResource {

    private static final String FORM_FIELD_API_ID = "api_id";
    private static final String FORM_FIELD_NAME = "name";
    private static final String FORM_FIELD_TYPE = "type";
    private static final String FORM_FIELD_X = "x";
    private static final String FORM_FIELD_Y = "y";
    private static final String FORM_FIELD_WIDTH = "width";
    private static final String FORM_FIELD_HEIGHT = "height";
    private static final String FORM_FIELD_SIGNER = "signer";
    private static final String FORM_FIELD_REQUIRED = "required";
    private static final String FORM_FIELD_PAGE = "page";
    private static final String FORM_FIELD_VALIDATION_TYPE = "validation_type";

    public FormField() {
        super();
    }

    public FormField(JSONObject json) throws HelloSignException {
        super(json, null);
    }

    public String getApiId() {
        return getString(FORM_FIELD_API_ID);
    }
    public void setApiId(String apiId) {
        set(FORM_FIELD_API_ID, apiId);
    }

    public String getName() {
        return getString(FORM_FIELD_NAME);
    }
    public void setName(String name) {
        set(FORM_FIELD_NAME, name);
    }
    public boolean hasName() {
        return has(FORM_FIELD_NAME);
    }
    public FieldType getType() {
        return FieldType.valueOf(getString(FORM_FIELD_TYPE));
    }
    public void setType(FieldType type) {
        set(FORM_FIELD_TYPE, type.toString());
    }
    public String getTypeString() {
        if (has(FORM_FIELD_TYPE)) {
            return getType().toString();
        }
        return null;
    }
    public Integer getX() {
        return getInteger(FORM_FIELD_X);
    }
    public void setX(Integer x) {
        set(FORM_FIELD_X, x);
    }
    public Integer getY() {
        return getInteger(FORM_FIELD_Y);
    }
    public void setY(Integer y) {
        set(FORM_FIELD_Y, y);
    }
    public Integer getWidth() {
        return getInteger(FORM_FIELD_WIDTH);
    }
    public void setWidth(Integer width) {
        set(FORM_FIELD_WIDTH, width);
    }
    public Integer getHeight() {
        return getInteger(FORM_FIELD_HEIGHT);
    }
    public void setHeight(Integer height) {
        set(FORM_FIELD_HEIGHT, height);
    }
    public Integer getSigner() {
        return getInteger(FORM_FIELD_SIGNER);
    }
    public void setSigner(Integer signer) {
        set(FORM_FIELD_SIGNER, signer);
    }
    public Boolean getRequired() {
        return getBoolean(FORM_FIELD_REQUIRED);
    }
    public void setRequired(Boolean required) {
        set(FORM_FIELD_REQUIRED, required);
    }
    public void setPage(Integer page) {
        set(FORM_FIELD_PAGE, page);
    }
    public Integer getPage() {
        return getInteger(FORM_FIELD_PAGE);
    }
    public void setValidationType(ValidationType type) {
        set(FORM_FIELD_VALIDATION_TYPE, type.toString());
    }
    public ValidationType getValidationType() {
        return ValidationType.valueOf(getString(FORM_FIELD_VALIDATION_TYPE));
    }
    public String getValidationTypeString() {
        if (has(FORM_FIELD_VALIDATION_TYPE)) {
            return getValidationType().toString();
        }
        return null;
    }
}
