package com.obolonyk.webserver;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ContentReaderITest {
    @Test
    @DisplayName("test Content Reader On File")
    void testContentReaderOnFile() throws IOException {
        String expected = "Hello";
        String uri = "/text.txt";
        ContentReader contentReader = new ContentReader("src/test/resources");
        Reader reader = contentReader.readContent(uri);
        BufferedReader bufferedReader = new BufferedReader(reader);
        String actual = bufferedReader.readLine();
        assertEquals(expected, actual);
    }
}
