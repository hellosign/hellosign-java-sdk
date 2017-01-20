package com.hellosign.sdk.http;

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

import java.io.Serializable;
import java.util.Map;

import com.hellosign.sdk.HelloSignException;

public class HttpPutRequest extends HttpPostRequest {

    protected String method = "PUT";

    public HttpPutRequest(String url) throws HelloSignException {
        super(url);
    }

    public HttpPutRequest(String url, Authentication auth) throws HelloSignException {
        super(url, auth);
    }

    public HttpPutRequest(String url, Map<String, Serializable> fields, Authentication auth) throws HelloSignException {
        super(url, fields, auth);
    }

    public HttpPutRequest(String url, Map<String, Serializable> fields) throws HelloSignException {
        super(url, fields);
    }
}
