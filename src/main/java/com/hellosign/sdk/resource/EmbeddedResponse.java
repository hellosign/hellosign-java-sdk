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

import java.util.Date;

import org.json.JSONObject;

import com.hellosign.sdk.HelloSignException;

/**
 * Class to hold an embedded signature request response.
 * 
 * @author "Chris Paul (chris@hellosign.com)"
 */
public class EmbeddedResponse extends AbstractResource {

    private static final String EMBEDDED_KEY = "embedded";
    private static final String EMBEDDED_EDIT_URL = "edit_url";
    private static final String EMBEDDED_SIGN_URL = "sign_url";
    private static final String EMBEDDED_EXPIRES_AT = "expires_at";

    public EmbeddedResponse(JSONObject json) throws HelloSignException {
        super(json, EMBEDDED_KEY);
    }

    public String getSignUrl() {
        return getString(EMBEDDED_SIGN_URL);
    }

    public String getEditUrl() {
        return getString(EMBEDDED_EDIT_URL);
    }

    public Date getExpiresAt() {
        return getDate(EMBEDDED_EXPIRES_AT);
    }
}
