package com.hellosign.sdk;

/**
 * The MIT License (MIT)
 * 
 * Copyright (C) 2014 hellosign.com
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
import java.lang.reflect.Constructor;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.json.JSONException;
import org.json.JSONObject;

import com.hellosign.sdk.http.Authentication;
import com.hellosign.sdk.http.HttpGetRequest;
import com.hellosign.sdk.http.HttpPostRequest;
import com.hellosign.sdk.resource.AbstractRequest;
import com.hellosign.sdk.resource.AbstractResourceList;
import com.hellosign.sdk.resource.Account;
import com.hellosign.sdk.resource.EmbeddedRequest;
import com.hellosign.sdk.resource.EmbeddedResponse;
import com.hellosign.sdk.resource.SignatureRequest;
import com.hellosign.sdk.resource.Team;
import com.hellosign.sdk.resource.Template;
import com.hellosign.sdk.resource.TemplateDraft;
import com.hellosign.sdk.resource.TemplateSignatureRequest;
import com.hellosign.sdk.resource.UnclaimedDraft;
import com.hellosign.sdk.resource.support.OauthData;
import com.hellosign.sdk.resource.support.SignatureRequestList;
import com.hellosign.sdk.resource.support.TemplateList;

/**
 * You'll need the HelloSignClient to do just about everything, from creating 
 * signatures to updating account information.
 * 
 * To use this class, instantiate a client with valid credentials like so:
 * 
 *   HelloSignClient client = new HelloSignClient(user, key);
 * 
 * Then, use the client to perform your requests. The client uses the Unirest
 * library to perform its HTTP requests. (http://unirest.io/java.html).
 * 
 * @author "Chris Paul (chris@hellosign.com)"
 */
public class HelloSignClient {

	public static final String DEFAULT_ENCODING = "UTF-8";
	
	// ======================================================================
	// HelloSign API URL Endpoints
	// ======================================================================
	
	private static final String API_VERSION = "v3";
	
    private String URL_HELLOSIGN;

    // The base URL can be overridden by setting the "hellosign.base.url"
    // system property.
	private static final String URL_HELLOSIGN_PRODUCTION = "https://api.hellosign.com";
    
    private String URL_OAUTH_TOKEN;
    private static final String URL_OAUTH_TOKEN_PRODUCTION = "/oauth/token";
    
    private String URL_API;
    private String URL_ACCOUNT;
    private String URL_VALIDATE_ACCOUNT;
    private String URL_ACCOUNT_CREATE;
    private String URL_TEAM;
    private String URL_TEAM_CREATE;
    private String URL_TEAM_DESTROY;
    private String URL_TEAM_ADD_MEMBER;
    private String URL_TEAM_REMOVE_MEMBER;
    private String URL_SIGNATURE_REQUEST;
    private String URL_SIGNATURE_REQUEST_LIST;
	private String URL_SIGNATURE_REQUEST_SEND;
	private String URL_TEMPLATE;
	private String URL_TEMPLATE_LIST;
	private String URL_TEMPLATE_ADD_USER;
	private String URL_TEMPLATE_REMOVE_USER;
	private String URL_TEMPLATE_DELETE;
	private String URL_TEMPLATE_CREATE_EMBEDDED_DRAFT; // Embedded Templates
	private String URL_TEMPLATE_SIGNATURE_REQUEST;
	private String URL_SIGNATURE_REQUEST_CANCEL;
	private String URL_SIGNATURE_REQUEST_REMIND;
	private String URL_SIGNATURE_REQUEST_FINAL_COPY;
	private String URL_SIGNATURE_REQUEST_FILES;
	private String URL_SIGNATURE_REQUEST_EMBEDDED;
	private String URL_SIGNATURE_REQUEST_EMBEDDED_TEMPLATE;
	private String URL_EMBEDDED_SIGN_URL;
	private String URL_EMBEDDED_EDIT_URL; // Embedded Templates
	private String URL_UNCLAIMED_DRAFT_CREATE;
	private String URL_UNCLAIMED_DRAFT_CREATE_EMBEDDED;
	private String URL_UNCLAIMED_DRAFT_CREATE_EMBEDDED_WITH_TEMPLATE;

	private String URL_PARAM_FILE_TYPE = "file_type";
	
	public static final String FINAL_COPY_FILE_NAME = "final-copy";
	public static final String FINAL_COPY_FILE_EXT = "pdf";
	
	public static final String FILES_FILE_NAME = "files";
	public static final String FILES_FILE_EXT = "pdf";
	
