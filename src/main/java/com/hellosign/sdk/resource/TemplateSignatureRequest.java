package com.hellosign.sdk.resource;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.hellosign.sdk.HelloSignException;
import com.hellosign.sdk.resource.support.Signer;

/**
 * Represents a HelloSign signature request based on a Template. 
 * 
 * Unlike the SignatureRequest, this object is only used to submit
 * the request. A successfully submitted TemplateSignatureRequest will
 * return a SignatureRequest object from the server.
 * 
 * @author "Chris Paul (chris@hellosign.com)"
 */
public class TemplateSignatureRequest extends AbstractRequest {

	private static final String TEMPLATE_ID = "reusable_form_id";
	private static final String TEMPLATE_SIGNERS = "signers";
	private static final String TEMPLATE_SIGNERS_EMAIL = "email_address";
	private static final String TEMPLATE_SIGNERS_NAME = "name";
	private static final String TEMPLATE_CCS = "ccs";
	private static final String TEMPLATE_CCS_EMAIL = "email_address";
	private static final String TEMPLATE_CUSTOM_FIELDS = "custom_fields";

	// Signers, CC email addresses and custom fields are required
	// to have an associated role. We'll manage these in a Map,
	// as opposed to storing them on the JSON object like other 
	// fields are stored, so we can support this association. 
	private Map<String, Signer> signers = new HashMap<String, Signer>();	
	private Map<String, String> ccs = new HashMap<String, String>();
	private Map<String, String> customFields = new HashMap<String, String>();

	/**
	 * Returns a reference to the map of current roles 
	 * to CC email addresses.
	 * @return Map<String, String>
	 */
	public Map<String, String> getCCs() {
		return ccs;
	}
	
	/**
	 * Overwrites the map of roles to CC email addresses.
	 * @param newCCs
	 */
	public void setCCs(Map<String, String> newCCs) {
		ccs = newCCs;
	}
	
	/**
	 * Sets the CC email address for the provided role.
	 * @param role String
	 * @param email String
	 */
	public void setCC(String role, String email) {
		ccs.put(role, email);
	}
	
	/**
	 * Clears the list of current CC email addresses.
	 */
	public void clearCCs() {
		ccs = new HashMap<String, String>();
	}
	
	/**
	 * Adds the signer to the list of signers for this request.
	 * @param email String
	 * @param name String
	 * @throws HelloSignException 
	 */
	public void setSigner(String role, String email, String name) throws HelloSignException {
		signers.put(role, new Signer(email, name));
	}
	
	/**
	 * Returns a reference to the signers list. This can be modified and 
	 * re-added to the request using setSigners(). Useful for more explicit 
	 * modification.
	 * @return List<Signer>
	 */
	public Map<String, Signer> getSigners() {
		return signers;
	}
	
	/**
	 * Overwrites the current list of signers for this request with the
	 * given list.
	 * @param signers List<Signer>
	 */
	public void setSigners(Map<String, Signer> signers) {
		this.signers = signers;
	}
	
	/**
	 * Removes signer(s) from this request by email address. 
	 * If more than one signer is listed by the given email, it
	 * will remove all instances of that signer. If no user 
	 * is listed by the given email, nothing will happen.
	 * @param email String
	 */
	public void removeSignerByEmail(String email) throws HelloSignException {
		if (email == null) {
			return;
		}
		for (int i = 0; i < signers.size(); i++) {
			if (email.equalsIgnoreCase(signers.get(i).getEmail())) {
				signers.remove(i);
			}
		}
	}
	
	/**
	 * Adds the value to fill in for a custom field with the given field name. 
	 * @param fieldName String field name to be filled in
	 * @param value String value
	 */
	public void setCustomFieldValue(String fieldName, String value) {
		customFields.put(fieldName, value);
	}
	
	/**
	 * Returns the map of custom fields for the template. 
	 * This is a map of String field names to String field values.
	 * @return Map<String, String>
	 */
	public Map<String, String> getCustomFields() {
		return customFields;
	}
	
	/**
	 * Overwrites the current map of custom fields to the provided map.
	 * This is a map of String field names to String field values.
	 * @param fields Map<String, String>
	 */
	public void setCustomFields(Map<String, String> fields) {
		customFields = fields;
	}
	
	/**
	 * Clears the current custom fields for this request.
	 */
	public void clearCustomFields() {
		customFields = new HashMap<String, String>();
	}
	
	/**
	 * Set the template ID of the template that should be used with this request.
	 * @param id String
	 */
	public void setTemplateId(String id) {
		set(TEMPLATE_ID, id);
	}
	
	/**
	 * Get the template ID that will be used with this request.
	 * @return String
	 */
	public String getTemplateId() {
		return getString(TEMPLATE_ID);
	}
	
	
	/**
	 * Internal method used to retrieve the necessary POST fields to submit the
	 * signature request. 
	 * @return Map<String, Serializable>
	 * @throws HelloSignException
	 */
	public Map<String, Serializable> getPostFields() throws HelloSignException {
		Map<String, Serializable> fields = new HashMap<String, Serializable>();
		try {
			// Mandatory fields
			fields.put(TEMPLATE_ID, getTemplateId());
			Map<String, Signer> signerz = getSigners();
			for (String role : signerz.keySet()) {
				Signer s = signerz.get(role);
				fields.put(TEMPLATE_SIGNERS 
						+ "[" + role + "][" + TEMPLATE_SIGNERS_EMAIL + "]", 
						s.getEmail());
				fields.put(TEMPLATE_SIGNERS 
						+ "[" + role + "][" + TEMPLATE_SIGNERS_NAME + "]", 
						s.getNameOrRole());
			}
						
			// Optional fields
			if (hasTitle()) {
				fields.put(REQUEST_TITLE, getTitle());
			}
			if (hasSubject()) {
				fields.put(REQUEST_SUBJECT, getSubject());	
			}
			if (hasMessage()) {
				fields.put(REQUEST_MESSAGE, getMessage());	
			}
			Map<String, String> ccz = getCCs();
			for (String role : ccz.keySet()) {
				fields.put(TEMPLATE_CCS 
						+ "[" + role + "][" + TEMPLATE_CCS_EMAIL + "]", 
						ccz.get(role));
			}
			Map<String, String> customFields = getCustomFields();
			for (String fieldName : customFields.keySet()) {
				fields.put(TEMPLATE_CUSTOM_FIELDS 
						+ "[" + fieldName + "]", 
						customFields.get(fieldName));
			}
			if (isTestMode()) {
				fields.put(REQUEST_TEST_MODE, true);
			}
		} catch (Exception ex) {
			throw new HelloSignException(
					"Could not extract form fields from TemplateSignatureRequest.", ex);
		}
		return fields;
	}

	@Override
	public String getId() {
		return null;
	}
}
