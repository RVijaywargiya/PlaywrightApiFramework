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

        JsonNode node = root.get(key);
        if (node == null) {
            throw new RuntimeException("Key '" + key + "' not found in response JSON");
        }
        return node.asText();
    }

    public static void printResponseBody(APIResponse response) {
        try {
            String responseBody = response.text();
            Object prettyJson = mapper.readValue(responseBody, Object.class);
            printPrettyJson(prettyJson);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static void printResponseBody(Object responseBody) {
        try {
            Object prettyJson = mapper.convertValue(responseBody, Object.class);
            printPrettyJson(prettyJson);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void printPrettyJson(Object value) throws JsonProcessingException {
        System.out.println("Response JSON:\n" + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(value));
    }
}