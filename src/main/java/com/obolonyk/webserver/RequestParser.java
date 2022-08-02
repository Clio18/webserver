package com.obolonyk.webserver;

import com.obolonyk.webserver.entity.HttpMethod;
import com.obolonyk.webserver.entity.Request;

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
        for (String line = reader.readLine(); line!=null; line = reader.readLine()) {
            if (line.equals("")){
                return;
            }
            fillMapHeaders(headers, line);
        }
        request.setHeaders(headers);
    }

    static void fillMapHeaders(Map<String, String> headers, String line) {
        int index = line.indexOf(":");
        String keyLine = line;
        String headerLine = "";
        if (index !=-1) {
            byte[] lineBytes = line.getBytes();
            byte[] key = new byte[index];
            byte[] header = new byte[lineBytes.length - index];
            System.arraycopy(lineBytes, 0, key, 0, index);
            System.arraycopy(lineBytes, index, header, 0, header.length);
            keyLine = new String(key);
            headerLine = new String(header);
        }
        headers.put(keyLine, headerLine);
    }
}

