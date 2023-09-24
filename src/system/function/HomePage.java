package system.function;
import system.ExitPage;

import java.util.InputMismatchException;
import java.util.Scanner;

public class HomePage {

    public HomePage() {
        System.out.println("Welcome to Port Management System :)))");
        start();
    }


    public void start() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1: Login");
            System.out.println("2: Exit");
            System.out.println("Your choice: ");
            try {
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        new LoginPage();
                        break;
                    case 2:
                        System.out.println("App exit successfully");
                        new ExitPage();
                        System.exit(0);
                    default:
                        System.out.println("Please choose the correct option");
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter a number!");
                start();
            }
        }

    }
}
