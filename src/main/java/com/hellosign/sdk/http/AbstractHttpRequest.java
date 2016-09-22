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

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import java.util.Properties;
import java.util.Scanner;

import org.json.JSONException;
import org.json.JSONObject;

import com.hellosign.sdk.HelloSignException;

public abstract class AbstractHttpRequest {

    public final static String DEFAULT_ENCODING = "UTF-8";
    public final static String USER_AGENT = createUserAgent();

    private static String createUserAgent() {
        String filename = "config.properties";
        Properties props = new Properties();
        InputStream is = AbstractHttpRequest.class.getClassLoader().getResourceAsStream(filename);
        if (is != null) {
            try {
                props.load(is);
            } catch (IOException e) {
                throw new Error(e);
            }
        } else {
            throw new Error(new FileNotFoundException("Could not find " + filename));
        }
        String version = props.getProperty("sdk.version");
        if (version == null) {
            version = "x.x.x";
        }
        return "hellosign-java-sdk/" + version;
    }

    protected String url;
    protected Authentication auth;

    public static String convertStreamToString(InputStream in) {
        Scanner s = new Scanner(in);
        s.useDelimiter("\\A");
        String result = (s.hasNext()) ? s.next() : "";
        s.close();
        return result;
    }

    protected static void validate(JSONObject json, int code) throws HelloSignException {
        if (json.has("error")) {
            try {
                JSONObject error = json.getJSONObject("error");
                String message = error.getString("error_msg");
                String type = error.getString("error_name");
                throw new HelloSignException(message, code, type);
            } catch (JSONException ex) {
                throw new HelloSignException(ex);
            }
        }
    }

    /**
     * Creates an HTTP connection.
     *
     * Optionally checks for proxy parameters and creates a proxied connection
     * using the system properties: 
     *   "hellosign.proxy.url" - the URL of the HTTP proxy
     *   "hellosign.proxy.port" - the port of the HTTP proxy
     *
     * @param url String URL to connect to
     * @return HttpUrlConnection the (proxied) connection to the URL
     * @throws MalformedURLException thrown if the URL is invalid
     * @throws IOException thrown if IO cannot be established with the URL
     */
    public static HttpURLConnection getConnection(String url) throws MalformedURLException, IOException {
        HttpURLConnection conn = null;
        Proxy proxy = null;
        String proxyUrlStr = System.getProperty("hellosign.proxy.url");
        String proxyPortStr = System.getProperty("hellosign.proxy.port");
        Integer proxyPort = 80; // Default to port 80
        if (proxyPortStr != null) {
            proxyPort = Integer.parseInt(proxyPortStr);
        }
        if (proxyUrlStr != null) {
            proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyUrlStr, proxyPort));
        }
        if (proxy == null) {
            conn = (HttpURLConnection) new URL(url).openConnection(); 
        } else {
            conn = (HttpURLConnection) new URL(url).openConnection(proxy);
        }
        return conn;
    }
}
