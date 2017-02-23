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
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import com.hellosign.sdk.HelloSignException;
import com.hellosign.sdk.resource.support.CustomField;
import com.hellosign.sdk.resource.support.Document;
import com.hellosign.sdk.resource.support.FormField;
import com.hellosign.sdk.resource.support.ResponseData;
import com.hellosign.sdk.resource.support.Signature;
import com.hellosign.sdk.resource.support.Signer;

/**
 * Represents a HelloSign signature request. This object is used to both submit
 * a request and to represent the request object returned from the server.
 * 
 * @author "Chris Paul (chris@hellosign.com)"
 */
public class SignatureRequest extends AbstractRequest {

    public static final String SIGREQ_KEY = "signature_request";
    public static final String SIGREQ_ID = "signature_request_id";
    public static final String SIGREQ_SIGNERS = "signers";
    public static final String SIGREQ_SIGNER_EMAIL = "email_address";
    public static final String SIGREQ_SIGNER_NAME = "name";
    public static final String SIGREQ_SIGNER_ORDER = "order";
    public static final String SIGREQ_SIGNER_PIN = "pin";
    public static final String SIGREQ_CCS = "cc_email_addresses";
    public static final String SIGREQ_FILES = "file";
    public static final String SIGREQ_FORM_FIELDS = "form_fields_per_document";
    public static final String SIGREQ_IS_COMPLETE = "is_complete";
    public static final String SIGREQ_HAS_ERROR = "has_error";
    public static final String SIGREQ_RESPONSE_DATA = "response_data";
    public static final String SIGREQ_FINAL_COPY_URL = "final_copy_url";
    public static final String SIGREQ_FILES_URL = "files_url";
    public static final String SIGREQ_SIGNING_URL = "signing_url";
    public static final String SIGREQ_DETAILS_URL = "details_url";
    public static final String SIGREQ_IS_DECLINED = "is_declined";
    public static final String SIGREQ_CUSTOM_FIELDS = "custom_fields";

    public static final String SIGREQ_FORMAT_ZIP = "zip";
    public static final String SIGREQ_FORMAT_PDF = "pdf";

    // Fields specific to request
    private List<Signer> signers = new ArrayList<Signer>();

    public SignatureRequest() {
        super();
    }

    public SignatureRequest(JSONObject json) throws HelloSignException {
        super(json, SIGREQ_KEY);
    }

    public SignatureRequest(JSONObject json, String key) throws HelloSignException {
        super(json, key);
    }

    /**
     * Returns the ID for this request.
     */
    public String getId() {
        return getString(SIGREQ_ID);
    }

    /**
     * Returns true if this request has an ID. Useful if checking to see if this
     * request is for submission or is the result of a call to HelloSign.
     * 
     * @return true if the request has an ID, false otherwise
     */
    public boolean hasId() {
        return has(SIGREQ_ID);
    }

    /**
     * Returns the CC email addresses for this request.
     * 
     * @return List
     */
    public List<String> getCCs() {
        return getList(String.class, SIGREQ_CCS);
    }

    /**
     * Adds a CC'd email address to this request.
     * 
     * @param email String email address
     */
    public void addCC(String email) {
        add(SIGREQ_CCS, email);
    }

    /**
     * Returns a list of signatures for this request.
     * 
     * @return List
     */
    public List<Signature> getSignatures() {
        return getList(Signature.class, "signatures");
    }

    /**
     * Returns the signature for the given email/name combination, or null if
     * not found on this request.
     * 
     * @param email String email address
     * @param name String name
     * @return Signature or null if not found
     * @throws HelloSignException if the email or name are empty
     */
    public Signature getSignature(String email, String name) throws HelloSignException {
        if (email == null || "".equals(email)) {
            throw new HelloSignException("Email address cannot be empty");
        }
        if (name == null || "".equals(name)) {
            throw new HelloSignException("Name cannot be empty");
        }
        for (Signature s : getSignatures()) {
            if (email.equalsIgnoreCase(s.getEmail()) && name.equalsIgnoreCase(s.getName())) {
                return s;
            }
        }
        return null;
    }

    /**
     * Adds the signer to the list of signers for this request.
     * 
     * @param signer Signer
     * @throws HelloSignException thrown if there is a problem adding the
     *         signer.
     */
    public void addSigner(Signer signer) throws HelloSignException {
        signers.add(signer);
    }

    /**
     * Adds the signer to the list of signers for this request.
     * 
     * @param email String
     * @param name String
     * @throws HelloSignException thrown if there is a problem adding the
     *         signer.
     */
    public void addSigner(String email, String name) throws HelloSignException {
        signers.add(new Signer(email, name));
    }

    /**
     * Adds the signer with the given order to the list of signers for this
     * request. NOTE: The order refers to the 1-base index, not 0-base. This is
     * to reflect the indexing used by the HelloSign API. This means that adding
     * an item at order 1 will place it in the 0th index of the list (it will be
     * the first item).
     * 
     * @param email String
     * @param name String
     * @param order int
     * @throws HelloSignException thrown if there is a problem adding the
     *         signer.
     */
    public void addSigner(String email, String name, int order) throws HelloSignException {
        try {
            signers.add((order - 1), new Signer(email, name));
        } catch (Exception ex) {
            throw new HelloSignException(ex);
        }
    }

    /**
     * Returns a reference to the signers list. This can be modified and
     * re-added to the request using setSigners(). Useful for more explicit
     * modification.
     * 
     * @return List
     */
    public List<Signer> getSigners() {
        return signers;
    }

