package com.hellosign.sdk.resource.support;

import org.json.JSONObject;

import com.hellosign.sdk.HelloSignException;
import com.hellosign.sdk.resource.AbstractResourceList;
import com.hellosign.sdk.resource.Template;

/**
 * Represents a paged list of HelloSign Templates (a.k.a., ReusableForms). 
 * 
 * @author "Chris Paul (chris@hellosign.com)"
 */
public class TemplateList extends AbstractResourceList<Template> {
	
	public TemplateList(JSONObject json) throws HelloSignException {
		super(json, "reusable_forms");
	}
}
