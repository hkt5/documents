package logic;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.pdmodel.common.PDMetadata;
import org.apache.xmpbox.XMPMetadata;
import org.apache.xmpbox.schema.AdobePDFSchema;
import org.apache.xmpbox.schema.DublinCoreSchema;
import org.apache.xmpbox.schema.XMPBasicSchema;
import org.apache.xmpbox.xml.XmpSerializer;

import javax.xml.transform.TransformerException;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;


public class AddMetaDataToPDF implements AddMetadata {
    @Override
    public boolean addKeywordToMetaData(File file, String hash) {
        try {
            PDDocument document = PDDocument.load(file);
            if (document.isEncrypted()) {
                document.setAllSecurityToBeRemoved(true);
            }
            PDDocumentInformation documentInformation = new PDDocumentInformation();
            documentInformation.setTitle("Apache PDFBox Adding Metadata PDF Document in Java");
            documentInformation.setSubject("Apache PDFBox Adding Metadata PDF Document in Java");
            documentInformation.setAuthor("Simple Solution");
            documentInformation.setCreator("Java Application");
            documentInformation.setProducer("Simple Solution");
            documentInformation.setKeywords("Java, Pdf Document, PDFBox, Simple Solution");
            documentInformation.setCreationDate(Calendar.getInstance());
            documentInformation.setModificationDate(Calendar.getInstance());
            documentInformation.setCustomMetadataValue("Website", "https://simplesolution.dev");
            documentInformation.setCustomMetadataValue("Email", "contact@simplesolution.dev");

            document.setDocumentInformation(documentInformation);

            PDDocumentCatalog catalog = document.getDocumentCatalog();

            XMPMetadata metadata = XMPMetadata.createXMPMetadata();
            AdobePDFSchema pdfSchema = metadata.createAndAddAdobePDFSchema();
            pdfSchema.setKeywords(documentInformation.getKeywords());
            pdfSchema.setProducer(documentInformation.getProducer());

            XMPBasicSchema basicSchema = metadata.createAndAddXMPBasicSchema();
            basicSchema.setCreateDate(documentInformation.getCreationDate());
            basicSchema.setModifyDate(documentInformation.getModificationDate());
            basicSchema.setCreatorTool(documentInformation.getCreator());
            basicSchema.setMetadataDate(documentInformation.getCreationDate());

            DublinCoreSchema dcSchema = metadata.createAndAddDublinCoreSchema();
            dcSchema.setTitle(documentInformation.getTitle());
            dcSchema.addCreator(documentInformation.getAuthor());
            dcSchema.setDescription(documentInformation.getSubject());

            PDMetadata metadataStream = new PDMetadata(document);
            catalog.setMetadata(metadataStream);

            XmpSerializer xmpSerializer = new XmpSerializer();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            xmpSerializer.serialize(metadata, byteArrayOutputStream, false);
            metadataStream.importXMPMetadata(byteArrayOutputStream.toByteArray());

            document.save(file);
            document.close();
            return true;
        } catch (IOException e) {
            System.out.println(e);
            return false;

        } catch (TransformerException e) {
            System.out.println(e);
            return false;
        }

    }
}
