package com.hellosign.sdk.test;

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

import java.io.Serializable;
import java.util.Map;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hellosign.sdk.AbstractHelloSignTest;
import com.hellosign.sdk.HelloSignClient;
import com.hellosign.sdk.HelloSignException;
import com.hellosign.sdk.resource.AbstractRequest;
import com.hellosign.sdk.resource.EmbeddedRequest;
import com.hellosign.sdk.resource.EmbeddedResponse;
import com.hellosign.sdk.resource.TemplateDraft;
import com.hellosign.sdk.resource.TemplateSignatureRequest;
import com.hellosign.sdk.resource.UnclaimedDraft;
import com.hellosign.sdk.resource.support.types.UnclaimedDraftType;

/**
 * Exercise the EmbeddedRequest wrapper.
 * 
 * @author "Chris Paul (chris@hellosign.com)"
 */
public class EmbeddedTemplateTest extends AbstractHelloSignTest {

    private static final Logger logger = LoggerFactory.getLogger(EmbeddedTemplateTest.class);

    @Test
    public void testEmbeddedTemplateCreate() throws Exception {
        if (!isHelloSignAvailable()) {
            logger.debug("No API access, skipping tests...");
            return;
        }
        HelloSignClient client = new HelloSignClient(auth);

        TemplateDraft draft = new TemplateDraft();
        draft.setTestMode(true);
        draft.setTitle("NDA Template");
        draft.addSignerRole("Client");
        draft.addCCRole("Lawyer");
        draft.addFile(getTestFile("nda.pdf"));

        EmbeddedRequest eReq = new EmbeddedRequest(clientId, draft);
        TemplateDraft t = client.createEmbeddedTemplateDraft(eReq);

        String editUrl = t.getEditUrl();
        String id = t.getId();
        String expiration = t.getExpiresAt();

        logger.debug("Edit URL: " + editUrl);
        logger.debug("Template ID: " + id);
        logger.debug("Edit URL expiration: " + expiration);

        assertNotNull(editUrl);
        assertNotNull(id);
        assertNotNull(expiration);
    }

    @Test
    public void testTemplateDelete() throws Exception {
        if (!isHelloSignAvailable()) {
            logger.debug("No API access, skipping tests...");
            return;
        }
        HelloSignClient client = new HelloSignClient(auth);
        try {
            if (client.deleteTemplate("fake_id")) {
                fail("Exception should have been thrown");
            }
        } catch (HelloSignException ex) {
            assertTrue(true);
        }
//        assertTrue(client.deleteTemplate("REAL_TEMPLATE_ID_HERE"));
    }

    @Test
    public void testTemplateEdit() throws Exception {
        if (!isHelloSignAvailable()) {
            logger.debug("No API access, skipping tests...");
            return;
        }
        HelloSignClient client = new HelloSignClient(auth);
        try {
            EmbeddedResponse res = client.getEmbeddedTemplateEditUrl("fake_id");
            if (res != null) {
                fail("Exception should have been thrown");
            }
        } catch (HelloSignException ex) {
            assertTrue(true);
        }
//        assertNotNull(client.getEmbeddedTemplateEditUrl("REAL_EMBEDDED_TEMPLATE_ID_HERE"));
    }

    @Test
    public void testTemplateSignatureRequestPreview() throws Exception {
        if (!isHelloSignAvailable()) {
            logger.debug("No API access, skipping tests...");
            return;
        }
//        HelloSignClient client = new HelloSignClient(auth);
//        TemplateSignatureRequest tsr = new TemplateSignatureRequest();
//        tsr.setTemplateId("f4f3e7bc3fa6580853a830fdaf23d7fc4be20a00");
//        tsr.setSigner("tester", "test@test.com", "Tester");
//        tsr.setTitle("Test!");
//        tsr.setMessage("This is just a test...");
//        UnclaimedDraft ucd = new UnclaimedDraft(tsr);
//        ucd.setType(UnclaimedDraftType.request_signature);
//        ucd.setRequesterEmail("chris@hellosign.com");
//        EmbeddedRequest embeddedReq = new EmbeddedRequest(clientId, ucd);
//        UnclaimedDraft resp = (UnclaimedDraft) client.createEmbeddedRequest(embeddedReq);
//        assertNotNull(resp);
    }
}
