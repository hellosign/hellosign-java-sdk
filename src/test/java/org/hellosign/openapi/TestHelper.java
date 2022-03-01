package org.hellosign.openapi;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.mockito.Mockito;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import static org.mockito.Mockito.*;

public class TestHelper {
    public static InputStream readFileFromResource(String fileName) {
        return TestHelper.class.getClassLoader().getResourceAsStream("fixtures/" + fileName + ".json");
    }

    public static <T> T getFixtureData(Class<T> classType, String topLevelFieldName) throws IOException {
        ObjectMapper objectMapper = JSON.getDefault().getMapper();
        JsonNode jsonNode = objectMapper.readTree(readFileFromResource(classType.getSimpleName()));
        return objectMapper.convertValue(jsonNode.get(topLevelFieldName), classType);
    }

    public static ApiClient setUpMock(int statusCode, Object obj) throws ApiException {
        ApiClient apiClient = Mockito.spy(ApiClient.class);
        ApiResponse response = new ApiResponse<>(statusCode, new HashMap<>(), obj);
        doReturn(response).when(apiClient).invokeAPI(any(), any(), any(), any(), any(), any(), any(), any(), any(), any(), any(), any(), eq(false));
        return apiClient;
    }
}
