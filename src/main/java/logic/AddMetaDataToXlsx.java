package logic;

import org.apache.poi.ooxml.POIXMLProperties;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.*;

public class AddMetaDataToXlsx implements MetaDataAddable{
    @Override
    public boolean addKeywordToMetaData(File file, String hash) {
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            XSSFWorkbook xlsxSetMetadata = new XSSFWorkbook(fileInputStream);
            POIXMLProperties props = xlsxSetMetadata.getProperties();
            POIXMLProperties.CoreProperties coreProp=props.getCoreProperties();
            coreProp.setKeywords(hash);

            FileOutputStream out = new FileOutputStream(file);
            xlsxSetMetadata.write(out);
            out.close();
            System.out.println("Dodano Hash w Xlsx");
            return true;
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("NIe Dodano Hash w Xlsx");
            return false;
        } catch (IOException ioException) {
            System.out.println("NIe Dodano Hash w Xlsx");
            return false;
        }
    }
}
