package logic;

public class ProgramArgumentsController {

    private static final int USER_DO_NOT_USE_PROGRAM_ARGUMENTS = 0;
    private static final int USER_DECISION_TO_COPY_FILE = 1;
    private static final int USER_DECISION_TO_READ_FILE = 2;
    private static final String STRING_CONTAINS_DASH_AND_COPY_WORD = "-copy";
    private static final String STRING_CONTAINS_DASH_AND_C_CHAR = "-c";
    private static final String STRING_CONTAINS_DASH_AND_READ_WORD = "-read";
    private static final String STRING_CONTAINS_DASH_AND_R_CHAR = "-r";

    public int readProgramArguments(String[] args) {
        if (args[0].equals(STRING_CONTAINS_DASH_AND_COPY_WORD) || args[0].equals(STRING_CONTAINS_DASH_AND_C_CHAR)) {
            return USER_DECISION_TO_COPY_FILE;
        } else if (args[0].equals(STRING_CONTAINS_DASH_AND_READ_WORD) || args[0].equals(STRING_CONTAINS_DASH_AND_R_CHAR)) {
            return USER_DECISION_TO_READ_FILE;
        } else {
            return USER_DO_NOT_USE_PROGRAM_ARGUMENTS;
        }
    }

}
