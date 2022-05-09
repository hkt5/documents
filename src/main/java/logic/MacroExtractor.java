package logic;

import data.ResultData;
import org.apache.poi.poifs.macros.VBAMacroExtractor;
import ui.Messageble;
import ui.UserInterface;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class MacroExtractor implements FileStrategy{
    private Messageble messageble;
    private UserInterfaceController userInterfaceController;
    private KeyboardReader keyboardReader;
    private File fileToExtractor;

    public MacroExtractor(){
        this.messageble = new UserInterface();
        this.keyboardReader = new KeyboardReader(new BufferedReader(new InputStreamReader(System.in)));
        this.userInterfaceController = new UserInterfaceController(new GetFileFromConsole());
        this.fileToExtractor = userInterfaceController.getPathFromUser(keyboardReader);
    }

    public MacroExtractor(File fileToExtractor) {
        this.messageble = new UserInterface();
        this.keyboardReader = new KeyboardReader(new BufferedReader(new InputStreamReader(System.in)));
        this.userInterfaceController = new UserInterfaceController(new GetFileFromConsole());
        this.fileToExtractor = fileToExtractor;
    }

    @Override
    public ResultData perform() {
        while (!checkExtensionIsEndOfCharX(fileToExtractor)) {
            messageble.getMessage("you-can-extract-macro-from-docx-xlsx");
            this.fileToExtractor = userInterfaceController.getPathFromUser(keyboardReader);
        }
        ResultData resultData = new ResultData();
        try {
            VBAMacroExtractor vbaMacroExtractor = new VBAMacroExtractor();
            vbaMacroExtractor.extract(fileToExtractor, fileToExtractor);
            resultData.setResultMassage("macro-has-been-extracted");
        } catch (IllegalArgumentException illegalArgumentException) {
            resultData.setResultMassage("file-doest-contain-marco");
        } catch (IOException ioException) {
            System.out.println(ioException);
        }

        return new ResultData();
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
}
