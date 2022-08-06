package com.obolonyk.webserver.io;

import java.io.*;

public class ContentReader {
    private String webAppPath;

    public ContentReader(String webAppPath) {
        this.webAppPath = webAppPath;
    }

   public InputStream readContent(String uri) throws FileNotFoundException {
        File file = new File(webAppPath, uri);
        if (file.exists()) {
            return new FileInputStream(file);
        } else {
            return null;
        }
    }

   public InputStream readContent(InputStream inputStream){
        return inputStream;
    }

}
