package com.hellosign.sdk.resource.support;

import com.hellosign.sdk.HelloSignException;
import com.hellosign.sdk.resource.AbstractResourceList;
import com.hellosign.sdk.resource.SignatureRequest;
import org.json.JSONObject;

/**
 * Represents a paged list of SignatureRequests returned from HelloSign.
 */
public class SignatureRequestList extends AbstractResourceList<SignatureRequest> {

    public SignatureRequestList(JSONObject json) throws HelloSignException {
        super(json, "signature_requests");
    }
}
