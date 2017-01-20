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
import java.lang.reflect.Constructor;
import java.net.HttpURLConnection;

import org.json.JSONException;
import org.json.JSONObject;

import com.hellosign.sdk.http.Authentication;
import com.hellosign.sdk.http.HttpClient;
import com.hellosign.sdk.resource.AbstractRequest;
import com.hellosign.sdk.resource.AbstractResourceList;
import com.hellosign.sdk.resource.Account;
import com.hellosign.sdk.resource.ApiApp;
import com.hellosign.sdk.resource.EmbeddedRequest;
import com.hellosign.sdk.resource.EmbeddedResponse;
import com.hellosign.sdk.resource.SignatureRequest;
import com.hellosign.sdk.resource.Team;
import com.hellosign.sdk.resource.Template;
import com.hellosign.sdk.resource.TemplateDraft;
import com.hellosign.sdk.resource.TemplateSignatureRequest;
import com.hellosign.sdk.resource.UnclaimedDraft;
import com.hellosign.sdk.resource.support.ApiAppList;
import com.hellosign.sdk.resource.support.OauthData;
import com.hellosign.sdk.resource.support.Signature;
import com.hellosign.sdk.resource.support.SignatureRequestList;
import com.hellosign.sdk.resource.support.TemplateList;

/**
 * You'll need the HelloSignClient to do just about everything, from creating
 * signatures to updating account information.
 * 
 * To use this class, instantiate a client with valid credentials like so:
 * 
 * HelloSignClient client = new HelloSignClient(user, key);
 * 
 * Then, use the client to perform your requests. The client uses the Unirest
 * library to perform its HTTP requests. (http://unirest.io/java.html).
 * 
 * @author "Chris Paul (chris@hellosign.com)"
 */
public class HelloSignClient {

    public static final String DEFAULT_ENCODING = "UTF-8";
    public static final String DEFAULT_BASE_API_URL = "https://api.hellosign.com/v3";
    public static final String DEFAULT_OAUTH_TOKEN_URL = "https://www.hellosign.com/oauth/token";

    public static final String ACCOUNT_URI = "/account";
    public static final String VALIDATE_ACCOUNT_URI = "/account/validate";
    public static final String ACCOUNT_CREATE_URI = "/account/create";
    public static final String TEAM_URI = "/team";
    public static final String TEAM_CREATE_URI = "/team/create";
    public static final String TEAM_DESTROY_URI = "/team/destroy";
    public static final String TEAM_ADD_MEMBER_URI = "/team/add_member";
    public static final String TEAM_REMOVE_MEMBER_URI = "/team/remove_member";
    public static final String SIGNATURE_REQUEST_URI = "/signature_request";
    public static final String SIGNATURE_REQUEST_LIST_URI = "/signature_request/list";
    public static final String SIGNATURE_REQUEST_SEND_URI = "/signature_request/send";
    public static final String TEMPLATE_URI = "/template";
    public static final String TEMPLATE_FILE_URI = "/template/files";
    public static final String TEMPLATE_LIST_URI = "/template/list";
    public static final String TEMPLATE_ADD_USER_URI = "/template/add_user";
    public static final String TEMPLATE_REMOVE_USER_URI = "/template/remove_user";
    public static final String TEMPLATE_DELETE_URI = "/template/delete";
    public static final String TEMPLATE_CREATE_EMBEDDED_DRAFT_URI = "/template/create_embedded_draft";
    public static final String TEMPLATE_SIGNATURE_REQUEST_URI = "/signature_request/send_with_template";
    public static final String SIGNATURE_REQUEST_CANCEL_URI = "/signature_request/cancel";
    public static final String SIGNATURE_REQUEST_REMIND_URI = "/signature_request/remind";
    public static final String SIGNATURE_REQUEST_FINAL_COPY_URI = "/signature_request/final_copy";
    public static final String SIGNATURE_REQUEST_FILES_URI = "/signature_request/files";
    public static final String SIGNATURE_REQUEST_UPDATE_URI = "/signature_request/update";
    public static final String SIGNATURE_REQUEST_EMBEDDED_URI = "/signature_request/create_embedded";
    public static final String SIGNATURE_REQUEST_EMBEDDED_TEMPLATE_URI = "/signature_request/create_embedded_with_template";
    public static final String EMBEDDED_SIGN_URL_URI = "/embedded/sign_url";
    public static final String EMBEDDED_EDIT_URL_URI = "/embedded/edit_url";
    public static final String UNCLAIMED_DRAFT_CREATE_URI = "/unclaimed_draft/create";
    public static final String UNCLAIMED_DRAFT_CREATE_EMBEDDED_URI = "/unclaimed_draft/create_embedded";
    public static final String UNCLAIMED_DRAFT_CREATE_EMBEDDED_WITH_TEMPLATE_URI = "/unclaimed_draft/create_embedded_with_template";
    public static final String API_APP_URI = "/api_app";
    public static final String API_APP_LIST_URI = "/api_app/list";

