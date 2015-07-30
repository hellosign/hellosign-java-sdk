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

import java.io.Serializable;
import java.net.HttpURLConnection;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hellosign.sdk.AbstractHelloSignTest;
import com.hellosign.sdk.HelloSignClient;
import com.hellosign.sdk.HelloSignException;
import com.hellosign.sdk.http.HttpPostRequest;
import com.hellosign.sdk.resource.SignatureRequest;
import com.hellosign.sdk.resource.support.SignatureRequestList;

/**
 * Exercises the Signature Request capability.
 * 
 * @author "Chris Paul (chris@hellosign.com)"
 */
public class SignatureRequestTest extends AbstractHelloSignTest {

    private static final Logger logger = LoggerFactory.getLogger(SignatureRequestTest.class);

    @Test
    public void testSignatureRequestCreate() throws HelloSignException {
        // Create the signature request
        SignatureRequest request = new SignatureRequest();
        request.setTestMode(true);
        request.setTitle("NDA with Acme Co.");
        request.setSubject("The NDA we talked about");
        request.setMessage("Please sign this NDA and then we can discuss more. Let me know if you have any questions.");
        request.addSigner("jill@example.com", "Jill");
        request.addSigner("jack@example.com", "Jack", 1);
        request.setOrderMatters(true);
        request.addCC("lawyer@hellosign.com");
        request.addCC("lawyer@example.com");
        request.addFile(getTestFile("nda.pdf"));
        request.addFile(getTestFile("AppendixA.pdf"));

        // Test the POST fields
        logger.debug("Building POST fields... ");
        Map<String, Serializable> fields = request.getPostFields();
        assertTrue(areFieldsEqual(getExpectedFields(), fields));
        logger.debug("\tSuccess!");

        // HelloSignClient client = new HelloSignClient(validUserEmail, validUserPass);
        // SignatureRequest response = client.sendSignatureRequest(request);

        // The following is for testing purposes only. Don't do this in your code, 
        // you'll want to use the lines above to send your request, much easier!

        if (isHelloSignAvailable()) {
            HelloSignClient client = new HelloSignClient(auth);
            logger.debug("Creating new request...");
            logger.debug("POST " + client.getSignatureRequestUrl());
            HttpPostRequest postRequest = new HttpPostRequest(
                    client.getSignatureRequestSendUrl(),
                    fields, auth);
            JSONObject createResponse = postRequest.getJsonResponse();
            assertTrue(
                    areJSONObjectsEqualIgnoreData(
                            getExpectedJSONResponse(), 
                            createResponse));
            logger.debug("\tSuccess!");

            // Convert the JSON response to a SignatureRequest
            SignatureRequest response = new SignatureRequest(createResponse);
            assertNotNull(request);
            logger.debug("SignatureRequest created with ID: " + response.getId());

            // Test retrieval by user list
            logger.debug("Testing SignatureRequest list...");
            SignatureRequestList list = client.getSignatureRequests();
            List<SignatureRequest> foundRequests = 
                    list.filterCurrentPageBy(SignatureRequest.REQUEST_TITLE, request.getTitle());

            // If the request isn't found, let's page through the results from the server until we find it
            while (foundRequests.size() == 0) {
                Integer nextPage = list.getPage() + 1;
                if (nextPage > list.getNumPages()) {
                    break;
                }
                list = client.getSignatureRequests(nextPage);
                foundRequests = 
                        list.filterCurrentPageBy(SignatureRequest.REQUEST_TITLE, request.getTitle());
            }

            // Make sure we have at least one request
            assertTrue(foundRequests.size() >= 1);
            logger.debug("\tSuccess!");

            // Test retrieval by ID
            logger.debug("Testing direct SignatureRequest retrieval...");
            SignatureRequest retrievedReq = client.getSignatureRequest(response.getId());
            assertNotNull(retrievedReq);
            assertEquals(retrievedReq.getId(), response.getId());
            logger.debug("\tSuccess!");

            // Test signature request reminder
            logger.debug("Testing SignatureRequest reminder...");
            SignatureRequest remindedReq = client.requestEmailReminder(response.getId(), "jack@example.com");
            assertNotNull(remindedReq);
            assertEquals(remindedReq.getId(), response.getId());
            logger.debug("\tSuccess!");

            // Test final copy retrieval (should fail)
//            logger.debug("Testing retrieval of final copy...");
//            try {
//                client.getFinalCopy(response.getId());
//            } catch (HelloSignException ex) {
//                assertEquals(ex.getMessage(), "Not ready");
//            }

//            logger.debug("Testing retrieval of files...");
//            File f = null;
//            int tries = 0;
//            int sleep = 5000; // 5 seconds
//            while (f == null && tries < 10) {
//                try {
//                    f = client.getFiles(response.getId());
//                } catch (HelloSignException ex) {
//                    assertEquals(ex.getMessage(), "Not ready");
//                    try {
//                        logger.debug("Sleeping " + sleep + " milliseconds...");
//                        Thread.sleep(sleep);
//                        tries++;
//                    } catch (Exception e) {};
//                }
//            }
//            
//            assertNotNull(f);
//            assertTrue(f.exists());

            logger.debug("\tSuccess!");

            // Cancel the signature request
            // client.cancelSignatureRequest(response.getId());
            logger.debug("Cancelling new request...");
            String cancelUrl = client.getSignatureRequestCancelUrl() + "/" + response.getId();
            logger.debug("POST " + cancelUrl);
            postRequest = new HttpPostRequest(cancelUrl, auth);
            logger.debug("HTTP response code: " + postRequest.getHttpResponseCode());
//            assertTrue(HttpURLConnection.HTTP_OK == postRequest.getHttpResponseCode());
            logger.debug("\tSuccess!");
        }
    }

//    @Test
//    public void testFinalCopyRetrieval() throws HelloSignException {
//        HelloSignClient client = new HelloSignClient(validApiKey);
//        File f = client.getFinalCopy("6a310a025cac668778f1e2b5624e8934e0975ed2");
//        logger.debug(f.getAbsolutePath());
//        assertNotNull(f);
//    }

//    @Test(expected=HelloSignException.class)
//    public void testRetrieveDeletedSig() throws HelloSignException {
//        HelloSignClient client = new HelloSignClient(validApiKey);
//        client.getSignatureRequest("711d4f19607e4c49928057b2a326d30c1b65418f");
//    }

