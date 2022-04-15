package logic;

import org.apache.poi.ooxml.POIXMLProperties;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import java.io.*;

public class AddMetaDataToDocx implements MetaDataAddable{
    @Override
    public boolean addKeywordToMetaData(File file, String hash) {
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            XWPFDocument xwpfDocument = new XWPFDocument(fileInputStream);
            POIXMLProperties props = xwpfDocument.getProperties();
            POIXMLProperties.CoreProperties coreProp=props.getCoreProperties();
            coreProp.setKeywords(hash);
            FileOutputStream out = new FileOutputStream(file);
            xwpfDocument.write(out);
            out.close();
            return true;
        } catch (IOException ioException) {
            return false;
        }
    }
}
