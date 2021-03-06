package logic;

import ui.Messageble;
import ui.UserInterface;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class GetUserDecisionFromConsole implements GetDecision{

    private static final int USER_OPTION_IS_COPY_FILE = 1;
    private static final int USER_OPTION_IS_READ_FILE = 2;
    private static final int USER_OPTION_IS_COMPARE_FILE = 3;
    private static final int USER_OPTION_IS_MACRO_EXTRACTOR = 4;
    private Messageble messageble;
    private KeyboardReader keyboardReader;

    public GetUserDecisionFromConsole() {
        this.messageble = new UserInterface();
        this.keyboardReader = new KeyboardReader(new BufferedReader(new InputStreamReader(System.in)));
    }

    @Override
    public int getUserDecision(GetNumberFromUser getNumberFromUser) {
        int chooseNumberForUser;
        do {
            messageble.getMessage("welcome-message");
            chooseNumberForUser = getNumberFromUser.getNumberFromUser();
            displayMessageAboutWrongChoose(chooseNumberForUser);
        } while (chooseNumberForUser != USER_OPTION_IS_COPY_FILE &&
                chooseNumberForUser != USER_OPTION_IS_READ_FILE &&
                chooseNumberForUser != USER_OPTION_IS_COMPARE_FILE &&
                chooseNumberForUser != USER_OPTION_IS_MACRO_EXTRACTOR);
        return chooseNumberForUser;
    }

    private void displayMessageAboutWrongChoose(int chooseNumberForUser) {
        if (chooseNumberForUser != USER_OPTION_IS_COPY_FILE &&
                chooseNumberForUser != USER_OPTION_IS_READ_FILE &&
                chooseNumberForUser != USER_OPTION_IS_COMPARE_FILE &&
                chooseNumberForUser != USER_OPTION_IS_MACRO_EXTRACTOR)  {
            messageble.getMessage("bad-choose");
        }
    }
}
