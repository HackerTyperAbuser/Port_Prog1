package system.function.manager;

import system.function.AdminMenuBar;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ManagerMenu {

    public ManagerMenu() {
        start();
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. View Manager");
            System.out.println("2. Add Manager");
            System.out.println("3. Search Manager");
            System.out.println("4. Delete Manager");
            System.out.println("5. Update Manager");
            System.out.println("6. Back to Menu");
            System.out.println("Your choice: ");

            try {
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        new ViewManager();
                        break;
                    case 2:
                        new AddManager();
                        break;
                    case 3:
                        new SearchManager();
                        break;
                    case 4:
                        new DeleteManager();
                        break;
                    case 5:
                        new UpdateManager();
                        break;
                    case 6:
                        new AdminMenuBar();
                        return; // Exit the method and return to the previous menu
                    default:
                        System.out.println("Please choose the correct option!");
                }
            } catch (InputMismatchException e) {
                System.out.println("Please pick a number");
                scanner.nextLine(); // Consume the invalid input
            }
        }
    }
}
