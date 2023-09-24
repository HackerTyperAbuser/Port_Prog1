package system.function;


import system.ExitPage;
import system.function.container.ContainerMenu;
import system.function.manager.ManagerMenu;
import system.function.port.*;
import system.function.statistics.StatisticMenu;
import system.function.trip.TripMenu;
import system.function.vehicle.VehicleMenu;

import java.util.InputMismatchException;
import java.util.Scanner;

public class AdminMenuBar {

    public AdminMenuBar() {
        start();
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose an option:");
        System.out.println("1. Port");
        System.out.println("2. Container");
        System.out.println("3. Vehicle");
        System.out.println("4. Trip");
        System.out.println("5. Manager");
        System.out.println("6. Statistics");
        System.out.println("7. Exit App");
        System.out.println("Your choice: ");

        try {
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    new PortMenu();
                    break;
                case 2:
                    new ContainerMenu();
                    break;
                case 3:
                    new VehicleMenu();
                    break;
                case 4:
                    new TripMenu();
                    break;
                case 5:
                    new ManagerMenu();
                    return;
                case 6:
                    new StatisticMenu();
                    return;
                case 7:
                    System.out.println("App exit successfully");
                    new ExitPage();
                    System.exit(0);
                default:
                    System.out.println("Please choose the correct option!");
            }
        } catch (InputMismatchException e) {
            System.out.println("Please pick a number");
            scanner.nextLine(); // Consume the invalid input
        }
    }

}