    public static final String PARAM_FILE_TYPE_URI = "file_type";
    public static final String PARAM_TEMPLATE_GET_URL = "get_url";
    public static final String FINAL_COPY_FILE_NAME = "final-copy";
    public static final String FINAL_COPY_FILE_EXT = "pdf";
    public static final String FILES_FILE_NAME = "files";
    public static final String FILES_FILE_EXT = "pdf";
    public static final String TEMPLATE_FILE_NAME = "template";
    public static final String TEMPLATE_FILE_EXT = "pdf";
    public static final String OAUTH_CODE = "code";
    public static final String OAUTH_STATE = "state";
    public static final String OAUTH_GRANT_TYPE = "grant_type";
    public static final String OAUTH_REFRESH_TOKEN = "refresh_token";
    public static final String OAUTH_GRANT_TYPE_AUTHORIZE_CODE = "authorization_code";
    public static final String OAUTH_GRANT_TYPE_REFRESH_TOKEN = "refresh_token";
    public static final String CLIENT_SECRET = "client_secret";
    public static final String CLIENT_ID = "client_id";
    public static final String EMBEDDED_TEMPLATE_SKIP_SIGNER_ROLES = "skip_signer_roles";
    public static final String EMBEDDED_TEMPLATE_SKIP_SUBJECT_MESSAGE = "skip_subject_message";

    private Authentication auth;
    private HttpClient httpClient;

    // The base URL for all standard API endpoints, which can be
    // overridden by setting the "hellosign.base.url" system property.
    private String BASE_URI;

    // The base URL for retrieving an OAuth tokens, which can be
    // overridden by setting the "hellosign.oauth.base.url" system property.
    private String OAUTH_TOKEN_URL;

    /**
     * Default constructor that allows the injection of an HttpClient.
     * 
     * In most cases, you should use the constructor that accepts your API key:
     * 
     * @see #HelloSignClient(String)
     */
    public HelloSignClient() {
        setHttpClient(new HttpClient());

        // Set overrides if present
        BASE_URI = DEFAULT_BASE_API_URL;
        String baseUrl = System.getProperty("hellosign.base.url");
        if (baseUrl != null && !baseUrl.isEmpty()) {
            BASE_URI = baseUrl;
        }
        OAUTH_TOKEN_URL = DEFAULT_OAUTH_TOKEN_URL;
        String customOauthToken = System.getProperty("hellosign.oauth.base.url");
        if (customOauthToken != null && !customOauthToken.isEmpty()) {
            OAUTH_TOKEN_URL = customOauthToken;
        }
    }

    /**
     * Creates a new HelloSign client using your API key.
     * 
     * @param apiKey
     *            String API key
     * @throws HelloSignException
     *             thrown if there's a problem setting the credentials
     * @see <a href=
     *      "https://www.hellosign.com/home/myAccount/current_tab/api">Account
     *      Settings</a>
     */
    public HelloSignClient(String apiKey) throws HelloSignException {
        this();
        Authentication a = new Authentication();
        a.setApiKey(apiKey);
        setAuth(a);
    }

    /**
     * Creates a new HelloSign client using your website account's email address
     * and password.
     * 
     * @param email
     *            String email
     * @param password
     *            String password
     * @throws HelloSignException
     *             thrown if there is a problem setting the credentials
     * @deprecated Use {@link #HelloSignClient(String)} instead.
     */
    public HelloSignClient(String email, String password) throws HelloSignException {
        this();
        Authentication a = new Authentication();
        a.setWebsiteCredentials(email, password);
        setAuth(a);
    }

    /**
     * Create a client with the provided authentication object.
     * 
     * @param auth
     *            HelloSignAuthentication
     * @throws HelloSignException
     *             thrown if the HelloSignAuthentication parameters are invalid
     *             or null
     */
    public HelloSignClient(Authentication auth) throws HelloSignException {
        this();
        setAuth(auth);
    }

    /**
     * Stores the Authentication that should be used for HTTP requests against
     * the HelloSign API.
     * 
     * @param auth
     *            Authentication
     * @throws HelloSignException
     *             thrown if there's a problem initializing the authentication
     *             credentials
     */
    public void setAuth(Authentication auth) throws HelloSignException {
        this.auth = new Authentication(auth);
    }

    /**
     * Retrieves the authentication details being used by this client.
     * 
     * @return Authentication
     */
    public Authentication getAuth() {
        return auth;
    }

    /**
     * Set the HttpClient that should be used for HTTP requests.
     * 
     * @param client
     *            HttpClient
     */
    public void setHttpClient(HttpClient client) {
        httpClient = client;
    }

    /**
     * Returns the current user's account information.
     * 
     * @return Account
     * @throws HelloSignException
     *             thrown if there's a problem processing the HTTP request or
     *             the JSON response.
     */
    public Account getAccount() throws HelloSignException {
        return new Account(httpClient.withAuth(auth).get(BASE_URI + ACCOUNT_URI).asJson());
    }

    /**
     * Returns true if an account exists with the provided email address. Note
     * this is limited to the visibility of the currently authenticated user.
     * 
     * @param email
     *            String email address
     * @return true if the account exists, false otherwise
     */
    public boolean isAccountValid(String email) {
        boolean isValid = false;
        if (email != null && !email.isEmpty()) {
            try {
                Account account = new Account(
                        httpClient.withAuth(auth).withPostField(Account.ACCOUNT_EMAIL_ADDRESS, email)
                                .post(BASE_URI + VALIDATE_ACCOUNT_URI).asJson());
                isValid = (account.hasEmail() && email.equalsIgnoreCase(account.getEmail()));
            } catch (HelloSignException ex) {
                // Ignore
            }
        }
        return isValid;
    }

