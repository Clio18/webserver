package com.obolonyk.webserver;

import com.obolonyk.webserver.entity.HttpStatus;
import com.obolonyk.webserver.entity.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;

import static com.obolonyk.webserver.ResponseWriter.writeErrorResponse;
import static com.obolonyk.webserver.ResponseWriter.writeResponse;
import static org.junit.jupiter.api.Assertions.*;

class ResponseWriterTest {
    @Test
    @DisplayName("test WriteResponse")
    void testWriteResponse() throws IOException {
        String content = "Hello\r\n";
        String expectedResponse = "HTTP/1.1 200 OK\r\n\r\n" + content;
        Response response = new Response();
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
             Writer writer = new OutputStreamWriter(byteArrayOutputStream);
             Reader reader = new InputStreamReader(new ByteArrayInputStream(content.getBytes()));) {
            response.setHttpStatus(HttpStatus.OK);
            response.setContent(reader);
            writeResponse(writer, response);
            writer.flush();
            String actualResponse = byteArrayOutputStream.toString();
            assertEquals(expectedResponse, actualResponse);
        }
    }

    @Test
    @DisplayName("test WriteErrorResponse")
    void testWriteErrorResponse() throws IOException {
        String expectedResponse = "HTTP/1.1 "  + HttpStatus.NOT_FOUND.getCode() + " "
                + HttpStatus.NOT_FOUND.getMessage() + "\r\n";
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
             Writer writer = new OutputStreamWriter(byteArrayOutputStream)) {
            writeErrorResponse(writer, HttpStatus.NOT_FOUND);
            writer.flush();
            String actualResponse = byteArrayOutputStream.toString();
            assertEquals(expectedResponse, actualResponse);
        }
    }
}