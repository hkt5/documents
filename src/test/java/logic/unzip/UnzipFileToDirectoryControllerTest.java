package logic.unzip;

import logic.GetPathFromConsole;
import logic.TempFileCreator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class UnzipFileToDirectoryControllerTest {
    TempFileCreator tempFileCreator;

    @BeforeEach
    public void setup(){
        tempFileCreator = new TempFileCreator();
    }

    @Test
    @DisplayName("Should unzip docx file to directory")
    public void unzipTest(@TempDir Path tempDir) throws IOException {
        Path directory = Files.createDirectory(tempDir.resolve("text"));
        Path destinationDirectory = Files.createDirectory(tempDir.resolve("destination"));
        Path docxFile = Files.createFile(tempDir.resolve("text/text.docx"));
        tempFileCreator.createTempDocxFile(docxFile);
        UnzipFileToDirectoryable unzipFileToDirectoryable = new UnzipFileToDirectoryController();
        unzipFileToDirectoryable.unzip(docxFile, destinationDirectory);
        assertEquals(11, destinationDirectory.toFile().listFiles().length, "Should return 11 files");

    }
}