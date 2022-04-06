package logic;

import logic.AddMetaDataToPDF;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AddMetaDataToPdfTest {

    private AddMetaDataToPDF addMetaDataToPDF;

    @BeforeEach
    public void setup(){
        addMetaDataToPDF = new AddMetaDataToPDF();
    }

    @Test
    @DisplayName("Should add KeyWord metadata to PDF file.")
    public void AddMetaDataToXlsxTest(@TempDir Path tempDir) throws IOException {
        // given
        Path pdfFile = Files.createFile(tempDir.resolve("test.pdf"));
        String testHash = "43jk345h3k5k3534lk5j3l45";
        createTempPdfFile(pdfFile);
        //when
        addMetaDataToPDF.addKeywordToMetaData(pdfFile.toFile(),testHash);
        PDDocument doc = PDDocument.load(pdfFile.toFile());
        PDDocumentInformation pdd = doc.getDocumentInformation();
        String writeHash = pdd.getKeywords();
        doc.close();
        //then
        assertEquals(testHash, writeHash);
    }

    private void createTempPdfFile(Path pathFile) throws IOException {
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage( page );
        PDFont font = PDType1Font.HELVETICA;
        PDPageContentStream contentStream = new PDPageContentStream(document, page);
        contentStream.beginText();
        contentStream.setFont( font, 12 );
        contentStream.moveTextPositionByAmount( 100, 700 );
        contentStream.drawString( "Hello World" );
        contentStream.endText();
        contentStream.close();
        document.save(pathFile.toFile());
        document.close();
    }


}