package com.hellosign.sdk.resource;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.json.JSONException;
import org.json.JSONObject;

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

	public static final String EVENT_KEY = "event";
	public static final String EVENT_METADATA = "event_metadata";
	public static final String REPORTED_FOR_ACCOUNT_ID = "reported_for_account_id";
	public static final String RELATED_SIGNATURE_ID = "related_signature_id";
	public static final String EVENT_TYPE = "event_type";
	public static final String EVENT_TIME = "event_time";
	public static final String EVENT_HASH = "event_hash";
	
	public static final String HASH_ALGORITHM = "HmacSHA256";
	
	private SignatureRequest request;
	
	/**
	 * Default constructor. Provide this constructor with the JSONObject
	 * created from the API response.
	 * @param json JSONObject
	 * @throws HelloSignException
	 */
	public Event(JSONObject json) throws HelloSignException {
		super(json, EVENT_KEY);
		if (EventType.test.equals(getType())) {
			return;
		}
		try {
			request = new SignatureRequest(json.getJSONObject(SignatureRequest.SIGREQ_KEY));
		} catch (JSONException ex) {
			throw new HelloSignException(ex);
		}
	}
	
	/**
	 * Returns the account ID that this event is reporting for.
	 * @return String
	 * @throws HelloSignException
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
	 * @return true or false if it does not have an account ID
	 * @throws HelloSignException
	 */
	public boolean hasAccountId() throws HelloSignException {
		return has(REPORTED_FOR_ACCOUNT_ID);
	}
	
	/**
	 * Returns the event time as a Java Date object.
	 * @return Date
	 */
	public Date getEventDate() {
		return getDate(EVENT_TIME);
	}
	
	/**
	 * Returns the signature ID to which this event is associated. 
	 * @return String
	 * @throws HelloSignException
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
	 * Returns the associated Signature object with this event. 
	 * @return Signature
	 * @throws HelloSignException
	 */
	public Signature getRelatedSignature() throws HelloSignException {
		String id = getRelatedSignatureId();
		if (id == null) {
			return null;
		}
		for (Signature sig : request.getSignatures()) {
			if (id.equals(sig.getId())) {
				return sig;
			}
		}
		return null;
	}
	
	/**
	 * Returns a reference to the SignatureRequest that is attached to the
	 * Event.
	 * @return SignatureRequest
	 */
	public SignatureRequest getSignatureRequest() {
		return request;
	}
	
	/**
	 * Returns true if this Event is associated with a Signature Request.
	 * @return true, or false if it does not have a Signature Request
	 */
	public boolean hasSignatureRequest() {
		return request != null && request.getId() != null;
	}
	
	/**
	 * Returns the EventType for this event.
	 * @return EventType enum
	 */
	public EventType getType() {
		return EventType.valueOf(getTypeString());
	}
	
	/**
	 * Returns the event type String.
	 * @return String event type
	 */
	public String getTypeString() {
		return getString(EVENT_TYPE);
	}
	
	/**
	 * Returns the unique hash string identifying this event.
	 * @return String hash
	 */
	public String getHash() {
		return getString(EVENT_HASH);
	}
	
	/**
	 * Returns true if the event hash matches the computed hash using the provided
	 * API key.
	 * @param apiKey String api key.
	 * @return true if the hashes match, false otherwise
	 * @throws HelloSignException
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
			e.printStackTrace();
		}
		return false;
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
		if (hasSignatureRequest()) {
			retStr += "\nEvent signature request:\n";
			retStr += request.toString();
		}
		return retStr;
	}
}
