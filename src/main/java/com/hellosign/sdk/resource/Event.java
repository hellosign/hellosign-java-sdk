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

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hellosign.sdk.HelloSignException;
import com.hellosign.sdk.resource.support.Signature;
import com.hellosign.sdk.resource.support.types.EventType;

/**
 * This is a utility class for assisting with the development of callback
 * services to respond to HelloSign events.
 * 
 * @author "Chris Paul (chris@hellosign.com)"
 */
public class Event extends AbstractResource {

    private static final Logger logger = LoggerFactory.getLogger(Event.class);

    public static final String EVENT_KEY = "event";
    public static final String EVENT_METADATA = "event_metadata";
    public static final String REPORTED_FOR_ACCOUNT_ID = "reported_for_account_id";
    public static final String REPORTED_FOR_APP_ID = "reported_for_app_id";
    public static final String RELATED_SIGNATURE_ID = "related_signature_id";
    public static final String EVENT_TYPE = "event_type";
    public static final String EVENT_TIME = "event_time";
    public static final String EVENT_HASH = "event_hash";
    public static final String EVENT_MESSAGE = "event_message";

    public static final String HASH_ALGORITHM = "HmacSHA256";

    private AbstractResource resource;

    /**
     * Default constructor. Provide this constructor with the JSONObject created
     * from the API response.
     * 
     * @param json JSONObject
     * @throws HelloSignException thrown if there is a problem parsing the
     *         JSONObject.
     */
    public Event(JSONObject json) throws HelloSignException {
        super(json, EVENT_KEY);
        try {
            if (EventType.test.equals(getType())) {
                return;
            }
            if (json.has(SignatureRequest.SIGREQ_KEY)) {
                resource = new SignatureRequest(json.getJSONObject(SignatureRequest.SIGREQ_KEY));
            } else if (json.has(Template.TEMPLATE_KEY)) {
                resource = new Template(json.getJSONObject(Template.TEMPLATE_KEY));
            }
        } catch (JSONException ex) {
            throw new HelloSignException(ex);
        }
    }

    /**
     * Returns the account ID that this event is reporting for.
     * 
     * @return String
     * @throws HelloSignException thrown if there is a problem parsing the
     *         backing JSON object.
     */
    public String getAccountId() throws HelloSignException {
        JSONObject metadata = (JSONObject) get(EVENT_METADATA);
        String id = null;
        try {
            id = metadata.getString(REPORTED_FOR_ACCOUNT_ID);
        } catch (JSONException ex) {
            throw new HelloSignException(ex);
        }
        return id;
    }

    /**
     * Returns true if this event has a "reported_for_account_id" field.
     * 
     * @return true or false if it does not have an account ID
     * @throws HelloSignException thrown if there is a problem parsing the
     *         backing JSONObject.
     */
    public boolean hasAccountId() throws HelloSignException {
        return has(REPORTED_FOR_ACCOUNT_ID);
    }

    /**
     * Returns the API app ID for which this event is reported.
     * 
     * @return String
     * @throws HelloSignException thrown if there is a problem parsing the
     *         backing JSONObject.
     */
    public String getAppId() throws HelloSignException {
        JSONObject metadata = (JSONObject) get(EVENT_METADATA);
        String id = null;
        try {
            id = metadata.getString(REPORTED_FOR_APP_ID);
        } catch (JSONException ex) {
            throw new HelloSignException(ex);
        }
        return id;
    }

    /**
     * Returns true if this event has a "reported_for_app_id" field.
     * 
     * @return boolean
     * @throws HelloSignException thrown if there is a problem parsing the
     *         backing JSONObject.
     */
    public boolean hasAppId() throws HelloSignException {
        return has(REPORTED_FOR_APP_ID);
    }

    /**
     * Returns the event time as a Java Date object.
     * 
     * @return Date
     */
    public Date getEventDate() {
        return getDate(EVENT_TIME);
    }

    /**
     * Returns the message if any from the event
     * 
     * @return String
     * @throws HelloSignException thrown if there is a problem parsing the
     *         backing JSONObject.
     */
    public String getEventMessage() throws HelloSignException {
        JSONObject metadata = (JSONObject) get(EVENT_METADATA);
        String eventMessage = null;
        try {
            eventMessage = metadata.getString(EVENT_MESSAGE);
        } catch (JSONException ex) {
            throw new HelloSignException(ex);
        }
        return eventMessage;
    }

