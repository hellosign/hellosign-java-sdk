package com.hellosign.sdk.resource.support;

import com.hellosign.sdk.HelloSignException;
import com.hellosign.sdk.resource.AbstractResource;
import org.json.JSONObject;

public class Metadata extends AbstractResource {

    public static final String METADATA_KEY = "metadata";

    public Metadata() {
        super();
    }

    public Metadata(JSONObject json) throws HelloSignException {
        super(json, METADATA_KEY);
    }

    public String get(String key) {
        return getString(key);
    }

    public void set(String key, String value) {
        super.set(key, value);
    }
}
