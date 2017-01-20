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

public class OauthData extends AbstractResource {

    private static final String OAUTH_DATA = "oauth_data";
    private static final String OAUTH_ACCESS_TOKEN = "access_token";
    private static final String OAUTH_TOKEN_TYPE = "token_type";
    private static final String OAUTH_REFRESH_TOKEN = "refresh_token";
    private static final String OAUTH_EXPIRES_IN = "expires_in";

    public OauthData(JSONObject json) throws HelloSignException {
        super(json, OAUTH_DATA);
    }

    public String getAccessToken() {
        return getString(OAUTH_ACCESS_TOKEN);
    }

    public String getTokenType() {
        return getString(OAUTH_TOKEN_TYPE);
    }

    public String getRefreshToken() {
        return getString(OAUTH_REFRESH_TOKEN);
    }

    public Integer getExpiresIn() {
        return getInteger(OAUTH_EXPIRES_IN);
    }
}
