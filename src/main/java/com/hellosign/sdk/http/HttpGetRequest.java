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
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hellosign.sdk.HelloSignException;

public class HttpGetRequest extends AbstractHttpRequest {

    private static final Logger logger = LoggerFactory.getLogger(HttpGetRequest.class);

    private Map<String, String> parameters = null;

    /**
     * Constructor
     * @param url String
     * @throws HelloSignException thrown if there is a problem making
     * the HTTP request or processing the response
     */
    public HttpGetRequest(String url) 
            throws HelloSignException {
        this(url, null, null);
    }

    /**
     * Constructor
     * @param url String
     * @param auth Authentication
     * @throws HelloSignException thrown if there is a problem making
     * the HTTP request or processing the response
     */
    public HttpGetRequest(String url, Authentication auth) 
            throws HelloSignException {
        this(url, null, auth);
    }

    /**
     * Constructor
     * @param url String
     * @param parameters Map
     * @throws HelloSignException thrown if there is a problem making
     * the HTTP request or processing the response
     */
    public HttpGetRequest(String url, Map<String, String> parameters) 
            throws HelloSignException {
        this(url, parameters, null);
    }

    /**
     * Constructor
     * @param url String
     * @param parameters Map
     * @param auth Authentication
     * @throws HelloSignException thrown if there is a problem making
     * the HTTP request or processing the response
     */
    public HttpGetRequest(String url, Map<String, String> parameters, Authentication auth) 
            throws HelloSignException {
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
     * Executes the GET request and converts the response to a JSON
     * object. 
     * @return JSONObject
     * @throws HelloSignException thrown if there is a problem making
     * the HTTP request.
     */
    public JSONObject getJsonResponse() throws HelloSignException {
        JSONObject json = null;
        try {
            HttpURLConnection connection = get(url, parameters, auth);
            int httpCode = connection.getResponseCode();
            InputStream is = null;
            if (HttpURLConnection.HTTP_OK == httpCode) {
                is = connection.getInputStream();
            } else {
                is = connection.getErrorStream();
            }
            String response = convertStreamToString(is);
            json = new JSONObject(response);
            validate(json, httpCode);
        } catch (Exception ex) {
            throw new HelloSignException(ex);
        }
        return json;
    }

    /**
     * Execute the GET request and process the response as a file. The file
     * will be stored as a temporary file with the given filename, but using
     * the File.createTemporaryFile() method. This file will append a 
     * timestamp to the filename.  
     * @param filename String
     * @return File
     * @throws HelloSignException thrown if there is a problem creating the file
     */
    public File getFileResponse(String filename) throws HelloSignException {
        File f = createTemporaryFile(filename);
        return getFile(url, auth, f);
    }

    /**
     * Helper method to create a temporary file.
     * @param filename String
     * @return File
     * @throws HelloSignException thrown if the file cannot be created
     */
    private static File createTemporaryFile(String filename) 
            throws HelloSignException {
        String prefix = filename.substring(0, filename.indexOf("."));
        String postfix = filename.substring(filename.indexOf(".") + 1, filename.length());
        if (prefix == null || postfix == null) {
            throw new HelloSignException("Invalid file name: " + prefix + "." + postfix);
        }
        File f = null;
        try {
            f = File.createTempFile(prefix, "." + postfix);
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new HelloSignException(ex.getMessage());
        }
        return f;
    }

    /**
     * Helper method to execute an HTTP GET request.
     * @param url String
     * @param parameters Map<String, String>
     * @param auth Authentication
     * @return HttpURLConnection
     * @throws UnsupportedEncodingException thrown if the encoding is not supported
     * @throws IOException thrown if there is a problem handling the request
     * @throws MalformedURLException thrown if the URL is unrecognizable
     */
    private static HttpURLConnection get(String url,
            Map<String, String> parameters, Authentication auth)
            throws UnsupportedEncodingException, IOException,
            MalformedURLException {
        if (parameters != null) {
            url += "?";
            Iterator<String> keys = parameters.keySet().iterator();
            while (keys.hasNext()) {
                String key = keys.next();
                url += URLEncoder.encode(key, DEFAULT_ENCODING) 
                        + "=" + 
                        URLEncoder.encode(parameters.get(key), DEFAULT_ENCODING);
                if (keys.hasNext()) {
                    url += "&";
                }
            }
        }
        logger.debug("GET: " + url);
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestProperty("Accept-Charset", DEFAULT_ENCODING);
        connection.setRequestProperty("user-agent", USER_AGENT);
        if (auth != null) {
            logger.debug("Authenticating...");
            auth.authenticate(connection, url);
        }
        return connection;
    }

    /**
     * Helper method to make an HTTP request and return a File object.
     * @param url String
     * @param auth Authentication
     * @param f File that will be written to
     * @return File
     * @throws HelloSignException thrown if there is a problem with the
     * HTTP request or writing to the file
     */
    private static File getFile(String url, Authentication auth, File f) 
            throws HelloSignException {
        InputStream in = null;
        OutputStream out = null;
        try {
            HttpURLConnection connection = get(url, null, auth);
            int httpCode = connection.getResponseCode();
            if (httpCode == HttpURLConnection.HTTP_OK) {
                Files.copy(connection.getInputStream(), f.toPath(), StandardCopyOption.REPLACE_EXISTING);
                return f;    
            }

            // We know we encountered an error here, so let's parse
            // the response as an API error message
            in = connection.getErrorStream();
            String responseStr = convertStreamToString(in);
            try {
                JSONObject json = new JSONObject(responseStr);
                validate(json, httpCode);
            } catch (JSONException e) {
                throw new HelloSignException(
                    "Error encountered attempting to parse API error response: " 
                    + e.getMessage());
            }

            // If we were not able to throw an exception by now, 
            // throw one, because the user should not be here.
            throw new HelloSignException(
                    "Unable to process response from HelloSign. Please contact support.");

        } catch (HelloSignException ex) {
            // Rethrow any exceptions we're throwing ourselves
            throw ex; 
        } catch (Exception e) {
            // Wrap any exceptions we receive
            throw new HelloSignException(e); 
        } finally {
            // Cleanup
            if (in != null) {
                try { in.close(); } catch (Exception e) {}
            }
            if (out != null) {
                try { out.close(); } catch (Exception e) {}
            }
        }
    }

}
