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

import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hellosign.sdk.HelloSignException;

public class HttpGetRequest extends AbstractHttpRequest {

    private static final Logger logger = LoggerFactory.getLogger(HttpGetRequest.class);

    private Map<String, String> parameters = null;

    /**
     * Constructor
     * 
     * @param url String
     * @throws HelloSignException thrown if there is a problem making the HTTP
     *         request or processing the response
     */
    public HttpGetRequest(String url) throws HelloSignException {
        this(url, null, null);
    }

    /**
     * Constructor
     * 
     * @param url String
     * @param auth Authentication
     * @throws HelloSignException thrown if there is a problem making the HTTP
     *         request or processing the response
     */
    public HttpGetRequest(String url, Authentication auth) throws HelloSignException {
        this(url, null, auth);
    }

    /**
     * Constructor
     * 
     * @param url String
     * @param parameters Map
     * @throws HelloSignException thrown if there is a problem making the HTTP
     *         request or processing the response
     */
    public HttpGetRequest(String url, Map<String, String> parameters) throws HelloSignException {
        this(url, parameters, null);
    }

    /**
     * Constructor
     * 
     * @param url String
     * @param parameters Map
     * @param auth Authentication
     * @throws HelloSignException thrown the URL is empty
     */
    public HttpGetRequest(String url, Map<String, String> parameters, Authentication auth) throws HelloSignException {
        if (url == null || "".equals(url)) {
            throw new HelloSignException("URL cannot be null or empty");
        }
        this.url = url;
        if (parameters != null) {
            this.parameters = parameters;
        }
        if (auth != null) {
            this.auth = new Authentication(auth);
        }
    }

    /**
     * @return HttpURLConnection used to make the HTTP request
     * @see AbstractHttpRequest#getConnection()
     */
    @Override
    protected HttpURLConnection getConnection() throws HelloSignException {
        if (parameters != null) {
            url += "?";
            Iterator<String> keys = parameters.keySet().iterator();
            while (keys.hasNext()) {
                String key = keys.next();
                try {
                    url += URLEncoder.encode(key, DEFAULT_ENCODING) + "="
                            + URLEncoder.encode(parameters.get(key), DEFAULT_ENCODING);
                } catch (UnsupportedEncodingException ex) {
                    throw new HelloSignException(ex);
                }
                if (keys.hasNext()) {
                    url += "&";
                }
            }
        }
        logger.debug("GET: " + url);
        HttpURLConnection connection;
        try {
            connection = getProxiedConnection(url);
        } catch (Exception e) {
            throw new HelloSignException(e);
        }
        connection.setRequestProperty("Accept-Charset", DEFAULT_ENCODING);
        connection.setRequestProperty("user-agent", USER_AGENT);
        if (auth != null) {
            logger.debug("Authenticating...");
            auth.authenticate(connection, url);
        }
        return connection;
    }
}
