package logic.metaDataReader;

import logic.TempFileCreator;
import org.apache.poi.ooxml.POIXMLProperties;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DocxMetaDataReaderTest {

    private TempFileCreator tempFileCreator;
    private DocxMetaDataReader docxMetaDataReader;

    @BeforeEach
    public void setup() {
        tempFileCreator = new TempFileCreator();
        docxMetaDataReader = new DocxMetaDataReader();
    }

    @Test
    @DisplayName("Should return 'Test keyword' keyword meta data")
    void getMataDataFromDocxTest(@TempDir Path tempDir) throws IOException {
        Path docxFile = Files.createFile(tempDir.resolve("test.docx"));
        String testKeyWords = "Test keyword";
        tempFileCreator.createTempDocxFile(docxFile);
        FileInputStream fileInputStream = new FileInputStream(docxFile.toFile());
        XWPFDocument xwpfDocument = new XWPFDocument(fileInputStream);
        POIXMLProperties props = xwpfDocument.getProperties();
        POIXMLProperties.CoreProperties coreProp=props.getCoreProperties();
        coreProp.setKeywords(testKeyWords);
        FileOutputStream out = new FileOutputStream(docxFile.toFile());
        xwpfDocument.write(out);
        out.close();

        Map<String, Optional<Object>> metaDataToTest = docxMetaDataReader.getMataData(docxFile.toFile());
        String resultKeywords = metaDataToTest.get("Keywords").isPresent() ? (String) metaDataToTest.get("Keywords").get(): null;
        assertEquals(testKeyWords, resultKeywords);
    }

}