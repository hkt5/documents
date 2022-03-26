package logic;

import ui.Messageble;
import ui.UserInterface;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class KeyboardReader {
    private BufferedReader bufferedReader;
    private Messageble messageble;

    public KeyboardReader() {
        this.bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        this.messageble = new UserInterface();
    }

    public String readLine() {
        try {
            return bufferedReader.readLine();
        } catch (IOException iioException) {
            messageble.getMessage("reader-error");
        }
        return null;
    }
}
