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

import org.json.JSONObject;
import org.junit.Test;

import com.hellosign.sdk.AbstractHelloSignTest;
import com.hellosign.sdk.resource.support.types.FieldType;

/**
 * @author "Chris Paul (chris@hellosign.com)"
 */
public class TemplateDraftTest extends AbstractHelloSignTest {

    @Test
    public void testTemplateDraftCreate() throws Exception {
        TemplateDraft t = new TemplateDraft();
        assertNotNull(t);

        t = new TemplateDraft(new JSONObject("{}"));
        assertNotNull(t);
        assertNull(t.getId());
        assertNull(t.getEditUrl());
        assertNull(t.getExpiresAt());

        t = new TemplateDraft(new JSONObject("{'template':{'template_id':'1234567890abcdef'}}"));
        assertNotNull(t);
        assertNotNull(t.getId());
        assertNull(t.getEditUrl());
        assertNull(t.getExpiresAt());

        t = new TemplateDraft(new JSONObject("{'template':{'template_id':'1234567890abcdef','edit_url':'https://www.google.com/','expires_at':'1234567890'}}"));
        assertNotNull(t);
        assertNotNull(t.getId());
        assertNotNull(t.getEditUrl());
        assertNotNull(t.getExpiresAt());
    }

    @Test
    public void testTemplateDraftPostFields() throws Exception {
        TemplateDraft t = new TemplateDraft();
        t.setTestMode(true);
        t.addFile(getTestFile("nda.pdf"));
        t.addSignerRole("Signer 1");
        t.addSignerRole("Signer 2");
        t.addCCRole("CC Role 1");
        t.setTitle("Title");
        t.setSubject("Subject");
        t.setMessage("Message");
        t.addMergeField("Text", FieldType.text);
        t.addMergeField("Checkbox", FieldType.checkbox);
        assertTrue(areFieldsEqual(getExpectedFields(), t.getPostFields()));
    }
}
