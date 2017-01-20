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
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hellosign.sdk.HelloSignException;

/**
 * This utility class provides an abstraction layer for sending multipart HTTP
 * POST requests to a web server.
 */
public class HttpPostRequest extends AbstractHttpRequest {

    private static final Logger logger = LoggerFactory.getLogger(HttpPostRequest.class);

    private final String boundary;
    private static final String LINE_FEED = "\r\n";

    protected String method = "POST";

    private HttpURLConnection httpConn;
    private OutputStream outputStream;
    private PrintWriter writer;

    private Map<String, Serializable> fields = null;

    /**
     * Constructor
     * 
     * @param url String
     * @throws HelloSignException thrown if there is a problem making the HTTP
     *         request or processing the response
     */
    public HttpPostRequest(String url) throws HelloSignException {
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
    public HttpPostRequest(String url, Authentication auth) throws HelloSignException {
        this(url, null, auth);
    }

    /**
     * Constructor
     * 
     * @param url String
     * @param fields Map
     * @throws HelloSignException thrown if there is a problem making the HTTP
     *         request or processing the response
     */
    public HttpPostRequest(String url, Map<String, Serializable> fields) throws HelloSignException {
        this(url, fields, null);
    }

    /**
     * Constructor
     * 
     * @param url String
     * @param fields Map
     * @param auth Authentication
     * @throws HelloSignException thrown if there is a problem making the HTTP
     *         request or processing the response
     */
    public HttpPostRequest(String url, Map<String, Serializable> fields, Authentication auth)
            throws HelloSignException {
        if (url == null || "".equals(url)) {
            throw new HelloSignException("URL cannot be null or empty");
        }
        this.url = url;
        if (fields != null) {
            this.fields = fields;
        }
        if (auth != null) {
            this.auth = new Authentication(auth);
        }
        // creates a unique boundary based on time stamp
        boundary = "===" + Long.toHexString(System.currentTimeMillis()) + "===";
    }

    /**
     * Helper method to make an HTTP POST request. Intelligently detects whether
     * Files have been attached and sends as an multipart form request.
     * 
     * @return HttpUrlConnection
     * @throws HelloSignException thrown if there is a problem making the HTTP
     *         request or processing the result
     */
    @Override
    protected HttpURLConnection getConnection() throws HelloSignException {
        if (fields != null) {
            for (String key : fields.keySet()) {
                if (fields.get(key) instanceof File) {
                    return postWithFile();
                }
            }
        }
        return postQuery();
    }

    /**
     * Helper method to make an HTTP POST request.
     * 
     * @return HttpURLConnection
     * @throws HelloSignException thrown if there is a problem making the HTTP
     *         request or processing the result
     */
    private HttpURLConnection postQuery() throws HelloSignException {
        logger.debug(this.method + ": " + url);
        HttpURLConnection connection;
        try {
            connection = getProxiedConnection(url);
            if (!this.method.equals("POST")) {
                connection.setRequestMethod(method);
            }
        } catch (Exception e) {
            throw new HelloSignException(e);
        }
        connection.setDoOutput(true); // sets POST method

        connection.setRequestProperty("user-agent", USER_AGENT);
        connection.setRequestProperty("accept-encoding", DEFAULT_ENCODING);
        auth.authenticate(connection, url);
        StringBuffer sb = new StringBuffer();
        if (fields != null) {
            Iterator<String> keys = fields.keySet().iterator();
            while (keys.hasNext()) {
                String key = keys.next();
                Serializable val = fields.get(key);
                if (val == null) {
                    throw new HelloSignException("POST value for key '" + key + "' is null");
                }
                String value;
                String encodedKey;
                try {
                    value = URLEncoder.encode(val.toString(), DEFAULT_ENCODING);
                    encodedKey = URLEncoder.encode(key, DEFAULT_ENCODING);
                } catch (UnsupportedEncodingException e) {
                    throw new HelloSignException(e);
                }
                logger.debug("\t" + key + " = " + val.toString());
                sb.append(encodedKey + "=" + value);
                if (keys.hasNext()) {
                    sb.append("&");
                }
            }
        }
        try {
            OutputStream output = connection.getOutputStream();
            try {
                output.write(sb.toString().getBytes(DEFAULT_ENCODING));
            } finally {
                try {
                    output.close();
                } catch (IOException logOrIgnore) {
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new HelloSignException(ex.getMessage());
        }
        return connection;
    }

    /**
     * Helper method to make an HTTP POST request with a File.
     * 
     * @return HttpURLConnection
     * @throws HelloSignException thrown if there is a problem making the HTTP
     *         request or processing the result
     */
    private HttpURLConnection postWithFile() throws HelloSignException {
        try {
            openMultipartPostConnection();
            if (fields != null) {
                for (String key : fields.keySet()) {
                    Serializable val = fields.get(key);
                    if (val instanceof File) {
                        addFilePart(key, (File) val);
                    } else {
                        addFormField(key, val.toString());
                    }
                }
            }
            return finish();
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new HelloSignException(ex.getMessage());
        }
    }

    private void openMultipartPostConnection() throws IOException {
        httpConn = getProxiedConnection(this.url);
        httpConn.setUseCaches(false);
        httpConn.setDoOutput(true); // indicates POST method
        httpConn.setDoInput(true);
        httpConn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
        httpConn.setRequestProperty("User-Agent", USER_AGENT);
        if (auth != null) {
            auth.authenticate(httpConn, this.url);
        }
        outputStream = httpConn.getOutputStream();
        writer = new PrintWriter(new OutputStreamWriter(outputStream, DEFAULT_ENCODING), true);
    }

    private void addFormField(String name, String value) {
        write("--" + boundary).write(LINE_FEED);
        write("Content-Disposition: form-data; name=\"" + name + "\"").write(LINE_FEED);
        write("Content-Type: text/plain; charset=" + DEFAULT_ENCODING).write(LINE_FEED);
        write(LINE_FEED);
        write(value).append(LINE_FEED);
        writer.flush();
    }

    private void addFilePart(String fieldName, File uploadFile) throws IOException {
        String fileName = uploadFile.getName();
        write("--" + boundary).write(LINE_FEED);
        write("Content-Disposition: form-data; name=\"" + fieldName + "\"; filename=\"" + fileName + "\"")
                .write(LINE_FEED);
        write("Content-Type: " + URLConnection.guessContentTypeFromName(fileName)).write(LINE_FEED);
        write("Content-Transfer-Encoding: binary").write(LINE_FEED);
        write(LINE_FEED);
        writer.flush();

        FileInputStream inputStream = new FileInputStream(uploadFile);
        byte[] buffer = new byte[4096];
        int bytesRead = -1;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
            logger.debug("  " + bytesRead + " bytes written...");
        }
        outputStream.flush();
        inputStream.close();

        write(LINE_FEED);
        writer.flush();
    }

    private HttpURLConnection finish() throws IOException {
        writer.flush();
        write("--" + boundary + "--").write(LINE_FEED);
        writer.close();
        return httpConn;
    }

    private PrintWriter write(String str) {
        logger.debug(str);
        return writer.append(str);
    }

}