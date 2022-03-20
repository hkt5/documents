package controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

class UserInterfaceControllerTest {

    private UserInterfaceController userInterfaceController;
    private final InputStream systemIn = System.in;
    private final PrintStream systemOut = System.out;

    private ByteArrayInputStream testIn;
    private ByteArrayOutputStream testOut;

    @BeforeEach
    public void setUp() {
        userInterfaceController = new UserInterfaceController();
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
    }

    private void provideInput(String data) {
        testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }

    private String getOutput() {
        return testOut.toString();
    }

    @AfterEach
    public void restoreSystemInputOutput() {
        System.setIn(systemIn);
        System.setOut(systemOut);
    }

    @Test
    public void userInterfaceGetStrategyTest1() {
        userInterfaceController.userInterfaceGetStrategy();
        final String testString = "1";
        provideInput(testString);

        assertEquals(1, userInterfaceController.userInterfaceGetStrategy());
    }

    @Test
    public void userInterfaceGetStrategyTest2() {
        userInterfaceController.userInterfaceGetStrategy();
        final String testString = "2";
        provideInput(testString);

        assertEquals(2, userInterfaceController.userInterfaceGetStrategy());
    }

    @Test
    public void userInterfaceGetStrategyTest3() {
        final String testString = "3";
        provideInput(testString);

        assertEquals(0, userInterfaceController.userInterfaceGetStrategy());
    }

}