package logic;

import data.ResultData;
import ui.Messageble;
import ui.UserInterface;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;


public class ReadFile implements FileStrategy {

    private Messageble messageble;
    private UserInterfaceController userInterfaceController;
    private KeyboardReader keyboardReader;
    private File fileToCompare;

    public ReadFile(){
        this.messageble = new UserInterface();
        this.keyboardReader = new KeyboardReader(new BufferedReader(new InputStreamReader(System.in)));
        this.userInterfaceController = new UserInterfaceController(new GetFileFromConsole());
        fileToCompare = userInterfaceController.getPathFromUser(keyboardReader);
    }

    @Override
    public ResultData perform() {
        return null;
    }
}
