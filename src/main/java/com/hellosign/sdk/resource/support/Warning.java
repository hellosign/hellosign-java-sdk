package com.hellosign.sdk.resource.support;

import org.json.JSONObject;

import com.hellosign.sdk.HelloSignException;
import com.hellosign.sdk.resource.AbstractResource;

public class Warning extends AbstractResource {

    public static final String WARNING_MESSAGE = "warning_msg";
    public static final String WARNING_NAME = "warning_name";

    public Warning(JSONObject json) throws HelloSignException {
        super(json, null);
    }

    public String getMessage() {
        return getString(WARNING_MESSAGE);
    }

    public String getName() {
        return getString(WARNING_NAME);
    }

    public String toString() {
        return "WARNING: " + getName() + " - " + getMessage();
    }
}
