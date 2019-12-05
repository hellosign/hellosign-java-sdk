package com.hellosign.sdk;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import com.hellosign.sdk.resource.EmbeddedRequest;
import com.hellosign.sdk.resource.SignatureRequest;
import com.hellosign.sdk.resource.TemplateDraft;
import com.hellosign.sdk.resource.UnclaimedDraft;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;

public class PostFieldsTest {

    @Test
    public void testSignatureRequest() throws Exception {
        SignatureRequest req = new SignatureRequest();
        req.addFileUrl("http://www.orimi.com/pdf-test.pdf");
        req.addSigner("chris@hellosign.com", "Chris");
        req.setTestMode(true);
        req.addMetadata("test_key", "test_value");
        Map<String, String> fields = new HashMap<>();
        fields.put("Field A", "Hello");
        fields.put("Field B", "World!");
        req.setCustomFields(fields);

        Map<String, Serializable> postFields = req.getPostFields();
        assertEquals("chris@hellosign.com", postFields.get("signers[0][email_address]"));
        assertEquals("test_value", postFields.get("metadata[test_key]"));
        assertEquals(true, postFields.get("test_mode"));
        assertEquals(
            "[{\"name\":\"Field A\",\"value\":\"Hello\"},{\"name\":\"Field B\",\"value\":\"World!\"}]",
            postFields.get("custom_fields"));
        assertEquals("Chris", postFields.get("signers[0][name]"));
        assertEquals("http://www.orimi.com/pdf-test.pdf", postFields.get("file_url[0]"));
    }

    @Test
    public void testEmbeddedSignatureRequest() throws Exception {
        SignatureRequest req = new SignatureRequest();
        req.addFileUrl("http://www.orimi.com/pdf-test.pdf");
        req.addSigner("chris@hellosign.com", "Chris");
        req.setTestMode(true);
        req.addMetadata("test_key", "test_value");
        Map<String, String> fields = new HashMap<>();
        fields.put("Field A", "Hello");
        fields.put("Field B", "World!");
        req.setCustomFields(fields);

        EmbeddedRequest embeddedReq = new EmbeddedRequest("034fb51064187cf28e4aad1c2533ad8f", req);

        Map<String, Serializable> postFields = embeddedReq.getPostFields();
        assertEquals("chris@hellosign.com", postFields.get("signers[0][email_address]"));
        assertEquals("test_value", postFields.get("metadata[test_key]"));
        assertEquals(true, postFields.get("test_mode"));
        assertEquals(
            "[{\"name\":\"Field A\",\"value\":\"Hello\"},{\"name\":\"Field B\",\"value\":\"World!\"}]",
            postFields.get("custom_fields"));
        assertEquals("Chris", postFields.get("signers[0][name]"));
        assertEquals("034fb51064187cf28e4aad1c2533ad8f", postFields.get("client_id"));
        assertEquals("http://www.orimi.com/pdf-test.pdf", postFields.get("file_url[0]"));
    }

    @Test
    public void testCustomFieldsOnEmbeddedRequest() throws Exception {
        SignatureRequest req = new SignatureRequest();
        EmbeddedRequest embeddedReq = new EmbeddedRequest(null, req);

        Map<String, String> fields = new HashMap<>();
        fields.put("Field A", "Hello");
        fields.put("Field B", "World!");
        req.setCustomFields(fields);

        Map<String, Serializable> postFields = embeddedReq.getPostFields();
        assertEquals(
            "[{\"name\":\"Field A\",\"value\":\"Hello\"},{\"name\":\"Field B\",\"value\":\"World!\"}]",
            postFields.get("custom_fields"));
    }

    @Test
    public void testEmbeddedUnclaimedDraft() throws Exception {
        UnclaimedDraft draft = new UnclaimedDraft();
        draft.setRequesterEmail("chris@hellosign.com");

        draft.setTestMode(true);
        draft.addMetadata("test_key", "test_value");
        Map<String, String> fields = new HashMap<>();
        fields.put("Field A", "Hello");
        fields.put("Field B", "World!");
        draft.setCustomFields(fields);

        EmbeddedRequest embeddedReq = new EmbeddedRequest("034fb51064187cf28e4aad1c2533ad8f",
            draft);

        Map<String, Serializable> postFields = embeddedReq.getPostFields();
        assertEquals("chris@hellosign.com", postFields.get("requester_email_address"));
        assertEquals(true, postFields.get("test_mode"));
        assertEquals(
            "[{\"name\":\"Field A\",\"value\":\"Hello\"},{\"name\":\"Field B\",\"value\":\"World!\"}]",
            postFields.get("custom_fields"));
        assertEquals("034fb51064187cf28e4aad1c2533ad8f", postFields.get("client_id"));
    }

    @Test
    public void testEmbeddedUnclaimedDraftFromSigRequest() throws Exception {
        SignatureRequest req = new SignatureRequest();
        req.addFileUrl("http://www.orimi.com/pdf-test.pdf");
        req.setTestMode(true);
        req.addMetadata("test_key", "test_value");
        Map<String, String> fields = new HashMap<>();
        fields.put("Field A", "Hello");
        fields.put("Field B", "World!");
        req.setCustomFields(fields);

        UnclaimedDraft draft = new UnclaimedDraft(req);
        draft.setRequesterEmail("chris@hellosign.com");

        EmbeddedRequest embeddedReq = new EmbeddedRequest("034fb51064187cf28e4aad1c2533ad8f",
            draft);

        Map<String, Serializable> postFields = embeddedReq.getPostFields();
        assertEquals("chris@hellosign.com", postFields.get("requester_email_address"));
        assertEquals(true, postFields.get("test_mode"));
        assertEquals(
            "[{\"name\":\"Field A\",\"value\":\"Hello\"},{\"name\":\"Field B\",\"value\":\"World!\"}]",
            postFields.get("custom_fields"));
        assertEquals("034fb51064187cf28e4aad1c2533ad8f", postFields.get("client_id"));
    }

    @Test
    public void testTemplateDraftDropsCustomFields() throws Exception {
        TemplateDraft draft = new TemplateDraft();
        Map<String, String> fields = new HashMap<>();
        fields.put("Field A", "Hello");
        fields.put("Field B", "World!");
        draft.setCustomFields(fields);

        Map<String, Serializable> postFields = draft.getPostFields();
        assertNull(postFields.get("custom_fields"));
    }
}
