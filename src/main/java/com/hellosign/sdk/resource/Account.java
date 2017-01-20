package com.hellosign.sdk.resource;

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
import com.hellosign.sdk.resource.support.OauthData;
import com.hellosign.sdk.resource.support.Quotas;
import com.hellosign.sdk.resource.support.types.RoleType;

/**
 * Stores HelloSign Account information.
 * 
 * @author "Chris Paul (chris@hellosign.com)"
 */
public class Account extends AbstractResource {

    public static final String ACCOUNT_KEY = "account";
    public static final String ACCOUNT_ID = "account_id";
    public static final String ACCOUNT_EMAIL_ADDRESS = "email_address";
    public static final String ACCOUNT_IS_PAID_HS = "is_paid_hs";
    public static final String ACCOUNT_IS_PAID_HF = "is_paid_hf";
    public static final String ACCOUNT_CALLBACK_URL = "callback_url";
    public static final String ACCOUNT_ROLE_CODE = "role_code";
    public static final String OAUTH_DATA = "oauth_data";
    public static final String ACCOUNT_PASSWORD = "password";

    private Quotas quotas;
    private OauthData oauthData;

    public Account() {
        super();
    }

    public Account(JSONObject json) throws HelloSignException {
        this(json, ACCOUNT_KEY);
    }

    public Account(JSONObject json, String custom_key) throws HelloSignException {
        super(json, custom_key);
        quotas = new Quotas(dataObj);
    }

    public String getId() {
        return getString(ACCOUNT_ID);
    }

    public boolean hasId() {
        return has(ACCOUNT_ID);
    }

    public String getEmail() {
        return getString(ACCOUNT_EMAIL_ADDRESS);
    }

    public boolean hasEmail() {
        return has(ACCOUNT_EMAIL_ADDRESS);
    }

    public boolean isPaidHS() {
        return getBoolean(ACCOUNT_IS_PAID_HS);
    }

    public boolean isPaidHF() {
        return getBoolean(ACCOUNT_IS_PAID_HF);
    }

    public Integer getTemplatesLeft() {
        return quotas.getTemplatesLeft();
    }

    public Integer getApiSignatureRequestsLeft() {
        return quotas.getApiSignatureRequestsLeft();
    }

    public Integer getDocumentsLeft() {
        return quotas.getDocumentsLeft();
    }

    public String getCallbackUrl() {
        return getString(ACCOUNT_CALLBACK_URL);
    }

    public boolean hasCallbackUrl() {
        return has(ACCOUNT_CALLBACK_URL);
    }

    public RoleType getRoleCode() throws HelloSignException {
        String code = getString(ACCOUNT_ROLE_CODE);
        for (RoleType type : RoleType.values()) {
            if (type.toString().equalsIgnoreCase(code)) {
                return type;
            }
        }
        throw new HelloSignException("Unknown role code: " + code);
    }

    public boolean hasRoleCode() {
        return has(ACCOUNT_ROLE_CODE);
    }

    public Quotas getQuotas() {
        return quotas;
    }

    public OauthData getOauthData() throws HelloSignException {
        return oauthData;
    }

    public void setOauthData(OauthData data) {
        oauthData = data;
    }
}
