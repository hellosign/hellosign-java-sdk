package com.hellosign.sdk.resource;

import com.hellosign.sdk.HelloSignException;
import java.util.Date;
import org.json.JSONObject;

/**
 * POJO that represents a HelloSign File URL resource.
 */
public class FileUrlResponse extends AbstractResource {

    public static final String FILE_KEY = "file";
    public static final String FILE_URL_KEY = "file_url";
    public static final String EXPIRES_AT_KEY = "expires_at";

    public FileUrlResponse() {
        super();
    }

    public FileUrlResponse(JSONObject jsonObject) throws HelloSignException {
        super(jsonObject, FILE_KEY);
    }

    public String getFileUrl() {
        return getString(FILE_URL_KEY);
    }

    public Date getExpiresAt() {
        return getDate(EXPIRES_AT_KEY);
    }
}