	public static final String OAUTH_CODE = "code";
	public static final String OAUTH_STATE = "state";
	public static final String OAUTH_GRANT_TYPE = "grant_type";
	public static final String OAUTH_REFRESH_TOKEN = "refresh_token";
	public static final String OAUTH_GRANT_TYPE_AUTHORIZE_CODE = "authorization_code";
	public static final String OAUTH_GRANT_TYPE_REFRESH_TOKEN = "refresh_token";

	public static final String CLIENT_SECRET = "client_secret";
	public static final String CLIENT_ID = "client_id";
	
    private Authentication auth = new Authentication();
    
    private HelloSignClient() {
    	URL_HELLOSIGN = URL_HELLOSIGN_PRODUCTION;
    	String baseUrl = System.getProperty("hellosign.base.url");
    	if (baseUrl != null && !"".equals(baseUrl)) {
    		URL_HELLOSIGN = baseUrl;
    	}
    	URL_OAUTH_TOKEN = URL_HELLOSIGN + URL_OAUTH_TOKEN_PRODUCTION;
    	String disableSslCheck = System.getProperty("hellosign.disable.ssl");
    	if ("true".equalsIgnoreCase(disableSslCheck)) {
    	    disableStrictSSL();
    	}
    	initApiEndpoints();
    }
    
    private void initApiEndpoints() {
    	URL_API = URL_HELLOSIGN + "/" + API_VERSION;
		URL_ACCOUNT = URL_API + "/account";
        URL_VALIDATE_ACCOUNT = URL_ACCOUNT + "/verify";
        URL_ACCOUNT_CREATE = URL_ACCOUNT + "/create";
        URL_TEAM = URL_API + "/team";
        URL_TEAM_CREATE = URL_TEAM + "/create";
        URL_TEAM_DESTROY = URL_TEAM + "/destroy";
        URL_TEAM_ADD_MEMBER = URL_TEAM + "/add_member";
        URL_TEAM_REMOVE_MEMBER = URL_TEAM + "/remove_member";
        URL_SIGNATURE_REQUEST = URL_API + "/signature_request";
        URL_SIGNATURE_REQUEST_LIST = URL_SIGNATURE_REQUEST + "/list";
    	URL_SIGNATURE_REQUEST_SEND = URL_SIGNATURE_REQUEST + "/send";
    	URL_TEMPLATE = URL_API + "/template";
    	URL_TEMPLATE_LIST = URL_TEMPLATE + "/list";
    	URL_TEMPLATE_ADD_USER = URL_TEMPLATE + "/add_user";
    	URL_TEMPLATE_REMOVE_USER = URL_TEMPLATE + "/remove_user";
    	URL_TEMPLATE_DELETE = URL_TEMPLATE + "/delete";
    	URL_TEMPLATE_CREATE_EMBEDDED_DRAFT = URL_TEMPLATE + "/create_embedded_draft";
    	URL_TEMPLATE_SIGNATURE_REQUEST = URL_SIGNATURE_REQUEST + "/send_with_template";
    	URL_SIGNATURE_REQUEST_CANCEL = URL_SIGNATURE_REQUEST + "/cancel";
    	URL_SIGNATURE_REQUEST_REMIND = URL_SIGNATURE_REQUEST + "/remind";
    	URL_SIGNATURE_REQUEST_FINAL_COPY = URL_SIGNATURE_REQUEST + "/final_copy";
    	URL_SIGNATURE_REQUEST_FILES = URL_SIGNATURE_REQUEST + "/files";
    	URL_SIGNATURE_REQUEST_EMBEDDED = URL_SIGNATURE_REQUEST + "/create_embedded";
    	URL_SIGNATURE_REQUEST_EMBEDDED_TEMPLATE = URL_SIGNATURE_REQUEST + "/create_embedded_with_template";
    	URL_EMBEDDED_SIGN_URL = URL_API + "/embedded/sign_url";
    	URL_EMBEDDED_EDIT_URL = URL_API + "/embedded/edit_url";
    	URL_UNCLAIMED_DRAFT_CREATE = URL_API + "/unclaimed_draft/create";
    	URL_UNCLAIMED_DRAFT_CREATE_EMBEDDED = URL_API + "/unclaimed_draft/create_embedded";
    	URL_UNCLAIMED_DRAFT_CREATE_EMBEDDED_WITH_TEMPLATE = URL_API + "/unclaimed_draft/create_embedded_with_template";
	}

	/**
     * Creates a new HelloSign client using your API key.
     * 
     * Your HelloSign API key can be found on the Settings page:
     *   https://www.hellosign.com/home/myAccount/current_tab/integrations
     *   
     * @param apiKey String API key
     */
    public HelloSignClient(String apiKey) {
    	this();
    	auth.setApiKey(apiKey);
    }
    
