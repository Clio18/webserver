package com.obolonyk.webserver;

import com.obolonyk.webserver.entity.HttpStatus;
import com.obolonyk.webserver.entity.Request;
import com.obolonyk.webserver.entity.Response;

import java.io.*;

public class RequestHandler {
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private ContentReader contentReader;

    public RequestHandler(BufferedReader bufferedReader, BufferedWriter bufferedWriter, ContentReader contentReader) {
        this.bufferedReader = bufferedReader;
        this.bufferedWriter = bufferedWriter;
        this.contentReader = contentReader;
    }

    void handle() throws IOException {
        Request request = RequestParser.parseRequest(bufferedReader);
        Reader reader = contentReader.readContent(request.getUri());
        ResponseWriter responseWriter = new ResponseWriter();
        if (reader!=null){
        Response response = new Response();
        response.setHttpStatus(HttpStatus.OK);
        response.setContent(reader);
        responseWriter.writeResponse(bufferedWriter, response);
        } else {
            responseWriter.writeErrorResponse(bufferedWriter, HttpStatus.NOT_FOUND);
        }
    }
}
