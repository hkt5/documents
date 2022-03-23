package logic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.*;
import static org.junit.jupiter.api.Assertions.*;

class UserInterfaceControllerTest {

    private UserInterfaceController userInterfaceController;

    @BeforeEach
    public void setUp() {
        userInterfaceController = new UserInterfaceController();
    }

    @Test
    public void userInterfaceGetStrategyTest1() {
        String input = "1";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        assertEquals(1, userInterfaceController.getUserDecision());
    }

    @Test
    public void userInterfaceGetStrategyTest2() {
        String input = "2";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        assertEquals(2, userInterfaceController.getUserDecision());
    }

    @Test
    public void userInterfaceGetStrategyTest3() {
        String input = "3";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        assertEquals(0, userInterfaceController.getUserDecision());
    }

}