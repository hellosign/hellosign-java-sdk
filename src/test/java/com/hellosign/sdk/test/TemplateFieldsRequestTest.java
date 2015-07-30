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
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hellosign.sdk.AbstractHelloSignTest;
import com.hellosign.sdk.HelloSignClient;
import com.hellosign.sdk.resource.SignatureRequest;
import com.hellosign.sdk.resource.Template;
import com.hellosign.sdk.resource.support.Document;
import com.hellosign.sdk.resource.support.TemplateList;

public class TemplateFieldsRequestTest extends AbstractHelloSignTest {

    private static final Logger logger = LoggerFactory.getLogger(TemplateFieldsRequestTest.class);

    /**
     * Prior to executing this request, a template must be created on the HelloSign server:
     * 
     * 1. Title: NDA Template
     * 2. Two documents
     * 3. Two signers:
     *    1. "Client"
     *    2. "Representative"
     * 4. Two CC email roles:
     *    1. "Client Lawyer"
     *    2. "Our Lawyer"
     * 5. Two signature fields on the first document.
     * 6. Two initial fields on the second document.
     * @throws Exception
     */
    @Test
    public void testSignatureCreateFromTemplateFormFields() throws Exception {
        if (!isHelloSignAvailable()) {
            logger.debug("No API access, skipping tests...");
            return;
        }

        // Retrieve the template we created for this test
        String templateTitle = "NDA Template";
        HelloSignClient client = new HelloSignClient(auth);
        TemplateList templateList = client.getTemplates();
        List<Template> templates = templateList.filterCurrentPageBy(Template.TEMPLATE_TITLE, templateTitle);

        // If the template isn't found, let's page through the 
        // results from the server until we find it
        while (templates.size() == 0) {
            Integer nextPage = templateList.getPage() + 1;
            if (nextPage > templateList.getNumPages()) {
                break;
            }
            templateList = client.getTemplates(nextPage);
            templates = templateList.filterCurrentPageBy(Template.TEMPLATE_TITLE, templateTitle);
        }
//        assertEquals(1, templates.size());
        if (templates.size() == 0) {
            logger.debug("\tNo template found, skipping test.");
            return;
        }

        Template template = templates.get(0);

        // Grab the documents stored in this template
        List<Document> templateDocs = template.getDocuments();
        if (templateDocs.size() != 2) {
            logger.debug("\tTemplate does not have 2 documents, skipping test.");
        }

        // Create a new signature request -- it must have 2 documents and 2 signers
        SignatureRequest request = new SignatureRequest();
        request.setTitle("New NDA");
        request.addSigner("jack@example.com", "Jack");
        request.addSigner("jill@example.com", "Jill");
        request.addFile(getTestFile("nda.docx"), 0);
        request.addFile(getTestFile("AppendixA.docx"), 1);

        // 
        List<Document> requestDocs = request.getDocuments();
        for (int i = 0; i < 2; i++) {
            Document rd = requestDocs.get(i);
            Document td = templateDocs.get(i);
            rd.setFormFields(td.getFormFields());
        }
        Map<String, Serializable> fields = request.getPostFields();
        for (String key : fields.keySet()) {
            logger.debug(key + "=" + fields.get(key));
        }
        assertTrue(areFieldsEqual(getExpectedFields(), fields));
        SignatureRequest response = client.sendSignatureRequest(request);
        assertNotNull(response);
        assertTrue(response.hasId());
    }
}
