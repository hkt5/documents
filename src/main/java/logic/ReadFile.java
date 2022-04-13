package logic;

import data.ResultData;
import ui.Messageble;
import ui.UserInterface;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;


public class ReadFile implements FileStrategy {

    private static final int GREATER_THAN_ZERO = 0;
    private Messageble messageble;
    private UserInterfaceController userInterfaceController;
    private KeyboardReader keyboardReader;
    private File fileToCompare;

    public ReadFile(){
        this.messageble = new UserInterface();
        this.keyboardReader = new KeyboardReader(new BufferedReader(new InputStreamReader(System.in)));
        this.userInterfaceController = new UserInterfaceController(new GetFileFromConsole());
        this.fileToCompare = userInterfaceController.getPathFromUser(keyboardReader);
    }

    public ReadFile(File fileToCompare) {
        this.messageble = new UserInterface();
        this.keyboardReader = new KeyboardReader(new BufferedReader(new InputStreamReader(System.in)));
        this.userInterfaceController = new UserInterfaceController(new GetFileFromConsole());
        this.fileToCompare = fileToCompare;
    }

    @Override
    public ResultData perform() {
        ResultData resultData = new ResultData();
        if (checkExtensionIsPdf(fileToCompare) || checkExtensionIsEndOfCharX(fileToCompare)) {
            resultData.setResultMassage("file-is-based-on-xml");
        } else {
            resultData.setResultMassage("file-is-not-based-on-xml");
        }
        return resultData;
    }

    private Boolean checkExtensionIsPdf(File file) {
        String fileName = file.toString();
        int index = fileName.lastIndexOf('.');
        if (index > GREATER_THAN_ZERO) {
            return compareExtensionToPdf(fileName.substring(index + 1));
        }
        return Boolean.FALSE;
    }

    private Boolean checkExtensionIsEndOfCharX(File file) {
        String fileName = file.toString();
        int index = fileName.toCharArray().length;
        if (fileName.substring(index -1).equals("x")) {
            return true;
        } else {
            return false;
        }
    }

    private Boolean compareExtensionToPdf(String extension) {
        switch (extension) {
            case "pdf":
                return Boolean.TRUE;
            default:
                return Boolean.FALSE;
        }
    }


}
