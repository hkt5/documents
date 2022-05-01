package logic.metaDataReader;

import data.MetaData;
import data.XlsxMetaData;
import org.apache.poi.ooxml.POIXMLProperties;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class XlsxMetaDataReader implements MetaDataReadable{

    @Override
    public MetaData getMataData(File file) {
        XlsxMetaData metaData = new XlsxMetaData();
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            XSSFWorkbook xlsxSetMetadata = new XSSFWorkbook(fileInputStream);
            POIXMLProperties props = xlsxSetMetadata.getProperties();
            POIXMLProperties.CoreProperties coreProp = props.getCoreProperties();
            metaData.setCategory(coreProp.getCategory());
            metaData.setContentStatus(coreProp.getContentStatus());
            metaData.setContentType(coreProp.getContentType());
            metaData.setCreated(coreProp.getCreated());
            metaData.setCreator(coreProp.getCreator());
            metaData.setDescription(coreProp.getDescription());
            metaData.setIdentifier(coreProp.getIdentifier());
            metaData.setKeywords(coreProp.getKeywords());
            metaData.setLastModifiedByUser(coreProp.getLastModifiedByUser());
            metaData.setLastPrinted(coreProp.getLastPrinted());
            metaData.setModified(coreProp.getModified());
            metaData.setRevision(coreProp.getRevision());
            metaData.setSubject(coreProp.getSubject());
            metaData.setTitle(coreProp.getTitle());
            metaData.setUnderlyingProperties(coreProp.getUnderlyingProperties());
        } catch (IOException ioException) {
            System.out.println(ioException);
        } finally {
            return metaData;
        }
    }
}
