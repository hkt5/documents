package ui;

public class UserInterface implements Messageble{

    public static void showInterface() {
        System.out.println("What do you want to do?");
        System.out.println("1. Copy File");
        System.out.println("2. Compare File");
    }

    public static void showMessageAfterBadChoose() {
        System.out.println("Bad choice! You can only choose 1 or 2");
    }

    @Override
    public String getMessage(String messageName) {
        return null;
    }
}