    /**
     * Creates a new HelloSign client using your website account's
     * email address and password.
     * 
     * Note: This method is not suggested. You're using the API, so 
     * sign up for an API key already!
     * 
     *   https://www.hellosign.com/home/myAccount/current_tab/integrations
     *   
     * @param email String email
     * @param password String password
     * @throws HelloSignException 
     */
    public HelloSignClient(String email, String password) 
    		throws HelloSignException {
    	this();
    	auth.setWebsiteCredentials(email, password);
    }
    
    /**
     * Create a client with the provided authentication object.
     * @param auth HelloSignAuthentication
     * @throws HelloSignException thrown if the HelloSignAuthentication
     * parameters are invalid or null
     */
    public HelloSignClient(Authentication auth) 
    		throws HelloSignException {
    	this();
    	this.auth = new Authentication(auth);
    }

	/**
	 * Returns the current user's account information.
	 * @return Account
	 * @throws HelloSignException
	 */
	public Account getAccount() throws HelloSignException {
		HttpGetRequest request = new HttpGetRequest(URL_ACCOUNT, auth);
		JSONObject json = request.getJsonResponse();
		return new Account(json);
	}
	
	/**
	 * Returns true if an account exists with the provided email address. Note this is
	 * limited to the visibility of the currently authenticated user.
	 * @param email String email address
	 * @return true if the account exists, false otherwise
	 * @throws HelloSignException
	 */
	public boolean isAccountValid(String email) throws HelloSignException {
		if (email == null) {
			return false;
		}
		Map<String, Serializable> params = 
				new HashMap<String, Serializable>();
		params.put("email_address", email);
		HttpPostRequest request = new HttpPostRequest(
				URL_VALIDATE_ACCOUNT, params, auth);
		JSONObject response = request.getJsonResponse();
		if (response.has(Account.ACCOUNT_KEY)) {
			try {
				JSONObject account = response.getJSONObject(Account.ACCOUNT_KEY);
				if (account.has(Account.ACCOUNT_EMAIL_ADDRESS)) {
					return email.equalsIgnoreCase(
							account.getString(Account.ACCOUNT_EMAIL_ADDRESS));
				}
			} catch (JSONException ex) {
				throw new HelloSignException(ex);
			}
		}
		return false;
	}
	
	/**
	 * Updates the current user's callback URL. 
	 * @param callback String URL
	 * @throws HelloSignException 
	 */
	public Account setCallback(String callback) throws HelloSignException {
		Map<String, Serializable> properties = new HashMap<String, Serializable>();
		properties.put(Account.ACCOUNT_CALLBACK_URL, callback);
		HttpPostRequest request = new HttpPostRequest(URL_ACCOUNT, properties, auth);
		JSONObject json = request.getJsonResponse();
		return new Account(json);
	}
	
    /**
     * Creates a new HelloSign account. The user will still need to validate their email address
     * to complete the creation process to set a password. Note: This request does not require
     * authentication, so just performs the basic POST.
     * @param email String New user's email address
     * @return Account New user's account information
     * @throws HelloSignException
     */
	public Account createAccount(String email) throws HelloSignException {
	    return createAccount(email, null, null);
	}

	/**
	 * Creates a new HelloSign account. The user will still need to validate their email address
	 * to complete the creation process.
	 * 
	 * Note: This request does not require authentication, so just performs the basic POST.
	 * 
	 * @param email String New user's email address
	 * @param password String New user's password
	 * @return Account New user's account information
	 * @throws HelloSignException
	 * @deprecated as of 3.1.1, replaced by {@link #createAccount(String)}
	 */
	public Account createAccount(String email, String password) throws HelloSignException {
		return createAccount(email, password, null, null);
	}

   /**
     * Creates a new HelloSign account and provides OAuth app credentials to automatically
     * generate an OAuth token with the user Account response.
     * @param email String New user's email address
     * @param clientId String Client ID
     * @param clientSecret String App secret
     * @return Account New user's account information
     * @throws HelloSignException
     */
    public Account createAccount(String email, String clientId, String clientSecret) throws HelloSignException {
        Map<String, Serializable> fields = new HashMap<String, Serializable>();
        fields.put(Account.ACCOUNT_EMAIL_ADDRESS, email);
        if (clientId != null && clientSecret != null) {
            fields.put(CLIENT_ID, clientId);
            fields.put(CLIENT_SECRET, clientSecret);
        }
        HttpPostRequest request = new HttpPostRequest(URL_ACCOUNT_CREATE, 
                fields, auth);
        JSONObject json = request.getJsonResponse();
        return new Account(json);
    }
	
