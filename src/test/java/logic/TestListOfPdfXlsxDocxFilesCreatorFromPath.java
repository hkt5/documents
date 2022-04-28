package logic;

import logic.ListFileCreator.ListOfPdfXlsxDocxFilesFromPathCreator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


class TestListOfPdfXlsxDocxFilesCreatorFromPath {

    private ListOfPdfXlsxDocxFilesFromPathCreator listOfFileCreatorFromPath;

    @BeforeEach
    public void setup(){
        listOfFileCreatorFromPath = new ListOfPdfXlsxDocxFilesFromPathCreator();
    }

    @Test
    @DisplayName("Should return list of correct file with extension (docx, xlsx, pdf) form the path")
    public void TestGetListOfFile(@TempDir Path tempDir) throws IOException {
        //given
        Path pdfFile = Files.createFile(tempDir.resolve("test.pdf"));
        Path docxFile = Files.createFile(tempDir.resolve("test.docx"));
        Path xlsxFile = Files.createFile(tempDir.resolve("test.xlsx"));
        Path txtFile = Files.createFile(tempDir.resolve("test.txt"));
        Path Directory = Files.createDirectory(tempDir.resolve("text"));
        Path pdfFileInDirectory = Files.createFile(tempDir.resolve("text/text.pdf"));
        Path docxFileInDirectory = Files.createFile(tempDir.resolve("text/text.docx"));

        //when
        List<File> listOfFile = listOfFileCreatorFromPath.getListOfFile(tempDir.toFile().getPath());
        //then
        assertEquals(5, listOfFile.size(), "Should return list of 5 correct files");
    }

    @Test
    @DisplayName("Should return empty list")
    public void TestGetListOfFile2() {
        //given
        String wrongPath = "C:/sdfs";
        //when
        List<File> listOfFile = listOfFileCreatorFromPath.getListOfFile(wrongPath);
        //then
        assertEquals(0, listOfFile.size(), "Should return empty list");
    }
}