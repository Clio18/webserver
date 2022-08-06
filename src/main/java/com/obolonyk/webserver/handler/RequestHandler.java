package com.obolonyk.webserver.handler;

import com.obolonyk.webserver.io.ContentReader;
import com.obolonyk.webserver.io.RequestParser;
import com.obolonyk.webserver.io.ResponseWriter;
import com.obolonyk.webserver.entity.HttpStatus;
import com.obolonyk.webserver.entity.Request;
import com.obolonyk.webserver.entity.Response;

import java.io.*;

public class RequestHandler {
    private BufferedReader bufferedReader;
    private OutputStream outputStream;
    private ContentReader contentReader;

    public RequestHandler(BufferedReader bufferedReader, OutputStream outputStream, ContentReader contentReader) {
        this.bufferedReader = bufferedReader;
        this.outputStream = outputStream;
        this.contentReader = contentReader;
    }

    public void handle() throws IOException {
        Request request = RequestParser.parseRequest(bufferedReader);
        InputStream reader = contentReader.readContent(request.getUri());
        ResponseWriter responseWriter = new ResponseWriter();
        if (reader!=null){
        Response response = new Response();
        response.setHttpStatus(HttpStatus.OK);

        response.setContent(reader);
        responseWriter.writeResponse(outputStream, response);
        } else {
            responseWriter.writeErrorResponse(outputStream, HttpStatus.NOT_FOUND);
        }
    }
}
