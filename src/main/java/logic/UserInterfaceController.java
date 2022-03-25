package logic;

import data.Files;
import ui.Messageble;
import ui.UserInterface;
import java.io.File;
import java.util.List;
import java.util.Scanner;

public class UserInterfaceController {

    private static final int USER_OPTION_IS_COPY_FILE = 1;
    private static final int USER_OPTION_IS_READ_FILE = 2;
    private static final int GREATER_THAN_ZERO = 0;
    private final Messageble messageble = new UserInterface();

    public int getUserDecision() {
        messageble.getMessage("welcome-message");
        Scanner scanner = new Scanner(System.in);
        int selectionOfUserOptions = scanner.nextInt();
        while (selectionOfUserOptions != USER_OPTION_IS_COPY_FILE && selectionOfUserOptions != USER_OPTION_IS_READ_FILE) {
            messageble.getMessage("bad-choose");
            selectionOfUserOptions = scanner.nextInt();
        }
        scanner.close();
        return selectionOfUserOptions;
    }

    public List<File> getListOfFiles() {
        messageble.getMessage("please-enter-the-path");
        Scanner scanner = new Scanner(System.in);
        File file = new File(scanner.nextLine());
        if (file.exists()) return null;
        return null;
    }

    private void readPath(File file) throws Exception {
        if (!file.exists()) throw new Exception("Path to file or directory not found");
        if (file.isDirectory()) {
            readDirectory(file);
        } else {
            readFile(file);
        }
    }

    private void readFile(File file) {


    }

    private void readDirectory(File file) {

    }

    private Boolean checkExtensionIsDocxOrXlsxOrPdf(File file) {
        String fileName = file.toString();
        int index = fileName.lastIndexOf('.');
        if (index > GREATER_THAN_ZERO) {
            String fileExtension = fileName.substring(index + 1);
            switch (fileExtension) {
                case "docx":
                case "xlsx":
                case "pdf":
                    return Boolean.TRUE;
                default:
                    return Boolean.FALSE;
            }
        }
        return Boolean.FALSE;
    }
}
