package logic;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;

public class TempFileCreator {

    public void createTempDocxFile(Path path) throws IOException {
        XWPFDocument document = new XWPFDocument();
        FileOutputStream out = new FileOutputStream(path.toFile());
        XWPFParagraph paragraph = document.createParagraph();
        XWPFRun run = paragraph.createRun();
        run.setText("Test paragraph");
        document.write(out);
        out.close();
    }

    public void createTempPdfFile(Path pathFile) throws IOException {
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage( page );
        document.save(pathFile.toFile());
        document.close();
    }

    public void createTempXlsXFile(Path path) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Java Books");
        try (FileOutputStream outputStream = new FileOutputStream(path.toFile())) {
            workbook.write(outputStream);
        }
    }

    public void createTempTxtFile(Path path) throws FileNotFoundException {
        PrintWriter out = new PrintWriter(path.toFile());
        out.println("test");
        out.close();
    }
}
