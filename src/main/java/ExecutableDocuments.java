import model.UserOptions;
import java.io.File;
import java.util.Scanner;

public class ExecutableDocuments {


    public static void main(String[] args)  {
        UserOptions userOptions = new UserOptions();
        FileStrategy fileStrategy = null;
        if (args.length > 0) {
            //to do
        } else {
            try {
                userOptions.setStrategy(userInterfaceGetStrategy());
            } catch (Exception e) {
                System.out.println("You can choose only 1 or 2");
            }
        }
        if (userOptions.getStrategy() == 1) fileStrategy = new CopyFile();
        if (userOptions.getStrategy() == 2) fileStrategy = new ReadFile();
        fileStrategy.perform(userInterfaceGetFile());
    }

    private static int userInterfaceGetStrategy() throws Exception {
        showInterface();
        Scanner scanner = new Scanner(System.in);
        int selectionOfUserOptionsIsOneOrTwo = scanner.nextInt();
        if (selectionOfUserOptionsIsOneOrTwo != 1 || selectionOfUserOptionsIsOneOrTwo !=2) throw new Exception("The function matches only number: 1,2");
        scanner.close();
        return selectionOfUserOptionsIsOneOrTwo;
    }

    private static File userInterfaceGetFile() {
        Scanner scanner = new Scanner(System.in);
        File fileToPerformOperation = new File(scanner.nextLine());
        return fileToPerformOperation;
    }

    private static void showInterface() {
        System.out.println("What do you want to do?");
        System.out.println("1. Copy File");
        System.out.println("2. Compare File");
    }
}
