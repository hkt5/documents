package logic;

import data.FileDifference;
import data.ResultData;
import data.StatusFile;
import logic.ListFileCreator.ListOfFilesFromPathCreator;
import logic.unzip.UnzipFileToDirectoryController;
import logic.unzip.UnzipFileToDirectoryable;
import ui.Messageble;
import ui.UserInterface;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CompareFile implements FileStrategy {
    private final String PDF_FILE = "pdf";
    private final String DOCX_FILE = "docx";
    private final String XLSX_FILE = "xlsx";
    private static final int GREATER_THAN_ZERO = 0;
    private Messageble messageble;
    private UserInterfaceController userInterfaceController;
    private KeyboardReader keyboardReader;
    private UnzipFileToDirectoryable unzipFileToDirectoryable;
    private File sourceFile;
    private File fileToCompare;

    public CompareFile(){
        this.messageble = new UserInterface();
        this.keyboardReader = new KeyboardReader(new BufferedReader(new InputStreamReader(System.in)));
        this.userInterfaceController = new UserInterfaceController(new GetFileFromConsole());
        unzipFileToDirectoryable = new UnzipFileToDirectoryController();
        getFilesFromUser();
    }

    public CompareFile(File sourceFile, File fileToCompare) {
        this.messageble = new UserInterface();
        this.keyboardReader = new KeyboardReader(new BufferedReader(new InputStreamReader(System.in)));
        this.userInterfaceController = new UserInterfaceController(new GetFileFromConsole());
        unzipFileToDirectoryable = new UnzipFileToDirectoryController();
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
            List<File> sourceFiles = new ListOfFilesFromPathCreator().getListOfFile(tempDirSource.toString());
            List<File> filesToCompare = new ListOfFilesFromPathCreator().getListOfFile(tempDirToCompare.toString());

            List<FileDifference> fileDifferences = getListOfDifferences(sourceFiles, filesToCompare);

        } catch (IOException ioException) {
            System.out.println(ioException.fillInStackTrace());
        }

        ResultData resultData = new ResultData();
        resultData.setResultMassage("test");
        return resultData;
    }

    private List<FileDifference> getListOfDifferences(List<File> sourceFiles, List<File> compareFiles ) throws IOException {
        List<FileDifference> listOfFileNameWithDifference = new ArrayList<>();
        for (File sourceFile : sourceFiles) {
            if (fileNameExistInListOfFile(sourceFile, compareFiles)) {
                Path pathSourceFile = sourceFile.toPath();
                Path pathCompareFile = compareFiles.get(getIndex(sourceFile.getName(), compareFiles)).toPath();
                List<String> list0fFileDifference = diffFiles(pathSourceFile, pathCompareFile);
                listOfFileNameWithDifference.add(new FileDifference(sourceFile.getName(), StatusFile.CHANGE, list0fFileDifference));

            } else {
                listOfFileNameWithDifference.add(new FileDifference(sourceFile.getName(), StatusFile.DELETE));
            }
        }
        Iterator<File> compareFileIterator = compareFiles.iterator();
        while (compareFileIterator.hasNext() && fileNameExistInListOfFile(compareFileIterator.next(), sourceFiles)) {
            listOfFileNameWithDifference.add(new FileDifference(compareFileIterator.next().getName(), StatusFile.NEW));
        }
        return listOfFileNameWithDifference;
    }

    private boolean fileNameExistInListOfFile(File file, List<File> listOfFiles) {
        Iterator<File> listOfFilesIterator = listOfFiles.iterator();
        while (listOfFilesIterator.hasNext() && listOfFilesIterator.next().getName().equals(file.getName())){
            return true;
        }
        return false;
    }

    private int getIndex(String fileName, List<File> listFiles) {
        int index = -1;
        for (int i = 0; i < listFiles.size(); i++) {
            if(listFiles.get(i).getName().equals(fileName)) {
                index = i;
                break;
            }
        }
        return index;
    }

    private static List<String> diffFiles(Path firstFile, Path secondFile) throws IOException {
        List<String> firstFileContent = Files.readAllLines(firstFile, Charset.defaultCharset());
        List<String> secondFileContent = Files.readAllLines(secondFile, Charset.defaultCharset());
        List<String> diff = new ArrayList<>();
        firstFileContent.stream()
                .filter(line -> !secondFileContent.contains(line))
                .forEach(lineNotExist -> diff.add(lineNotExist));
        return diff;
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
}
