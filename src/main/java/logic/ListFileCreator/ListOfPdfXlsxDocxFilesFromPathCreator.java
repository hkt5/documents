package logic.ListFileCreator;

import ui.Messageble;
import ui.UserInterface;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ListOfPdfXlsxDocxFilesFromPathCreator implements ListFileCreator {

    private static final int GREATER_THAN_ZERO = 0;
    private Messageble messageble;
    private List<File> listOfFiles;

    public ListOfPdfXlsxDocxFilesFromPathCreator() {
        this.messageble = new UserInterface();
        this.listOfFiles = new ArrayList<>();
    }

    @Override
    public List<File> getListOfFile(String pathToFile) {
        File file = new File(pathToFile);
        try {
            readPath(file);
        } catch (Exception e) {
            messageble.getMessage("wrong-path");
        }
        return listOfFiles;
    }

    private void readPath(File file) throws Exception {
        if (!file.exists()) throw new Exception("Path to file or directory not found");
        if (file.isDirectory()) {
            readDirectory(file);
        } else {
            addFileToList(file);
        }
    }

    private void addFileToList(File file) {
        if (checkExtensionIsDocxOrXlsxOrPdf(file)) {
            listOfFiles.add(file);
        }
    }

    private void readDirectory(File file) {
        File[] files = file.listFiles();
        for(File fileInDirectory : files) {
            if(fileInDirectory.isFile()) {
                addFileToList(fileInDirectory);
            } else {
                readDirectory(fileInDirectory);
            }
        }
    }

    private Boolean checkExtensionIsDocxOrXlsxOrPdf(File file) {
        String fileName = file.toString();
        int index = fileName.lastIndexOf('.');
        if (index > GREATER_THAN_ZERO) {
            return compareExtension(fileName.substring(index + 1));
        }
        return Boolean.FALSE;
    }

    private Boolean compareExtension(String extension) {
        switch (extension) {
            case "docx":
            case "xlsx":
            case "pdf":
                return Boolean.TRUE;
            default:
                return Boolean.FALSE;
        }
    }

}
