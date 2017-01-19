package com.hellosign.sdk.http;

import java.net.HttpURLConnection;

import com.hellosign.sdk.HelloSignException;

public class HttpOptionsRequest extends AbstractHttpRequest {

    public HttpOptionsRequest(String url) {
    	this.url = url;
    }

	@Override
	protected HttpURLConnection getConnection() throws HelloSignException {
		HttpURLConnection connection = null;
        try {
            connection = getProxiedConnection(url);
            connection.setRequestMethod("OPTIONS");
        } catch (Exception ex) {
            throw new HelloSignException(ex);
        }
        return connection;
	}
}
