package logic;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDMetadata;
import org.apache.xmpbox.XMPMetadata;
import org.apache.xmpbox.schema.AdobePDFSchema;
import org.apache.xmpbox.schema.DublinCoreSchema;
import org.apache.xmpbox.schema.XMPBasicSchema;
import org.apache.xmpbox.xml.XmpSerializer;

import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;

public class AddMetaDataToPDF implements AddMetadata {
    @Override
    public void addKeywordToMetaData(File file, String hash) {
        try {
            PDDocumentInformation documentInformation = new PDDocumentInformation();
            documentInformation.setKeywords(hash);

            PDDocument doc = PDDocument.load(file);
            PDDocumentCatalog catalog = doc.getDocumentCatalog();
            PDMetadata metadata = catalog.getMetadata();
            //metadata.setMetadata(documentInformation);

        } catch (IOException e) {

        }

    }
}
