package com.hellosign.sdk.resource.support.types;

import com.hellosign.sdk.HelloSignException;
import com.hellosign.sdk.resource.AbstractResourceList;
import com.hellosign.sdk.resource.BulkSendJobs;
import org.json.JSONObject;

public class BulkSendJobsList extends AbstractResourceList<BulkSendJobs> {

    public static final String BULK_SEND_JOBS = "bulk_send_jobs";

    public BulkSendJobsList(JSONObject json) throws HelloSignException {
        super(json, BULK_SEND_JOBS);
    }
}
