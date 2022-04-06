package logic;

import org.apache.poi.ooxml.POIXMLProperties;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddMetaDataToDocxTest {

    private AddMetaDataToDocx addMetaDataToDocx;

    @BeforeEach
    public void setup(){
        addMetaDataToDocx = new AddMetaDataToDocx();
    }

    @Test
    @DisplayName("Should add KeyWord metadata to DOCX file.")
    public void addKeywordToMetaDataTest(@TempDir Path tempDir) throws IOException {
        // given
        Path docxFile = Files.createFile(tempDir.resolve("test.docx"));
        String testHash = "43jk345h3k5k3534lk5j3l45";
        createTempDocxFile(docxFile);
        //when
        addMetaDataToDocx.addKeywordToMetaData(docxFile.toFile(),testHash);
        FileInputStream fileInputStream = new FileInputStream(docxFile.toFile());
        XWPFDocument xwpfDocument = new XWPFDocument(fileInputStream);
        POIXMLProperties props = xwpfDocument.getProperties();
        POIXMLProperties.CoreProperties coreProp = props.getCoreProperties();
        String writeHash = coreProp.getKeywords();
        fileInputStream.close();
        xwpfDocument.close();
        //then
        assertEquals(testHash, writeHash);
    }

    private void createTempDocxFile(Path path) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(path.toFile());
        XWPFDocument xwpfDocument = new XWPFDocument(fileInputStream);
        XWPFParagraph paragraph = xwpfDocument.createParagraph();
        XWPFRun run = paragraph.createRun();
        run.setText("Test");
        FileOutputStream out = new FileOutputStream(path.toFile());
        xwpfDocument.write(out);
        out.close();
    }
}
