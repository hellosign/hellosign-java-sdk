package com.hellosign.sdk.resource.support;

import com.hellosign.sdk.HelloSignException;
import com.hellosign.sdk.resource.AbstractResource;
import com.hellosign.sdk.resource.support.types.ApiAppOauthScopeType;
import java.util.HashSet;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * An object describing an API app's OAuth properties.
 */
public class ApiAppOauth extends AbstractResource {

    public static final String APIAPP_OAUTH_KEY = "oauth";
    public static final String APIAPP_OAUTH_CALLBACK_URL = "callback_url";
    public static final String APIAPP_OAUTH_SCOPES = "scopes";
    public static final String APIAPP_OAUTH_SECRET = "secret";

    private Set<ApiAppOauthScopeType> scopes = new HashSet<>();

    /**
     * Default constructor.
     */
    public ApiAppOauth() {
        super();
    }

    /**
     * Constructor that instantiates an ApiApp OAuth object based on the JSON response from the
     * HelloSign API.
     *
     * @param json JSONObject
     * @throws HelloSignException thrown if there is a problem updating the OAuth scopes.
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
     * Set this API app OAuth callback.
     *
     * @param url String
     */
    public void setCallbackUrl(String url) {
        set(APIAPP_OAUTH_CALLBACK_URL, url);
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
     * Set this API app's OAuth scopes.
     *
     * @param scopes List
     */
    public void setScopes(Set<ApiAppOauthScopeType> scopes) {
        this.scopes = scopes;
    }

    /**
     * Add a scope to this API App's OAuth scope list. Duplicates will be ignored.
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
        this.scopes = new HashSet<>();
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
