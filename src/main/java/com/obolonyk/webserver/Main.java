package com.obolonyk.webserver;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Server server = new Server();
        server.setPort(3005);
        server.start();
    }
}
