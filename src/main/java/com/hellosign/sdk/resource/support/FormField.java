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
import com.hellosign.sdk.resource.support.types.ValidationType;

/**
 * This class represents a HelloSign Form Field object.
 * 
 * @author "Chris Paul (chris@hellosign.com)"
 */
public class FormField extends CustomField {

    private static final String FORM_FIELD_SIGNER = "signer";
    private static final String FORM_FIELD_PAGE = "page";
    private static final String FORM_FIELD_VALIDATION_TYPE = "validation_type";

    public FormField() {
        super();
    }

    public FormField(JSONObject json) throws HelloSignException {
        super(json);
    }

    /**
     * Returns the signer index for this form field.
     * 
     * @return Integer signer index or null if not set
     */
    public Integer getSigner() {
        return getInteger(FORM_FIELD_SIGNER);
    }

    /**
     * Set the signer index that should complete this form field.
     * 
     * @param signer Integer index
     */
    public void setSigner(Integer signer) {
        set(FORM_FIELD_SIGNER, signer);
    }

    /**
     * Set the page number for this component. The (x, y) coordinates will be
     * relative to the top left corner of that page.
     * 
     * @param page Integer page number
     */
    public void setPage(Integer page) {
        set(FORM_FIELD_PAGE, page);
    }

    /**
     * Returns the page number this component is on.
     * 
     * @return Integer page number, or null if not set
     */
    public Integer getPage() {
        return getInteger(FORM_FIELD_PAGE);
    }

    /**
     * Set the validation rule for this field. This will force the signer to
     * enter data that conforms to the validation rule.
     * 
     * @param type ValidationType
     */
    public void setValidationType(ValidationType type) {
        set(FORM_FIELD_VALIDATION_TYPE, type.toString());
    }

    /**
     * Return the validation rule being used for this field
     * 
     * @return ValidationType validation type, null if not set
     */
    public ValidationType getValidationType() {
        return ValidationType.valueOf(getString(FORM_FIELD_VALIDATION_TYPE));
    }

    /**
     * Return the validation rule being used for this field as a string
     * 
     * @return String validation type, null if not set
     */
    public String getValidationTypeString() {
        if (has(FORM_FIELD_VALIDATION_TYPE)) {
            return getValidationType().toString();
        }
        return null;
    }

    /**
     * Set this field as required.
     * 
     * @param isRequired boolean true if field is required by signer
     * @deprecated Use {@link #setIsRequired(Boolean)}
     */
    public void setRequired(boolean isRequired) {
        setIsRequired(isRequired);
    }
}
