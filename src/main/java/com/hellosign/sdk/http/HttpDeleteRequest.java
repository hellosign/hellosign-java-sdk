package com.hellosign.sdk.http;

import java.net.HttpURLConnection;

import com.hellosign.sdk.HelloSignException;

public class HttpDeleteRequest extends AbstractHttpRequest {

    public HttpDeleteRequest(String url) throws HelloSignException {
        this(url, null);
    }

    public HttpDeleteRequest(String url, Authentication auth) throws HelloSignException {
        if (url == null || url.isEmpty()) {
            throw new HelloSignException("URL cannot be null or empty");
        }
        this.url = url;
        if (auth != null) {
            this.auth = new Authentication(auth);
        }
    }

    @Override
    protected HttpURLConnection getConnection() throws HelloSignException {
        HttpURLConnection connection;
        try {
            connection = getProxiedConnection(url);
            connection.setRequestProperty("Accept-Charset", DEFAULT_ENCODING);
            connection.setRequestProperty("user-agent", USER_AGENT);
            if (auth != null) {
                auth.authenticate(connection, url);
            }
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestMethod("DELETE");
        } catch (Exception ex) {
            throw new HelloSignException(ex);
        }
        return connection;
    }
}
