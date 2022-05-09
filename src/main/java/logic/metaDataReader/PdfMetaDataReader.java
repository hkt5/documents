package logic.metaDataReader;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class PdfMetaDataReader implements MetaDataReadable{
    @Override
    public Map<String, Optional<Object>> getMataData(File file) {
        HashMap<String, Optional<Object>> metaData = new HashMap<>();
        try {
            PDDocument doc = PDDocument.load(file);
            PDDocumentInformation pdd = doc.getDocumentInformation();
            metaData.put("Author", Optional.ofNullable(pdd.getAuthor()));
            metaData.put("CreationDate", Optional.ofNullable(pdd.getCreationDate()));
            metaData.put("Creator", Optional.ofNullable(pdd.getCreator()));
            metaData.put("Keywords", Optional.ofNullable(pdd.getKeywords()));
            metaData.put("MetadataKeys", Optional.ofNullable(pdd.getMetadataKeys()));
            metaData.put("ModificationDate", Optional.ofNullable(pdd.getModificationDate()));
            metaData.put("Producer", Optional.ofNullable(pdd.getProducer()));
            metaData.put("Subject", Optional.ofNullable(pdd.getSubject()));
            metaData.put("Title", Optional.ofNullable(pdd.getTitle()));
            metaData.put("Trapped", Optional.ofNullable(pdd.getTrapped()));
            doc.close();
        } catch (IOException ioException) {
            System.out.println("PdfMetaDataReader - " + ioException);
        } finally {
            return metaData;
        }
    }
}
