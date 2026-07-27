package utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.APIResponse;

public final class JsonUtils {

    private static final ObjectMapper mapper = new ObjectMapper();

    private JsonUtils() {
    }

    public static <T> T fromJson(String json, Class<T> clazz) {
        try {
            return mapper.readValue(json, clazz);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String getValueFromJson(APIResponse response, String key) {
        String responseBody = response.text();

        JsonNode root = null;
        try {
            root = mapper.readTree(responseBody);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return root.get(key).asText();
    }

    public static void printResponseBody(APIResponse response) {
        try {
            String responseBody = response.text();
            Object prettyJson = mapper.readValue(responseBody, Object.class);
            System.out.println("Response JSON:\n" + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(prettyJson));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}