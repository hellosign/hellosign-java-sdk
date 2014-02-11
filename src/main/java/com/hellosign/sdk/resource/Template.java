package com.hellosign.sdk.resource;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import com.hellosign.sdk.HelloSignException;
import com.hellosign.sdk.resource.support.CustomField;
import com.hellosign.sdk.resource.support.Document;
import com.hellosign.sdk.resource.support.TemplateRole;

/**
 * POJO that represents a HelloSign Template resource.
 * 
 * @author "Chris Paul (chris@hellosign.com)"
 */
public class Template extends AbstractResource {
	
	private static final String TEMPLATE_KEY = "reusable_form";
	
	public static final String TEMPLATE_ID = "reusable_form_id";
	public static final String TEMPLATE_TITLE = "title";
	public static final String TEMPLATE_MESSAGE = "message";
	public static final String TEMPLATE_DOCUMENTS = "documents";
	public static final String TEMPLATE_SIGNER_ROLES = "signer_roles";
	public static final String TEMPLATE_CC_ROLES = "cc_roles";
	public static final String TEMPLATE_ACCOUNTS = "accounts";
	public static final String TEMPLATE_CAN_EDIT = "can_edit";
	public static final String TEMPLATE_IS_CREATOR = "is_creator";
	public static final String TEMPLATE_CUSTOM_FIELDS = "custom_fields";
	
	public Template() {
		super();
	}	
	public Template(JSONObject json) throws HelloSignException {
		super(json, TEMPLATE_KEY);
	}

	public String getId() {
		return getString(TEMPLATE_ID);
	}
	public boolean hasId() {
		return has(TEMPLATE_ID);
	}
	public String getTitle() {
		return getString(TEMPLATE_TITLE);
	}
	public boolean hasTitle() {
		return has(TEMPLATE_TITLE);
	}
	public String getMessage() {
		return getString(TEMPLATE_MESSAGE);
	}
	public boolean hasMessage() {
		return has(TEMPLATE_MESSAGE);
	}
	public List<Document> getDocuments() {
		return getList(Document.class, TEMPLATE_DOCUMENTS);
	}
	/**
	 * Returns a list of SignerRoles. If they are ordered, the list is returned
	 * in order.
	 * @return List<SignerRole>
	 */
	public List<TemplateRole> getSignerRoles() {
		List<TemplateRole> masterList = getList(TemplateRole.class, TEMPLATE_SIGNER_ROLES);
		if (masterList == null || masterList.size() == 0) {
			return masterList;
		}
		if (masterList.get(0).getOrder() == null) {
			return masterList;
		}
		List<TemplateRole> sortedList = new ArrayList<TemplateRole>(masterList.size());
		for (TemplateRole r : masterList) {
			sortedList.add(r.getOrder(), r);
		}
		return sortedList;
	}
	public List<TemplateRole> getCCRoles() {
		return getList(TemplateRole.class, TEMPLATE_CC_ROLES);
	}
	public List<Account> getAccounts() {
		return getList(Account.class, TEMPLATE_ACCOUNTS);
	}
	public boolean canEdit() {
		return getBoolean(TEMPLATE_CAN_EDIT);
	}
	public boolean isCreator() {
		return getBoolean(TEMPLATE_IS_CREATOR);
	}
	public List<CustomField> getCustomFields() {
		return getList(CustomField.class, TEMPLATE_CUSTOM_FIELDS);
	}
}
