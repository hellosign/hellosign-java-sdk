package com.hellosign.sdk.resource.support;

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

import static org.junit.Assert.*;

import java.util.List;

import org.json.JSONObject;
import org.junit.Test;

import com.hellosign.sdk.AbstractHelloSignTest;
import com.hellosign.sdk.resource.Template;

public class TemplateListTest extends AbstractHelloSignTest {

	/*
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
	*/
}
