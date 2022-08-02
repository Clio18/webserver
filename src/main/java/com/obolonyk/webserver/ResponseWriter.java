package com.obolonyk.webserver;

import com.obolonyk.webserver.entity.HttpStatus;
import com.obolonyk.webserver.entity.Response;

import java.io.IOException;
import java.io.Writer;

public class ResponseWriter {

    static void writeResponse(Writer writer, Response response) throws IOException{
        writer.write("HTTP/1.1 " + response.getHttpStatus().getCode() + " "
                + response.getHttpStatus().getMessage() + "\r\n");
        writer.write("\r\n");
        char[] buffer = new char[1024];
        int bytesRead;
        // 500
        while ((bytesRead = response.getContent().read(buffer)) != -1) {
            writer.write(buffer, 0, bytesRead);
        }
    }

    static void writeErrorResponse(Writer writer, HttpStatus httpStatus) throws IOException {
        writer.write("HTTP/1.1 " + httpStatus.getCode() + " " + httpStatus.getMessage() + "\r\n");
    }
}
