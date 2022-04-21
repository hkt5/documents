package logic;

import data.ResultData;
import logic.ListFileCreator.ListFileCreator;
import logic.ListFileCreator.ListOfFilesFromPathCreator;
import logic.unzip.UnzipFileToDirectoryController;
import logic.unzip.UnzipFileToDirectoryable;
import ui.Messageble;
import ui.UserInterface;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class CompareFile implements FileStrategy {
    private final String PDF_FILE = "pdf";
    private final String DOCX_FILE = "docx";
    private final String XLSX_FILE = "xlsx";
    private static final int GREATER_THAN_ZERO = 0;
    private Messageble messageble;
    private UserInterfaceController userInterfaceController;
    private KeyboardReader keyboardReader;
    private UnzipFileToDirectoryable unzipFileToDirectoryable;
    private ListFileCreator listFileCreator;
    private File sourceFile;
    private File fileToCompare;

    public CompareFile(){
        this.messageble = new UserInterface();
        this.keyboardReader = new KeyboardReader(new BufferedReader(new InputStreamReader(System.in)));
        this.userInterfaceController = new UserInterfaceController(new GetFileFromConsole());
        unzipFileToDirectoryable = new UnzipFileToDirectoryController();
        listFileCreator = new ListOfFilesFromPathCreator();
        getFilesFromUser();
    }

    public CompareFile(File sourceFile, File fileToCompare) {
        this.messageble = new UserInterface();
        this.keyboardReader = new KeyboardReader(new BufferedReader(new InputStreamReader(System.in)));
        this.userInterfaceController = new UserInterfaceController(new GetFileFromConsole());
        unzipFileToDirectoryable = new UnzipFileToDirectoryController();
        listFileCreator = new ListOfFilesFromPathCreator();
        this.sourceFile = sourceFile;
        this.fileToCompare = fileToCompare;
    }

    @Override
    public ResultData perform() {
        try {
            Path tempDirSource = Files.createTempDirectory("");
            Path tempDirToCompare = Files.createTempDirectory("");
            unzipFileToDirectoryable.unzip(sourceFile.toPath(), tempDirSource);
            unzipFileToDirectoryable.unzip(fileToCompare.toPath(), tempDirToCompare);
            List<File> sourceFiles = listFileCreator.getListOfFile(tempDirSource.toString());
            List<File> filesToCompare = listFileCreator.getListOfFile(tempDirSource.toString());

            for (File f : sourceFiles) {
                System.out.println(f.getAbsolutePath().replace(tempDirSource.toString(), ""));
            }
            System.out.println("--------------------------------------");
            for (File f : filesToCompare) {
                System.out.println(f.getAbsolutePath().replace(tempDirToCompare.toString(), ""));
            }

        } catch (IOException ioException) {
            System.out.println(ioException);
        }
        FileInputStream fis = null;
        ZipInputStream zipIs = null;
        ZipEntry zEntry = null;
        try {
            fis = new FileInputStream(sourceFile);
            zipIs = new ZipInputStream(new BufferedInputStream(fis));
            while ((zEntry = zipIs.getNextEntry()) != null) {
                try {

                    Path firstFile = Paths.get("C:\\Prywatne\\sdf.txt");
                    File file = new File(zEntry.getName());
                    Path tempDirWithPrefix2 = Files.createTempDirectory("");
                    System.out.println(tempDirWithPrefix2.toAbsolutePath());
                    System.out.println("Path: " + file.getAbsolutePath());
                    FileOutputStream fos = new FileOutputStream(tempDirWithPrefix2 + "/" + zEntry.getName());
                    List<String> diff = diffFiles(Paths.get(tempDirWithPrefix2 + "/" + zEntry.getName()), firstFile);
                    for (String aaa : diff) {
                        System.out.println(aaa);
                    }
                    System.out.println("ZIP entry" + zEntry.getName());
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
            zipIs.close();
            fis.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        ResultData resultData = new ResultData();
        resultData.setResultMassage("test");
        return resultData;
    }

    private void getFilesFromUser() {
        messageble.getMessage("add-first-file");
        this.sourceFile = userInterfaceController.getPathFromUser(keyboardReader);
        while (!checkFileExtension(sourceFile, PDF_FILE) && !checkFileExtension(sourceFile, XLSX_FILE) && !checkFileExtension(sourceFile, DOCX_FILE)) {
            messageble.getMessage("wrong-extension");
            this.sourceFile = userInterfaceController.getPathFromUser(keyboardReader);
        }
        String extensionFirstFile = getExtension(sourceFile);
        messageble.getMessage("add-second-file");
        this.fileToCompare = userInterfaceController.getPathFromUser(keyboardReader);
        while (!getExtension(fileToCompare).equals(extensionFirstFile)) {
            messageble.getMessage("compare-file-should-the-same-extension");
            this.fileToCompare = userInterfaceController.getPathFromUser(keyboardReader);
        }
    }

    private Boolean checkFileExtension(File file, String extension) {
        String fileName = file.toString();
        int index = fileName.lastIndexOf('.');
        String fileExtension = fileName.substring(index + 1);
        return fileExtension.equals(extension);
    }

    private String getExtension(File file) {
        String fileName = file.toString();
        int index = fileName.lastIndexOf('.');
        return fileName.substring(index + 1);
    }

    private static List<String> diffFiles(Path firstFile, Path secondFile) throws IOException {
        List<String> firstFileContent = Files.readAllLines(firstFile, Charset.defaultCharset());
        List<String> secondFileContent = Files.readAllLines(secondFile, Charset.defaultCharset());
        List<String> diff = new ArrayList<String>();
        for (String line : firstFileContent) {
            if (!secondFileContent.contains(line)) {
                diff.add(line);
            }
        }
        return diff;
    }
}
