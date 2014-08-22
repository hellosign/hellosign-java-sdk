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
import com.hellosign.sdk.resource.Template;
import com.hellosign.sdk.resource.TemplateSignatureRequest;
import com.hellosign.sdk.resource.support.TemplateList;

/**
 * This test is meant to be run internally. Will be skipped by automated tests.
 * 
 * @author "Chris Paul (chris@hellosign.com)"
 */
public class MultiTemplateSignatureRequestTest extends AbstractHelloSignTest {
	
	private static final Logger logger = LoggerFactory.getLogger(TemplateSignatureRequestTest.class);

	@Test
	public void testMultiTemplateSignatureRequestCreateAndSend() throws HelloSignException {
		if (!isHelloSignAvailable()) {
			logger.debug("No API access, skipping tests...");
			return;
		}
    	// Retrieve user's templates
    	logger.debug("Testing Template list... ");
    	HelloSignClient client = new HelloSignClient(auth);
    	
    	// Create the signature request based on template
    	TemplateSignatureRequest request = new TemplateSignatureRequest();
    	request.addTemplateId("c1a5d2dfb74e732c146cb038f9a3ac7d21b1ae0d");
    	request.addTemplateId("9bcc3e903b2c24b1068ca32c051ab3d6b73707ca", 0);
    	request.addTemplateId("fa6d13bc5cddb80f2973c0b12a3a066e75471f06");
    	request.setTestMode(true);
    	request.setSubject("MultiTemplateTest");
    	request.setMessage("Glad we could come to an agreement.");
    	request.setSigner("Tester", "chris+1@hellosign.com", "Chris 1");
    	request.setCustomFieldValue("Text", "Hello world!");
    	
    	logger.debug("Building POST fields... ");
    	Map<String, Serializable> fields = request.getPostFields();
    	assertTrue(areFieldsEqual(getExpectedFields(), fields));
    	logger.debug("\tFields match!");

    	// HelloSignClient client = new HelloSignClient(validUserEmail, validUserPass);
		// response = client.sendTemplateSignatureRequest(request);
		
		// The following is for testing purposes only. Don't do this in your code, 
		// you'll want to use the line above to send your request, much easier!
	
		logger.debug("Creating new request...");			
    	logger.debug("POST " + client.getTemplateSignatureRequestUrl());
    	HttpPostRequest postRequest = new HttpPostRequest(
    			client.getTemplateSignatureRequestUrl(), 
    			fields, auth);
    	JSONObject jsonResponse = postRequest.getJsonResponse();
    	assertTrue(areJSONObjectsEqualIgnoreData(getExpectedJSONResponse(), jsonResponse));
    	
    	// Convert the JSON response to a SignatureRequest
    	SignatureRequest response = new SignatureRequest(jsonResponse);
    	assertNotNull(response);
    	logger.debug("\tSuccess!");
		logger.debug("Created signature request with ID: " + response.getId());

		// Cancel newly created request
		logger.debug("Cancelling new request...");
    	String cancelUrl = client.getSignatureRequestCancelUrl() + "/" + response.getId();
    	logger.debug("POST " + cancelUrl);
    	postRequest = new HttpPostRequest(cancelUrl, auth);
    	assertTrue(HttpURLConnection.HTTP_OK == postRequest.getHttpResponseCode());
    	logger.debug("\tSuccess!");
	}

	
}
