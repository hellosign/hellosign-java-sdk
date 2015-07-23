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

import org.json.JSONObject;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hellosign.sdk.AbstractHelloSignTest;
import com.hellosign.sdk.HelloSignClient;
import com.hellosign.sdk.HelloSignException;
import com.hellosign.sdk.resource.Account;

/**
 * Simple test for the Account resource class.
 * 
 * @author "Chris Paul (chris@hellosign.com)"
 */
public class AccountTest extends AbstractHelloSignTest {

    private static final Logger logger = LoggerFactory.getLogger(AccountTest.class);

    @Test
    public void testAccountWithValidJSON() throws Exception {
        JSONObject account = new JSONObject(getTestFileAsString("validAccount.txt"));
        Account u = new Account(account);
        assertNotNull(u);
    }

    @Test
    public void testCreateAccount() throws HelloSignException {
        // We only want to test account creation against a local test environment
        if (isHelloSignAvailable()) {
            String testEmail = "test-" + System.currentTimeMillis() + "@test.com";
            String testPassword = "password";
            HelloSignClient client = new HelloSignClient(validApiKey);
            try {
                Account account = client.createAccount(testEmail, testPassword);
                assertNotNull(account);
                assertTrue(account.hasId());
            } catch (HelloSignException ex) {
                fail(ex.getMessage());
            }
        }
    }

    @Test
    public void testUpdateAccount() throws HelloSignException {
        if (!isHelloSignAvailable()) {
            logger.debug("No API access, skipping tests...");
            return;
        }
        HelloSignClient client = new HelloSignClient(validApiKey);
//        Account currentAccount = client.getAccount();
//        String origCallback = (currentAccount.hasCallbackUrl() ? currentAccount.getCallbackUrl() : "");

        Account account = client.setCallback(callbackUrl);
        assertNotNull(account);
        assertTrue(account.hasId());
        assertEquals(callbackUrl, account.getCallbackUrl());

//        account = client.setCallback(origCallback);
//        assertNotNull(account);
//        assertTrue(account.hasId());
//        assertEquals("Callback URL was reset",  origCallback, account.getCallbackUrl());
    }
}
