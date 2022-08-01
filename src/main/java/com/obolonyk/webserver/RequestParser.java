package com.obolonyk.webserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RequestParser {

    public static Request parseRequest(BufferedReader reader) throws IOException {
        Request request = new Request();
        injectUriAndMethod(reader, request);
        injectHeaders(reader, request);
        return request;
    }

    static void injectUriAndMethod(BufferedReader reader, Request request) throws IOException {
        String line = reader.readLine();
        String[] strings = line.split(" ");
        if (strings[0].equals(HttpMethod.GET.toString())) {
            request.setHttpMethod(HttpMethod.GET);
        }
        request.setUri(strings[1]);
    }

    static void injectHeaders(BufferedReader reader, Request request) throws IOException {
        Map<String, String> headers = new HashMap<>();
        for (String line = reader.readLine(); line != null; line = reader.readLine()) {
            String[] strings = line.split(":");
            headers.put(strings[0], strings[1]);
        }
        request.setHeaders(headers);
    }
}

