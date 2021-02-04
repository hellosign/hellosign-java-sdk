package com.hellosign.sdk.resource;

import com.hellosign.sdk.HelloSignException;
import org.json.JSONObject;

import java.util.Date;

public class BulkSendJobs extends AbstractResource {

    public static final String BULKSENDJOB_ID = "bulk_send_job_id";
    public static final String BULKSENDJOB_TOTAL = "total";
    public static final String BULKSENDJOB_ISCREATOR = "is_creator";
    public static final String BULKSENDJOB_CREATEDAT = "created_at";
    public static final String BULKSENDJOB = "bulk_send_job";

    public BulkSendJobs(){
        super();
    }

    public BulkSendJobs(JSONObject jsonObject)throws HelloSignException{
        super(jsonObject, BULKSENDJOB);
    }

    /**
     * Get Bulk send job id.
     */
    public String getBulkSendJobId() {
        return getString(BULKSENDJOB_ID);
    }

    /**
     * Get Bulk send job total.
     */
    public String getBulkSendJobTotal() {
        return getString(BULKSENDJOB_TOTAL);
    }

    /**
     * Get Bulk send job is Creator.
     */
    public Boolean getBulkSendJobIsCreator() {
        return getBoolean(BULKSENDJOB_ISCREATOR);
    }

    /**
     * Get Bulk send job created at.
     */
    public Date getBulkSendJobCreatedAt() {
        return getDate(BULKSENDJOB_CREATEDAT);
    }
}
