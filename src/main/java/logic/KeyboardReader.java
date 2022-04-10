package logic;

import ui.Messageble;
import ui.UserInterface;
import java.io.BufferedReader;
import java.io.IOException;

public class KeyboardReader {
    private BufferedReader bufferedReader;
    private Messageble messageble;

    public KeyboardReader(BufferedReader bufferedReader) {
        this.bufferedReader = bufferedReader;
        this.messageble = new UserInterface();
    }

    public String readLine() {
        String typedUserString = "";
        try {
            typedUserString = bufferedReader.readLine();
        } catch (IOException iioException) {
            messageble.getMessage("reader-error");
        }
        return typedUserString;
    }
}
