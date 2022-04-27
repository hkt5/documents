package logic.unzip;

import logic.ListFileCreator.ListOfFilesFromPathCreator;
import logic.TempFileCreator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class UnzipFileToDirectoryControllerTest {
    TempFileCreator tempFileCreator;
    ListOfFilesFromPathCreator listOfFilesFromPathCreator;
    List<String> controlListFile;

    @BeforeEach
    public void setup(){
        tempFileCreator = new TempFileCreator();
        listOfFilesFromPathCreator = new ListOfFilesFromPathCreator();
        controlListFile = new ArrayList<>(
                (Arrays.asList(
                        "[Content_Types].xml",
                        "app.xml",
                        "core.xml",
                        ".rels",
                        "document.xml",
                        "settings.xml",
                        "document.xml.rels")
                ));
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
        List<String> listOfUnzipFileName = listOfFilesFromPathCreator.getListOfFile(destinationDirectory.toString()).stream()
                .map(file -> file.getName())
                .collect(Collectors.toList());
        assertEquals(controlListFile, listOfUnzipFileName, "Unzip file should the same");
        assertEquals(7, listOfUnzipFileName.size(), "Should return 7 files");
    }
}