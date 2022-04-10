package logic;

import org.junit.jupiter.api.Test;
import java.io.*;
import static org.junit.jupiter.api.Assertions.*;

class KeyboardReaderTest {

    @Test
    public void readLineTest() {
        String expectedString = "Some text";
        Reader reader = new StringReader("Some text");
        BufferedReader bufferedReader = new BufferedReader(reader);
        String actual = new KeyboardReader(bufferedReader).readLine();
        assertEquals(expectedString, actual);
    }
}