package logic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CopyFileTest {

    private ListOfFileCreatorFromPath listOfFileCreatorFromPath;
    private CopyFile copyFile;

    @BeforeEach
    public void setup() {
        listOfFileCreatorFromPath = new ListOfFileCreatorFromPath();
        copyFile = new CopyFile();
    }

    @Test
    @DisplayName("Should copy List<File> to directory.")
    public void performTest(@TempDir Path tempDir) throws IOException {
        List<File> tempListOfFile = createTempListOfFile(tempDir);
        Path destinationDirectory = Files.createDirectory(tempDir.resolve("text/destination"));
        boolean copiedFiles = copyFile.perform(tempListOfFile, destinationDirectory.toFile());
        assertEquals(true, copiedFiles,"Should return 3 files");

    }

    private List<File> createTempListOfFile(Path tempDir) throws IOException {
        Path directory = Files.createDirectory(tempDir.resolve("text"));
        Path pdfFile = Files.createFile(tempDir.resolve("text/text.pdf"));
        Path docxFile = Files.createFile(tempDir.resolve("text/text.docx"));
        Path xlsxFile = Files.createFile(tempDir.resolve("text/test.xlsx"));
        Path txtFile = Files.createFile(tempDir.resolve("text/test.txt"));

        return listOfFileCreatorFromPath.getListOfFile(directory.toString());
    }

}