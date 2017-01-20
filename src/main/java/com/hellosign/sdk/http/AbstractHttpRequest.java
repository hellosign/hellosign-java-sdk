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

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Properties;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hellosign.sdk.HelloSignException;

public abstract class AbstractHttpRequest {

    private static final Logger logger = LoggerFactory.getLogger(AbstractHttpRequest.class);

    public final static String DEFAULT_ENCODING = "UTF-8";
    public final static String USER_AGENT = createUserAgent();

    // Request variables
    protected String url;
    protected Authentication auth;

    // Response variables
    protected Integer lastHttpStatusCode;
    protected InputStream lastResponseStream;

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

    /**
     * Executes this HTTP request and preserves the response stream and HTTP
     * response code for processing.
     * 
     * @throws HelloSignException Thrown if there is an error while making the
     *         HTTP request to the HelloSign API.
     */
    public void execute() throws HelloSignException {
        HttpURLConnection connection = getConnection();
        try {
            // Execute the request and save the HTTP status code
            lastHttpStatusCode = connection.getResponseCode();

            // Save the stream object for processing
            if (lastHttpStatusCode >= 200 && lastHttpStatusCode < 300) {
                logger.debug("OK!");
                lastResponseStream = connection.getInputStream();
            } else {
                logger.error("Error! HTTP Code = " + lastHttpStatusCode);
                lastResponseStream = connection.getErrorStream();
            }
        } catch (Exception ex) {
            throw new HelloSignException(ex);
        }
    }

    /**
     * Returns the last HTTP response code.
     * 
     * @return Integer response code
     */
    public Integer getResponseCode() {
        return lastHttpStatusCode;
    }

    /**
     * Returns the last response stream as a string.
     * 
     * @return String
     */
    public String getResponseBody() {
        String responseStr = "";
        if (lastResponseStream == null) {
            logger.error("Unable to parse JSON from empty response!");
        } else {
            Scanner s = new Scanner(lastResponseStream);
            s.useDelimiter("\\A");
            responseStr = (s.hasNext()) ? s.next() : "";
            s.close();
        }
        return responseStr;
    }

    /**
     * Creates an HTTP connection.
     *
     * Optionally checks for proxy parameters and creates a proxied connection
     * using the system properties: "hellosign.proxy.url" - the URL of the HTTP
     * proxy "hellosign.proxy.port" - the port of the HTTP proxy
     *
     * @param url String URL to connect to
     * @return HttpUrlConnection the (proxied) connection to the URL
     * @throws MalformedURLException thrown if the URL is invalid
     * @throws IOException thrown if IO cannot be established with the URL
     */
    protected static HttpURLConnection getProxiedConnection(String url) throws MalformedURLException, IOException {
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

    /**
     * The method class will create the appropriate connection with an endpoint,
     * parameters, etc.
     * 
     * @return HttpURLConnection
     * @throws HelloSignException Thrown if a connection cannot be created
     */
    abstract protected HttpURLConnection getConnection() throws HelloSignException;

    /**
     * Write the last response to a file.
     * 
     * @param f File
     * @return long bytes written
     * @throws HelloSignException Thrown if an exception occurs during the copy
     *         of the response stream to the given file.
     */
    public long getResponseAsFile(File f) throws HelloSignException {
        long bytesWritten = 0;
        try {
            bytesWritten = Files.copy(lastResponseStream, f.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            throw new HelloSignException(e);
        }
        return bytesWritten;
    }
}
