package logic;

import ui.Messageble;
import ui.UserInterface;

import java.util.Scanner;

public class UserInterfaceController {

    private static final int USER_OPTION_IS_COPY_FILE = 1;
    private static final int USER_OPTION_IS_READ_FILE = 2;
    private Messageble messageble = new UserInterface();

    public int getUserDecision() {
        messageble.getMessage("welcome-message");
        Scanner scanner = new Scanner(System.in);
        int selectionOfUserOptions = scanner.nextInt();
        while (selectionOfUserOptions != USER_OPTION_IS_COPY_FILE && selectionOfUserOptions != USER_OPTION_IS_READ_FILE) {
            messageble.getMessage("bad-choose");
            selectionOfUserOptions = scanner.nextInt();
        }
        scanner.close();
        return selectionOfUserOptions;
    }
}
