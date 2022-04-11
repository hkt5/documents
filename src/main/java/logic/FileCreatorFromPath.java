package logic;

import ui.Messageble;
import ui.UserInterface;
import java.io.File;

public class FileCreatorFromPath implements FileCreatorable{

    private static final int GREATER_THAN_ZERO = 0;
    private Messageble messageble;
    private File fileFromPath;

    public FileCreatorFromPath() {
        this.messageble = new UserInterface();
    }

    @Override
    public File getFile(String pathToFile) {
        File file = new File(pathToFile);
        try {
            readPath(file);
        } catch (Exception e) {
            messageble.getMessage("wrong-path");
        }
        return fileFromPath;
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
            fileFromPath = file;
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
