package com.obolonyk.webserver;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ServerTest {

    @Test
    void test() throws IOException {
        Server server = new Server();
        server.setPort(3005);
        ContentReader contentReader = new ContentReader("src/main/resources");
        server.setContentReader(contentReader);
        server.start();
    }

}