package system.function.trip;

import system.function.AdminMenuBar;
import system.function.ManagerMenuBar;
import system.function.port.AddPort;
import system.function.port.DeletePort;
import system.function.port.SearchPort;
import system.function.port.ViewPort;
import system.models.Port;

import java.util.InputMismatchException;
import java.util.Scanner;

public class TripMenu {

    public TripMenu() {
        start();
    }

    public TripMenu(Port controlPort) {
        start(controlPort);
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. View Trips");
            System.out.println("2. Update Trips");
            System.out.println("3. Back To Menu");
            System.out.println("Your choice: ");

            try {
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        new ViewTrip();
                        break;
                    case 2:
                        new UpdateTrip();
                        break;
                    case 3:
                        new AdminMenuBar();
                        return;
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
            System.out.println("1. View Trips");
            System.out.println("2. Back To Menu");
            System.out.println("Your choice: ");

            try {
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        new ViewTrip(controlPort);
                        break;
                    case 2:
                        new ManagerMenuBar(controlPort);
                        break;
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
