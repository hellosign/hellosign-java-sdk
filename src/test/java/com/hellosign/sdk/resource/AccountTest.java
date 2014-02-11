package com.hellosign.sdk.resource;

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
				assertTrue(ex.getMessage().contains("Unauthorized user"));
				logger.debug("\tUser (" + validApiKey + ") does not have authorization to create a new user. Skipping.");
				return;
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
//		Account currentAccount = client.getAccount();
//		String origCallback = (currentAccount.hasCallbackUrl() ? currentAccount.getCallbackUrl() : "");
		
		Account account = client.setCallback(callbackUrl);
		assertNotNull(account);
		assertTrue(account.hasId());
		assertEquals(callbackUrl, account.getCallbackUrl());
		
//		account = client.setCallback(origCallback);
//		assertNotNull(account);
//		assertTrue(account.hasId());
//		assertEquals("Callback URL was reset",  origCallback, account.getCallbackUrl());
	}
}