	/**
	 * Creates a new HelloSign account and provides OAuth app credentials to automatically
	 * generate an OAuth token with the user Account response.
	 * @param email String New user's email address
	 * @param password String New user's password (NOTE: WILL BE IGNORED BY THE API)
	 * @param clientId String Client ID
	 * @param clientSecret String App secret
	 * @return Account New user's account information
	 * @throws HelloSignException
	 * @deprecated as of 3.1.1, replaced by {@link #createAccount(String, String, String)}
	 */
	public Account createAccount(String email, String password, String clientId, String clientSecret) throws HelloSignException {
		Map<String, Serializable> fields = new HashMap<String, Serializable>();
		fields.put(Account.ACCOUNT_EMAIL_ADDRESS, email);

		// Deprecated - we no longer allow setting a password when creating an account
		fields.put(Account.ACCOUNT_PASSWORD, password);

		if (clientId != null && clientSecret != null) {
			fields.put(CLIENT_ID, clientId);
			fields.put(CLIENT_SECRET, clientSecret);
		}
		HttpPostRequest request = new HttpPostRequest(URL_ACCOUNT_CREATE, fields, auth);
		JSONObject json = request.getJsonResponse();
		return new Account(json);
	}
	
	/**
	 * Performs an OAuth request and returns the necessary data for authorizing an API
	 * application.
	 * @param code String OAuth code
	 * @param clientId String OAuth client ID
	 * @param secret String OAuth secret
	 * @return OauthData object containing OAuth token details
	 * @throws HelloSignException
	 */
	public OauthData getOauthData(String code, String clientId, String secret) 
			throws HelloSignException {
		return getOauthData(code, clientId, secret, true);
	}
	
	/**
	 * Performs an OAuth request and returns the necessary data for authorizing an API
	 * application, and will automatically set the access token and code for making
	 * authenticated requests with this client.
	 * @param code String OAuth code
	 * @param clientId String OAuth client ID
	 * @param secret String OAuth secret
	 * @param autoSetRequestToken true if the token should be immediately applied to 
	 * 	this client
	 * @return OauthData object containing OAuth token details
	 * @throws HelloSignException
	 */
	public OauthData getOauthData(
			String code, String clientId, String secret, boolean autoSetRequestToken) 
			throws HelloSignException {
		Map<String, Serializable> fields = new HashMap<String, Serializable>();
		// TODO: What should this be?
		fields.put(OAUTH_STATE, "demo");
		fields.put(OAUTH_CODE, code);
		fields.put(CLIENT_ID, clientId);
		fields.put(OAUTH_GRANT_TYPE, OAUTH_GRANT_TYPE_AUTHORIZE_CODE);
		fields.put(CLIENT_SECRET, secret);
		HttpPostRequest request = new HttpPostRequest(URL_OAUTH_TOKEN, fields, auth);
		JSONObject json = request.getJsonResponse();
		OauthData data = new OauthData(json);
		if (data != null && autoSetRequestToken) {
			setAccessToken(data.getAccessToken(), data.getTokenType());
		}
		return data;
	}

	/**
	 * Refreshes the OauthData for this client with the provided refresh
	 * token.
	 * @param refreshToken String
	 * @return OauthData new OAuthData returned from HelloSign
	 * @throws HelloSignException
	 */
	public OauthData refreshOauthData(String refreshToken) 
			throws HelloSignException {
		Map<String, Serializable> fields = new HashMap<String, Serializable>();
		fields.put(OAUTH_GRANT_TYPE, OAUTH_GRANT_TYPE_REFRESH_TOKEN);
		fields.put(OAUTH_REFRESH_TOKEN, refreshToken);
		HttpPostRequest request = new HttpPostRequest(URL_OAUTH_TOKEN, fields, auth);
		JSONObject json = request.getJsonResponse();
		OauthData data = new OauthData(json);
		if (data != null) {
			setAccessToken(data.getAccessToken(), data.getTokenType());
		}
		return data;
	}

	/**
	 * Retrieves the Team for the current user account.
	 * @return Team
	 * @throws HelloSignException
	 */
	public Team getTeam() throws HelloSignException {
		HttpGetRequest request = new HttpGetRequest(URL_TEAM, auth);
		return new Team(request.getJsonResponse());
	}

	/**
	 * Creates a new team for the current user with the given name.
	 * @param teamName String team name
	 * @return Team
	 */
	public Team createTeam(String teamName) throws HelloSignException {
		Map<String, Serializable> fields = new HashMap<String, Serializable>();
		fields.put(Team.TEAM_NAME, teamName);
		HttpPostRequest request = new HttpPostRequest(URL_TEAM_CREATE, fields, auth);
		JSONObject json = request.getJsonResponse();
		return new Team(json);
	}
	
