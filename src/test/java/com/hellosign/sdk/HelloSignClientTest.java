package com.hellosign.sdk;

import static org.junit.Assert.*;

import org.junit.Test;

import com.hellosign.sdk.HelloSignClient;
import com.hellosign.sdk.resource.Account;

public class HelloSignClientTest extends AbstractHelloSignTest {

    @Test
    public void testAuthWithValidCredentials() {
    	Account account = null;
        try {
        	HelloSignClient client = new HelloSignClient(validApiKey);
        	account = client.getAccount();
        } catch (HelloSignException ex) {
        	ex.printStackTrace();
        	fail("Unexpected exception encountered!");
        }
        assertNotNull(account);
    }
    
    @Test
    public void testAuthWithInvalidCredentials() {
    	Account account = null;
        try {
        	HelloSignClient client = new HelloSignClient(invalidUserEmail, invalidUserPass);
        	account = client.getAccount();
        } catch (HelloSignException ex) {
        	assertNotNull("Expected exception encountered.", ex);
        }
        assertNull("No account was returned", account);
    }
    
}
