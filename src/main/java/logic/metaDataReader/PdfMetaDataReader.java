package logic.metaDataReader;

import data.MetaData;
import data.PdfMetaData;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;

import java.io.File;
import java.io.IOException;

public class PdfMetaDataReader implements MetaDataReadable{
    @Override
    public MetaData getMataData(File file) {
        PdfMetaData pdfMetaData = new PdfMetaData();
        try {
            PDDocument doc = PDDocument.load(file);
            PDDocumentInformation pdd = doc.getDocumentInformation();
            pdfMetaData.setAuthor(pdd.getAuthor());
            pdfMetaData.setCOSObject(pdd.getCOSObject());
            pdfMetaData.setCreationDate(pdd.getCreationDate());
            pdfMetaData.setCreator(pdd.getCreator());
            pdfMetaData.setKeywords(pdd.getKeywords());
            pdfMetaData.setMetadataKeys(pdd.getMetadataKeys());
            pdfMetaData.setModificationDate(pdd.getModificationDate());
            pdfMetaData.setProducer(pdd.getProducer());
            pdfMetaData.setSubject(pdd.getSubject());
            pdfMetaData.setTitle(pdd.getTitle());
            pdfMetaData.setTrapped(pdd.getTrapped());
        } catch (IOException ioException) {
            System.out.println(ioException);
        } finally {
            return pdfMetaData;
        }
    }
}
