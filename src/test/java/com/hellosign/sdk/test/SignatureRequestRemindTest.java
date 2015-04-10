package com.hellosign.sdk.test;

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

import java.net.HttpURLConnection;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hellosign.sdk.AbstractHelloSignTest;
import com.hellosign.sdk.HelloSignClient;
import com.hellosign.sdk.resource.EmbeddedRequest;
import com.hellosign.sdk.resource.SignatureRequest;

/**
 * Exercise the EmbeddedRequest wrapper.
 * 
 * @author "Chris Paul (chris@hellosign.com)"
 */
public class SignatureRequestRemindTest extends AbstractHelloSignTest {

	private static final Logger logger = LoggerFactory.getLogger(SignatureRequestRemindTest.class);
	
	@Test
	public void testSignatureRequestRemind() throws Exception {
		// Create the signature request
		SignatureRequest request = new SignatureRequest();
    	request.setTestMode(true);
    	request.setTitle("Request");
    	request.addSigner("chris@hellosign.com", "Chris");
    	request.addFile(getTestFile("nda.pdf"));
    	
		if (isHelloSignAvailable()) {
			// Just pass the request through the HelloSignClient since we are already
			// testing the request output for SignatureRequest/TemplateSignatureRequest
			// in their respective test classes.
	    	HelloSignClient client = new HelloSignClient(auth);
	    	SignatureRequest response = (SignatureRequest) client.sendSignatureRequest(request);
	    	assertNotNull(response);
	    	assertTrue(response.hasId());
            String id = response.getId();

	    	// Cancel the signature request
	    	Thread.sleep(5000);
	    	SignatureRequest reminderResp = client.requestEmailReminder(response.getId(), "chris@hellosign.com");
	    	assertNotNull(reminderResp);
	    	assertTrue(id.equals(reminderResp.getId()));
		}
	}
}
