package com.obolonyk.webserver.entity;

import java.io.Reader;

public class Response {
    private HttpStatus httpStatus;
    private Reader content;

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public void setContent(Reader content) {
        this.content = content;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public Reader getContent() {
        return content;
    }
}
