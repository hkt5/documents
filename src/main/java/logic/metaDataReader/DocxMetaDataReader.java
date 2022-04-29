package logic.metaDataReader;

import data.MetaData;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class DocxMetaDataReader implements MetaDataReadable{
    @Override
    public Map<String, String> getMataData(File file) {
        Map<String, String> mapMetaData = new LinkedHashMap<>();
        try {
            PDDocument doc = PDDocument.load(file);
            PDDocumentInformation pdd = doc.getDocumentInformation();
            MetaData metaData = new MetaData();
            metaData.setAuthor(pdd.getAuthor());
            metaData.setCreationDate(pdd.getCreationDate());
            metaData.setCreator(pdd.getCreator());
            metaData.setKeywords(pdd.getKeywords());
            metaData.setMetadataKeys(pdd.getMetadataKeys());
            metaData.setModificationDate(pdd.getModificationDate());
            metaData.setProducer(pdd.getProducer());
            metaData.setSubject(pdd.getSubject());
            metaData.setTitle(pdd.getTitle());
            metaData.setTrapped(pdd.getTrapped());
        } catch (IOException ioException) {
            System.out.println(ioException);
        } finally {
            return mapMetaData;
        }
    }
}
