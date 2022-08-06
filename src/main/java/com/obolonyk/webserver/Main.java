package com.obolonyk.webserver;

import com.obolonyk.webserver.io.ContentReader;
import com.obolonyk.webserver.server.Server;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Server server = new Server();
        server.setPort(3005);
        ContentReader contentReader = new ContentReader("src/main/resources");
        server.setContentReader(contentReader);
        server.start();
    }
}
