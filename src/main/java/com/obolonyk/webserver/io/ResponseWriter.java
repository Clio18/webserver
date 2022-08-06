package com.obolonyk.webserver.io;

import com.obolonyk.webserver.entity.HttpStatus;
import com.obolonyk.webserver.entity.Response;

import java.io.IOException;
import java.io.OutputStream;


public class ResponseWriter {

    public static void writeResponse(OutputStream writer, Response response) throws IOException{
        String firstLine = "HTTP/1.1 " + response.getHttpStatus().getCode() + " "
                + response.getHttpStatus().getMessage() + "\r\n";
        writer.write(firstLine.getBytes());
        writer.write("\r\n".getBytes());
        byte[] buffer = new byte[1024];
        int bytesRead;
        // 500
        while ((bytesRead = response.getContent().read(buffer)) != -1) {
            writer.write(buffer, 0, bytesRead);
        }
    }

    public static void writeErrorResponse(OutputStream writer, HttpStatus httpStatus) throws IOException {
        String firstLine = "HTTP/1.1 " + httpStatus.getCode() + " " + httpStatus.getMessage() + "\r\n";
        writer.write(firstLine.getBytes());
    }
}
