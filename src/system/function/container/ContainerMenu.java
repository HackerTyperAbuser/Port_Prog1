package system.function.container;

import system.function.AdminMenuBar;
import system.function.ManagerMenuBar;
import system.function.port.AddPort;
import system.function.port.DeletePort;
import system.function.port.SearchPort;
import system.function.port.ViewPort;
import system.models.Port;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ContainerMenu {

    public ContainerMenu() {
        start();
    }

    public ContainerMenu(Port controlPort) {
        start(controlPort);
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. View Container");
            System.out.println("2. Add Container");
            System.out.println("3. Search Container");
            System.out.println("4. Delete Container");
            System.out.println("5. Update Container");
            System.out.println("6. Back to Menu");
            System.out.println("Your choice: ");

            try {
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        new ViewContainer();
                        break;
                    case 2:
                        new AddContainer();
                        break;
                    case 3:
                        new SearchContainer();
                        break;
                    case 4:
                        new DeleteContainer();
                        break;
                    case 5:
                        new UpdateContainer();
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
            System.out.println("1. View Container");
            System.out.println("2. Add Container");
            System.out.println("3. Search Container");
            System.out.println("4. Delete Container");
            System.out.println("5. Update Container");
            System.out.println("6. Back to Menu");
            System.out.println("Your choice: ");

            try {
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        new ViewContainer(controlPort);
                        break;
                    case 2:
                        new AddContainer(controlPort);
                        break;
                    case 3:
                        new SearchContainer(controlPort);
                        break;
                    case 4:
                        new DeleteContainer(controlPort);
                        break;
                    case 5:
                        new UpdateContainer(controlPort);
                        break;
                    case 6:
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
