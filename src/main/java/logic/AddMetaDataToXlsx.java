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
            return true;
        } catch (FileNotFoundException fileNotFoundException) {
            return false;
        } catch (IOException ioException) {
            return false;
        }
    }
}
