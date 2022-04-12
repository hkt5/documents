package logic;

import data.ResultData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


import static org.junit.jupiter.api.Assertions.*;

class ReadFileTest {
    private TempFileCreator tempFileCreator;
    private FileStrategy fileStrategy;

    @BeforeEach
    public void setup() {
        tempFileCreator = new TempFileCreator();
    }

    @Test
    @DisplayName("Should show massage if the file is pdf file.")
    public void performWithPDFTest(@TempDir Path tempDir) throws IOException {
        Path pdfFile = Files.createFile(tempDir.resolve("text.pdf"));
        tempFileCreator.createTempPdfFile(pdfFile);
        FileStrategy fileStrategy = new ReadFile(pdfFile.toFile());
        ResultData resultData = fileStrategy.perform();
        assertEquals("file-is-based-on-xml", resultData.getResultMassage(),"Should return string: file-is-based-on-xml");
    }

    @Test
    @DisplayName("Should show massage if docx file is xml file.")
    public void performWithDocxTest(@TempDir Path tempDir) throws IOException {
        Path docxFile = Files.createFile(tempDir.resolve("text.docx"));
        tempFileCreator.createTempDocxFile(docxFile);
        FileStrategy fileStrategy = new ReadFile(docxFile.toFile());
        ResultData resultData = fileStrategy.perform();
        assertEquals("file-is-based-on-xml", resultData.getResultMassage(),"Should return string: file-is-based-on-xml");
    }

    @Test
    @DisplayName("Should show massage if docx file is xml file.")
    public void performWithXlsxTest(@TempDir Path tempDir) throws IOException {
        Path xlsxFile = Files.createFile(tempDir.resolve("text.xlsx"));
        tempFileCreator.createTempXlsXFile(xlsxFile);
        FileStrategy fileStrategy = new ReadFile(xlsxFile.toFile());
        ResultData resultData = fileStrategy.perform();
        assertEquals("file-is-based-on-xml", resultData.getResultMassage(),"Should return string: file-is-based-on-xml");
    }

    @Test
    @DisplayName("Should show massage if txt file is not xml file.")
    public void performWithTxtTest(@TempDir Path tempDir) throws IOException {
        Path txtFile = Files.createFile(tempDir.resolve("text.txt"));
        tempFileCreator.createTempTxtFile(txtFile);
        FileStrategy fileStrategy = new ReadFile(txtFile.toFile());
        ResultData resultData = fileStrategy.perform();
        assertEquals("file-not-is-based-on-xml", resultData.getResultMassage(),"Should return string: file-not-is-based-on-xml");
    }
}