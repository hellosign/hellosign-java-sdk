package com.hellosign.sdk.resource;

import com.hellosign.sdk.HelloSignException;
import org.json.JSONObject;

import java.util.List;

/**
 * Returns the status of the BulkSendJob and its SignatureRequests specified by the bulk_send_job_id parameter.
 * Response for GET /bulk_send_job/[:bulk_send_job_id] gives bulk_send_job, list_info and signature_requests.
 */
public class BulkSendJobById extends AbstractResource {

    public static final String BULKSENDJOB = "bulk_send_job";
    public static final String BULKSENDBYJOBID = "bulk_send_job_id";
    public static final String LIST_INFO = "list_info";
    public static final String SIGNATURE_REQUESTS = "signature_requests";

    public BulkSendJobById(){
        super();
    }

    public BulkSendJobById(JSONObject json) throws HelloSignException{
        super(json,BULKSENDBYJOBID);
    }

    /**
     * Get bulk_send_job Object.
     */
    public  BulkSendJobs getBulkSendJob() throws Exception {
        return (BulkSendJobs) getObject(BulkSendJobs.class, dataObj.get(BULKSENDJOB), BULKSENDJOB);
    }

    /**
     * Get list_info Object.
     */
    public Object getListInfo() {
        return getJSONObject().get(LIST_INFO);
    }

    /**
     * Get List of signature_requests.
     */
    public List<SignatureRequest> getSignatureRequests(){
        return getList(SignatureRequest.class, SIGNATURE_REQUESTS);
    }
}
