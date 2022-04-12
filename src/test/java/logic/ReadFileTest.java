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
    @DisplayName("Should show massage the file is pdf file.")
    public void performWithPDFTest(@TempDir Path tempDir) throws IOException {
        Path pdfFile = Files.createFile(tempDir.resolve("text/text.pdf"));
        tempFileCreator.createTempPdfFile(pdfFile);
        FileStrategy fileStrategy = new ReadFile(pdfFile.toFile());
        ResultData resultData = fileStrategy.perform();
        assertEquals("file-is-based-on-xml", resultData.getResultMassage(),"Should return string: files-copied");
    }
}