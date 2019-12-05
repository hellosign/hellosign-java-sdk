package com.hellosign.sdk.http;

import com.hellosign.sdk.HelloSignException;
import java.io.Serializable;
import java.util.Map;

public class HttpPutRequest extends HttpPostRequest {

    protected String method = "PUT";

    public HttpPutRequest(String url) throws HelloSignException {
        super(url);
    }

    public HttpPutRequest(String url, Authentication auth) throws HelloSignException {
        super(url, auth);
    }

    public HttpPutRequest(String url, Map<String, Serializable> fields, Authentication auth)
        throws HelloSignException {
        super(url, fields, auth);
    }

    public HttpPutRequest(String url, Map<String, Serializable> fields) throws HelloSignException {
        super(url, fields);
    }
}
