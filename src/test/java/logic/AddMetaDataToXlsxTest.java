package logic;

import org.apache.poi.ooxml.POIXMLProperties;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
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
import static org.junit.jupiter.api.Assertions.*;

class AddMetaDataToXlsxTest {

    private AddMetaDataToXlsx addMetaDataToXlsx;

    @BeforeEach
    public void setup(){
         addMetaDataToXlsx = new AddMetaDataToXlsx();
    }

    @Test
    @DisplayName("Should add KeyWord metadata to XLSX file.")
    public void AddMetaDataToXlsxTest(@TempDir Path tempDir) throws IOException {
        // given
        Path xlsxFile = Files.createFile(tempDir.resolve("test.xlsx"));
        String testHash = "43jk345h3k5k3534lk5j3l45";
        createTempXlsXFile(xlsxFile);
        //when
        addMetaDataToXlsx.addKeywordToMetaData(xlsxFile.toFile(),testHash);
        FileInputStream fileInputStream = new FileInputStream(xlsxFile.toFile());
        XSSFWorkbook xlsxSetMetadata = new XSSFWorkbook(fileInputStream);
        POIXMLProperties props = xlsxSetMetadata.getProperties();
        POIXMLProperties.CoreProperties coreProp = props.getCoreProperties();
        String writeHash = coreProp.getKeywords();
        fileInputStream.close();
        xlsxSetMetadata.close();
        //then
        assertEquals(testHash, writeHash);
    }


    private void createTempXlsXFile(Path path) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Java Books");

        Object[][] bookData = {
                {"Head First Java", "Kathy Serria", 79},
                {"Effective Java", "Joshua Bloch", 36},
        };

        int rowCount = 0;
        for (Object[] aBook : bookData) {
            Row row = sheet.createRow(++rowCount);
            int columnCount = 0;
            for (Object field : aBook) {
                Cell cell = row.createCell(++columnCount);
                if (field instanceof String) {
                    cell.setCellValue((String) field);
                } else if (field instanceof Integer) {
                    cell.setCellValue((Integer) field);
                }
            }
        }

        try (FileOutputStream outputStream = new FileOutputStream(path.toFile())) {
            workbook.write(outputStream);
        }
    }

}