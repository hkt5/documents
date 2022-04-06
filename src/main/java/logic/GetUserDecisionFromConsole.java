package logic;

import ui.Messageble;
import ui.UserInterface;

public class GetUserDecisionFromConsole implements GetDecision{

    private static final int USER_OPTION_IS_COPY_FILE = 1;
    private static final int USER_OPTION_IS_READ_FILE = 2;
    private Messageble messageble;
    private KeyboardReader keyboardReader;

    public GetUserDecisionFromConsole() {
        this.messageble = new UserInterface();
        this.keyboardReader = new KeyboardReader();
    }

    @Override
    public int getUserDecision() {
        int chooseNumberForUser;
        do {
            messageble.getMessage("welcome-message");
            chooseNumberForUser = getNumberFromUser();
            if (chooseNumberForUser != USER_OPTION_IS_COPY_FILE && chooseNumberForUser != USER_OPTION_IS_READ_FILE)  {
                messageble.getMessage("bad-choose");
            }
        } while (chooseNumberForUser != USER_OPTION_IS_COPY_FILE && chooseNumberForUser != USER_OPTION_IS_READ_FILE);
        return chooseNumberForUser;
    }

    private int getNumberFromUser() {
        int selectionOfUserOptions = 0;
        try {
            selectionOfUserOptions = Integer.parseInt(keyboardReader.readLine());
        } catch (NumberFormatException e) {
            selectionOfUserOptions = 0;
        }
        return selectionOfUserOptions;
    }
}
