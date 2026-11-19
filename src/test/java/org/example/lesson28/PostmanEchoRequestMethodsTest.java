package org.example.lesson28;

import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PostmanEchoRequestMethodsTest {
    @Test
    @DisplayName("GET Request: код 200 и все поля ответа")
    void getRequest() throws IOException {
        verifyRequest("GET Request");
    }

    @Test
    @DisplayName("POST Raw Text: код 200 и все поля ответа")
    void postRawText() throws IOException {
        verifyRequest("POST Raw Text");
    }

    @Test
    @DisplayName("POST Form Data: код 200 и все поля ответа")
    void postFormData() throws IOException {
        verifyRequest("POST Form Data");
    }

    @Test
    @DisplayName("PUT Request: код 200 и все поля ответа")
    void putRequest() throws IOException {
        verifyRequest("PUT Request");
    }

    @Test
    @DisplayName("PATCH Request: код 200 и все поля ответа")
    void patchRequest() throws IOException {
        verifyRequest("PATCH Request");
    }

    @Test
    @DisplayName("DELETE Request: код 200 и все поля ответа")
    void deleteRequest() throws IOException {
        verifyRequest("DELETE Request");
    }

    private static void verifyRequest(String name) throws IOException {
        Map<String, Object> definition = readRequest(name);
        Map<String, String> requestHeaders = pairs(definition.get("header"));
        Map<String, Object> url = object(definition.get("url"));
        String requestUrl = (String) url.get("raw");
        assertEquals("https", URI.create(requestUrl).getScheme());
        assertEquals("postman-echo.com", URI.create(requestUrl).getHost());

        Map<String, String> args = pairs(url.get("query"));
        Map<String, String> form = new LinkedHashMap<>();
        String wireBody = null;
        if (definition.containsKey("body")) {
            Map<String, Object> body = object(definition.get("body"));
            if ("raw".equals(body.get("mode"))) {
                wireBody = (String) body.get("raw");
            } else {
                assertEquals("urlencoded", body.get("mode"), "Неизвестный формат тела запроса");
                form = pairs(body.get("urlencoded"));
                wireBody = form.entrySet().stream()
                        .map(entry -> encode(entry.getKey()) + "=" + encode(entry.getValue()))
                        .collect(Collectors.joining("&"));
            }
        }

        RequestSpecification request = given()
                .config(RestAssuredConfig.config().httpClient(HttpClientConfig.httpClientConfig()
                        .setParam("http.connection.timeout", 15000)
                        .setParam("http.socket.timeout", 20000)))
                .redirects().follow(false)
                .headers(requestHeaders);
        if (!form.isEmpty()) {
            request.formParams(form);
        } else if (wireBody != null) {
            request.body(wireBody.getBytes(StandardCharsets.UTF_8));
        }
        Response response = request.when().request(Method.valueOf((String) definition.get("method")), requestUrl);

        Path output = Path.of("target", "echo-responses", name.replace(' ', '-') + ".json");
        Files.createDirectories(output.getParent());
        Files.writeString(output, response.asString(), StandardCharsets.UTF_8);
        response.then().statusCode(200).contentType(ContentType.JSON);

        Map<String, Object> expectedHeaders = new LinkedHashMap<>();
        expectedHeaders.put("host", "postman-echo.com");
        expectedHeaders.put("user-agent", requestHeaders.get("User-Agent"));
        expectedHeaders.put("accept", requestHeaders.get("Accept"));
        // Cloudflare нормализует Accept-Encoding на пути к серверу Echo.
        expectedHeaders.put("accept-encoding", List.of("br", "gzip"));
        expectedHeaders.put("x-forwarded-proto", "https");
        if (wireBody != null) {
            expectedHeaders.put("content-type", requestHeaders.get("Content-Type"));
            expectedHeaders.put("content-length", String.valueOf(wireBody.getBytes(StandardCharsets.UTF_8).length));
        }

        Map<String, Object> expectedBody = new LinkedHashMap<>();
        expectedBody.put("args", args);
        expectedBody.put("headers", expectedHeaders);
        expectedBody.put("url", requestUrl);
        if (wireBody != null) {
            expectedBody.put("data", form.isEmpty() ? wireBody : "");
            expectedBody.put("files", Map.of());
            expectedBody.put("form", form);
            expectedBody.put("json", form.isEmpty() ? null : form);
        }

        Map<String, Object> actualBody = new LinkedHashMap<>(response.jsonPath().getMap("$"));
        Map<String, Object> actualHeaders = new LinkedHashMap<>(object(actualBody.get("headers")));
        Object actualEncoding = actualHeaders.get("accept-encoding");
        assertTrue(actualEncoding instanceof String, "headers.accept-encoding должен быть строкой");
        actualHeaders.put("accept-encoding", Arrays.stream(((String) actualEncoding).split(",", -1))
                .map(String::trim).sorted().collect(Collectors.toList()));
        actualBody.put("headers", actualHeaders);

        // Map.equals сравнивает все ключи и вложенные значения, включая пустые объекты и null.
        // Лишнее, отсутствующее или неверное поле приводит к падению теста.
        assertEquals(expectedBody, actualBody, () -> name + ": полное тело ответа отличается\n"
                + response.asPrettyString());
    }

    private static Map<String, Object> readRequest(String name) throws IOException {
        try (InputStream input = PostmanEchoRequestMethodsTest.class
                .getResourceAsStream("/RequestMethods.postman_collection.json")) {
            assertNotNull(input, "Коллекция Postman должна быть включена в test resources");
            JsonPath collection = new JsonPath(input);
            assertEquals("Request Methods", collection.getString("item[0].name"));
            List<Map<String, Object>> items = collection.getList("item[0].item");
            assertEquals(6, items.size(), "В коллекции должны быть все шесть запросов Request Methods");
            return items.stream().filter(item -> name.equals(item.get("name")))
                    .map(item -> object(item.get("request"))).findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("В коллекции нет запроса: " + name));
        }
    }

    @SuppressWarnings("unchecked")
    private static Map<String, Object> object(Object value) {
        assertTrue(value instanceof Map, "Ожидается JSON-объект");
        return (Map<String, Object>) value;
    }

    private static Map<String, String> pairs(Object value) {
        Map<String, String> result = new LinkedHashMap<>();
        if (value == null) {
            return result;
        }
        assertTrue(value instanceof List, "Ожидается список параметров коллекции");
        for (Object entry : (List<?>) value) {
            Map<String, Object> pair = object(entry);
            if (!Boolean.TRUE.equals(pair.get("disabled"))) {
                result.put((String) pair.get("key"), (String) pair.get("value"));
            }
        }
        return result;
    }

    private static String encode(String value) {
        return URLEncoder.encode(value, StandardCharsets.UTF_8);
    }
}
