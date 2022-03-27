package logic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;


import java.io.File;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


class ListOfFileCreatorFromPathTest {

    private ListOfFileCreatorFromPath listOfFileCreatorFromPath;
    @TempDir
    Path tempDir;

    @BeforeEach
    public void setup(){
        listOfFileCreatorFromPath = new ListOfFileCreatorFromPath();
    }

    @Test
    @DisplayName("Should return list of correct file with extension (docx, xlsx, pdf) form the path")
    public void getListOfFileTest() {
        //given
        File directory = new File(String.valueOf(tempDir), "test-directory/");
        File docx = new File(String.valueOf(tempDir), "test-directory/test.docx");
        File xlsx = new File(String.valueOf(tempDir), "test-directory/test.xlsx");
        File pdf = new File(String.valueOf(tempDir), "test-directory/test.pdf");
        File txt = new File(String.valueOf(tempDir), "test-directory/test.txt");
        //when
        List<File> listOfFile = listOfFileCreatorFromPath.getListOfFile(directory.getPath());
        //then
        assertEquals(3, listOfFile.size());
    }
}