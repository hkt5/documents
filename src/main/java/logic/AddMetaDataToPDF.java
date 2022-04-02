package logic;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import java.io.File;
import java.io.IOException;

public class AddMetaDataToPDF implements MetaDataAddable {
    @Override
    public boolean addKeywordToMetaData(File file, String hash) {
        try {
            PDDocument doc = PDDocument.load(file);
            PDDocumentInformation pdd = doc.getDocumentInformation();
            pdd.setKeywords(hash);
            doc.setDocumentInformation(pdd);
            doc.save(file);
            doc.close();
            System.out.println("Dodano Hash w dpf");
            return true;
        } catch (IOException ioException) {
            System.out.println("Nie dodano Hash w dpf");
            return false;
        }
    }
}
