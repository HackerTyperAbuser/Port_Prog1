package system.function;

import system.ExitPage;
import system.function.container.ContainerMenu;
import system.function.manager.ManagerMenu;
import system.function.port.PortMenu;
import system.function.statistics.StatisticMenu;
import system.function.trip.TripMenu;
import system.function.vehicle.VehicleMenu;
import system.models.Port;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ManagerMenuBar {

    public ManagerMenuBar(Port controlPort) {
        start(controlPort);
    }

    public void start(Port controlPort) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose an option:");
        System.out.println("1. Container");
        System.out.println("2. Vehicle");
        System.out.println("3. Trip");
        System.out.println("4. Port");
        System.out.println("5. Statistics");
        System.out.println("6. Exit App");

        int choice = -1; // Initialize choice with an invalid value

        while (choice < 1 || choice > 5) {
            System.out.print("Your choice: ");

            try {
                choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        new ContainerMenu(controlPort);
                        break;
                    case 2:
                        new VehicleMenu(controlPort);
                        break;
                    case 3:
                        new TripMenu(controlPort);
                        break;
                    case 4:
                        new PortMenu(controlPort);
                        break;
                    case 5:
                        new StatisticMenu();
                        break;
                    case 6:
                        System.out.println("App exit successfully");
                        new ExitPage();
                        System.exit(0);
                    default:
                        System.out.println("Please choose a valid option (1-5).");
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid number (1-5).");
                scanner.nextLine(); // Consume the invalid input
            }
        }
    }
}
