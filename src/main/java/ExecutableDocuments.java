import controller.ConsoleController;
import controller.UserInterfaceController;
import model.UserOptions;
import java.io.File;
import java.util.Scanner;

public class ExecutableDocuments {

    private ConsoleController consoleController;

    public static void main(String[] args)  {
        UserOptions userOptions = new UserOptions();
        UserInterfaceController userInterfaceController = new UserInterfaceController();
        FileStrategy fileStrategy = null;
        if (args.length > 0) {
            if (ConsoleController.readConsole(args) != 2) userOptions.setStrategy(ConsoleController.readConsole(args));
        } else {
            int numberChosenByUser;
            do {
                numberChosenByUser= userInterfaceController.userInterfaceGetStrategy();
                System.out.println("Bad choice! You can only choose 1 or 2");
            } while (numberChosenByUser == 0);
            userOptions.setStrategy(numberChosenByUser);
        }
        if (userOptions.getStrategy() == 1) fileStrategy = new CopyFile();
        if (userOptions.getStrategy() == 2) fileStrategy = new ReadFile();
    }
}
