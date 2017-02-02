package com.hellosign.sdk.http;

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

import java.net.HttpURLConnection;

import javax.xml.bind.DatatypeConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hellosign.sdk.HelloSignException;

/**
 * This class provides convenience methods for handling authentication
 * information for a HelloSignClient instance. We usually only want to use one
 * of these methods, but don't necessarily want to have to pass around all of
 * the information in method parameters.
 * 
 * @author Chris Paul (chris@hellosign.com)
 */
public class Authentication {

    private static final Logger logger = LoggerFactory.getLogger(Authentication.class);

    private String apiKey = new String();
    private String accessToken = new String();
    private String accessTokenType = new String();

    private static final String[] allowedOauthOps = { "account", "signature_request", "reusable_form", "template" };

    public Authentication() {}

    public Authentication(String apiKey) {
        this.apiKey = apiKey;
    }

    public Authentication(Authentication clone) throws HelloSignException {
        if (clone.hasApiKey()) {
            setApiKey(clone.getApiKey());
        }
        if (clone.hasAccessToken()) {
            setAccessToken(clone.getAccessToken(), clone.getAccessTokenType());
        }
    }

    /**
     * Returns a protected copy of the API key String.
     * 
     * @return String API key
     */
    public String getApiKey() {
        return new String(apiKey);
    }

    /**
     * Sets the API key to use for authenticating this client.
     * 
     * @param apiKey String API Key
     */
    public void setApiKey(String apiKey) {
        if (apiKey == null) {
            return;
        }
        this.apiKey = new String(apiKey);
    }

    /**
     * Returns true if the API Key has been set.
     * 
     * @return true, or false if the key is empty
     */
    public boolean hasApiKey() {
        return !("".equals(apiKey));
    }

    /**
     * Returns a protected copy of the access token.
     * 
     * @return String access token
     */
    public String getAccessToken() {
        return new String(accessToken);
    }

    /**
     * Returns a protected copy of the access token type string.
     * 
     * @return String access token type
     */
    public String getAccessTokenType() {
        return new String(accessTokenType);
    }

    /**
     * Sets the access token for the HelloSign client authentication.
     * 
     * @param accessToken String
     * @param tokenType String
     * @throws HelloSignException if either the accessToken or tokenType are
     *         null
     */
    public void setAccessToken(String accessToken, String tokenType) throws HelloSignException {
        if (accessToken == null) {
            throw new HelloSignException("Access Token cannot be null");
        }
        if (tokenType == null) {
            throw new HelloSignException("Token Type cannot be null");
        }
        this.accessToken = new String(accessToken);
        this.accessTokenType = new String(tokenType);
    }

    /**
     * Returns true if an access token and token type have been provided.
     * 
     * @return true or false if either are not set
     */
    public boolean hasAccessToken() {
        return !("".equals(accessToken) || "".equals(accessTokenType));
    }

    private boolean isOperationOauth(String url) {
        for (String op : allowedOauthOps) {
            if (url.contains(op)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Authorizes the HTTP connection using this instance's credentials.
     * 
     * @param httpConn HttpURLConnection to be authenticated
     * @param url String URL against which this connection should be authorized.
     */
    public void authenticate(HttpURLConnection httpConn, String url) {
        String authorization = null;
        if (hasAccessToken() && isOperationOauth(url)) {
            logger.debug("Using OAuth token to authenticate");
            authorization = getAccessTokenType() + " " + getAccessToken();
        } else if (hasApiKey()) {
            logger.debug("Using API Key to authenticate");
            String apiKey = getApiKey() + ":";
            authorization = "Basic " + DatatypeConverter.printBase64Binary(apiKey.getBytes()).trim();
        }
        if (authorization != null) {
            logger.debug("Authorization: " + authorization);
            httpConn.setRequestProperty("authorization", authorization);
        }
    }
}
