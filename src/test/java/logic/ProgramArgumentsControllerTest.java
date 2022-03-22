package logic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProgramArgumentsControllerTest {

    private ProgramArgumentsController programArgumentsController;
    private String[] readArrayString;
    private String[] shortReadArrayString;
    private String[] copyArrayString;
    private String[] shortCopyArrayString;
    private String[] wrongArrayString;

    @BeforeEach
    void setUp() {
        programArgumentsController = new ProgramArgumentsController();
        copyArrayString = new String[]{"-copy"};
        shortCopyArrayString = new String[]{"-c"};
        readArrayString = new String[]{"-read"};
        shortReadArrayString = new String[]{"-r"};
        wrongArrayString = new String[]{"dsdfsf"};
    }

    @Test
    @DisplayName("Argument console checking")
    void testReadProgramArguments() {
        assertEquals(1, programArgumentsController.readProgramArguments(copyArrayString), "Parameter -copy should return 1");
        assertEquals(1, programArgumentsController.readProgramArguments(shortCopyArrayString), "Parameter -c should return 1");
        assertEquals(2, programArgumentsController.readProgramArguments(readArrayString), "Parameter -read should return 2");
        assertEquals(2, programArgumentsController.readProgramArguments(shortReadArrayString), "Parameter -r should return 2");
        assertEquals(0, programArgumentsController.readProgramArguments(wrongArrayString), "Wrong parameter should return 0");
    }
}