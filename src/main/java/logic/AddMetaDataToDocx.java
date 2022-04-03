package logic;

import org.apache.poi.ooxml.POIXMLProperties;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.*;


public class AddMetaDataToDocx implements MetaDataAddable{
    @Override
    public boolean addKeywordToMetaData(File file, String hash) {
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            //XSSFWorkbook xlsxSetMetadata = new XSSFWorkbook(fileInputStream);
            XWPFDocument xwpfDocument = new XWPFDocument(fileInputStream);
            POIXMLProperties props = xwpfDocument.getProperties();
            POIXMLProperties.CoreProperties coreProp=props.getCoreProperties();
            coreProp.setKeywords(hash);
            FileOutputStream out = new FileOutputStream(file);
            xwpfDocument.write(out);
            out.close();
            System.out.println("Dodano Hash w docx");
            return true;
        } catch (IOException ioException) {
            System.out.println("Nie dodano Hash w docx");
            return false;
        }
    }
}