	/**
	 * Destroys the current user's team.
	 * @return int HTTP Status
	 * @throws HelloSignException
	 */
	public int destroyTeam() throws HelloSignException {
		HttpPostRequest request = new HttpPostRequest(URL_TEAM_DESTROY, auth);
		return request.getHttpResponseCode();
	}
	
	/**
	 * Updates the current user's team name.
	 * @param teamName String team name
	 * @return Team 
	 * @throws HelloSignException
	 */
	public Team updateTeamName(String teamName) throws HelloSignException {
		Map<String, Serializable> fields = new HashMap<String, Serializable>();
		fields.put(Team.TEAM_NAME, teamName);
		HttpPostRequest request = new HttpPostRequest(URL_TEAM, fields, auth);
		JSONObject json = request.getJsonResponse();
		return new Team(json);
	}
	
	/**
	 * Adds the user to the current user's team.
	 * @param idOrEmail String new team member's account ID or email address
	 * @return Team
	 * @throws HelloSignException
	 */
	public Team inviteTeamMember(String idOrEmail) throws HelloSignException {
		Map<String, Serializable> fields = new HashMap<String, Serializable>();
		if (idOrEmail.contains("@")) {
			fields.put(Account.ACCOUNT_EMAIL_ADDRESS, idOrEmail);
		} else {
			fields.put(Account.ACCOUNT_ID, idOrEmail);
		}
		HttpPostRequest request = new HttpPostRequest(URL_TEAM_ADD_MEMBER, fields, auth);
		JSONObject json = request.getJsonResponse();
		return new Team(json);	
	}
	
	/**
	 * Removes the team member indicated by the user account ID or email address.
	 * @param idOrEmail String removed team member's account ID or email address
	 * @return Team
	 * @throws HelloSignException
	 */
	public Team removeTeamMember(String idOrEmail) throws HelloSignException {
		Map<String, Serializable> fields = new HashMap<String, Serializable>();
		if (idOrEmail.contains("@")) {
			fields.put(Account.ACCOUNT_EMAIL_ADDRESS, idOrEmail);
		} else {
			fields.put(Account.ACCOUNT_ID, idOrEmail);
		}
		HttpPostRequest request = new HttpPostRequest(URL_TEAM_REMOVE_MEMBER, fields, auth);
		JSONObject json = request.getJsonResponse();
		return new Team(json);
	}
	
	/**
	 * Retrieves a Signature Request with the given ID.
	 * @param id String signature ID
	 * @return SignatureRequest
	 * @throws HelloSignException
	 */
	public SignatureRequest getSignatureRequest(String id) throws HelloSignException {
		HttpGetRequest request = new HttpGetRequest(URL_SIGNATURE_REQUEST + "/" + id, auth);
		return new SignatureRequest(request.getJsonResponse());
	}
	
	/**
	 * Retrieves the current user's signature requests. The resulting object represents
	 * a paged query result. The page information can be retrieved on from the ListInfo
	 * object on the SignatureRequestList.
	 * @return SignatureRequestList
	 * @throws HelloSignException
	 */
	public SignatureRequestList getSignatureRequests() 
			throws HelloSignException {
		HttpGetRequest request = new HttpGetRequest(
				URL_SIGNATURE_REQUEST_LIST, auth);
		return new SignatureRequestList(request.getJsonResponse());
	}
	
	/**
	 * Retrieves a specific page of the current user's signature requests.
	 * @param page int
	 * @return SignatureRequestList
	 * @throws HelloSignException
	 */
	public SignatureRequestList getSignatureRequests(int page) 
			throws HelloSignException {
		Map<String, String> params = new HashMap<String, String>();
		params.put(AbstractResourceList.PAGE, Integer.toString(page));
		HttpGetRequest request = new HttpGetRequest(
				URL_SIGNATURE_REQUEST_LIST, params, auth);
		return new SignatureRequestList(request.getJsonResponse());
	}
	
	/**
	 * Sends the provided signature request to HelloSign.
	 * @param req SignatureRequest
	 * @return SignatureRequest
	 * @throws HelloSignException
	 */
	public SignatureRequest sendSignatureRequest(SignatureRequest req) 
			throws HelloSignException {
		if (req.hasId()) {
			throw new HelloSignException(
					"Sending an existing signature request is not supported");		
		}
		HttpPostRequest request = new HttpPostRequest(
				URL_SIGNATURE_REQUEST_SEND, req.getPostFields(), auth);
		JSONObject json = request.getJsonResponse();
		return new SignatureRequest(json);
	}
	
