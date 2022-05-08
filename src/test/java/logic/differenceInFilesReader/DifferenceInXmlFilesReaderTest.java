package logic.differenceInFilesReader;

import net.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.*;

class DifferenceInXmlFilesReaderTest {
    private DifferenceInXmlFilesReader differenceInXmlFilesReader;

    @BeforeEach
    void setUp() {
        differenceInXmlFilesReader =  new DifferenceInXmlFilesReader();
    }

    @Test
    @DisplayName("Should return list of differences")
    void getListOfDifferencesTest(@TempDir Path tempDir) throws IOException {
        List<File> firstList = getTempListOfFile(tempDir, 3, true);
        List<File> secondList = getTempListOfFile(tempDir, 3 , false);

    }

    private List<File> getTempListOfFile(Path tempDir, int numberOfFiles, boolean numberBeforeText) throws IOException {
        List<File> tempListOfFile = new ArrayList<>();
        int intRandom = ThreadLocalRandom.current().nextInt(1, 10);
        for (int i = 0; i < numberOfFiles; i++) {
            String nameFile = intRandom + "/" + "text" + i + ".txt";
            Path txtFile = Files.createFile(tempDir.resolve(nameFile));
            createTempTxtFile(txtFile, 3, numberBeforeText);
            tempListOfFile.add(txtFile.toFile());
        }
        return tempListOfFile;
    }

    public void createTempTxtFile(Path path, int lineInFile, boolean numberBeforeText) throws FileNotFoundException {
        PrintWriter out = new PrintWriter(path.toFile());
        for (int i = 0; i < lineInFile; i++) {
            String line = (numberBeforeText) ? i + "test" : "test" + i;
            out.println(line);
        }
        out.close();
    }

}