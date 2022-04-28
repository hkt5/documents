package logic;

import ui.Messageble;
import ui.UserInterface;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

public class GetPathFromConsole implements GetPathable{

    boolean userHaveToRepeatWritePath = true;
    boolean userDoNotHaveToRepeatWritePath = false;

    private KeyboardReader keyboardReader;
    private Messageble messageble;

    public GetPathFromConsole() {
        this.keyboardReader = new KeyboardReader(new BufferedReader(new InputStreamReader(System.in)));
        this.messageble = new UserInterface();
    }

    @Override
    public File getPathFromUser(KeyboardReader keyboardReader) {
        File pathToDirectoryFromUser;
        do {
            messageble.getMessage("please-enter-destination-path");
            String nextPathToFile = keyboardReader.readLine();
            pathToDirectoryFromUser = new File(nextPathToFile);
        } while (checkIfUserPathExistAndIsDirectory(pathToDirectoryFromUser));
        return pathToDirectoryFromUser;
    }

    private boolean checkIfUserPathExistAndIsDirectory(File pathToDirectoryFromUser) {
        if (pathToDirectoryFromUser.isDirectory() && pathToDirectoryFromUser.exists()) {
            return userDoNotHaveToRepeatWritePath;
        } else
            messageble.getMessage("wrong-directory");
            return userHaveToRepeatWritePath;
    }
}
