package logic;

import data.ResultData;
import logic.ListFileCreator.ListOfPdfXlsxDocxFilesFromPathCreator;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CopyFileTest {
    private ListOfPdfXlsxDocxFilesFromPathCreator listOfFileCreatorFromPath;

    @BeforeEach
    public void setup() {
        listOfFileCreatorFromPath = new ListOfPdfXlsxDocxFilesFromPathCreator();
    }

    @Test
    @DisplayName("Should copy List<File> to directory.")
    public void performTest(@TempDir Path tempDir) throws IOException {
        List<File> tempListOfFile = createTempListOfFile(tempDir);
        Path destinationDirectory = Files.createDirectory(tempDir.resolve("destination"));
        CopyFile copyFile = new CopyFile(tempListOfFile, destinationDirectory.toFile());
        ResultData copiedFiles = copyFile.perform();
        List<File> copiedListOfFiles = new ListOfPdfXlsxDocxFilesFromPathCreator().getListOfFile(destinationDirectory.toAbsolutePath().toString());
        assertEquals("files-copied", copiedFiles.getResultMassage(),"Should return string: files-copied");
        assertEquals(3, copiedListOfFiles.size(), "Should copied 3 files.");
    }

    private List<File> createTempListOfFile(Path tempDir) throws IOException {
        Path directory = Files.createDirectory(tempDir.resolve("text"));
        Path pdfFile = Files.createFile(tempDir.resolve("text/text.pdf"));
        createTempPdfFile(pdfFile);
        Path docxFile = Files.createFile(tempDir.resolve("text/text.docx"));
        createTempDocxFile(docxFile);
        Path xlsxFile = Files.createFile(tempDir.resolve("text/test.xlsx"));
        createTempXlsXFile(xlsxFile);
        Path txtFile = Files.createFile(tempDir.resolve("text/test.txt"));
        createTempTxtFile(txtFile);
        return listOfFileCreatorFromPath.getListOfFile(directory.toString());
    }

    private void createTempDocxFile(Path path) throws IOException {
        XWPFDocument document = new XWPFDocument();
        FileOutputStream out = new FileOutputStream(path.toFile());
        XWPFParagraph paragraph = document.createParagraph();
        XWPFRun run = paragraph.createRun();
        run.setText("Test paragraph");
        document.write(out);
        out.close();
    }

    private void createTempPdfFile(Path pathFile) throws IOException {
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage( page );
        document.save(pathFile.toFile());
        document.close();
    }

    private void createTempXlsXFile(Path path) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Java Books");
        try (FileOutputStream outputStream = new FileOutputStream(path.toFile())) {
            workbook.write(outputStream);
        }
    }

    private void createTempTxtFile(Path path) throws FileNotFoundException {
        PrintWriter out = new PrintWriter(path.toFile());
        out.println("test");
        out.close();
    }

}