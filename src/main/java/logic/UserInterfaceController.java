package logic;

import ui.UserInterface;

import java.util.Scanner;

public class UserInterfaceController {

    private static final int userOptionIsCopyFile = 1;
    private static final int userOptionIsReadFile = 2;

    public int getUserDecision() {
        UserInterface.showInterface();
        Scanner scanner = new Scanner(System.in);
        int selectionOfUserOptionsIsOneOrTwo;
        do {
            UserInterface.showMessageAfterBadChoose();
            selectionOfUserOptionsIsOneOrTwo = scanner.nextInt();
        } while (selectionOfUserOptionsIsOneOrTwo != userOptionIsCopyFile && selectionOfUserOptionsIsOneOrTwo != userOptionIsReadFile);
        scanner.close();
        return selectionOfUserOptionsIsOneOrTwo;
    }
}