    /**
     * Updates the current user's callback URL.
     * 
     * @param callback
     *            String URL
     * @return Account
     * @throws HelloSignException
     *             thrown if there's a problem processing the HTTP request or
     *             the JSON response.
     */
    public Account setCallback(String callback) throws HelloSignException {
        return new Account(httpClient.withAuth(auth).withPostField(Account.ACCOUNT_CALLBACK_URL, callback)
                .post(BASE_URI + ACCOUNT_URI).asJson());
    }

    /**
     * Creates a new HelloSign account. The user will still need to validate
     * their email address to complete the creation process to set a password.
     * Note: This request does not require authentication, so just performs the
     * basic POST.
     * 
     * @param email
     *            String New user's email address
     * @return Account New user's account information
     * @throws HelloSignException
     *             thrown if there's a problem processing the HTTP request or
     *             the JSON response.
     */
    public Account createAccount(String email) throws HelloSignException {
        return createAccount(email, null, null);
    }

    /**
     * Creates a new HelloSign account. The user will still need to validate
     * their email address to complete the creation process.
     * 
     * Note: This request does not require authentication, so just performs the
     * basic POST.
     * 
     * @param email
     *            String New user's email address
     * @param password
     *            String New user's password
     * @return Account New user's account information
     * @throws HelloSignException
     *             thrown if there's a problem processing the HTTP request or
     *             the JSON response.
     * @deprecated as of 3.1.1, replaced by {@link #createAccount(String)}
     */
    public Account createAccount(String email, String password) throws HelloSignException {
        return createAccount(email, password, null, null);
    }

    /**
     * Creates a new HelloSign account and provides OAuth app credentials to
     * automatically generate an OAuth token with the user Account response.
     * 
     * @param email
     *            String New user's email address
     * @param clientId
     *            String Client ID
     * @param clientSecret
     *            String App secret
     * @return Account New user's account information
     * @throws HelloSignException
     *             thrown if there's a problem processing the HTTP request or
     *             the JSON response.
     */
    public Account createAccount(String email, String clientId, String clientSecret) throws HelloSignException {
        HttpClient client = httpClient.withAuth(auth).withPostField(Account.ACCOUNT_EMAIL_ADDRESS, email);
        if (clientId != null && clientSecret != null) {
            client = client.withPostField(CLIENT_ID, clientId).withPostField(CLIENT_SECRET, clientSecret);
        }
        JSONObject response = client.post(BASE_URI + ACCOUNT_CREATE_URI).asJson();
        JSONObject copy;
        try {
            copy = new JSONObject(response.toString());
        } catch (JSONException e) {
            throw new HelloSignException(e);
        }
        OauthData data = new OauthData(copy);
        Account account = new Account(response);
        account.setOauthData(data);
        return account;
    }

    /**
     * Creates a new HelloSign account and provides OAuth app credentials to
     * automatically generate an OAuth token with the user Account response.
     * 
     * @param email
     *            String New user's email address
     * @param password
     *            String New user's password (NOTE: WILL BE IGNORED BY THE API)
     * @param clientId
     *            String Client ID
     * @param clientSecret
     *            String App secret
     * @return Account New user's account information
     * @throws HelloSignException
     *             thrown if there's a problem processing the HTTP request or
     *             the JSON response.
     * @deprecated as of 3.1.1, replaced by
     *             {@link #createAccount(String, String, String)}
     */
    public Account createAccount(String email, String password, String clientId, String clientSecret)
            throws HelloSignException {
        return createAccount(email, clientId, clientSecret);
    }

    /**
     * Performs an OAuth request and returns the necessary data for authorizing
     * an API application.
     * 
     * @param code
     *            String OAuth code
     * @param clientId
     *            String OAuth client ID
     * @param secret
     *            String OAuth secret
     * @return OauthData object containing OAuth token details
     * @throws HelloSignException
     *             thrown if there's a problem processing the HTTP request or
     *             the JSON response.
     * @deprecated Use
     *             {@link #getOauthData(String, String, String, String, boolean)}
     */
    public OauthData getOauthData(String code, String clientId, String secret) throws HelloSignException {
        return getOauthData(code, clientId, secret, "demo", false);
    }

    /**
     * Performs an OAuth request and returns the necessary data for authorizing
     * an API application, and will automatically set the access token and code
     * for making authenticated requests with this client.
     * 
     * NOTE: This method does not provide a state parameter and is deprecated.
     * Please update your references to this method as it will be removed in a
     * future release.
     * 
     * @param code
     *            String OAuth code
     * @param clientId
     *            String OAuth client ID
     * @param secret
     *            String OAuth secret
     * @param autoSetRequestToken
     *            true if the token should be immediately applied to this client
     * @return OauthData object containing OAuth token details
     * @throws HelloSignException
     *             thrown if there's a problem processing the HTTP request or
     *             the JSON response.
     * @deprecated Use
     *             {@link #getOauthData(String, String, String, String, boolean)}
     */
    public OauthData getOauthData(String code, String clientId, String secret, boolean autoSetRequestToken)
            throws HelloSignException {
        return getOauthData(code, clientId, secret, "demo", autoSetRequestToken);
    }