	/**
	 * Retrieves the templates for the current user account.
	 * @return TemplateList
	 * @throws HelloSignException
	 */
	public TemplateList getTemplates() throws HelloSignException {
		HttpGetRequest request = new HttpGetRequest(URL_TEMPLATE_LIST, auth);
		return new TemplateList(request.getJsonResponse());
	}
	
	/**
	 * Retreives a page of templates for the current user account.
	 * @param page int
	 * @return TemplateList
	 * @throws HelloSignException
	 */
	public TemplateList getTemplates(int page) throws HelloSignException {
		Map<String, String> params = new HashMap<String, String>();
		params.put(AbstractResourceList.PAGE, Integer.toString(page));
		HttpGetRequest request = new HttpGetRequest(URL_TEMPLATE_LIST, params, auth);
		return new TemplateList(request.getJsonResponse());
	}
	
	/**
	 * Retrieves a specific Template based on the provided Template ID.
	 * @param templateId String Template ID
	 * @return Template
	 * @throws HelloSignException
	 */
	public Template getTemplate(String templateId) throws HelloSignException {
		String url = URL_TEMPLATE + "/" + templateId;
		HttpGetRequest request = new HttpGetRequest(url, auth);
		return new Template(request.getJsonResponse());
	}
	
	/**
	 * Adds the provided user to the template indicated by the provided template ID. 
	 * The new user can be designated using their account ID or email address. 
	 * @param templateId String template ID
	 * @param idOrEmail String account ID or email address
	 * @return Template
	 * @throws HelloSignException
	 */
	public Template addTemplateUser(String templateId, String idOrEmail) 
			throws HelloSignException {
		String url = URL_TEMPLATE_ADD_USER + "/" + templateId;
		Map<String, Serializable> fields = new HashMap<String, Serializable>();
		if (idOrEmail.contains("@")) {
			fields.put(Account.ACCOUNT_EMAIL_ADDRESS, idOrEmail);
		} else {
			fields.put(Account.ACCOUNT_ID, idOrEmail);
		}
		HttpPostRequest request = new HttpPostRequest(url, fields, auth);
		JSONObject json = request.getJsonResponse();
		return new Template(json);
	}

	public boolean deleteTemplate(String templateId) 
			throws HelloSignException {
		String url = this.URL_TEMPLATE_DELETE + "/" + templateId;
		HttpPostRequest request = new HttpPostRequest(url, auth);
		int response = request.getHttpResponseCode();
		if (response == 200) {
			return true;
		}
		throw new HelloSignException("Unable to delete template with ID " + templateId + ". Server returned: " + response);
	}
	
	/**
	 * Adds the provided user to the template indicated by the provided template ID. 
	 * The new user can be designated using their account ID or email address. 
	 * @param templateId String template ID
	 * @param idOrEmail String account ID or email address
	 * @return Template
	 * @throws HelloSignException
	 */
	public Template removeTemplateUser(String templateId, String idOrEmail) 
			throws HelloSignException {
		String url = URL_TEMPLATE_REMOVE_USER + "/" + templateId;
		Map<String, Serializable> fields = new HashMap<String, Serializable>();
		if (idOrEmail.contains("@")) {
			fields.put(Account.ACCOUNT_EMAIL_ADDRESS, idOrEmail);
		} else {
			fields.put(Account.ACCOUNT_ID, idOrEmail);
		}
		HttpPostRequest request = new HttpPostRequest(url, fields, auth);
		JSONObject json = request.getJsonResponse();
		return new Template(json);
	}
	
	/**
	 * Creates a new Signature Request based on the template provided. 
	 * @param req TemplateSignatureRequest
	 * @return SignatureRequest
	 * @throws HelloSignException
	 */
	public SignatureRequest sendTemplateSignatureRequest(TemplateSignatureRequest req) 
			throws HelloSignException {
		HttpPostRequest request = new HttpPostRequest(URL_TEMPLATE_SIGNATURE_REQUEST, 
				req.getPostFields(), auth);
		JSONObject json = request.getJsonResponse();
		return new SignatureRequest(json);
	}
	
	/**
	 * Cancels an existing signature request. If it has been completed, it will delete
	 * the signature request from your account. 
	 * @param id SignatureRequest id
	 * @return HttpStatus code
	 * @throws HelloSignException
	 */
	public int cancelSignatureRequest(String id) throws HelloSignException {
    	String cancelUrl = URL_SIGNATURE_REQUEST_CANCEL + "/" + id;
    	HttpPostRequest request = new HttpPostRequest(cancelUrl, auth);
    	return request.getHttpResponseCode();
	}
	
