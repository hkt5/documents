import logic.*;
import data.UserOptions;

public class ExecutableDocuments {

    private static final int ARGS_IS_EMPTY = 0;
    private static final int USER_DO_NOT_USE_CONSOLE = 0;
    private static final int USER_OPTION_IS_COPY_FILE = 1;
    private static final int USER_OPTION_IS_READ_FILE = 2;
    private UserInterfaceController userInterfaceController =new UserInterfaceController();
    private ProgramArgumentsController programArgumentsController = new ProgramArgumentsController();
    private FileStrategy fileStrategy = null;
    private UserOptions userOptions = new UserOptions();

    public static void main(String[] args)  {
        ExecutableDocuments executableDocuments = new ExecutableDocuments();
        executableDocuments.run(args);
    }

    private void run(String[] args) {
        if (args.length > ARGS_IS_EMPTY && programArgumentsController.readProgramArguments(args) != USER_DO_NOT_USE_CONSOLE) {
            userOptions.setStrategy(programArgumentsController.readProgramArguments(args));
        } else {
            userOptions.setStrategy(userInterfaceController.getUserDecision());
        }
        if (userOptions.getStrategy() == USER_OPTION_IS_COPY_FILE) fileStrategy = new CopyFile();
        if (userOptions.getStrategy() == USER_OPTION_IS_READ_FILE) fileStrategy = new ReadFile();
    }
}
