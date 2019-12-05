package com.hellosign.sdk.resource.support;

import com.hellosign.sdk.HelloSignException;
import com.hellosign.sdk.resource.AbstractResource;
import org.json.JSONObject;

/**
 * Resource class that represents a signer or CC role for a Template. ("order"
 * won't matter for the CC role, but we'll use this to represent it for now
 * anyway).
 *
 * @author "Chris Paul (chris@hellosign.com)"
 */
public class TemplateRole extends AbstractResource {

    public static final String SIGNER_ROLE_NAME = "name";
    public static final String SIGNER_ROLE_ORDER = "order";

    public TemplateRole(JSONObject json) throws HelloSignException {
        super(json, null);
    }

    public String getRole() {
        return getString(SIGNER_ROLE_NAME);
    }

    public Integer getOrder() {
        return getInteger(SIGNER_ROLE_ORDER);
    }
}