    /**
     * Performs an OAuth request and returns the necessary data for authorizing
     * an API application, and will automatically set the access token and code
     * for making authenticated requests with this client.
     * 
     * @param code
     *            String OAuth code
     * @param clientId
     *            String OAuth client ID
     * @param secret
     *            String OAuth secret
     * @param state
     *            String OAuth client state
     * @param autoSetRequestToken
     *            true if the token should be applied to this client for future
     *            requests
     * @return OauthData object containing OAuth token details
     * @throws HelloSignException
     *             thrown if there's a problem processing the HTTP request or
     *             the JSON response.
     */
    public OauthData getOauthData(String code, String clientId, String secret, String state,
            boolean autoSetRequestToken) throws HelloSignException {
        OauthData data = new OauthData(httpClient.withAuth(auth).withPostField(OAUTH_STATE, state)
                .withPostField(OAUTH_CODE, code).withPostField(CLIENT_ID, clientId)
                .withPostField(OAUTH_GRANT_TYPE, OAUTH_GRANT_TYPE_AUTHORIZE_CODE).withPostField(CLIENT_SECRET, secret)
                .post(OAUTH_TOKEN_URL).asJson());
        if (data != null && autoSetRequestToken) {
            setAccessToken(data.getAccessToken(), data.getTokenType());
        }
        return data;
    }

    /**
     * Refreshes the OauthData for this client with the provided refresh token.
     * 
     * @param refreshToken
     *            String
     * @return OauthData new OAuthData returned from HelloSign
     * @throws HelloSignException
     *             thrown if there's a problem processing the HTTP request or
     *             the JSON response.
     */
    public OauthData refreshOauthData(String refreshToken) throws HelloSignException {
        OauthData data = new OauthData(
                httpClient.withAuth(auth).withPostField(OAUTH_GRANT_TYPE, OAUTH_GRANT_TYPE_REFRESH_TOKEN)
                        .withPostField(OAUTH_REFRESH_TOKEN, refreshToken).post(OAUTH_TOKEN_URL).asJson());
        if (data != null) {
            setAccessToken(data.getAccessToken(), data.getTokenType());
        }
        return data;
    }

    /**
     * Retrieves the Team for the current user account.
     * 
     * @return Team
     * @throws HelloSignException
     *             thrown if there's a problem processing the HTTP request or
     *             the JSON response.
     */
    public Team getTeam() throws HelloSignException {
        return new Team(httpClient.withAuth(auth).get(BASE_URI + TEAM_URI).asJson());
    }

    /**
     * Creates a new team for the current user with the given name.
     * 
     * @param teamName
     *            String team name
     * @return Team
     * @throws HelloSignException
     *             thrown if there's a problem processing the HTTP request or
     *             the JSON response.
     */
    public Team createTeam(String teamName) throws HelloSignException {
        return new Team(
                httpClient.withAuth(auth).withPostField(Team.TEAM_NAME, teamName).post(BASE_URI + TEAM_URI).asJson());
    }

    /**
     * Destroys the current user's team.
     * 
     * @return boolean if destroy was successful
     * @throws HelloSignException
     *             thrown if there's a problem processing the HTTP request or
     *             the JSON response.
     */
    public boolean destroyTeam() throws HelloSignException {
        return HttpURLConnection.HTTP_OK == httpClient.withAuth(auth).post(BASE_URI + TEAM_DESTROY_URI).asHttpCode();
    }

    /**
     * Updates the current user's team name.
     * 
     * @param teamName
     *            String team name
     * @return Team
     * @throws HelloSignException
     *             thrown if there's a problem processing the HTTP request or
     *             the JSON response.
     */
    public Team updateTeamName(String teamName) throws HelloSignException {
        return new Team(
                httpClient.withAuth(auth).withPostField(Team.TEAM_NAME, teamName).post(BASE_URI + TEAM_URI).asJson());
    }

    /**
     * Adds the user to the current user's team.
     * 
     * @param idOrEmail
     *            String new team member's account ID or email address
     * @return Team
     * @throws HelloSignException
     *             thrown if there's a problem processing the HTTP request or
     *             the JSON response.
     */
    public Team inviteTeamMember(String idOrEmail) throws HelloSignException {
        String key = (idOrEmail != null && idOrEmail.contains("@")) ? Account.ACCOUNT_EMAIL_ADDRESS
                : Account.ACCOUNT_ID;
        return new Team(
                httpClient.withAuth(auth).withPostField(key, idOrEmail).post(BASE_URI + TEAM_ADD_MEMBER_URI).asJson());
    }

    /**
     * Removes the team member indicated by the user account ID or email
     * address.
     * 
     * @param idOrEmail
     *            String removed team member's account ID or email address
     * @return Team
     * @throws HelloSignException
     *             thrown if there's a problem processing the HTTP request or
     *             the JSON response.
     */
    public Team removeTeamMember(String idOrEmail) throws HelloSignException {
        String key = (idOrEmail != null && idOrEmail.contains("@")) ? Account.ACCOUNT_EMAIL_ADDRESS
                : Account.ACCOUNT_ID;
        return new Team(httpClient.withAuth(auth).withPostField(key, idOrEmail).post(BASE_URI + TEAM_REMOVE_MEMBER_URI)
                .asJson());
    }

    /**
     * Retrieves a Signature Request with the given ID.
     * 
     * @param id
     *            String signature ID
     * @return SignatureRequest
     * @throws HelloSignException
     *             thrown if there's a problem processing the HTTP request or
     *             the JSON response.
     */
    public SignatureRequest getSignatureRequest(String id) throws HelloSignException {
        String url = BASE_URI + SIGNATURE_REQUEST_URI + "/" + id;
        return new SignatureRequest(httpClient.withAuth(auth).get(url).asJson());
    }

