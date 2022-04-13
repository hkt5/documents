package logic;

import ui.Messageble;
import ui.UserInterface;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

public class GetFileFromConsole implements GetPathable{

    boolean userHaveToRepeatWritePath = true;
    boolean userDoNotHaveToRepeatWritePath = false;

    private KeyboardReader keyboardReader;
    private Messageble messageble;

    public GetFileFromConsole() {
        this.keyboardReader = new KeyboardReader(new BufferedReader(new InputStreamReader(System.in)));
        this.messageble = new UserInterface();
    }

    @Override
    public File getPathFromUser(KeyboardReader keyboardReader) {
        File pathToFileFromUser;
        do {
            messageble.getMessage("please-enter-the-path-to-file");
            String nextPathToFile = keyboardReader.readLine();
            pathToFileFromUser = new File(nextPathToFile);
        } while (checkIfUserPathExist(pathToFileFromUser));
        return pathToFileFromUser;
    }

    private boolean checkIfUserPathExist(File pathToFileFromUser) {
        if (!pathToFileFromUser.isDirectory() && pathToFileFromUser.exists()) {
            return userDoNotHaveToRepeatWritePath;
        } else {
            messageble.getMessage("wrong-path-to-file");
            return userHaveToRepeatWritePath;
        }
    }
}
