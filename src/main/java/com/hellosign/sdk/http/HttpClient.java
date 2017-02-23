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

import java.io.File;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.HashMap;

/**
 * The MIT License (MIT)
 * 
 * Copyright (C) 2016 hellosign.com
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

import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hellosign.sdk.HelloSignException;

/**
 * Abstracts HTTP requests.
 * 
 * @author cpaul
 */
public class HttpClient {

    private static final Logger logger = LoggerFactory.getLogger(HttpClient.class);

    private Authentication auth;
    private Map<String, String> getParams;
    private Map<String, Serializable> postFields;
    private AbstractHttpRequest request;

    public HttpClient() {
        String disableSslCheck = System.getProperty("hellosign.disable.ssl");
        if (disableSslCheck != null && "true".equalsIgnoreCase(disableSslCheck)) {
            disableStrictSSL();
        }
    }

    public HttpClient withAuth(Authentication auth) {
        this.auth = auth;
        return this;
    }

    public HttpClient withGetParam(String key, String value) {
        if (getParams == null) {
            getParams = new HashMap<String, String>();
        }
        getParams.put(key, value);
        return this;
    }

    public HttpClient withGetParams(Map<String, String> params) {
        getParams = params;
        return this;
    }

    public HttpClient withPostField(String key, Serializable value) {
        if (postFields == null) {
            postFields = new HashMap<String, Serializable>();
        }
        postFields.put(key, value);
        return this;
    }

    public HttpClient withPostFields(Map<String, Serializable> fields) {
        this.postFields = fields;
        return this;
    }

    /**
     * Returns the response to the last HTTP request as a string.
     * 
     * @return String or null if the request has not been created
     */
    public String getLastResponse() {
        if (request == null) {
            return null;
        }
        return request.getResponseBody();
    }

    /**
     * Returns the HTTP status code for the last request.
     * 
     * @return Integer or null if the request has not been created
     */
    public Integer getLastResponseCode() {
        if (request == null) {
            return null;
        }
        return request.getResponseCode();
    }

    /**
     * Returns the last HTTP request body as a file.
     * 
     * @param f File that should contain the response
     * @return long bytes written
     * @throws HelloSignException thrown if there is a problem writing to the
     *         file or reading the response stream
     */
    public long getLastResponseAsFile(File f) throws HelloSignException {
        return request.getResponseAsFile(f);
    }

    /**
     * Inspects the JSONObject response for errors and throws an exception if
     * found.
     * 
     * @param json JSONObject response
     * @param code HTTP response code
     * @throws HelloSignException thrown if an error is reported from the API
     *         call
     */
    private void validate(JSONObject json) throws HelloSignException {
        if (json.has("error")) {
            try {
                JSONObject error = json.getJSONObject("error");
                String message = error.getString("error_msg");
                String type = error.getString("error_name");
                throw new HelloSignException(message, getLastResponseCode(), type);
            } catch (JSONException ex) {
                throw new HelloSignException(ex);
            }
        }
    }

    /**
     * Clears the client after a request has successfully completed.
     */
    private void reset() {
        this.auth = null;
        this.getParams = null;
        this.postFields = null;
        this.request = null;
    }

    /**
     * Executes the request and returns the response as a JSONObject.
     * 
     * @return JSONObject response
     * @throws HelloSignException thrown if there is a problem executing the
     *         request
     */
    public JSONObject asJson() throws HelloSignException {
        JSONObject json = null;
        String response = getLastResponse();
        logger.debug("Response body: " + response);
        try {
            json = new JSONObject(response);
        } catch (JSONException e) {
            throw new HelloSignException(e);
        }
        validate(json);
        reset();
        return json;
    }