    /**
     * Retrieves the current user's signature requests. The resulting object
     * represents a paged query result. The page information can be retrieved on
     * from the ListInfo object on the SignatureRequestList.
     * 
     * @return SignatureRequestList
     * @throws HelloSignException
     *             thrown if there's a problem processing the HTTP request or
     *             the JSON response.
     */
    public SignatureRequestList getSignatureRequests() throws HelloSignException {
        return new SignatureRequestList(httpClient.withAuth(auth).get(BASE_URI + SIGNATURE_REQUEST_LIST_URI).asJson());
    }

    /**
     * Retrieves a specific page of the current user's signature requests.
     * 
     * @param page
     *            int
     * @return SignatureRequestList
     * @throws HelloSignException
     *             thrown if there's a problem processing the HTTP request or
     *             the JSON response.
     */
    public SignatureRequestList getSignatureRequests(int page) throws HelloSignException {
        return new SignatureRequestList(
                httpClient.withAuth(auth).withGetParam(AbstractResourceList.PAGE, Integer.toString(page))
                        .get(BASE_URI + SIGNATURE_REQUEST_LIST_URI).asJson());
    }

    /**
     * Sends the provided signature request to HelloSign.
     * 
     * @param req
     *            SignatureRequest
     * @return SignatureRequest
     * @throws HelloSignException
     *             thrown if there's a problem processing the HTTP request or
     *             the JSON response.
     */
    public SignatureRequest sendSignatureRequest(SignatureRequest req) throws HelloSignException {
        if (req.hasId()) {
            throw new HelloSignException("Sending an existing signature request is not supported");
        }
        return new SignatureRequest(httpClient.withAuth(auth).withPostFields(req.getPostFields())
                .post(BASE_URI + SIGNATURE_REQUEST_SEND_URI).asJson());
    }

    /**
     * Update a signer's email address.
     * 
     * This method requires the ID of the siganture request that has already
     * been sent, as well as the signature_id that represents the signer that
     * should be updated. The ema
     * 
     * @param signatureRequestId
     *            String ID of the signature request that has already been sent
     *            and needs to be updated.
     * @param signatureId
     *            String ID of the signer that needs to be updated.
     * @param newEmailAddress
     *            String email address that the signer should be changed to
     * @return SignatureRequest The updated request data
     * @throws HelloSignException
     *             thrown if there's a problem processing the HTTP request or
     *             the JSON response.
     */
    public SignatureRequest updateSignatureRequest(String signatureRequestId, String signatureId,
            String newEmailAddress) throws HelloSignException {
        String url = BASE_URI + SIGNATURE_REQUEST_UPDATE_URI + "/" + signatureRequestId;
        return new SignatureRequest(httpClient.withAuth(auth).withPostField(Signature.SIGNATURE_ID, signatureId)
                .withPostField(SignatureRequest.SIGREQ_SIGNER_EMAIL, newEmailAddress).post(url).asJson());
    }

    /**
     * Retrieves the templates for the current user account.
     * 
     * @return TemplateList
     * @throws HelloSignException
     *             thrown if there's a problem processing the HTTP request or
     *             the JSON response.
     */
    public TemplateList getTemplates() throws HelloSignException {
        return new TemplateList(httpClient.withAuth(auth).get(BASE_URI + TEMPLATE_LIST_URI).asJson());
    }

    /**
     * Retreives a page of templates for the current user account.
     * 
     * @param page
     *            int
     * @return TemplateList
     * @throws HelloSignException
     *             thrown if there's a problem processing the HTTP request or
     *             the JSON response.
     */
    public TemplateList getTemplates(int page) throws HelloSignException {
        return new TemplateList(
                httpClient.withAuth(auth).withGetParam(AbstractResourceList.PAGE, Integer.toString(page))
                        .get(BASE_URI + TEMPLATE_LIST_URI).asJson());
    }

    /**
     * Retrieves the PDF file backing the Template specified by the provided
     * Template ID.
     * 
     * @param templateId
     *            String Template ID
     * @return File PDF file object
     * @throws HelloSignException
     *             thrown if there's a problem processing the HTTP request or
     *             the JSON response.
     */
    public File getTemplateFile(String templateId) throws HelloSignException {
        String url = BASE_URI + TEMPLATE_FILE_URI + "/" + templateId;
        String fileName = TEMPLATE_FILE_NAME + "." + TEMPLATE_FILE_EXT;
        return httpClient.withAuth(auth).get(url).asFile(fileName);
    }

    /**
     * Returns a signed URL that can be used to retrieve the file backing a
     * template.
     * 
     * @param templateId
     *            String Template ID
     * @return String URL or null if no file URL can be retrieved
     * @throws HelloSignException
     *             thrown if there's a problem processing the HTTP request or
     *             the JSON response.
     */
    public String getTemplateFileUrl(String templateId) throws HelloSignException {
        String fileUrl = null;
        String url = BASE_URI + TEMPLATE_FILE_URI + "/" + templateId;
        JSONObject response = httpClient.withAuth(auth).withGetParam(PARAM_TEMPLATE_GET_URL, "1").get(url).asJson();
        if (response.has("file_url")) {
            try {
                fileUrl = response.getString("file_url");
            } catch (JSONException ex) {
                throw new HelloSignException(ex);
            }
        }
        return fileUrl;
    }

