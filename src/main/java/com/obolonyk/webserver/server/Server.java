package com.obolonyk.webserver.server;

import com.obolonyk.webserver.io.ContentReader;
import com.obolonyk.webserver.handler.RequestHandler;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private int port;
    private ContentReader contentReader;

    public void setPort(int port) {
        this.port = port;
    }

    public void setContentReader(ContentReader contentReader) {
        this.contentReader = contentReader;
    }

    public void start() throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                try (Socket socket = serverSocket.accept();
                     BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                     OutputStream writer = socket.getOutputStream()) {
                    RequestHandler requestHandler = new RequestHandler(reader, writer, contentReader);
                    requestHandler.handle();
                }
            }
        }
    }
}
