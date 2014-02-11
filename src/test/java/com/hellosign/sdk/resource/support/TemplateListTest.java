package com.hellosign.sdk.resource.support;

import static org.junit.Assert.*;

import java.util.List;

import org.json.JSONObject;
import org.junit.Test;

import com.hellosign.sdk.AbstractHelloSignTest;
import com.hellosign.sdk.resource.Template;

public class TemplateListTest extends AbstractHelloSignTest {

	@Test
	public void testTemplateList() throws Exception {
		String templateListText = getTestFileAsString("page1.json");
		JSONObject templateObj = new JSONObject(templateListText);
		TemplateList list = new TemplateList(templateObj);
		List<Template> templates = list.filterCurrentPageBy(Template.TEMPLATE_TITLE, "Contract For Biz");
		assertEquals(0, templates.size());
		templates = list.filterCurrentPageBy(Template.TEMPLATE_TITLE, "Test Template 19");
		assertEquals(2, templates.size());
		templates = list.filterCurrentPageBy(Template.TEMPLATE_TITLE, "Template Test 2");
		assertEquals(1, templates.size());
		templates = list.filterCurrentPageBy(Template.TEMPLATE_CAN_EDIT, true);
		assertEquals(20, templates.size());
		
		// Simulate retrieval of 2nd page
		templateListText = getTestFileAsString("page2.json");
		templateObj = new JSONObject(templateListText);
		list = new TemplateList(templateObj);
		templates = list.filterCurrentPageBy(Template.TEMPLATE_TITLE, "Contract For Biz");
		assertEquals(1, templates.size());
	}
}
