package logic.ListFileCreator;

import ui.Messageble;
import ui.UserInterface;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ListOfFilesFromPathCreator implements ListFileCreator {

    private Messageble messageble;
    private List<File> listOfFiles;

    public ListOfFilesFromPathCreator() {
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
        if (!file.exists()) {
            throw new Exception("Path to file or directory not found");
        } else if (file.isDirectory()) {
            readDirectory(file);
        } else {
            listOfFiles.add(file);
        }
    }

    private void readDirectory(File file) {
        for(File fileInDirectory : file.listFiles()) {
            if(fileInDirectory.isFile()) {
                listOfFiles.add(fileInDirectory);
            } else {
                readDirectory(fileInDirectory);
            }
        }
    }
}
