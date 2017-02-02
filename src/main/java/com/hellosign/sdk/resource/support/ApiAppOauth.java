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

import java.util.Set;
import java.util.HashSet;

import org.json.JSONArray;
import org.json.JSONObject;

import com.hellosign.sdk.HelloSignException;
import com.hellosign.sdk.resource.AbstractResource;
import com.hellosign.sdk.resource.support.types.ApiAppOauthScopeType;

/**
 * An object describing an API app's OAuth properties.
 * 
 * @author "Chris Paul (chris@hellosign.com)"
 */
public class ApiAppOauth extends AbstractResource {

    public static final String APIAPP_OAUTH_KEY = "oauth";
    public static final String APIAPP_OAUTH_CALLBACK_URL = "callback_url";
    public static final String APIAPP_OAUTH_SCOPES = "scopes";
    public static final String APIAPP_OAUTH_SECRET = "secret";

    private Set<ApiAppOauthScopeType> scopes = new HashSet<ApiAppOauthScopeType>();

    /**
     * Default constructor.
     */
    public ApiAppOauth() {
        super();
    }

    /**
     * Constructor that instantiates an ApiApp OAuth object based on the JSON
     * response from the HelloSign API.
     * 
     * @param json JSONObject
     * @throws HelloSignException thrown if there is a problem updating the
     *         OAuth scopes.
     */
    public ApiAppOauth(JSONObject json) throws HelloSignException {
        super(json, APIAPP_OAUTH_KEY);
        if (has(APIAPP_OAUTH_SCOPES)) {
            try {
                JSONArray scopeArray = (JSONArray) get(APIAPP_OAUTH_SCOPES);
                for (int i = 0; i < scopeArray.length(); i++) {
                    String scope = scopeArray.getString(i);
                    scopes.add(ApiAppOauthScopeType.valueOf(scope));
                }
            } catch (Exception ex) {
                throw new HelloSignException(ex);
            }
        }
    }

    /**
     * The app's OAuth callback URL.
     * 
     * @return String callback URL or null
     */
    public String getCallbackUrl() {
        return getString(APIAPP_OAUTH_CALLBACK_URL);
    }

    /**
     * True if the OAuth callback is set.
     * 
     * @return Boolean
     */
    public Boolean hasCallbackUrl() {
        return has(APIAPP_OAUTH_CALLBACK_URL);
    }

    /**
     * The app's OAuth secret.
     * 
     * @return String or null
     */
    public String getSecret() {
        return getString(APIAPP_OAUTH_SECRET);
    }

    /**
     * Array of OAuth scopes used by the app.
     * 
     * @return List
     */
    public Set<ApiAppOauthScopeType> getScopes() {
        return scopes;
    }

    /**
     * Set this API app OAuth callback.
     * 
     * @param url String
     */
    public void setCallbackUrl(String url) {
        set(APIAPP_OAUTH_CALLBACK_URL, url);
    }

    /**
     * Set this API app's OAuth scopes.
     * 
     * @param scopes List
     */
    public void setScopes(Set<ApiAppOauthScopeType> scopes) {
        this.scopes = scopes;
    }

    /**
     * Add a scope to this API App's OAuth scope list. Duplicates will be
     * ignored.
     * 
     * @param scope ApiAppOauthScopeType
     */
    public void addScope(ApiAppOauthScopeType scope) {
        this.scopes.add(scope);
    }

    /**
     * Clear all OAuth scopes for this API App.
     */
    public void clearScopes() {
        this.scopes = new HashSet<ApiAppOauthScopeType>();
    }

    /**
     * Remove the specified OAuth scope from this API App.
     * 
     * @param scope ApiAppOauthScopeType
     */
    public void removeScope(ApiAppOauthScopeType scope) {
        this.scopes.remove(scope);
    }
}