	/**
	 * Instructs HelloSign to email the given address with a reminder to sign the 
	 * SignatureRequest referenced by the given request ID. 
	 * 
	 * Note: You cannot send a reminder within 1 hours of the last reminder that was
	 * sent, manually or automatically.
	 * 
	 * @param requestId String SignatureRequest ID
	 * @param email String email
	 * @return SignatureRequest The request to be reminded
	 * @throws HelloSignException
	 */
	public SignatureRequest requestEmailReminder(String requestId, String email) 
			throws HelloSignException {
		Map<String, Serializable> fields = new HashMap<String, Serializable>();
		fields.put(Account.ACCOUNT_EMAIL_ADDRESS, email);
		String remindUrl = URL_SIGNATURE_REQUEST_REMIND + "/" + requestId;
		HttpPostRequest request = new HttpPostRequest(remindUrl, fields, auth);
		JSONObject json = request.getJsonResponse();
		return new SignatureRequest(json);
	}
	
	/**
	 * Retrieves the final PDF copy of a signature request, if it exists. 
	 * @param requestId String SignatureRequest ID
	 * @return File final copy file, or null if it does not yet exist
	 * @throws HelloSignException
	 * @deprecated Use getFiles(requestId)
	 */
	public File getFinalCopy(String requestId) throws HelloSignException {
		String finalCopyUrl = URL_SIGNATURE_REQUEST_FINAL_COPY + "/" + requestId;
		String filename = FINAL_COPY_FILE_NAME + "." + FINAL_COPY_FILE_EXT; 
		HttpGetRequest request = new HttpGetRequest(finalCopyUrl, auth);
		return request.getFileResponse(filename);
	}

	/**
	 * Retrieves a PDF copy of the files associated with a signature request.
	 * @param requestId String signature ID
	 * @return File PDF file
	 * @throws HelloSignException
	 */
	public File getFiles(String requestId) throws HelloSignException {
		return getFiles(requestId, SignatureRequest.SIGREQ_FORMAT_PDF);
	}

	/**
	 * Retrieves the file associated with a signature request.
	 * @param requestId String signature ID
	 * @param format String format, see SignatureRequest for available types
	 * @return File
	 * @throws HelloSignException
	 */
	public File getFiles(String requestId, String format) throws HelloSignException {
		String filesUrl = URL_SIGNATURE_REQUEST_FILES + "/" + requestId;
		Map<String, String> params = new HashMap<String, String>();
		params.put(URL_PARAM_FILE_TYPE, format);
		HttpGetRequest request = new HttpGetRequest(filesUrl, params, auth);
		String filename = FILES_FILE_NAME + "." + FILES_FILE_EXT;
		return request.getFileResponse(filename);
	}
	
	/**
	 * Creates a signature request that can be embedded within your website.
	 * @param embeddedReq EmbeddedRequest
	 * @return SignatureRequest
	 * @throws HelloSignException
	 */
	public AbstractRequest createEmbeddedRequest(EmbeddedRequest embeddedReq) 
			throws HelloSignException {
		String url = URL_SIGNATURE_REQUEST_EMBEDDED;
		Class<?> returnType = SignatureRequest.class;
		AbstractRequest req = embeddedReq.getRequest();
		if (req instanceof TemplateSignatureRequest) {
			url = URL_SIGNATURE_REQUEST_EMBEDDED_TEMPLATE;
		} else if (req instanceof UnclaimedDraft) {
			if (((UnclaimedDraft) req).getRequest() instanceof TemplateSignatureRequest) {
				url = URL_UNCLAIMED_DRAFT_CREATE_EMBEDDED_WITH_TEMPLATE;
			} else {
				url = URL_UNCLAIMED_DRAFT_CREATE_EMBEDDED;
			}
			returnType = UnclaimedDraft.class;
		}
		HttpPostRequest request = new HttpPostRequest(url, embeddedReq.getPostFields(), auth);
		JSONObject json = request.getJsonResponse();
		try {
			Constructor<?> constructor = returnType.getConstructor(JSONObject.class);
			return (AbstractRequest) constructor.newInstance(json);
		} catch (Exception ex) {
			throw new HelloSignException(ex);
		}
	}

	/**
	 * Retrieves the necessary information to build an embedded signature
	 * request. 
	 * @param signatureId String ID of the signature request to embed
	 * @return EmbeddedResponse
	 * @throws HelloSignException
	 */
	public EmbeddedResponse getEmbeddedSignUrl(String signatureId) 
			throws HelloSignException {
		String url = URL_EMBEDDED_SIGN_URL + "/" + signatureId;
		HttpPostRequest request = new HttpPostRequest(url, auth);
		JSONObject json = request.getJsonResponse();
		return new EmbeddedResponse(json);
	}

