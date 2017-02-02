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

import java.util.Date;

import org.json.JSONObject;

import com.hellosign.sdk.HelloSignException;
import com.hellosign.sdk.resource.AbstractResource;
import com.hellosign.sdk.resource.support.types.SignatureStatus;

/**
 * Contains information related to a signature / signer.
 * 
 * @author "Chris Paul (chris@hellosign.com)"
 */
public class Signature extends AbstractResource {

    public static final String SIGNATURE_KEY = "signature";
    public static final String SIGNATURE_ID = "signature_id";
    public static final String SIGNER_EMAIL_ADDRESS = "signer_email_address";
    public static final String SIGNER_NAME = "signer_name";
    public static final String SIGNER_ORDER = "order";
    public static final String SIGNER_STATUS_CODE = "status_code";
    public static final String SIGNER_SIGNED_AT = "signed_at";
    public static final String SIGNER_LAST_VIEWED_AT = "last_viewed_at";
    public static final String SIGNER_LAST_REMINDED_AT = "last_reminded_at";
    public static final String SIGNER_DECLINE_REASON = "decline_reason";

    public Signature() {
        super();
    }

    public Signature(JSONObject json) throws HelloSignException {
        super(json, SIGNATURE_KEY);
    }

    /**
     * Returns the ID of the signer.
     * 
     * @return String ID
     */
    public String getId() {
        return getString(SIGNATURE_ID);
    }

    /**
     * Returns true if this signature object has a signature ID.
     * 
     * @return boolean
     */
    public boolean hasId() {
        return has(SIGNATURE_ID);
    }

    /**
     * Returns the email address of the signer.
     * 
     * @return String email address
     */
    public String getEmail() {
        return getString(SIGNER_EMAIL_ADDRESS);
    }

    /**
     * Returns true if this signature object has an email address.
     * 
     * @return boolean
     */
    public boolean hasEmail() {
        return has(SIGNER_EMAIL_ADDRESS);
    }

    /**
     * Returns the name of the signer.
     * 
     * @return String signer name
     */
    public String getName() {
        return getString(SIGNER_NAME);
    }

    /**
     * Returns true if this signature object has a name
     * 
     * @return boolean
     */
    public boolean hasName() {
        return has(SIGNER_NAME);
    }

    /**
     * Returns the order of this signer.
     * 
     * @return Integer signer order
     */
    public Integer getOrder() {
        return getInteger(SIGNER_ORDER);
    }

    /**
     * Returns the status of this signature/signer.
     * 
     * @return SignatureStatus status
     */
    public SignatureStatus getStatus() {
        return SignatureStatus.valueOf(getString(SIGNER_STATUS_CODE));
    }

    /**
     * Returns the status in string format.
     * 
     * @return String status
     */
    public String getStatusString() {
        return getStatus().toString();
    }

    /**
     * Returns the date the signer signed the request.
     * 
     * @return Date date the signer signed
     */
    public Date getDateSigned() {
        return getDate(SIGNER_SIGNED_AT);
    }

    /**
     * Returns the date the signer last viewed the request.
     * 
     * @return Date date the signer last viewed
     */
    public Date getLastViewed() {
        return getDate(SIGNER_LAST_VIEWED_AT);
    }

    /**
     * Returns the date at which the last reminder was sent to this signer.
     * 
     * @return Date date the last reminder was sent
     */
    public Date getLastReminded() {
        return getDate(SIGNER_LAST_REMINDED_AT);
    }

    /**
     * Returns the reason the signer declined this request.
     * 
     * @return string or null if the request does not have a decline_reason
     */
    public String getDeclineReason() {
        if (has(SIGNER_DECLINE_REASON)) {
            return getString(SIGNER_DECLINE_REASON);
        }
        return null;
    }
}
