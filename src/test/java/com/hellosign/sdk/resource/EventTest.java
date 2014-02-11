package com.hellosign.sdk.resource;

import static org.junit.Assert.*;

import org.junit.Test;

import com.hellosign.sdk.AbstractHelloSignTest;
import com.hellosign.sdk.HelloSignException;

/**
 * Exercises the Event object.
 * 
 * @author "Chris Paul (chris@hellosign.com)"
 */
public class EventTest extends AbstractHelloSignTest {

	@Test
	public void testEventHashing() throws HelloSignException {
		Event event = new Event(getExpectedJSONResponse());
		assertNotNull(event); 
		assertFalse(event.isValid(null));
		assertFalse(event.isValid(""));
		
		// This works for the JSON response in EventTest/expectedResponse.txt
		// Ensures that if the hash checking changes, we are notified in the API
		assertTrue(event.isValid("4e94adf59d17c417ce0d1d2cb34b953ba7a1eebe1ecbe31bb1c586af92af1e1d"));
	}
	
}