	/**
	 * Retrieves the necessary information to edit an embedded template.
	 * @param templateId String ID of the signature request to embed
	 * @return EmbeddedResponse
	 * @throws HelloSignException
	 */
	public EmbeddedResponse getEmbeddedTemplateEditUrl(String templateId) 
			throws HelloSignException {
		String url = this.URL_EMBEDDED_EDIT_URL + "/" + templateId;
		HttpPostRequest request = new HttpPostRequest(url, auth);
		JSONObject json = request.getJsonResponse();
		return new EmbeddedResponse(json);
	}

	/**
	 * Creates an unclaimed draft using the provided request draft object.
	 * @param draft UnclaimedDraft
	 * @return UnclaimedDraft The created draft
	 * @throws HelloSignException
	 */
	public UnclaimedDraft createUnclaimedDraft(UnclaimedDraft draft) 
			throws HelloSignException {
		String url = URL_UNCLAIMED_DRAFT_CREATE;
		if (draft.isForEmbeddedSigning()) {
			url = URL_UNCLAIMED_DRAFT_CREATE_EMBEDDED;
		}
		HttpPostRequest request = new HttpPostRequest(url, draft.getPostFields(), auth);
		JSONObject json = request.getJsonResponse();
		return new UnclaimedDraft(json);
	}

	/**
	 * Creates a template draft that can be used for embedded template creation.
	 * @param req EmbeddedRequest
	 * @return Template the unclaimed template draft
	 * @throws HelloSignException
	 */
	public TemplateDraft createEmbeddedTemplateDraft(EmbeddedRequest req)
			throws HelloSignException {
		String url = URL_TEMPLATE_CREATE_EMBEDDED_DRAFT;
		Class<?> returnType = TemplateDraft.class;
		HttpPostRequest request = new HttpPostRequest(url, req.getPostFields(), auth);
		JSONObject json = request.getJsonResponse();
		try {
			Constructor<?> constructor = returnType.getConstructor(JSONObject.class);
			return (TemplateDraft) constructor.newInstance(json);
		} catch (Exception ex) {
			throw new HelloSignException(ex);
		}
	}

	/**
	 * Performs a simple call to the HelloSign API to see if it's available
	 * with the given credentials.
	 * @return true if HelloSign is available and the client is online, 
	 * false otherwise.
	 */
	public boolean isOnline() {
		HttpURLConnection connection = null;
		try {
			connection = (HttpURLConnection) new URL(URL_API).openConnection();
			connection.setRequestMethod("OPTIONS");
			return HttpURLConnection.HTTP_OK == connection.getResponseCode();
		} catch (Exception ex) {
			// Ignore
		}
		return false;
	}
	
	/**
	 * Sets the access token for the OAuth user that this client will use to
	 * perform requests.
	 * @param accessToken String access token
	 * @param tokenType String token type
	 */
	public void setAccessToken(String accessToken, String tokenType) 
			throws HelloSignException {
		auth.setAccessToken(accessToken, tokenType);
	}
	
	// Utility methods to assist in testing, since we are dynamically configuring these URLs
	// based on a system property ("hellosign.env").
	public String getApiUrl() {
		return URL_API;
	}
	public String getSignatureRequestUrl() {
		return URL_SIGNATURE_REQUEST;
	}
	public String getSignatureRequestSendUrl() {
		return URL_SIGNATURE_REQUEST_SEND;
	}
	public String getSignatureRequestCancelUrl() {
		return URL_SIGNATURE_REQUEST_CANCEL;
	}
	public String getTemplateSignatureRequestUrl() {
		return URL_TEMPLATE_SIGNATURE_REQUEST;
	}
	
	// =======================================================================
	// Static helpers
	// =======================================================================
	private static void disableStrictSSL() {
		// Create a trust manager that does not validate certificate chains
		TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
			public X509Certificate[] getAcceptedIssuers() {
				return new X509Certificate[0];
			}

			public void checkClientTrusted(X509Certificate[] certs,
					String authType) {
			}

			public void checkServerTrusted(X509Certificate[] certs,
					String authType) {
			}
		} };

		// Ignore differences between given hostname and certificate hostname
		HostnameVerifier hv = new HostnameVerifier() {
			public boolean verify(String hostname, SSLSession session) {
				return true;
			}
		};

		// Install the all-trusting trust manager
		try {
			SSLContext sc = SSLContext.getInstance("SSL");
			sc.init(null, trustAllCerts, new SecureRandom());
			HttpsURLConnection
					.setDefaultSSLSocketFactory(sc.getSocketFactory());
			HttpsURLConnection.setDefaultHostnameVerifier(hv);
		} catch (Exception e) {
		}
	}

}
