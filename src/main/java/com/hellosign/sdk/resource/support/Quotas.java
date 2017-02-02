package com.hellosign.sdk.resource.support;

/**
 * The MIT License (MIT)
 * 
 * Copyright (C) 2017 hellosign.com
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

public class Quotas extends AbstractResource {
    public static final String QUOTAS_KEY = "quotas";
    public static final String QUOTA_TEMPLATES_LEFT = "templates_left";
    public static final String QUOTA_DOCUMENTS_LEFT = "documents_left";
    public static final String QUOTA_API_SIG_REQUESTS_LEFT = "api_signature_requests_left";

    public Quotas(JSONObject json) throws HelloSignException {
        super(json, QUOTAS_KEY);
    }

    public Integer getTemplatesLeft() {
        return getInteger(QUOTA_TEMPLATES_LEFT);
    }

    public Integer getApiSignatureRequestsLeft() {
        return getInteger(QUOTA_API_SIG_REQUESTS_LEFT);
    }

    public Integer getDocumentsLeft() {
        return getInteger(QUOTA_DOCUMENTS_LEFT);
    }
}
