package com.hellosign.sdk;

import static org.junit.Assert.assertEquals;

import com.hellosign.sdk.resource.SignatureRequest;
import java.util.Date;
import org.json.JSONObject;
import org.junit.Test;

public class AbstractResourceTest {

    @Test
    public void testCreatedAt() throws HelloSignException {
        JSONObject obj = new JSONObject("{\"signature_request\": {\"created_at\": 1644606159}}");
        TestResource request = new TestResource(obj);
        Date created_at = request.getCreatedAt();
        long timestamp = created_at.getTime();

        TestResource request2 = new TestResource();
        request2.set(SignatureRequest.SIGREQ_CREATED_AT, 1644606159);
        Date created_at2 = request2.getCreatedAt();
        long timestamp2 = created_at2.getTime();

        assertEquals(timestamp, timestamp2);

        TestResource request3 = new TestResource();
        Date date = new Date(1644606159 * 1000L);
        request3.set(SignatureRequest.SIGREQ_CREATED_AT, date);
        Date created_at3 = request3.getCreatedAt();
        long timestamp3 = created_at3.getTime();

        assertEquals(timestamp, timestamp3);
    }
}
