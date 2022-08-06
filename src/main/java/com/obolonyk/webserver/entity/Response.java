package com.obolonyk.webserver.entity;

import java.io.InputStream;

public class Response {
    private HttpStatus httpStatus;
    private InputStream content;

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public void setContent(InputStream content) {
        this.content = content;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public InputStream getContent() {
        return content;
    }
}
