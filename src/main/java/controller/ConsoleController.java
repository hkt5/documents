package controller;

public class ConsoleController {

    public static int readConsole(String[] args) {
        if (args[0].equals("-copy") || args[0].equals("-c")) {
            return 1;
        } else if (args[0].equals("-read") || args[0].equals("-r")) {
            return 2;
        } else {
            return 0;
        }
    }

}