    /**
     * Retrieves a specific Template based on the provided Template ID.
     * 
     * @param templateId
     *            String Template ID
     * @return Template
     * @throws HelloSignException
     *             thrown if there's a problem processing the HTTP request or
     *             the JSON response.
     */
    public Template getTemplate(String templateId) throws HelloSignException {
        String url = BASE_URI + TEMPLATE_URI + "/" + templateId;
        return new Template(httpClient.withAuth(auth).get(url).asJson());
    }

    /**
     * Adds the provided user to the template indicated by the provided template
     * ID. The new user can be designated using their account ID or email
     * address.
     * 
     * @param templateId
     *            String template ID
     * @param idOrEmail
     *            String account ID or email address
     * @return Template
     * @throws HelloSignException
     *             thrown if there's a problem processing the HTTP request or
     *             the JSON response.
     */
    public Template addTemplateUser(String templateId, String idOrEmail) throws HelloSignException {
        String url = BASE_URI + TEMPLATE_ADD_USER_URI + "/" + templateId;
        String key = (idOrEmail != null && idOrEmail.contains("@")) ? Account.ACCOUNT_EMAIL_ADDRESS
                : Account.ACCOUNT_ID;
        return new Template(httpClient.withAuth(auth).withPostField(key, idOrEmail).post(url).asJson());
    }

    /**
     * Delete the template designated by the template id
     * 
     * @param templateId
     *            String template ID
     * @return true if the delete was successful, false otherwise
     * @throws HelloSignException
     *             thrown if there is a problem processing the HTTP request
     */
    public boolean deleteTemplate(String templateId) throws HelloSignException {
        String url = BASE_URI + TEMPLATE_DELETE_URI + "/" + templateId;
        return HttpURLConnection.HTTP_OK == httpClient.withAuth(auth).post(url).asHttpCode();
    }

    /**
     * Adds the provided user to the template indicated by the provided template
     * ID. The new user can be designated using their account ID or email
     * address.
     * 
     * @param templateId
     *            String template ID
     * @param idOrEmail
     *            String account ID or email address
     * @return Template
     * @throws HelloSignException
     *             thrown if there's a problem processing the HTTP request or
     *             the JSON response.
     */
    public Template removeTemplateUser(String templateId, String idOrEmail) throws HelloSignException {
        String url = BASE_URI + TEMPLATE_REMOVE_USER_URI + "/" + templateId;
        String key = (idOrEmail != null && idOrEmail.contains("@")) ? Account.ACCOUNT_EMAIL_ADDRESS
                : Account.ACCOUNT_ID;
        return new Template(httpClient.withAuth(auth).withPostField(key, idOrEmail).post(url).asJson());
    }

    /**
     * Creates a new Signature Request based on the template provided.
     * 
     * @param req
     *            TemplateSignatureRequest
     * @return SignatureRequest
     * @throws HelloSignException
     *             thrown if there's a problem processing the HTTP request or
     *             the JSON response.
     */
    public SignatureRequest sendTemplateSignatureRequest(TemplateSignatureRequest req) throws HelloSignException {
        return new SignatureRequest(httpClient.withAuth(auth).withPostFields(req.getPostFields())
                .post(BASE_URI + TEMPLATE_SIGNATURE_REQUEST_URI).asJson());
    }

    /**
     * Cancels an existing signature request. If it has been completed, it will
     * delete the signature request from your account.
     * 
     * @param id
     *            SignatureRequest id
     * @return boolean true if successful
     * @throws HelloSignException
     *             thrown if there's a problem processing the HTTP request or
     *             the JSON response.
     */
    public boolean cancelSignatureRequest(String id) throws HelloSignException {
        String url = BASE_URI + SIGNATURE_REQUEST_CANCEL_URI + "/" + id;
        return HttpURLConnection.HTTP_OK == httpClient.withAuth(auth).post(url).asHttpCode();
    }

    /**
     * Instructs HelloSign to email the given address with a reminder to sign
     * the SignatureRequest referenced by the given request ID.
     * 
     * Note: You cannot send a reminder within 1 hours of the last reminder that
     * was sent, manually or automatically.
     * 
     * @param requestId
     *            String SignatureRequest ID
     * @param email
     *            String email
     * @return SignatureRequest The request to be reminded
     * @throws HelloSignException
     *             thrown if there's a problem processing the HTTP request or
     *             the JSON response.
     */
    public SignatureRequest requestEmailReminder(String requestId, String email) throws HelloSignException {
        String url = BASE_URI + SIGNATURE_REQUEST_REMIND_URI + "/" + requestId;
        return new SignatureRequest(
                httpClient.withAuth(auth).withPostField(Account.ACCOUNT_EMAIL_ADDRESS, email).post(url).asJson());
    }

    /**
     * Retrieves the final PDF copy of a signature request, if it exists.
     * 
     * @param requestId
     *            String SignatureRequest ID
     * @return File final copy file, or null if it does not yet exist
     * @throws HelloSignException
     *             thrown if there's a problem processing the HTTP request or
     *             the JSON response.
     * @deprecated Use {{@link #getFiles(String)}
     */
    public File getFinalCopy(String requestId) throws HelloSignException {
        String url = BASE_URI + SIGNATURE_REQUEST_FINAL_COPY_URI + "/" + requestId;
        String filename = FINAL_COPY_FILE_NAME + "." + FINAL_COPY_FILE_EXT;
        return httpClient.withAuth(auth).get(url).asFile(filename);
    }

