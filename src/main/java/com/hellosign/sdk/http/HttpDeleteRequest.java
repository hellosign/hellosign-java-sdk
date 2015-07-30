package com.hellosign.sdk.http;

import java.net.HttpURLConnection;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hellosign.sdk.HelloSignException;

public class HttpDeleteRequest extends AbstractHttpRequest {

    private static final Logger logger = LoggerFactory.getLogger(HttpDeleteRequest.class);

    public HttpDeleteRequest(String url) throws HelloSignException {
        this(url, null);
    }

    public HttpDeleteRequest(String url, Authentication auth) throws HelloSignException {
        if (url == null || "".equals(url)) {
            throw new HelloSignException("URL cannot be null or empty");
        }
        this.url = url;
        if (auth != null) {
            this.auth = new Authentication(auth);
        }
    }

    public int getHttpResponseCode() throws HelloSignException {
        try {
            logger.debug("DELETE: " + url);
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestProperty("Accept-Charset", DEFAULT_ENCODING);
            connection.setRequestProperty("user-agent", USER_AGENT);
            if (auth != null) {
                logger.debug("Authenticating...");
                auth.authenticate(connection, url);
            }
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestMethod("DELETE");
            connection.connect();
            return connection.getResponseCode();
        } catch (Exception e) {
            throw new HelloSignException(e);
        }
    }
}
