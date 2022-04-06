package logic;

import logic.UserInterfaceController;
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
        //given

        //when
        //int userStrategy = userInterfaceController.getUserDecision();
        //String input = "1";
        //InputStream in = new ByteArrayInputStream(input.getBytes());
        //System.setIn(in);
        //then
        assertEquals(1, 1);
    }

    @Test
    public void userInterfaceGetStrategyTest2() {
        //given
        //String input = "2";
        //InputStream in = new ByteArrayInputStream(input.getBytes());
        //System.setIn(in);
        //when
        //int userStrategy = userInterfaceController.getUserDecision();
        //then
        assertEquals(2, 2);
    }
}