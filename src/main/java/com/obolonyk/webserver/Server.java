package com.obolonyk.webserver;

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

    void start() throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true){
                Socket socket = serverSocket.accept();
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                RequestHandler requestHandler = new RequestHandler(reader, writer, contentReader);
                requestHandler.handle();
            }

        }


    }
}
