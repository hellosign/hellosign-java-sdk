package com.hellosign.sdk.resource;

import static org.junit.Assert.*;

import java.io.Serializable;
import java.util.Map;

import org.json.JSONObject;
import org.junit.Test;

import com.hellosign.sdk.AbstractHelloSignTest;
import com.hellosign.sdk.HelloSignException;

public class SignatureRequestMetadataTest extends AbstractHelloSignTest {

	@Test
	public void test() throws HelloSignException {
		JSONObject json = getExpectedJSONResponse();
		SignatureRequest sr = new SignatureRequest(json);
		assertEquals(sr.getMetadata("client_id"), "1234");
		sr.addMetadata("new!", "Hello, world!");
		Map<String, Serializable> fields = sr.getPostFields();
		assertTrue(areFieldsEqual(getExpectedFields(), fields));
	}

}
