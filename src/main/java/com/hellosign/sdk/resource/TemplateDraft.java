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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.hellosign.sdk.HelloSignException;
import com.hellosign.sdk.resource.support.Document;
import com.hellosign.sdk.resource.support.types.FieldType;

/**
 * Represents a HelloSign template draft.
 * 
 * @author "Chris Paul (chris@hellosign.com)"
 */
public class TemplateDraft extends AbstractRequest {

    public static final String TEMPLATE_DRAFT_KEY = "template";
    public static final String TEMPLATE_DRAFT_ID = "template_id";
    public static final String TEMPLATE_EDIT_URL = "edit_url";
    public static final String TEMPLATE_EXPIRES_AT = "expires_at";

    private List<String> ccRoles = new ArrayList<String>();
    private List<String> signerRoles = new ArrayList<String>();
    private Map<String, FieldType> mergeFields = new HashMap<String, FieldType>();

    public TemplateDraft() {
        super();
    }

    public TemplateDraft(JSONObject json) throws HelloSignException {
        super(json, TEMPLATE_DRAFT_KEY);
    }

    /**
     * Returns the ID for this request.
     */
    public String getId() {
        return getString(TEMPLATE_DRAFT_ID);
    }

    /**
     * Returns true if this request has an ID. Useful if checking to see if this
     * request is for submission or is the result of a call to HelloSign.
     * 
     * @return true if the request has an ID, false otherwise
     */
    public boolean hasId() {
        return has(TEMPLATE_DRAFT_ID);
    }

    /**
     * Returns the CC roles for this request.
     * 
     * @return List
     */
    public List<String> getCCRoles() {
        return ccRoles;
    }

    /**
     * Adds a named role for a CC recipient that must be specified when the
     * template is used.
     * 
     * @param ccRole String
     */
    public void addCCRole(String ccRole) {
        ccRoles.add(ccRole);
    }

    /**
     * Adds the signer role to the template draft.
     * 
     * @param signerRole String
     */
    public void addSignerRole(String signerRole) {
        signerRoles.add(signerRole);
    }

    /**
     * Returns the signer roles specified for this template draft.
     * 
     * @return List
     */
    public List<String> getSignerRoles() {
        return signerRoles;
    }

    /**
     * Adds the signer role with the given order to the list of signers for this
     * request. NOTE: The order refers to the 1-base index, not 0-base. This is
     * to reflect the indexing used by the HelloSign API. This means that adding
     * an item at order 1 will place it in the 0th index of the list (it will be
     * the first item).
     * 
     * @param role String
     * @param order int
     * @throws HelloSignException thrown if there is a problem adding the signer
     *         role.
     */
    public void addSignerRole(String role, int order) throws HelloSignException {
        try {
            signerRoles.add((order - 1), role);
        } catch (Exception ex) {
            throw new HelloSignException(ex);
        }
    }

    /**
     * Overwrites the current list of signer roles.
     * 
     * @param signerRoles List
     */
    public void setSignerRoles(List<String> signerRoles) {
        this.signerRoles = signerRoles;
    }

    /**
     * Removes the signer role.
     * 
     * @param signerRole String
     * @return boolean
     * @throws HelloSignException thrown if there is a problem removing the
     *         signer role.
     */
    public boolean removeSignerRole(String signerRole) throws HelloSignException {
        return signerRoles.remove(signerRole);
    }

    /**
     * Add merge fields to the template draft. These are fields that your app
     * can pre-populate whenever the *finished* template is used to send a
     * signature request.
     * 
     * @param name String name of the merge field that will be displayed to the
     *        user and used to key off the custom field when populating the
     *        value.
     * @param type FieldType (currently only "text" and "checkbox" are allowed)
     * @throws HelloSignException thrown if there is a problem adding the merge
     *         field.
     */
    public void addMergeField(String name, FieldType type) throws HelloSignException {
        if (!FieldType.checkbox.equals(type) && !FieldType.text.equals(type)) {
            throw new HelloSignException("Only 'text' or 'checkbox' types allowed for merge fields.");
        }
        mergeFields.put(name, type);
    }

