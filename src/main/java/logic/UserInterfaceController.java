package logic;

import data.FilesSafe;
import ui.Messageble;
import ui.UserInterface;

import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserInterfaceController {

    private static final int USER_OPTION_IS_COPY_FILE = 1;
    private static final int USER_OPTION_IS_READ_FILE = 2;
    private static final int GREATER_THAN_ZERO = 0;
    public static final String CHAR_IS_LETTER_N = "N";
    public static final String CHAR_IS_LETTER_E = "E";
    private Messageble messageble;
    private FilesSafe filesSafe;
    private KeyboardReader keyboardReader;

    public UserInterfaceController() {
        this.messageble = new UserInterface();
        this.filesSafe = new FilesSafe();
        this.keyboardReader = new KeyboardReader();
    }

    public int getUserDecision() {
        int selectionOfUserOptions = 0;
        do {
            messageble.getMessage("welcome-message");
            try {
                selectionOfUserOptions = Integer.parseInt(keyboardReader.readLine());
            } catch (NumberFormatException e) {
                selectionOfUserOptions = 0;
            }
            if (selectionOfUserOptions != USER_OPTION_IS_COPY_FILE && selectionOfUserOptions != USER_OPTION_IS_READ_FILE)  {
                messageble.getMessage("bad-choose");
            }
        } while (selectionOfUserOptions != USER_OPTION_IS_COPY_FILE && selectionOfUserOptions != USER_OPTION_IS_READ_FILE);
        return selectionOfUserOptions;
    }

    public List<File> getListOfPathFromUser() {
        do {
            messageble.getMessage("please-enter-the-path");
            String nextPathToFile = keyboardReader.readLine();
            getListOfFiles(nextPathToFile);
        } while (getUserDecisionToAddNextPath());
        return filesSafe.getFiles();
    }

    private boolean getUserDecisionToAddNextPath() {
        while (true) {
            messageble.getMessage("continue");
            String userDecision = keyboardReader.readLine();
            if (!userDecision.equals(CHAR_IS_LETTER_N) && !userDecision.equals(CHAR_IS_LETTER_E)) messageble.getMessage("bad-choose-letter");
            if (userDecision.equals(CHAR_IS_LETTER_N)) return true;
            else if (userDecision.equals(CHAR_IS_LETTER_E)) return false;
        }
    }

    public void getListOfFiles(String pathToFile) {

        File file = new File(pathToFile);
        try {
            readPath(file);
        } catch (Exception e) {
            messageble.getMessage("wrong-path");
        }
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
            filesSafe.addFile(file);
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
            String fileExtension = fileName.substring(index + 1);
            switch (fileExtension) {
                case "docx":
                case "xlsx":
                case "pdf":
                    return Boolean.TRUE;
                default:
                    return Boolean.FALSE;
            }
        }
        return Boolean.FALSE;
    }
}
