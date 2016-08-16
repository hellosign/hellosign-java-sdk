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
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

import java.io.File;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.json.JSONObject;

import com.hellosign.sdk.HelloSignException;
import com.hellosign.sdk.resource.support.ApiAppOauth;
import com.hellosign.sdk.resource.support.types.ApiAppOauthScopeType;

/**
 * Contains information about an API App
 * 
 * @author "Chris Paul (chris@hellosign.com)"
 */
public class ApiApp extends AbstractResource {

    public static final String APIAPP_KEY = "api_app";
    public static final String APIAPP_CALLBACK_URL = "callback_url";
    public static final String APIAPP_CLIENT_ID = "client_id";
    public static final String APIAPP_CREATED_AT = "created_at";
    public static final String APIAPP_DOMAIN = "domain";
    public static final String APIAPP_IS_APPROVED = "is_approved";
    public static final String APIAPP_NAME = "name";
    public static final String APIAPP_OWNER_ACCOUNT = "owner_account";
    public static final String APIAPP_CUSTOM_LOGO = "custom_logo_file";
    
    private ApiAppOauth oauth = null;
    private Account owner_account = null;
    private File custom_logo = null;

    /**
     * Default constructor.
     */
    public ApiApp() {
        super();
    }

    /**
     * Constructor that instantiates an ApiApp object based
     * on the JSON response from the HelloSign API.
     * @param json JSONObject
     * @throws HelloSignException thrown if there is a problem
     * parsing the JSONObject
     */
    public ApiApp(JSONObject json) throws HelloSignException {
        super(json, APIAPP_KEY);
        owner_account = new Account(dataObj, APIAPP_OWNER_ACCOUNT);
        if (dataObj.has(ApiAppOauth.APIAPP_OAUTH_KEY)) {
            oauth = new ApiAppOauth(dataObj);
        }
    }

    /**
     * The app's callback URL (for events).
     * @return String callback URL or null
     */
    public String getCallbackUrl() {
        return getString(APIAPP_CALLBACK_URL);
    }

    /**
     * True if the callback URL is non-null.
     * @return Boolean
     */
    public Boolean hasCallbackUrl() {
        return has(APIAPP_CALLBACK_URL);
    }

    /**
     * Set the callback URL for this API app's events.
     * @param url String
     */
    public void setCallbackUrl(String url) {
        set(APIAPP_CALLBACK_URL, url);
    }

    /**
     * The app's client ID.
     * @return String client ID
     */
    public String getClientId() {
        return getString(APIAPP_CLIENT_ID);
    }

    /**
     * Returns true if this app has a client ID.
     * @return boolean
     */
    public boolean hasClientId() {
        return has(APIAPP_CLIENT_ID);
    }

    /**
     * The time that the app was created.
     * @return Date
     */
    public Date getCreatedAt() {
        return getDate(APIAPP_CREATED_AT);
    }

    /**
     * The domain name associated with the app.
     * @return String domain name
     */
    public String getDomain() {
        return getString(APIAPP_DOMAIN);
    }

    /**
     * True if the domain has been set.
     * @return Boolean
     */
    public Boolean hasDomain() {
        return has(APIAPP_DOMAIN);
    }

    /**
     * Set this API app's domain.
     * @param domain String
     */
    public void setDomain(String domain) {
        set(APIAPP_DOMAIN, domain);
    }

    /**
     * Boolean to indicate if the app has been approved.
     * @return Boolean
     */
    public Boolean isApproved() {
        return getBoolean(APIAPP_IS_APPROVED);
    }

    /**
     * The name of the app.
     * @return String name
     */
    public String getName() {
        return getString(APIAPP_NAME);
    }

    /**
     * True if the name is set for this API App.
     * @return Boolean
     */
    public Boolean hasName() {
        return has(APIAPP_NAME);
    }

    /**
     * Set this API app's name.
     * @param name String
     */
    public void setName(String name) {
        set(APIAPP_NAME, name);
    }

    /**
     * An object describing the app's OAuth properties.
     * @return ApiAppOauth
     */
    public ApiAppOauth getOauthInfo() {
        return oauth;
    }

    public void setOAuthCallbackUrl(String url) {
        if (oauth == null) {
            oauth = new ApiAppOauth();
        }
        oauth.setCallbackUrl(url);
    }

    /**
     * Set this API app's OAuth scopes.
     * @param scopes List of ApiAppOauthScopeType
     */
    public void setScopes(Set<ApiAppOauthScopeType> scopes) {
        if (oauth == null) {
            oauth = new ApiAppOauth();
        }
        oauth.setScopes(scopes);
    }

    /**
     * Add a scope to this API App's OAuth scope list.
     * Duplicates will be ignored.
     * @param scope ApiAppOauthScopeType
     */
    public void addScope(ApiAppOauthScopeType scope) {
        if (oauth == null) {
            oauth = new ApiAppOauth();
        }
        oauth.addScope(scope);
    }

    /**
     * Clear all OAuth scopes for this API App.
     */
    public void clearScopes() {
        if (oauth == null) {
            return;
        }
        oauth.clearScopes();
    }

    /**
     * Remove the specified OAuth scope from this API App.
     * @param scope ApiAppOauthScopeType
     */
    public void removeScope(ApiAppOauthScopeType scope) {
        if (oauth == null) {
            return;
        }
        oauth.removeScope(scope);
    }

    /**
     * An object describing the app's owner.
     * 
     * NOTE: This Account object will only have the owner's
     * account ID and email address. All other values will
     * be null.
     * 
     * @return Account
     */
    public Account getOwnerAccount() {
        return owner_account;
    }

    /**
     * Add a custom logo image to this API app.
     * @param f File
     */
    public void setCustomLogo(File f) {
        custom_logo = f;
    }

    /**
     * Internal method used to retrieve the necessary POST fields to submit the
     * API app to HelloSign.
     * @return Map
     * @throws HelloSignException thrown if there is a problem serializing the
     * POST fields.
     */
    public Map<String, Serializable> getPostFields() throws HelloSignException {
        Map<String, Serializable> fields = new HashMap<String, Serializable>();
        try {
            if (hasName()) {
                fields.put(APIAPP_NAME, getName());
            }
            if (hasDomain()) {
                fields.put(APIAPP_DOMAIN, getDomain());
            }
            if (hasCallbackUrl()) {
                fields.put(APIAPP_CALLBACK_URL, getCallbackUrl());
            }
            if (custom_logo != null && custom_logo.exists()) {
                fields.put(APIAPP_CUSTOM_LOGO, custom_logo);
            }
            ApiAppOauth oauth = getOauthInfo();
            if (oauth != null) {
                if (oauth.hasCallbackUrl()) {
                    fields.put("oauth[callback_url]", oauth.getCallbackUrl());
                }
                Set<ApiAppOauthScopeType> scopes = oauth.getScopes();
                if (scopes.size() > 0) {
                    String scopeStr = "";
                    for (ApiAppOauthScopeType type : scopes) {
                        scopeStr += type.toString() + ",";
                    }
                    scopeStr = scopeStr.substring(0, scopeStr.length() - 1);
                    fields.put("oauth[scopes]", scopeStr);
                }
            }
        } catch (Exception e) {
            throw new HelloSignException(e);
        }
        return fields;
    }
}
