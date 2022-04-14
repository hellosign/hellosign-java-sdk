package com.hellosign.sdk;

import com.hellosign.sdk.resource.AbstractResource;
import com.hellosign.sdk.resource.SignatureRequest;
import java.util.Date;
import org.json.JSONObject;

public class TestResource extends AbstractResource {

    public TestResource() {
        super();
    }

    public TestResource(JSONObject obj) throws HelloSignException {
        super(obj, SignatureRequest.SIGREQ_KEY);
    }

    @Override
    public void set(String key, Object value) {
        super.set(key, value);
    }

    public Date getCreatedAt() {
        return getDate(SignatureRequest.SIGREQ_CREATED_AT);
    }
}