    /**
     * Overwrites the current list of signers for this request with the given
     * list.
     * 
     * @param signers List
     */
    public void setSigners(List<Signer> signers) {
        this.signers = signers;
    }

    /**
     * Removes the signer from the list. If that user does not exist, this will
     * throw a HelloSignException.
     * 
     * @param email String
     * @throws HelloSignException thrown if there is a problem removing the
     *         signer.
     */
    public void removeSigner(String email) throws HelloSignException {
        if (email == null) {
            throw new HelloSignException("Cannot remove null signer");
        }
        for (int i = 0; i < signers.size(); i++) {
            if (email.equalsIgnoreCase(signers.get(i).getEmail())) {
                signers.remove(i);
            }
        }
    }

    /**
     * Utility method that allows you to search for a Signature object on this
     * request by email and name. It requires both because neither alone is
     * enough to guarantee uniqueness (some requests can have multiple signers
     * using the same email address or name).
     * 
     * @param email String
     * @param name String
     * @return Signature, if found on this request, or null
     * @deprecated Use getSignature(email, name)
     */
    public Signature getSignatureBySigner(String email, String name) {
        if (email == null || name == null) {
            return null;
        }
        for (Signature s : getSignatures()) {
            if (name.equalsIgnoreCase(s.getName()) && email.equalsIgnoreCase(s.getEmail())) {
                return s;
            }
        }
        return null;
    }

    /**
     * Internal method used to retrieve the necessary POST fields to submit the
     * signature request.
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
            List<Signer> signerz = getSigners();
            for (int i = 0; i < signerz.size(); i++) {
                Signer s = signerz.get(i);
                fields.put(SIGREQ_SIGNERS + "[" + i + "][" + SIGREQ_SIGNER_EMAIL + "]", s.getEmail());
                fields.put(SIGREQ_SIGNERS + "[" + i + "][" + SIGREQ_SIGNER_NAME + "]", s.getNameOrRole());
                if (getOrderMatters()) {
                    fields.put(SIGREQ_SIGNERS + "[" + i + "][" + SIGREQ_SIGNER_ORDER + "]", i);
                }
                if (s.getAccessCode() != null) {
                    fields.put(SIGREQ_SIGNERS + "[" + i + "][" + SIGREQ_SIGNER_PIN + "]", s.getAccessCode());
                }
            }
            List<String> ccz = getCCs();
            for (int i = 0; i < ccz.size(); i++) {
                String cc = ccz.get(i);
                fields.put(SIGREQ_CCS + "[" + i + "]", cc);
            }
            JSONArray reqFormFields = new JSONArray(); // Main array for the
                                                       // request
            boolean hasFormFields = false;
            List<Document> docs = getDocuments();
            for (int i = 0; i < docs.size(); i++) {
                Document d = docs.get(i);
                fields.put(SIGREQ_FILES + "[" + i + "]", d.getFile());
                JSONArray docFormFields = new JSONArray();
                for (FormField ff : d.getFormFields()) {
                    hasFormFields = true;
                    docFormFields.put(ff.getJSONObject());
                }
                reqFormFields.put(docFormFields);
            }
            List<String> fileUrls = getFileUrls();
            for (int i = 0; i < fileUrls.size(); i++) {
                fields.put("file_url[" + i + "]", fileUrls.get(i));
            }
            if (hasFormFields) {
                fields.put(SIGREQ_FORM_FIELDS, reqFormFields.toString());
            }
            if (isTestMode()) {
                fields.put(REQUEST_TEST_MODE, true);
            }
            if (hasRedirectUrl()) {
                fields.put(REQUEST_REDIRECT_URL, getRedirectUrl());
            }
            if (hasUseTextTags()) {
                fields.put(REQUEST_USE_TEXT_TAGS, isUsingTextTags());
            }
            if (hasHideTextTags()) {
                fields.put(REQUEST_HIDE_TEXT_TAGS, isHidingTextTags());
            }
        } catch (Exception ex) {
            throw new HelloSignException("Could not extract form fields from SignatureRequest.", ex);
        }
        return fields;
    }

    /**
     * Returns the HelloSign-designated signature status, indicating whether all
     * signers have signed the document.
     * 
     * @return true, if all signers have signed the document, false otherwise.
     */
    public boolean isComplete() {
        return getBoolean(SIGREQ_IS_COMPLETE);
    }

    public boolean hasError() {
        return getBoolean(SIGREQ_HAS_ERROR);
    }

    public List<ResponseData> getResponseData() {
        return getList(ResponseData.class, SIGREQ_RESPONSE_DATA);
    }

    /**
     * @deprecated use getFilesUrl()
     * @return String URL
     */
    public String getFinalCopyUrl() {
        return getString(SIGREQ_FINAL_COPY_URL);
    }

    /**
     * Returns the API URL to retrieve the PDF copy of this signature request.
     * 
     * @return String URL
     */
    public String getFilesUrl() {
        return getString(SIGREQ_FILES_URL);
    }

    public String getSigningUrl() {
        return getString(SIGREQ_SIGNING_URL);
    }

    public String getDetailsUrl() {
        return getString(SIGREQ_DETAILS_URL);
    }

    public boolean isDeclined() {
        if (has(SIGREQ_IS_DECLINED)) {
            return getBoolean(SIGREQ_IS_DECLINED);
        }
        return false;
    }

    /**
     * Gets the custom fields associated with this request, set when sending the
     * request from a template.
     * 
     * @return List CustomFields
     */
    public List<CustomField> getCustomFields() {
        return getList(CustomField.class, SIGREQ_CUSTOM_FIELDS);
    }
}