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

    public static final String TEMPLATE_KEY = "template";
    public static final String TEMPLATE_ID = "template_id";
    public static final String TEMPLATE_TITLE = "title";
    public static final String TEMPLATE_MESSAGE = "message";
    public static final String TEMPLATE_DOCUMENTS = "documents";
    public static final String TEMPLATE_SIGNER_ROLES = "signer_roles";
    public static final String TEMPLATE_CC_ROLES = "cc_roles";
    public static final String TEMPLATE_ACCOUNTS = "accounts";
    public static final String TEMPLATE_CAN_EDIT = "can_edit";
    public static final String TEMPLATE_IS_CREATOR = "is_creator";
    public static final String TEMPLATE_CUSTOM_FIELDS = "custom_fields";
    public static final String TEMPLATE_IS_EMBEDDED = "is_embedded";

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
     * 
     * @return List
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

    /**
     * Returns true if this template was created on a site other than
     * hellosign.com.
     * 
     * @return boolean
     */
    public boolean isEmbedded() {
        boolean isEmbedded = false;
        if (has(TEMPLATE_IS_EMBEDDED)) {
            isEmbedded = getBoolean(TEMPLATE_IS_EMBEDDED);
        }
        return isEmbedded;
    }
}
