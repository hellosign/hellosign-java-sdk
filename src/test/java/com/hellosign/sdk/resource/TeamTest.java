package com.hellosign.sdk.resource;

import static org.junit.Assert.*;

import java.net.HttpURLConnection;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hellosign.sdk.AbstractHelloSignTest;
import com.hellosign.sdk.HelloSignClient;
import com.hellosign.sdk.HelloSignException;
import com.hellosign.sdk.resource.support.types.RoleType;

/**
 * Exercise the Team class and HelloSignClient team operations.
 * 
 * @author "Chris Paul (chris@hellosign.com)"
 */
public class TeamTest extends AbstractHelloSignTest {
	
	private static final Logger logger = LoggerFactory.getLogger(TeamTest.class);

	@Test
	public void testTeam() throws Exception {
		
		if (!isHelloSignAvailable()) {
			logger.debug("No API access, skipping tests...");
			return;
		}
		
		logger.debug("Retrieving current user's team...");
		HelloSignClient client = new HelloSignClient(auth);
		Team originalTeam = null;
		boolean teamCreated = false;
		
		try {
			originalTeam = client.getTeam();
			assertNotNull(originalTeam);

			// Determine if we're the owner of the current team
			// If not, we can skip these tests because there is no way to
			// do any of them without owner status and you cannot currently 
			// leave a team.
			for (Account account : originalTeam.getAccounts()) {
				if (validApiKey.equals(account.getEmail())) {
					if (RoleType.MEMBER.equals(account.getRoleCode())) {
						logger.debug("\tCannot test teams for user " + validApiKey + ". Must be owner of team.");
						return;
					} else {
						break;
					}
				}
			}
		} catch (HelloSignException ex) {
			// It's likely the team doesn't exist yet, so let's create one
		}
		
		String updatedName = "";
		if (originalTeam == null) {
			logger.debug("\tNo team found, creating one...");
			originalTeam = client.createTeam(teamName);
			assertNotNull(originalTeam);
			assertTrue(teamName.equals(originalTeam.getName()));
			teamCreated = true;
			updatedName = originalTeam.getName();
		}
		logger.debug("\tSuccess!");
		
		// Update the team name
		String newName = (updatedName != "" ? updatedName + " " : "") + "UPDATED";
		logger.debug("Updating team name to: " + newName);
		Team updatedTeam = client.updateTeamName(newName);
		assertNotNull(updatedTeam);
		assertTrue(newName.equals(updatedTeam.getName()));
		logger.debug("\tSuccess!");
		
		// Revert the team name
		logger.debug("Reverting team name to: " + originalTeam.getName());
		updatedTeam = client.updateTeamName(originalTeam.getName());
		assertNotNull(updatedTeam);
		assertTrue(originalTeam.getName().equals(updatedTeam.getName()));
		logger.debug("\tSuccess!");

		if (validUserEmail2 == null || validUserEmail2.equals("")) {
			logger.debug("No valid user provided, skipping remainder of tests.");
			return;
		}
		
		// Invite new user to team
//		logger.debug("Inviting new user to team: " + validUserEmail2);
//		boolean found = false;
//		try {
//			updatedTeam = client.inviteTeamMember(validUserEmail2);
//			assertNotNull(updatedTeam);
//			for (Account account : updatedTeam.getInvitedAccounts()) {
//				if (validUserEmail2.equals(account.getEmail())) {
//					found = true;
//					break;
//				}
//			}
//			assertTrue(found);
//			logger.debug("\tUser has been invited to join the team.");
//		} catch (HelloSignException ex) {
//			if ("This account is already on your team".equals(ex.getMessage())) {
//				logger.debug("\tCannot perform test, user is already a member of the team.");
//			} else {
//				throw ex;
//			}
//		}
//		
//		// Remove user from team if we invited them
//		if (found) {
//			logger.debug("Removing user from team: " + validUserEmail2);
//			updatedTeam = client.removeTeamMember(validUserEmail2);
//			assertNotNull(updatedTeam);
//			found = false;
//			for (Account account : updatedTeam.getAccounts()) {
//				if (validUserEmail2.equals(account.getEmail())) {
//					found = true;
//					break;
//				}
//			}
//			if (!found) {
//				for (Account account : updatedTeam.getInvitedAccounts()) {
//					if (validUserEmail2.equals(account.getEmail())) {
//						found = true;
//						break;
//					}
//				}
//			}
//			assertFalse(found);
//			logger.debug("\tSuccess!");
//		}
//		
		// We don't want to destroy the team if it has more than one member
		if (updatedTeam.getAccounts().size() != 1) {
			logger.debug("Skipping remainder of tests...");
			return;
		}
		
		// We don't want to destroy the team if we didn't create it for the test
		if (!teamCreated) {
			logger.debug("Skipping remainder of tests...");
			return;
		}
		
		// Test destroying team
		logger.debug("Testing destoy team...");
		assertTrue(HttpURLConnection.HTTP_OK == client.destroyTeam());
		logger.debug("\tSuccess!");
	}
}