    /**
     * Returns the signature ID to which this event is associated.
     * 
     * @return String
     * @throws HelloSignException thrown if there is a problem parsing the
     *         backing JSONObject.
     */
    public String getRelatedSignatureId() throws HelloSignException {
        JSONObject metadata = (JSONObject) get(EVENT_METADATA);
        if (metadata == null) {
            return null;
        }
        String id = null;
        try {
            id = metadata.getString(RELATED_SIGNATURE_ID);
        } catch (JSONException ex) {
            // No related signature request with this event
        }
        return id;
    }

    /**
     * Utility method to determine if this event has a related signature ID.
     * 
     * @return true if this field exists and is set, false otherwise
     */
    public boolean hasRelatedSignatureId() {
        JSONObject metadata = (JSONObject) get(EVENT_METADATA);
        if (metadata == null) {
            return false;
        }
        return metadata.has(RELATED_SIGNATURE_ID) && !metadata.isNull(RELATED_SIGNATURE_ID);
    }

    /**
     * Returns the associated Signature object with this event, if the event is
     * associated with a Signature Request.
     * 
     * @return Signature
     * @throws HelloSignException thrown if there is a problem parsing the
     *         backing JSONObject.
     */
    public Signature getRelatedSignature() throws HelloSignException {
        String id = getRelatedSignatureId();
        if (id == null) {
            return null;
        }
        for (Signature sig : ((SignatureRequest) resource).getSignatures()) {
            if (id.equals(sig.getId())) {
                return sig;
            }
        }
        return null;
    }

    /**
     * Returns a reference to the SignatureRequest that is attached to the
     * Event.
     * 
     * @return SignatureRequest
     */
    public SignatureRequest getSignatureRequest() {
        if (hasSignatureRequest()) {
            return (SignatureRequest) resource;
        }
        return null;
    }

    /**
     * Returns true if this Event is associated with a Signature Request.
     * 
     * @return boolean
     */
    public boolean hasSignatureRequest() {
        return resource != null && resource instanceof SignatureRequest;
    }

    /**
     * Returns a reference to the Template that is attached to the Event.
     * 
     * @return Template
     */
    public Template getTemplate() {
        if (hasTemplate()) {
            return (Template) resource;
        }
        return null;
    }

    /**
     * Returns true if this event is associated with a Template.
     * 
     * @return boolean
     */
    public boolean hasTemplate() {
        return resource != null && resource instanceof Template;
    }

    /**
     * Returns the EventType for this event.
     * 
     * @return EventType enum
     */
    public EventType getType() {
        String typeStr = getTypeString();
        EventType type = null;
        try {
            type = EventType.valueOf(typeStr);
        } catch (Exception ex) {
            logger.warn("Unexpected type: " + typeStr);
        }
        return type;
    }

    /**
     * Returns the event type String.
     * 
     * @return String event type
     */
    public String getTypeString() {
        return getString(EVENT_TYPE);
    }

    /**
     * Returns the unique hash string identifying this event.
     * 
     * @return String hash
     */
    public String getHash() {
        return getString(EVENT_HASH);
    }

    /**
     * Returns true if the event hash matches the computed hash using the
     * provided API key.
     * 
     * @param apiKey String api key.
     * @return true if the hashes match, false otherwise
     * @throws HelloSignException thrown if there is a problem parsing the API
     *         key.
     */
    public boolean isValid(String apiKey) throws HelloSignException {
        if (apiKey == null || apiKey == "") {
            return false;
        }
        try {
            Mac sha256HMAC = Mac.getInstance(HASH_ALGORITHM);
            SecretKeySpec secretKey = new SecretKeySpec(apiKey.getBytes(), HASH_ALGORITHM);
            sha256HMAC.init(secretKey);
            String data = String.valueOf(getLong(EVENT_TIME)) + getType();
            String computedHash = bytesToHex(sha256HMAC.doFinal(data.getBytes()));
            String providedHash = getString(EVENT_HASH);
            return computedHash.equalsIgnoreCase(providedHash);
        } catch (InvalidKeyException e) {
            throw new HelloSignException("Invalid API Key (" + e.getMessage() + "): " + apiKey);
        } catch (IllegalArgumentException e) {
            throw new HelloSignException("Invalid API Key (" + e.getMessage() + "): " + apiKey);
        } catch (NoSuchAlgorithmException e) {
            throw new HelloSignException("Unable to process API key", e);
        }
    }

    final protected static char[] hexArray = "0123456789ABCDEF".toCharArray();

    private static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }

    /**
     * Displays the Signature Request in addition to the Event data.
     */
    @Override
    public String toString() {
        String retStr = super.toString();
        if (resource != null) {
            String className = resource.getClass().getName();
            retStr += "\nAssociated " + className + ":\n";
            retStr += resource.toString();
        }
        return retStr;
    }
}
