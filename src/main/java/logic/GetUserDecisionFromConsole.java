package logic;

import ui.Messageble;
import ui.UserInterface;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class GetUserDecisionFromConsole implements GetDecision{

    private static final int USER_OPTION_IS_COPY_FILE = 1;
    private static final int USER_OPTION_IS_READ_FILE = 2;
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
        } while (chooseNumberForUser != USER_OPTION_IS_COPY_FILE && chooseNumberForUser != USER_OPTION_IS_READ_FILE);
        return chooseNumberForUser;
    }

    private void displayMessageAboutWrongChoose(int chooseNumberForUser) {
        if (chooseNumberForUser != USER_OPTION_IS_COPY_FILE && chooseNumberForUser != USER_OPTION_IS_READ_FILE)  {
            messageble.getMessage("bad-choose");
        }
    }
}
