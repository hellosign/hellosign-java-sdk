package org.hellosign.openapi;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hellosign.openapi.model.SubFormFieldsPerDocumentBase;
import org.junit.Assert;
import org.junit.Test;

import java.io.FileInputStream;
import java.util.Iterator;
import java.util.Map;

public class SubFormFieldsPerDocumentTest {
    public final String packageNamePrefix = "org.hellosign.openapi.model.";

    @Test
    public void testSubFormFieldsPerDocumentBase() throws Exception {
        ObjectMapper mapper = JSON.getDefault().getMapper();
        JsonNode content = mapper.readTree(new FileInputStream("oas/test_fixtures//SubFormFieldsPerDocument.json"));
        Iterator<Map.Entry<String, JsonNode>> fields = content.fields();
        while (fields.hasNext()) {
            Map.Entry<String, JsonNode> kv = fields.next();
            String fieldName = kv.getKey();
            JsonNode expected = kv.getValue();
            SubFormFieldsPerDocumentBase base = mapper.convertValue(expected, SubFormFieldsPerDocumentBase.class);

            Assert.assertTrue(Class.forName(packageNamePrefix + fieldName).isInstance(base));

            String serialized = mapper.writeValueAsString(base);
            JsonNode actual = mapper.readTree(serialized);
            Assert.assertEquals(expected, actual);  // String comparison doesn't work due to json fields may be out of order
        }
    }
}
