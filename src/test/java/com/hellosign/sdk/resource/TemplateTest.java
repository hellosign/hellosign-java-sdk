package com.hellosign.sdk.resource;

import static org.junit.Assert.*;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hellosign.sdk.AbstractHelloSignTest;
import com.hellosign.sdk.HelloSignClient;

/**
 * Exercises the Template methods in HelloSignClient, primarily the adding
 * and removal of users with access to a particular template.
 * 
 * Note: This test requires the use of two user accounts that are on the
 * same team.
 * 
 * @author "Chris Paul (chris@hellosign.com)"
 */
public class TemplateTest extends AbstractHelloSignTest {
	
	private static final Logger logger = LoggerFactory.getLogger(TemplateTest.class);

	@Test
	public void testAddAndRemoveTemplateUsers() throws Exception {
		if (!isHelloSignAvailable()) {
			logger.debug("No API access, skipping tests...");
			return;
		}
		if (templateId == null || templateId.equals("")) {
			logger.debug("No template ID provided, skipping tests...");
			return;
		}
		HelloSignClient client = new HelloSignClient(auth);
		logger.debug("Retrieving Template with ID: " + templateId);
		Template t = client.getTemplate(templateId);
		if (t == null) {
			logger.debug("\tNo template found, skipping");
			return;
		}
//		logger.debug("Original Template Accounts:");
//		for (Account a : t.getAccounts()) {
//			logger.debug("\t" + a.getEmail() + " (" + a.getId() + ")");
//		}
		
		// Ensure users are on the same team before adding/removing
		logger.debug("Checking user team status...");
		Team team = client.getTeam();
		boolean found = false;
		for (Account account : team.getAccounts()) {
			if (validUserEmail2.equals(account.getEmail())) {
				found = true;
				break;
			}
		}
		if (!found) {
			logger.debug(validUserEmail2 + " must be on the '" +
					team.getName() + "' team to proceed. Skipping tests...");
			return;
		}
		logger.debug("\tSuccess!");
		
		// Test adding the 2nd team member to the template 
		logger.debug("Adding user to template: " + validUserEmail2);
		Template t2 = client.addTemplateUser(t.getId(), validUserEmail2);
		assertNotNull(t2);
		assertEquals(t.getId(), t2.getId());
		logger.debug("\tSuccess!");
//		logger.debug("Post-Addition Accounts:");
//		for (Account a : t2.getAccounts()) {
//			logger.debug("\t" + a.getEmail() + "(" + a.getId() + ")");
//		}
		
		// Test removal of the same user
		logger.debug("Removing user: " + validUserEmail2);
		Template t3 = client.removeTemplateUser(t.getId(), validUserEmail2);
		assertNotNull(t3);
		assertEquals(t.getId(), t3.getId());
		logger.debug("\tSuccess!");
//		logger.debug("Post-Removal Accounts:");
//		for (Account a : t3.getAccounts()) {
//			logger.debug("\t" + a.getEmail() + "(" + a.getId() + ")");
//		}
	}
}
