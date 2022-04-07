package logic;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class GetNumberFromUser implements NumberFromUserGetable {

    private KeyboardReader keyboardReader;

    public GetNumberFromUser() {
        keyboardReader = new KeyboardReader(new BufferedReader(new InputStreamReader(System.in)));
    }

    @Override
    public int getNumberFromUser() {
        int selectionOfUserOptions = 0;
        try {
            selectionOfUserOptions = Integer.parseInt(keyboardReader.readLine());
        } catch (NumberFormatException e) {
            selectionOfUserOptions = 0;
        }
        return selectionOfUserOptions;
    }
}