    /**
     * Retrieves a PDF copy of the files associated with a signature request.
     * 
     * @param requestId
     *            String signature ID
     * @return File PDF file
     * @throws HelloSignException
     *             thrown if there's a problem processing the HTTP request or
     *             the JSON response.
     */
    public File getFiles(String requestId) throws HelloSignException {
        return getFiles(requestId, SignatureRequest.SIGREQ_FORMAT_PDF);
    }

    /**
     * Retrieves the file associated with a signature request.
     * 
     * @param requestId
     *            String signature ID
     * @param format
     *            String format, see SignatureRequest for available types
     * @return File
     * @throws HelloSignException
     *             thrown if there's a problem processing the HTTP request or
     *             the JSON response.
     */
    public File getFiles(String requestId, String format) throws HelloSignException {
        if (format == null || format.isEmpty()) {
            format = FILES_FILE_EXT;
        }
        String url = BASE_URI + SIGNATURE_REQUEST_FILES_URI + "/" + requestId;
        String fileName = FILES_FILE_NAME + "." + format;
        return httpClient.withAuth(auth).withGetParam(PARAM_FILE_TYPE_URI, format).get(url).asFile(fileName);
    }

    /**
     * Creates a signature request that can be embedded within your website.
     * 
     * @param embeddedReq
     *            EmbeddedRequest
     * @return SignatureRequest
     * @throws HelloSignException
     *             thrown if there's a problem processing the HTTP request or
     *             the JSON response.
     */
    public AbstractRequest createEmbeddedRequest(EmbeddedRequest embeddedReq) throws HelloSignException {
        String url = BASE_URI;
        Class<?> returnType = SignatureRequest.class;
        AbstractRequest req = embeddedReq.getRequest();
        if (req instanceof TemplateSignatureRequest) {
            url += SIGNATURE_REQUEST_EMBEDDED_TEMPLATE_URI;
        } else if (req instanceof UnclaimedDraft) {
            if (((UnclaimedDraft) req).getRequest() instanceof TemplateSignatureRequest) {
                url += UNCLAIMED_DRAFT_CREATE_EMBEDDED_WITH_TEMPLATE_URI;
            } else {
                url += UNCLAIMED_DRAFT_CREATE_EMBEDDED_URI;
            }
            returnType = UnclaimedDraft.class;
        } else {
            url += SIGNATURE_REQUEST_EMBEDDED_URI;
        }
        try {
            Constructor<?> constructor = returnType.getConstructor(JSONObject.class);
            return (AbstractRequest) constructor.newInstance(
                    httpClient.withAuth(auth).withPostFields(embeddedReq.getPostFields()).post(url).asJson());
        } catch (Exception ex) {
            throw new HelloSignException(ex);
        }
    }

    /**
     * Retrieves the necessary information to build an embedded signature
     * request.
     * 
     * @param signatureId
     *            String ID of the signature request to embed
     * @return EmbeddedResponse
     * @throws HelloSignException
     *             thrown if there's a problem processing the HTTP request or
     *             the JSON response.
     */
    public EmbeddedResponse getEmbeddedSignUrl(String signatureId) throws HelloSignException {
        String url = BASE_URI + EMBEDDED_SIGN_URL_URI + "/" + signatureId;
        return new EmbeddedResponse(httpClient.withAuth(auth).post(url).asJson());
    }

    /**
     * Retrieves the necessary information to edit an embedded template.
     * 
     * @param templateId
     *            String ID of the signature request to embed
     * @return EmbeddedResponse
     * @throws HelloSignException
     *             thrown if there's a problem processing the HTTP request or
     *             the JSON response.
     */
    public EmbeddedResponse getEmbeddedTemplateEditUrl(String templateId) throws HelloSignException {
        return getEmbeddedTemplateEditUrl(templateId, false, false, false);
    }

    /**
     * Retrieves the necessary information to edit an embedded template.
     * 
     * @param templateId
     *            String ID of the signature request to embed
     * @param skipSignerRoles
     *            true if the edited template should not allow the user to
     *            modify the template's signer roles. Defaults to false.
     * @param skipSubjectMessage
     *            true if the edited template should not allow the user to
     *            modify the template's subject and message. Defaults to false.
     * @return EmbeddedResponse
     * @throws HelloSignException
     *             thrown if there's a problem processing the HTTP request or
     *             the JSON response.
     */
    public EmbeddedResponse getEmbeddedTemplateEditUrl(String templateId, boolean skipSignerRoles,
            boolean skipSubjectMessage) throws HelloSignException {
        return getEmbeddedTemplateEditUrl(templateId, skipSignerRoles, skipSubjectMessage, false);
    }

    /**
     * Retrieves the necessary information to edit an embedded template.
     * 
     * @param templateId
     *            String ID of the signature request to embed
     * @param skipSignerRoles
     *            true if the edited template should not allow the user to
     *            modify the template's signer roles. Defaults to false.
     * @param skipSubjectMessage
     *            true if the edited template should not allow the user to
     *            modify the template's subject and message. Defaults to false.
     * @param testMode
     *            true if this request is a test request. Useful for editing
     *            locked templates.
     * @return EmbeddedResponse
     * @throws HelloSignException
     *             thrown if there's a problem processing the HTTP request or
     *             the JSON response.
     */
    public EmbeddedResponse getEmbeddedTemplateEditUrl(String templateId, boolean skipSignerRoles,
            boolean skipSubjectMessage, boolean testMode) throws HelloSignException {
        String url = BASE_URI + EMBEDDED_EDIT_URL_URI + "/" + templateId;
        return new EmbeddedResponse(
                httpClient.withAuth(auth).withPostField(EMBEDDED_TEMPLATE_SKIP_SIGNER_ROLES, skipSignerRoles)
                        .withPostField(EMBEDDED_TEMPLATE_SKIP_SUBJECT_MESSAGE, skipSubjectMessage)
                        .withPostField(AbstractRequest.REQUEST_TEST_MODE, testMode).post(url).asJson());
    }

