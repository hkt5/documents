package logic.metaDataReader;

import org.apache.poi.ooxml.POIXMLProperties;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class DocxMetaDataReader implements MetaDataReadable{
    @Override
    public Map<String, Optional<Object>> getMataData(File file) throws IOException {
        HashMap<String, Optional<Object>> metaData = new HashMap<>();
        FileInputStream fileInputStream = new FileInputStream(file);
        XWPFDocument xwpfDocument = new XWPFDocument(fileInputStream);
        POIXMLProperties props = xwpfDocument.getProperties();
        POIXMLProperties.CoreProperties coreProp = props.getCoreProperties();
        metaData.put("Category", Optional.ofNullable(coreProp.getCategory()));
        metaData.put("ContentStatus", Optional.ofNullable(coreProp.getContentStatus()));
        metaData.put("ContentType", Optional.ofNullable(coreProp.getContentType()));
        metaData.put("Created", Optional.ofNullable(coreProp.getCreated()));
        metaData.put("Creator", Optional.ofNullable(coreProp.getCreator()));
        metaData.put("Description", Optional.ofNullable(coreProp.getDescription()));
        metaData.put("Identifier", Optional.ofNullable(coreProp.getIdentifier()));
        metaData.put("Keywords", Optional.ofNullable(coreProp.getKeywords()));
        metaData.put("LastModifiedByUser", Optional.ofNullable(coreProp.getLastModifiedByUser()));
        metaData.put("LastPrinted", Optional.ofNullable(coreProp.getLastPrinted()));
        metaData.put("Modified", Optional.ofNullable(coreProp.getModified()));
        metaData.put("Revision", Optional.ofNullable(coreProp.getRevision()));
        metaData.put("Subject", Optional.ofNullable(coreProp.getSubject()));
        metaData.put("Title", Optional.ofNullable(coreProp.getTitle()));
        metaData.put("UnderlyingProperties", Optional.ofNullable(coreProp.getUnderlyingProperties()));
        return metaData;
    }
}