    @Test
    public void testTextTags() throws HelloSignException {
        SignatureRequest req = new SignatureRequest();
        req.setTestMode(true);
        req.addSigner("abeecher@example.com", "Alice");
        req.addFile(getTestFile("text_tags_test.pdf"));
        req.setUseTextTags(true);
        assertNotNull(req);
        assertTrue(req.isUsingTextTags());
        logger.debug("Sending: " + req.toString());
        if (isHelloSignAvailable()) {
            HelloSignClient client = new HelloSignClient(auth);
            logger.debug("Creating new request...");
            logger.debug("POST " + client.getSignatureRequestUrl());
            SignatureRequest resp = client.sendSignatureRequest(req);
            assertNotNull(resp);
            logger.debug("\tSuccess!");
        }
    }

    @Test
    public void testHiddenTextTags() throws HelloSignException {
        SignatureRequest req = new SignatureRequest();
        req.setTestMode(true);
        req.addSigner("abeecher@example.com", "Alice");
        req.addFile(getTestFile("test_white_text_tags.pdf"));
        req.setUseTextTags(true);
        req.setHideTextTags(true);
        assertNotNull(req);
        assertTrue(req.isUsingTextTags());
        logger.debug("Sending: " + req.toString());
        if (isHelloSignAvailable()) {
            HelloSignClient client = new HelloSignClient(auth);
            logger.debug("Creating new request...");
            logger.debug("POST " + client.getSignatureRequestUrl());
            SignatureRequest resp = client.sendSignatureRequest(req);
            assertNotNull(resp);
            logger.debug("\tSuccess!");
        }
    }
}
