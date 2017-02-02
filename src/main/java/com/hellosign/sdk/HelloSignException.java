package com.hellosign.sdk;

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

/**
 * This class wraps all hellosign-java-sdk exceptions. This allows a developer
 * to determine where the exception is coming from. It also allows us to store
 * the HTTP code and error type from API calls.
 * 
 * @author "Chris Paul (chris@hellosign.com)"
 */
public class HelloSignException extends Exception {

    private static final long serialVersionUID = -2011208957359154626L;

    private Integer httpCode;
    private String type;

    public HelloSignException(Exception e) {
        super(e);
    }

    public HelloSignException(String message) {
        super(message);
    }

    public HelloSignException(String message, Throwable e) {
        super(message, e);
    }

    public HelloSignException(String message, Integer httpCode, String type) {
        this(message, httpCode, type, null);
    }

    public HelloSignException(String message, Integer httpCode, String type, Exception e) {
        super(message, e);
        this.httpCode = httpCode;
        this.type = type;
    }

    /**
     * Returns the HTTP code associated with a HelloSign API call. This may be
     * null if the exception does not involve an API request.
     * 
     * @return Integer or null if an HTTP code does not exist
     */
    public Integer getHttpCode() {
        return httpCode;
    }

    /**
     * Returns the HelloSign API error type. This may be null if the exception
     * does not involve an API request.
     * 
     * @return String or null if the error type does not exist
     */
    public String getType() {
        return type;
    }
}
