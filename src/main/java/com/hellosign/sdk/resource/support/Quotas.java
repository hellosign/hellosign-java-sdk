package com.hellosign.sdk.resource.support;

import com.hellosign.sdk.HelloSignException;
import com.hellosign.sdk.resource.AbstractResource;
import org.json.JSONObject;

public class Quotas extends AbstractResource {

    public static final String QUOTAS_KEY = "quotas";
    public static final String QUOTA_TEMPLATES_LEFT = "templates_left";
    public static final String QUOTA_DOCUMENTS_LEFT = "documents_left";
    public static final String QUOTA_API_SIG_REQUESTS_LEFT = "api_signature_requests_left";

    public Quotas(JSONObject json) throws HelloSignException {
        super(json, QUOTAS_KEY);
    }

    public Integer getTemplatesLeft() {
        return getInteger(QUOTA_TEMPLATES_LEFT);
    }

    public Integer getApiSignatureRequestsLeft() {
        return getInteger(QUOTA_API_SIG_REQUESTS_LEFT);
    }

    public Integer getDocumentsLeft() {
        return getInteger(QUOTA_DOCUMENTS_LEFT);
    }
}
