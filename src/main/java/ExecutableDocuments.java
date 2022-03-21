import logic.*;
import data.UserOptions;

public class ExecutableDocuments {

    private static final int argsLengthIsZero = 0;
    private static final int userDoNotUseConsole = 0;
    private static final int userOptionIsCopyFile = 1;
    private static final int userOptionIsReadFile = 2;

    public static void main(String[] args)  {
        UserOptions userOptions = new UserOptions();
        UserInterfaceController userInterfaceController = new UserInterfaceController();
        ProgramArgumentsController programArgumentsController = new ProgramArgumentsController();
        FileStrategy fileStrategy = null;
        if (args.length > argsLengthIsZero) {
            if (programArgumentsController.readProgramArguments(args) != userDoNotUseConsole) userOptions.setStrategy(programArgumentsController.readProgramArguments(args));
        } else {
            userOptions.setStrategy(userInterfaceController.getUserDecision());
        }
        if (userOptions.getStrategy() == userOptionIsCopyFile) fileStrategy = new CopyFile();
        if (userOptions.getStrategy() == userOptionIsReadFile) fileStrategy = new ReadFile();
    }
}
