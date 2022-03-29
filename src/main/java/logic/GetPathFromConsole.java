package logic;

import ui.Messageble;
import ui.UserInterface;

import java.io.File;

public class GetPathFromConsole implements GetPathable{

    boolean userHaveToRepeatWritePath = true;
    boolean userDoNotHaveToRepeatWritePath = false;

    private KeyboardReader keyboardReader;
    private Messageble messageble;

    public GetPathFromConsole() {
        this.keyboardReader = new KeyboardReader();
        this.messageble = new UserInterface();
    }

    @Override
    public File getPathFromUser() {
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
