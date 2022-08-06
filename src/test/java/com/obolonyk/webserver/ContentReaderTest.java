package com.obolonyk.webserver;

import com.obolonyk.webserver.io.ContentReader;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

class ContentReaderTest {
    @Test
    @DisplayName("test ContentReader With InputStream")
    void testContentReaderWithInputStream() throws IOException {
        String expected = "Hello";
        ContentReader contentReader = new ContentReader("src/test/resources");
        InputStream reader = contentReader.readContent(new ByteArrayInputStream(expected.getBytes()));
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(reader));
        String actual = bufferedReader.readLine();
        assertEquals(expected, actual);
    }

}