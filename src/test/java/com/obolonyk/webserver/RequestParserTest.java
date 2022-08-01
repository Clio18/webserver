package com.obolonyk.webserver;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

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
                "Connection: Keep-Alive";
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
        RequestParser.injectUriAndMethod(bufferedReader, request);
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
        RequestParser.injectHeaders(bufferedReader, request);
        assertArrayEquals(expectedHeaders.keySet().toArray(), request.getHeaders().keySet().toArray());
    }

    @Test
    @DisplayName("test InjectUriAndMethod And Than Call InjectHeaders And Check Result")
    void testInjectUriAndMethodAndThanInjectHeaders () throws IOException {
        byte[] bytes = req.getBytes();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(bytes)));
        Request request = new Request();
        RequestParser.injectUriAndMethod(bufferedReader, request);
        RequestParser.injectHeaders(bufferedReader, request);
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
        Request request = RequestParser.parseRequest(bufferedReader);
        String expectedHttpMethod = "GET";
        String expectedUri = "/hello.htm";
        assertEquals(expectedHttpMethod, request.getHttpMethod().toString());
        assertEquals(expectedUri, request.getUri());
        assertArrayEquals(expectedHeaders.keySet().toArray(), request.getHeaders().keySet().toArray());
    }

}