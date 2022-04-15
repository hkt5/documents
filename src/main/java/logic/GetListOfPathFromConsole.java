package logic;

import ui.Messageble;
import ui.UserInterface;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class GetListOfPathFromConsole implements GetListOfPath{

    public static final String CHAR_IS_LETTER_N = "N";
    public static final String CHAR_IS_LETTER_E = "E";
    private Messageble messageble;
    private KeyboardReader keyboardReader;

    public GetListOfPathFromConsole() {
        this.messageble = new UserInterface();
        this.keyboardReader = new KeyboardReader(new BufferedReader(new InputStreamReader(System.in)));
    }

    @Override
    public List<File> getListOfPathFromUser(KeyboardReader keyboardReader) {
        List<File> listOfFile = new ArrayList<>();
        do {
            messageble.getMessage("please-enter-the-path");
            String nextPathToFile = keyboardReader.readLine();
            ListFileCreator listOfFileCreator = new ListOfFileCreatorFromPath();
            listOfFile.addAll(listOfFileCreator.getListOfFile(nextPathToFile));
        } while (getUserDecisionToAddNextPath());
        return listOfFile;
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
}