    /**
     * Returns the current map of merge field names to types.
     * 
     * @return Map
     */
    public Map<String, FieldType> getMergeFields() {
        return mergeFields;
    }

    /**
     * Clears the current merge field map.
     */
    public void clearMergeFields() {
        mergeFields = new HashMap<String, FieldType>();
    }

    /**
     * Utility method to detect whether the "edit_url" parameter is set on this
     * template object.
     * 
     * @return boolean
     */
    public boolean hasEditUrl() {
        return has(TEMPLATE_EDIT_URL);
    }

    /**
     * Returns the edit URL for creating an embedded template draft.
     * 
     * @return String edit URL
     */
    public String getEditUrl() {
        return getString(TEMPLATE_EDIT_URL);
    }

    /**
     * Utility method to detect whether the "expires_at" parameter is set on
     * this template object.
     * 
     * @return boolean
     */
    public boolean hasExpiresAt() {
        return has(TEMPLATE_EXPIRES_AT);
    }

    /**
     * Returns the expiration time for the edit URL of this template.
     * 
     * @return String expiration timestamp
     */
    public String getExpiresAt() {
        return getString(TEMPLATE_EXPIRES_AT);
    }

    /**
     * Helper method to convert a Java Map into the JSON string required
     * by the HelloSign API.
     * @param mergeFields Map
     * @return String
     * @throws HelloSignException Thrown if there's a problem parsing JSONObjects
     */
    public static String serializeMergeFields(Map<String, FieldType> mergeFields) throws HelloSignException {
        if (mergeFields != null) {
            JSONArray mergeFieldArray = new JSONArray();
            for (String key : mergeFields.keySet()) {
                FieldType type = mergeFields.get(key);
                JSONObject mergeFieldObj = new JSONObject();
                try {
                    mergeFieldObj.put("name", key);
                    mergeFieldObj.put("type", type.toString());
                } catch (JSONException e) {
                    throw new HelloSignException(e);
                }
                mergeFieldArray.put(mergeFieldObj);
            }
            return mergeFieldArray.toString();
        }
        return null;
    }

    /**
     * Internal method used to retrieve the necessary POST fields.
     * 
     * @return Map
     * @throws HelloSignException thrown if there is a problem serializing the
     *         POST fields.
     */
    public Map<String, Serializable> getPostFields() throws HelloSignException {
        Map<String, Serializable> fields = super.getPostFields();
        try {
            if (hasTitle()) {
                fields.put(REQUEST_TITLE, getTitle());
            }
            if (hasSubject()) {
                fields.put(REQUEST_SUBJECT, getSubject());
            }
            if (hasMessage()) {
                fields.put(REQUEST_MESSAGE, getMessage());
            }
            List<String> signerRoles = getSignerRoles();
            for (int i = 0; i < signerRoles.size(); i++) {
                String s = signerRoles.get(i);
                fields.put("signer_roles[" + i + "][name]", s);
                if (getOrderMatters()) {
                    fields.put("signer_roles[" + i + "][order]", i);
                }
            }

            List<String> ccRoles = getCCRoles();
            for (int i = 0; i < ccRoles.size(); i++) {
                String cc = ccRoles.get(i);
                fields.put("cc_roles[" + i + "]", cc);
            }

            List<Document> docs = getDocuments();
            for (int i = 0; i < docs.size(); i++) {
                Document d = docs.get(i);
                fields.put("file[" + i + "]", d.getFile());
            }

            List<String> fileUrls = getFileUrls();
            for (int i = 0; i < fileUrls.size(); i++) {
                fields.put("file_url[" + i + "]", fileUrls.get(i));
            }

            String mergeFieldStr = TemplateDraft.serializeMergeFields(getMergeFields());
            if (mergeFieldStr != null) {
                fields.put("merge_fields", mergeFieldStr);
            }

            if (hasUsePreexistingFields()) {
                fields.put(REQUEST_USE_PREEXISTING_FIELDS, true);
            }

            if (isTestMode()) {
                fields.put(REQUEST_TEST_MODE, true);
            }
        } catch (Exception ex) {
            throw new HelloSignException("Could not extract form fields from TemplateDraft.", ex);
        }
        return fields;
    }
}