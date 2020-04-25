package com.hellosign.sdk.resource;

import com.hellosign.sdk.HelloSignException;
import com.hellosign.sdk.resource.support.CustomField;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Represents an Embedded signature request (either standard or templated). An embedded request is
 * one that can be signed from either within HelloSign or from within an iFrame on your website.
 *
 * @author "Chris Paul (chris@hellosign.com)"
 */
public class EmbeddedRequest extends AbstractRequest {

    public static final String EMBEDDED_CLIENT_ID = "client_id";

    private String clientId;
    private AbstractRequest request;

    // Hide the default constructor -- we want to force the user to specify a
    // request object that this Embedded request will wrap around.
    @SuppressWarnings("unused")
    private EmbeddedRequest() {
    }

    /**
     * Creates an Embedded signature request using the client ID and the AbstractRequest object
     * provided. First, instantiate the request object (either a SignatureRequest or
     * TemplateSignatureRequest) and then create your EmbeddedRequest using that object.
     *
     * @param clientId String client ID
     * @param request AbstractRequest
     */
    public EmbeddedRequest(String clientId, AbstractRequest request) {
        setClientId(clientId);
        setRequest(request);
    }

    /**
     * Set the client ID of the web app you're using to create this embedded signature request. See:
     * http://app.hellosign.com/api/embedded
     *
     * @return String client ID
     */
    public String getClientId() {
        return clientId;
    }

    /**
     * Set the client ID of the web app you're using to create this embedded signature request. See:
     * http://app.hellosign.com/api/embedded
     *
     * @param clientId String client ID
     */
    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    /**
     * Get the AbstractRequest associated with this Embedded signature request.
     *
     * @return AbstractRequest
     */
    public AbstractRequest getRequest() {
        return request;
    }

    /**
     * Set the AbstractRequest associated with this Embedded signature request.
     *
     * @param request AbstractRequest
     */
    public void setRequest(AbstractRequest request) {
        this.request = request;
    }

    public Map<String, Serializable> getPostFields() throws HelloSignException {
        Map<String, Serializable> map = request.getPostFields();
        map.put(EMBEDDED_CLIENT_ID, getClientId());
        return map;
    }

    // =========================================================================================
    // Overrides to pass through to request object
    // =========================================================================================

    @Override
    public String getTitle() {
        return request.getTitle();
    }

    @Override
    public void setTitle(String title) {
        request.setTitle(title);
    }

    @Override
    public boolean hasTitle() {
        return request.hasTitle();
    }

    @Override
    public String getSubject() {
        return request.getSubject();
    }

    @Override
    public void setSubject(String subject) {
        request.setSubject(subject);
    }

    @Override
    public boolean hasSubject() {
        return request.hasSubject();
    }

    @Override
    public String getMessage() {
        return request.getMessage();
    }

    @Override
    public void setMessage(String message) {
        request.setMessage(message);
    }

    @Override
    public boolean hasMessage() {
        return request.hasMessage();
    }

    @Override
    public boolean isTestMode() {
        return request.isTestMode();
    }

    @Override
    public void setTestMode(boolean testMode) {
        request.setTestMode(testMode);
    }

    @Override
    public String getRedirectUrl() {
        return request.getRedirectUrl();
    }

    @Override
    public void setRedirectUrl(String url) {
        request.setRedirectUrl(url);
    }

    @Override
    public boolean hasRedirectUrl() {
        return request.hasRedirectUrl();
    }

    @Override
    public String getId() {
        return request.getId();
    }

    @Override
    public void addCustomField(CustomField field) {
        this.request.addCustomField(field);
    }

    @Override
    public void setCustomFieldValue(String fieldNameOrApiId, String value) {
        this.request.setCustomFieldValue(fieldNameOrApiId, value);
    }

    @Override
    public List<CustomField> getCustomFields() {
        return request.getCustomFields();
    }

    @Override
    public void setCustomFields(Map<String, String> fields) {
        request.setCustomFields(fields);
    }

    @Override
    public Map<String, String> getCustomFieldsMap() {
        return request.getCustomFieldsMap();
    }

    @Override
    public void clearCustomFields() {
        request.clearCustomFields();
    }
}
