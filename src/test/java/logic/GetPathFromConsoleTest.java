package logic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class GetPathFromConsoleTest {
    private GetPathFromConsole getPathFromConsole;
    @Mock
    KeyboardReader keyboardReader;

    @BeforeEach
    public void setup(){
        getPathFromConsole = new GetPathFromConsole();
    }

    @Test
    @DisplayName("Should return path to directory from user")
    public void getPathFromUserTest(@TempDir Path tempDir) throws IOException {
        Path directory = Files.createDirectory(tempDir.resolve("test"));
        Reader reader = new StringReader(directory.toFile().getAbsolutePath());
        BufferedReader bufferedReader = new BufferedReader(reader);
        KeyboardReader keyboardReader = new KeyboardReader(bufferedReader);
        File fileResult = getPathFromConsole.getPathFromUser(keyboardReader);
        assertEquals(fileResult.toString(), directory.toString());
    }

}