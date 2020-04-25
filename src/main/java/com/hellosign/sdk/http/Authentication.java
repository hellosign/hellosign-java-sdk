package com.hellosign.sdk.http;

import com.hellosign.sdk.HelloSignException;
import java.net.HttpURLConnection;
import java.util.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class provides convenience methods for handling authentication information for a
 * HelloSignClient instance. We usually only want to use one of these methods, but don't necessarily
 * want to have to pass around all of the information in method parameters.
 */
public class Authentication {

    private static final Logger logger = LoggerFactory.getLogger(Authentication.class);
    private static final String[] allowedOauthOps = {"account", "signature_request",
        "reusable_form", "template"};
    private String apiKey = "";
    private String accessToken = "";
    private String accessTokenType = "";

    public Authentication() {
    }

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
        return apiKey;
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
        this.apiKey = apiKey;
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
        return accessToken;
    }

    /**
     * Returns a protected copy of the access token type string.
     *
     * @return String access token type
     */
    public String getAccessTokenType() {
        return accessTokenType;
    }

    /**
     * Sets the access token for the HelloSign client authentication.
     *
     * @param accessToken String
     * @param tokenType String
     * @throws HelloSignException if either the accessToken or tokenType are null
     */
    public void setAccessToken(String accessToken, String tokenType) throws HelloSignException {
        if (accessToken == null) {
            throw new HelloSignException("Access Token cannot be null");
        }
        if (tokenType == null) {
            throw new HelloSignException("Token Type cannot be null");
        }
        this.accessToken = accessToken;
        this.accessTokenType = tokenType;
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
            authorization = "Basic " + Base64.getEncoder().encodeToString(apiKey.getBytes()).trim();

        }
        if (authorization != null) {
            logger.debug("Authorization: " + authorization);
            httpConn.setRequestProperty("authorization", authorization);
        }
    }
}
