package com.obolonyk.webserver;

import com.obolonyk.webserver.io.ContentReader;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ContentReaderITest {
    @Test
    @DisplayName("test Content Reader On File")
    void testContentReaderOnFile() throws IOException {
        String expected = "Hello";
        String uri = "/text.txt";
        ContentReader contentReader = new ContentReader("src/test/resources");
        InputStream reader = contentReader.readContent(uri);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(reader));
        String actual = bufferedReader.readLine();
        assertEquals(expected, actual);
    }
}