    /**
     * Creates an unclaimed draft using the provided request draft object.
     * 
     * @param draft
     *            UnclaimedDraft
     * @return UnclaimedDraft The created draft
     * @throws HelloSignException
     *             thrown if there's a problem processing the HTTP request or
     *             the JSON response.
     */
    public UnclaimedDraft createUnclaimedDraft(UnclaimedDraft draft) throws HelloSignException {
        String url = BASE_URI;
        if (draft.isForEmbeddedSigning()) {
            url += UNCLAIMED_DRAFT_CREATE_EMBEDDED_URI;
        } else {
            url += UNCLAIMED_DRAFT_CREATE_URI;
        }
        return new UnclaimedDraft(httpClient.withAuth(auth).withPostFields(draft.getPostFields()).post(url).asJson());
    }

    /**
     * Creates a template draft that can be used for embedded template creation.
     * 
     * @param req
     *            EmbeddedRequest
     * @return Template the unclaimed template draft
     * @throws HelloSignException
     *             thrown if there's a problem processing the HTTP request or
     *             the JSON response.
     */
    public TemplateDraft createEmbeddedTemplateDraft(EmbeddedRequest req) throws HelloSignException {
        return new TemplateDraft(httpClient.withAuth(auth).withPostFields(req.getPostFields())
                .post(BASE_URI + TEMPLATE_CREATE_EMBEDDED_DRAFT_URI).asJson());
    }

    /**
     * Retrieves the API app configuration for the given Client ID.
     * 
     * @param clientId
     *            String
     * @return ApiApp
     * @throws HelloSignException
     *             thrown if there's a problem processing the HTTP request or
     *             the JSON response.
     */
    public ApiApp getApiApp(String clientId) throws HelloSignException {
        String url = BASE_URI + API_APP_URI + "/" + clientId;
        return new ApiApp(httpClient.withAuth(auth).get(url).asJson());
    }

    /**
     * Retrieves a paged list of API apps for the authenticated account.
     * 
     * @return ApiAppList
     * @throws HelloSignException
     *             thrown if there's a problem processing the HTTP request or
     *             the JSON response.
     */
    public ApiAppList getApiApps() throws HelloSignException {
        return new ApiAppList(httpClient.withAuth(auth).get(BASE_URI + API_APP_LIST_URI).asJson());
    }

    /**
     * Creates a new ApiApp using the properties set on the provided ApiApp.
     * 
     * @param app
     *            ApiApp
     * @return ApiApp newly created ApiApp
     * @throws HelloSignException
     *             thrown if there's a problem processing the HTTP request or
     *             the JSON response.
     */
    public ApiApp createApiApp(ApiApp app) throws HelloSignException {
        return new ApiApp(
                httpClient.withAuth(auth).withPostFields(app.getPostFields()).post(BASE_URI + API_APP_URI).asJson());
    }

    /**
     * Attempts to delete the API app with the given client ID.
     * 
     * @param clientId
     *            String The Client ID of the app that should be deleted.
     * @return boolean true if the API app was successfully deleted
     * @throws HelloSignException
     *             thrown if there's a problem processing the HTTP request or
     *             the JSON response.
     */
    public boolean deleteApiApp(String clientId) throws HelloSignException {
        String url = API_APP_URI + "/" + clientId;
        return HttpURLConnection.HTTP_NO_CONTENT == httpClient.withAuth(auth).delete(url).asHttpCode();
    }

    /**
     * Updates the API app with the given ApiApp object properties.
     * 
     * @param app
     *            ApiApp
     * @return ApiApp updated ApiApp
     * @throws HelloSignException
     *             thrown if there's a problem processing the HTTP request or
     *             the JSON response.
     */
    public ApiApp updateApiApp(ApiApp app) throws HelloSignException {
        if (!app.hasClientId()) {
            throw new HelloSignException("Cannot update an ApiApp without a client ID. Create one first!");
        }
        String url = API_APP_URI + "/" + app.getClientId();
        return new ApiApp(httpClient.withAuth(auth).withPostFields(app.getPostFields()).put(url).asJson());
    }

    /**
     * Performs an OPTIONS call to the HelloSign API to see if it's online.
     * 
     * @return true if HelloSign is available and the client is online, false
     *         otherwise.
     * @throws HelloSignException
     *             thrown if there's a problem processing the HTTP request or
     *             response.
     */
    public boolean isOnline() throws HelloSignException {
        boolean isOnline = false;
        try {
            isOnline = (httpClient.options(BASE_URI).asHttpCode() == HttpURLConnection.HTTP_OK);
        } catch (HelloSignException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new HelloSignException(ex);
        }
        return isOnline;
    }

    /**
     * Sets the access token for the OAuth user that this client will use to
     * perform requests.
     * 
     * @param accessToken
     *            String access token
     * @param tokenType
     *            String token type
     * @throws HelloSignException
     *             thrown if there's a problem setting the access token.
     */
    public void setAccessToken(String accessToken, String tokenType) throws HelloSignException {
        auth.setAccessToken(accessToken, tokenType);
    }
}
