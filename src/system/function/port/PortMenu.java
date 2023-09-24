package system.function.port;

import system.function.AdminMenuBar;
import system.function.ManagerMenuBar;
import system.models.Port;

import java.util.InputMismatchException;
import java.util.Scanner;

public class PortMenu {

    public PortMenu() {
        start();
    }

    public PortMenu(Port controlPort) {
        start(controlPort);
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. View Port");
            System.out.println("2. Add Port");
            System.out.println("3. Search Port");
            System.out.println("4. Delete Port");
            System.out.println("5. Update Port");
            System.out.println("6. Back to Menu");
            System.out.println("Your choice: ");

            try {
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        new ViewPort();
                        break;
                    case 2:
                        new AddPort();
                        break;
                    case 3:
                        new SearchPort();
                        break;
                    case 4:
                        new DeletePort();
                        break;
                    case 5:
                        new UpdatePort();
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

    public void start(Port controlPort) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. My Port");
            System.out.println("2. Update Port Information");
            System.out.println("3. Exit");
            System.out.println("Your choice: ");

            try {
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        new ViewPort(controlPort);
                        break;
                    case 2:
                        new ManagerMenuBar(controlPort);
                        break;
                    case 5:
                        new ManagerMenuBar(controlPort);
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

