package controller;

import java.util.Scanner;

public class UserInterfaceController {
    public int userInterfaceGetStrategy() {
        showInterface();
        Scanner scanner = new Scanner(System.in);
        int selectionOfUserOptionsIsOneOrTwo = scanner.nextInt();
        if (selectionOfUserOptionsIsOneOrTwo != 1 && selectionOfUserOptionsIsOneOrTwo != 2) return 0;
        scanner.close();
        return selectionOfUserOptionsIsOneOrTwo;
    }

    public static  void showInterface() {
        System.out.println("What do you want to do?");
        System.out.println("1. Copy File");
        System.out.println("2. Compare File");
    }
}
