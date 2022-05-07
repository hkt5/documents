package logic.metaDataReader;

import logic.TempFileCreator;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
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

import static org.junit.jupiter.api.Assertions.*;

class PdfMetaDataReaderTest {

    private TempFileCreator tempFileCreator;
    private PdfMetaDataReader pdfMetaDataReader;

    @BeforeEach
    public void setup() {
        tempFileCreator = new TempFileCreator();
        pdfMetaDataReader = new PdfMetaDataReader();
    }

    @Test
    @DisplayName("Should return 'Test keyword' keyword meta data")
    void getMataDataFromPdfTest(@TempDir Path tempDir) throws IOException {
        Path pdfFile = Files.createFile(tempDir.resolve("test.pdf"));
        String testKeyWords = "Test keyword";
        tempFileCreator.createTempPdfFile(pdfFile);
        PDDocument doc = PDDocument.load(pdfFile.toFile());
        PDDocumentInformation pdd = doc.getDocumentInformation();
        pdd.setKeywords(testKeyWords);
        doc.setDocumentInformation(pdd);
        doc.save(pdfFile.toFile());
        doc.close();

        Map<String, Optional<Object>> metaDataToTest = pdfMetaDataReader.getMataData(pdfFile.toFile());
        String resultKeywords = metaDataToTest.get("Keywords").isPresent() ? (String) metaDataToTest.get("Keywords").get(): null;
        assertEquals(testKeyWords, resultKeywords);
    }


}