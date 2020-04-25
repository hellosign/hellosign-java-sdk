package com.hellosign.sdk.http;

import com.hellosign.sdk.HelloSignException;
import java.net.HttpURLConnection;

public class HttpOptionsRequest extends AbstractHttpRequest {

    public HttpOptionsRequest(String url) {
        this.url = url;
    }

    @Override
    protected HttpURLConnection getConnection() throws HelloSignException {
        HttpURLConnection connection;
        try {
            connection = getProxiedConnection(url);
            connection.setRequestMethod("OPTIONS");
        } catch (Exception ex) {
            throw new HelloSignException(ex);
        }
        return connection;
    }
}
