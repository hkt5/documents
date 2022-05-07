package logic.metaDataReader;

import logic.TempFileCreator;
import org.apache.poi.ooxml.POIXMLProperties;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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

class XlsxMetaDataReaderTest {
    private TempFileCreator tempFileCreator;
    private XlsxMetaDataReader xlsxMetaDataReader;

    @BeforeEach
    public void setup() {
        tempFileCreator = new TempFileCreator();
        xlsxMetaDataReader = new XlsxMetaDataReader();
    }

    @Test
    @DisplayName("Should return 'Test keyword' keyword meta data")
    void getMataDataFromXlsxTest(@TempDir Path tempDir) throws IOException {
        Path xlsxFile = Files.createFile(tempDir.resolve("test.xlsx"));
        String testKeyWords = "Test keyword";
        tempFileCreator.createTempDocxFile(xlsxFile);
        FileInputStream fileInputStream = new FileInputStream(xlsxFile.toFile());
        XSSFWorkbook xlsxSetMetadata = new XSSFWorkbook(fileInputStream);
        POIXMLProperties props = xlsxSetMetadata.getProperties();
        POIXMLProperties.CoreProperties coreProp = props.getCoreProperties();
        coreProp.setKeywords(testKeyWords);
        FileOutputStream out = new FileOutputStream(xlsxFile.toFile());
        xlsxSetMetadata.write(out);
        out.close();

        Map<String, Optional<Object>> metaDataToTest = xlsxMetaDataReader.getMataData(xlsxFile.toFile());
        String resultKeywords = metaDataToTest.get("Keywords").isPresent() ? (String) metaDataToTest.get("Keywords").get(): null;
        assertEquals(testKeyWords, resultKeywords);
    }

}