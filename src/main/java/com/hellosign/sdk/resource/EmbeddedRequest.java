package com.hellosign.sdk.resource;

/**
 * The MIT License (MIT)
 * 
 * Copyright (C) 2015 hellosign.com
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hellosign.sdk.HelloSignException;
import com.hellosign.sdk.resource.support.CustomField;
import org.json.JSONArray;

/**
 * Represents an Embedded signature request (either standard or templated). An
 * embedded request is one that can be signed from either within HelloSign or
 * from within an iFrame on your website.
 * 
 * @author "Chris Paul (chris@hellosign.com)"
 */
public class EmbeddedRequest extends AbstractRequest {

    public static final String EMBEDDED_CLIENT_ID = "client_id";

    private String clientId;
    private AbstractRequest request;
    private List<CustomField> customFields = new ArrayList<CustomField>();

    // Hide the default constructor -- we want to force the user to specify a
    // request object that this Embedded request will wrap around.
    @SuppressWarnings("unused")
    private EmbeddedRequest() {
    }

    /**
     * Creates an Embedded signature request using the client ID and the
     * AbstractRequest object provided. First, instantiate the request object
     * (either a SignatureRequest or TemplateSignatureRequest) and then create
     * your EmbeddedRequest using that object.
     * 
     * @param clientId String client ID
     * @param request AbstractRequest
     */
    public EmbeddedRequest(String clientId, AbstractRequest request) {
        setClientId(clientId);
        setRequest(request);
    }

    /**
     * Set the client ID of the web app you're using to create this embedded
     * signature request. See: http://app.hellosign.com/api/embedded
     * 
     * @return String client ID
     */
    public String getClientId() {
        return clientId;
    }

    /**
     * Set the client ID of the web app you're using to create this embedded
     * signature request. See: http://app.hellosign.com/api/embedded
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

    /**
     * Add the custom field to this request. This is useful for specifying a
     * pre-filled value and/or a field editor.
     *
     * @param field CustomField
     */
    public void addCustomField(CustomField field) {
        customFields.add(field);
    }

    /**
     * Add the custom fields to this request. This is useful for specifying a
     * pre-filled value and/or a field editor.
     *
     * @param fields CustomField
     */
    public void addCustomFields(Collection<CustomField> fields) {
        customFields.addAll(fields);
    }

    /**
     * Adds the value to fill in for a custom field with the given field name.
     *
     * @param fieldNameOrApiId String name (or "Field Label") of the custom field
     *        to be filled in. The "api_id" can also be used instead of the name.
     * @param value String value
     */
    public void setCustomFieldValue(String fieldNameOrApiId, String value) {
        CustomField f = new CustomField();
        f.setName(fieldNameOrApiId);
        f.setValue(value);
        customFields.add(f);
    }

    /**
     * Returns the map of custom fields for the template. This is a map of
     * String field names to String field values.
     *
     * @return Map
     */
    public Map<String, String> getCustomFields() {
        Map<String, String> fields = new HashMap<String, String>();
        for (CustomField f : customFields) {
            fields.put(f.getName(), f.getValue());
        }
        return fields;
    }

    /**
     * Returns a list of CustomField objects for this template.
     *
     * @return List of CustomFields
     */
    public List<CustomField> getCustomFieldsList() {
        return customFields;
    }

    /**
     * Clears the current custom fields for this request.
     */
    public void clearCustomFields() {
        customFields = new ArrayList<CustomField>();
    }

    public Map<String, Serializable> getPostFields() throws HelloSignException {
        Map<String, Serializable> fields = request.getPostFields();
        fields.put(EMBEDDED_CLIENT_ID, getClientId());

        if (customFields.size() > 0) {
            JSONArray array = new JSONArray();
            for (CustomField f : customFields) {
                array.put(f.getJSONObject());
            }
            fields.put(REQUEST_CUSTOM_FIELDS, array.toString());
        }

        return fields;
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
    public void setTestMode(boolean testMode) {
        request.setTestMode(testMode);
    }

    @Override
    public boolean isTestMode() {
        return request.isTestMode();
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
}
