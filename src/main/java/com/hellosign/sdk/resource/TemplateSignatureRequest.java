package com.hellosign.sdk.resource;

/**
 * The MIT License (MIT)
 * 
 * Copyright (C) 2014 hellosign.com
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hellosign.sdk.HelloSignException;
import com.hellosign.sdk.resource.support.Signer;

/**
 * Represents a HelloSign signature request based on one or more Templates. 
 * 
 * Unlike the SignatureRequest, this object is only used to submit
 * the request. A successfully submitted TemplateSignatureRequest will
 * return a SignatureRequest object from the server.
 * 
 * @author "Chris Paul (chris@hellosign.com)"
 */
public class TemplateSignatureRequest extends AbstractRequest {

	// Placing all "template_id" parameters in the new "template_ids[]"
	// array parameter for now. May remove this at some point.
	private static final String TEMPLATE_ID = "template_id";
	private static final String TEMPLATE_IDS = "template_ids";
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

	public TemplateSignatureRequest() {
		super();
	}

	/**
	 * Convenience constructor that accepts a single Template.
	 * @param template Template
	 * @throws HelloSignException
	 */
	public TemplateSignatureRequest(Template template) throws HelloSignException {
		this();
		setTemplateId(template.getId());
	}

	/**
	 * Convenience constructor that accepts a list of Templates.
	 * @param templates List<Template>
	 * @throws HelloSignException
	 */
	public TemplateSignatureRequest(List<Template> templates) throws HelloSignException {
		this();
		for (Template template : templates) {
			addTemplateId(template.getId());
		}
	}

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
	public void setTemplateId(String id) throws HelloSignException {
		clearList(TEMPLATE_IDS);
		addTemplateId(id, null);
	}
	
	/**
	 * Get the template ID that will be used with this request.
	 * @return String 
	 */
	public String getTemplateId() throws HelloSignException {
		List<String> templateIds = getTemplateIds();
		if (templateIds.size() == 0) {
			return null;
		}
		return templateIds.get(0);
	}

	/**
	 * Adds the template ID to be used in this request.
	 * @param id
	 */
	public void addTemplateId(String id) throws HelloSignException {
		addTemplateId(id, null);
	}

	/**
	 * Add the template ID to be used at the specified index.
	 * @param id
	 * @param index
	 * @throws HelloSignException
	 */
	public void addTemplateId(String id, Integer index) throws HelloSignException {
		List<String> currentList = getList(String.class, TEMPLATE_IDS);
		if (index == null) {
			index = currentList.size();
		} else if (index < 0) {
			throw new HelloSignException("index cannot be negative");
		} else if (index > currentList.size()) {
			throw new HelloSignException("index is greater than template ID list size: " + currentList.size());
		}
		if (index == currentList.size()) {
			add(TEMPLATE_IDS, id); // Just append the item
		} else {
			// Insert the item at the given index
			clearList(TEMPLATE_IDS);
			int limit = currentList.size(); // We'll be adding one
			for (int i = 0; i < limit + 1; i++) {
				if (i == index) {
					add(TEMPLATE_IDS, id);
				}
				if (i < limit) {
					add(TEMPLATE_IDS, currentList.get(i));
				}
			}
		}
	}

	/**
	 * Get the list of template IDs that are used for this request.
	 * @return List<String>
	 */
	public List<String> getTemplateIds() {
		return getList(String.class, TEMPLATE_IDS);
	}
	
	/**
	 * Internal method used to retrieve the necessary POST fields to submit the
	 * signature request. 
	 * @return Map<String, Serializable>
	 * @throws HelloSignException
	 */
	public Map<String, Serializable> getPostFields() throws HelloSignException {
		Map<String, Serializable> fields = super.getPostFields();
		try {
			// Mandatory fields
			List<String> templateIds = getTemplateIds();
			for (int i = 0; i < templateIds.size(); i++) {
				fields.put(TEMPLATE_IDS + "[" + i + "]", templateIds.get(i));
			}
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
	
	/**
	 * Override the following, since templates do not have text tags.
	 */
	
	/**
	 * Not implemented for templates.
	 */
	@Override
	public boolean hasUseTextTags() {
		return false;
	}
	/**
	 * Not implemented for templates.
	 */
	@Override
	public Boolean isUsingTextTags() {
		return null;
	}
	/**
	 * Not implemented for templates.
	 */
	@Override
	public void setUseTextTags(boolean useTextTags) {}
	/**
	 * Not implemented for templates.
	 */
	@Override
	public boolean hasHideTextTags() {
		return false;
	}
	/**
	 * Not implemented for templates.
	 */
	@Override
	public Boolean isHidingTextTags() {
		return null;
	}
	/**
	 * Not implemented for templates.
	 */
	@Override
	public void setHideTextTags(boolean hideTextTags) {}
}
