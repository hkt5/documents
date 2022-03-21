package logic;

public class ProgramArgumentsController {

    private static final int userDoNotUseConsole = 0;
    private static final int userDecisionToCopyFile = 1;
    private static final int userDecisionToReadFile = 2;
    private static final String STRING_CONTAINS_DASH_AND_COPY_WORD = "-copy";
    private static final String STRING_CONTAINS_DASH_AND_C_CHAR = "-c";
    private static final String STRING_CONTAINS_DASH_AND_READ_WORD = "-read";
    private static final String STRING_CONTAINS_DASH_AND_R_CHAR = "-c";

    public int readProgramArguments(String[] args) {
        if (args[0].equals(STRING_CONTAINS_DASH_AND_COPY_WORD) || args[0].equals(STRING_CONTAINS_DASH_AND_C_CHAR)) {
            return userDecisionToCopyFile;
            // to do - Warning:(16, 74) Condition 'args[0].equals(STRING_CONTAINS_DASH_AND_R_CHAR)' is always 'false' when reached
        } else if (args[0].equals(STRING_CONTAINS_DASH_AND_READ_WORD) || args[0].equals(STRING_CONTAINS_DASH_AND_R_CHAR)) {
            return userDecisionToReadFile;
        } else {
            return userDoNotUseConsole;
        }
    }

}
