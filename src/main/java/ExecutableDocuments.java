
import data.ResultData;
import logic.*;
import data.UserOptions;
import ui.UserInterface;

public class ExecutableDocuments {

    private static final int ARGS_IS_EMPTY = 0;
    private static final int USER_DO_NOT_USE_CONSOLE = 0;
    private static final int USER_OPTION_IS_COPY_FILE = 1;
    private static final int USER_OPTION_IS_READ_FILE = 2;
    private static final int USER_OPTION_IS_COMPARE_FILE = 3;
    private static final int USER_OPTION_IS_MACRO_EXTRACTOR = 4;
    private UserInterfaceController userInterfaceController;
    private ProgramArgumentsController programArgumentsController;
    private UserOptions userOptions;
    private FileStrategy fileStrategy;
    private GetNumberFromUser getNumberFromUser;
    private UserInterface userInterface;

    public ExecutableDocuments() {
        this.userInterfaceController = new UserInterfaceController();
        this.programArgumentsController = new ProgramArgumentsController();
        this.userOptions = new UserOptions();
        this.fileStrategy = null;
        this.getNumberFromUser = new GetNumberFromUser();
        this.userInterface = new UserInterface();
    }

    public static void main(String[] args)  {
        ExecutableDocuments executableDocuments = new ExecutableDocuments();
        executableDocuments.run(args);
    }

    private void run(String[] args) {
        if (args.length > ARGS_IS_EMPTY && programArgumentsController.readProgramArguments(args) != USER_DO_NOT_USE_CONSOLE) {
            userOptions.setStrategy(programArgumentsController.readProgramArguments(args));
        } else {
            userOptions.setStrategy(userInterfaceController.getUserDecision(getNumberFromUser));
        }
        if (userOptions.getStrategy() == USER_OPTION_IS_COPY_FILE) {
            fileStrategy = new CopyFile();
        } else if (userOptions.getStrategy() == USER_OPTION_IS_READ_FILE)  {
            fileStrategy = new ReadFile();
        } else if (userOptions.getStrategy() == USER_OPTION_IS_COMPARE_FILE) {
            fileStrategy = new CompareFile();
        } else if (userOptions.getStrategy() == USER_OPTION_IS_MACRO_EXTRACTOR) {
            fileStrategy = new MacroExtractor();
        }
        // for test
        ResultData resultData = fileStrategy.perform();
        userInterface.getMessage(resultData.getResultMassage());
        if (resultData.getResultData() != null) userInterface.getMessage(resultData.getResultData());
    }
}
