package com.hellosign.sdk.resource.support;

import org.json.JSONObject;

import com.hellosign.sdk.HelloSignException;
import com.hellosign.sdk.resource.AbstractResourceList;
import com.hellosign.sdk.resource.ApiApp;

public class ApiAppList extends AbstractResourceList<ApiApp> {

    public ApiAppList(JSONObject json) throws HelloSignException {
        super(json, "api_apps");
    }

}
