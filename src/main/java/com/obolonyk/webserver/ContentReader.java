package com.obolonyk.webserver;

import java.io.*;

public class ContentReader {
    private String webAppPath;

    public ContentReader(String webAppPath) {
        this.webAppPath = webAppPath;
    }

    Reader readContent(String uri) throws FileNotFoundException {
        return readContent(new FileInputStream(new File(webAppPath, uri)));
    }

    Reader readContent(InputStream inputStream) {
        return new InputStreamReader(inputStream);
    }

}
