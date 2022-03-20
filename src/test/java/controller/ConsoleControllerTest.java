package controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConsoleControllerTest {

    private ConsoleController consoleController;
    private String[] readArrayString;
    private String[] shortReadArrayString;
    private String[] copyArrayString;
    private String[] shortCopyArrayString;
    private String[] wrongArrayString;

    @BeforeEach
    void setUp() {
        consoleController = new ConsoleController();
        readArrayString = new String[]{"-read"};
        shortReadArrayString = new String[]{"-r"};
        copyArrayString = new String[]{"-copy"};
        shortCopyArrayString = new String[]{"-c"};
        wrongArrayString = new String[]{"dsdfsf"};
    }

    @Test
    @DisplayName("Argument console checking")
    void testMultiply() {
        assertEquals(1, consoleController.readConsole(copyArrayString), "Parametr -copy should return 1");
        assertEquals(1, consoleController.readConsole(shortCopyArrayString), "Parametr -c should return 1");
        assertEquals(2, consoleController.readConsole(readArrayString), "Parametr -read should return 2");
        assertEquals(2, consoleController.readConsole(shortReadArrayString), "Parametr -r should return 2");
        assertEquals(0, consoleController.readConsole(wrongArrayString), "Wrong parametr should return 0");
    }
}