    /**
     * Executes the request and returns the response as a File.
     * 
     * @param fileName String name of destination file
     * @return File response
     * @throws HelloSignException thrown if there is a problem executing the
     *         request
     */
    public File asFile(String fileName) throws HelloSignException {
        Integer lastResponseCode = getLastResponseCode();
        if (lastResponseCode != null && lastResponseCode != HttpURLConnection.HTTP_OK) {
            this.asJson(); // Will validate response
        }
        File f = createTemporaryFile(fileName);
        if (getLastResponseAsFile(f) == 0) {
            logger.warn("No bytes written to file: " + fileName);
        }
        reset();
        return f;
    }

    /**
     * Helper method to create a temporary file.
     * 
     * @param filename String
     * @return File temporary file handle
     * @throws HelloSignException thrown if the file cannot be created
     */
    private File createTemporaryFile(String filename) throws HelloSignException {
        String prefix = filename.substring(0, filename.indexOf("."));
        String postfix = filename.substring(filename.indexOf(".") + 1, filename.length());
        if (prefix == null || postfix == null) {
            throw new HelloSignException("Invalid file name: " + prefix + "." + postfix);
        }
        File f = null;
        try {
            f = File.createTempFile(prefix, "." + postfix);
        } catch (Exception ex) {
            throw new HelloSignException(ex);
        }
        return f;
    }

    /**
     * Executes the request and returns the HTTP response code.
     * 
     * @return int HTTP response code
     * @throws HelloSignException thrown if no request has been performed
     */
    public int asHttpCode() throws HelloSignException {
        Integer code = getLastResponseCode();
        if (code == null) {
            throw new HelloSignException("No request performed");
        }
        if (code >= 200 && code < 300) {
            reset();
            return code;
        }
        throw new HelloSignException("HTTP Code " + code);
    }

    /**
     * Initializes a GET request to the given URL.
     * 
     * @param url String url
     * @return HttpClient
     * @throws HelloSignException thrown if the url is invalid
     */
    public HttpClient get(String url) throws HelloSignException {
        if (postFields != null) {
            logger.warn("POST fields set for a GET request, they will be ignored");
        }
        request = new HttpGetRequest(url, getParams, auth);
        request.execute();
        return this;
    }

    /**
     * Initializes a POST request to the given URL.
     * 
     * @param url String url
     * @return HttpClient
     * @throws HelloSignException thrown if the url is invalid
     */
    public HttpClient post(String url) throws HelloSignException {
        if (getParams != null) {
            logger.warn("GET parameters set for a POST request, they will be ignored");
        }
        request = new HttpPostRequest(url, postFields, auth);
        request.execute();
        return this;
    }

    /**
     * Initializes a DELETE request to the given URL.
     * 
     * @param url String url
     * @return HttpClient
     * @throws HelloSignException thrown if the url is invalid
     */
    public HttpClient delete(String url) throws HelloSignException {
        request = new HttpDeleteRequest(url, auth);
        request.execute();
        return this;
    }

    /**
     * Makes a PUT request to the given URL
     * 
     * @param url String url
     * @return HttpClient
     * @throws HelloSignException thrown if the url is invalid
     */
    public HttpClient put(String url) throws HelloSignException {
        request = new HttpPutRequest(url, postFields, auth);
        request.execute();
        return this;
    }

    /**
     * Makes an OPTIONS request to the given URL
     * 
     * @param url String URL
     * @return HttpClient
     * @throws HelloSignException thrown if the URL is invalid
     */
    public HttpClient options(String url) throws HelloSignException {
        request = new HttpOptionsRequest(url);
        request.execute();
        return this;
    }

    /**
     * Helper method that allows this client to ignore SSL certificates when
     * making API requests.
     */
    private void disableStrictSSL() {
        // Create a trust manager that does not validate certificate chains
        TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }

            public void checkClientTrusted(X509Certificate[] certs, String authType) {
            }

            public void checkServerTrusted(X509Certificate[] certs, String authType) {
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
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            HttpsURLConnection.setDefaultHostnameVerifier(hv);
        } catch (Exception e) {
        }
    }
}
