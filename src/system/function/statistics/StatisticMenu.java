package system.function.statistics;

import system.function.AdminMenuBar;

import java.util.InputMismatchException;
import java.util.Scanner;

public class StatisticMenu {

    public StatisticMenu() {
        start();
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Find Trips");
            System.out.println("2. Total Container Weight");
            System.out.println("3. Fuel Usage For Today");
            System.out.println("4. Ships In Port");
            System.out.println("5. Back to Menu");
            System.out.println("Your choice: ");

            try {
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        new FindTrips();
                        break;
                    case 2:
                        new TotalContainerWeight();
                        break;
                    case 3:
                        new FuelUsedToday();
                        break;
                    case 4:
                        new ListShipInPort();
                        break;
                    case 5:
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
