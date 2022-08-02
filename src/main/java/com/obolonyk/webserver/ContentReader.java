package com.obolonyk.webserver;

import java.io.*;

public class ContentReader {
    private String webAppPath;

    public ContentReader(String webAppPath) {
        this.webAppPath = webAppPath;
    }

    Reader readContent(String uri) throws FileNotFoundException {
        File file = new File(webAppPath, uri);
        if (file.exists()) {
            return readContent(new FileInputStream(file));
        } else {
            return null;
        }
    }

    Reader readContent(InputStream inputStream) {
        return new InputStreamReader(inputStream);
    }

}
