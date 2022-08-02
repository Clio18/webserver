package com.obolonyk.webserver;

import com.obolonyk.webserver.entity.Request;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import static com.obolonyk.webserver.RequestParser.*;
import static org.junit.jupiter.api.Assertions.*;

class RequestParserTest {
    private String req;
    private Map<String, String> expectedHeaders;

    @BeforeEach
    void init (){
        req = "GET /hello.htm HTTP/1.1\n" +
                "User-Agent: Mozilla/4.0 (compatible; MSIE5.01; Windows NT)\n" +
                "Host: www.tutorialspoint.com\n" +
                "Accept-Language: en-us\n" +
                "Accept-Encoding: gzip, deflate\n" +
                "Connection: Keep-Alive\r\n";

        expectedHeaders = new HashMap<>();
        expectedHeaders.put("User-Agent", "Mozilla/4.0 (compatible; MSIE5.01; Windows NT)\n");
        expectedHeaders.put("Host", "www.tutorialspoint.com\n");
        expectedHeaders.put("Accept-Language", "en-us\n");
        expectedHeaders.put("Accept-Encoding", "gzip, deflate\n");
        expectedHeaders.put("Connection", "Keep-Alive");
    }

    @Test
    @DisplayName("test InjectUriAndMethod And Check Result")
    void testInjectUriAndMethod () throws IOException {
        byte[] bytes = req.getBytes();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(bytes)));
        Request request = new Request();
        injectUriAndMethod(bufferedReader, request);
        String expectedHttpMethod = "GET";
        String expectedUri = "/hello.htm";
        assertEquals(expectedHttpMethod, request.getHttpMethod().toString());
        assertEquals(expectedUri, request.getUri());
    }

    @Test
    @DisplayName("test InjectHeaders And Check Result")
    void testInjectHeaders () throws IOException {
        byte[] bytes = req.getBytes();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(bytes)));
        Request request = new Request();
        bufferedReader.readLine();
        injectHeaders(bufferedReader, request);
        assertArrayEquals(expectedHeaders.keySet().toArray(), request.getHeaders().keySet().toArray());
    }

    @Test
    @DisplayName("test InjectUriAndMethod And Than Call InjectHeaders And Check Result")
    void testInjectUriAndMethodAndThanInjectHeaders () throws IOException {
        byte[] bytes = req.getBytes();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(bytes)));
        Request request = new Request();
        injectUriAndMethod(bufferedReader, request);
        injectHeaders(bufferedReader, request);
        String expectedHttpMethod = "GET";
        String expectedUri = "/hello.htm";
        assertEquals(expectedHttpMethod, request.getHttpMethod().toString());
        assertEquals(expectedUri, request.getUri());
        assertArrayEquals(expectedHeaders.keySet().toArray(), request.getHeaders().keySet().toArray());
    }

    @Test
    @DisplayName("test ParseRequest And Check Result")
    void testParseRequest () throws IOException {
        byte[] bytes = req.getBytes();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(bytes)));
        Request request = parseRequest(bufferedReader);
        String expectedHttpMethod = "GET";
        String expectedUri = "/hello.htm";
        assertEquals(expectedHttpMethod, request.getHttpMethod().toString());
        assertEquals(expectedUri, request.getUri());
        assertArrayEquals(expectedHeaders.keySet().toArray(), request.getHeaders().keySet().toArray());
    }

    @Test
    @DisplayName("test FillMapHeaders With Normal Key Value Header")
    void testFillMapHeadersWithNormalKeyValueHeader(){
        String line = "User-Agent: Mozilla/4.0 (compatible MSIE5.01; Windows NT)\n";
        Map<String, String> expectedMap = new HashMap<>();
        Map<String, String> actualMap = new HashMap<>();
        expectedMap.put("User-Agent", ": Mozilla/4.0 (compatible MSIE5.01; Windows NT)\n");
        fillMapHeaders(actualMap, line);
        assertEquals(expectedMap, actualMap);
    }

    @Test
    @DisplayName("test FillMapHeaders With Normal Key Value Header")
    void testFillMapHeadersWithHeaderInOneLine(){
        String line = "User-Agent\n";
        Map<String, String> expectedMap = new HashMap<>();
        Map<String, String> actualMap = new HashMap<>();
        expectedMap.put("User-Agent\n", "");
        fillMapHeaders(actualMap, line);
        assertEquals(expectedMap, actualMap);
    }

}