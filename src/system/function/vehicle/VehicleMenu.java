package system.function.vehicle;

import system.function.AdminMenuBar;
import system.function.ManagerMenuBar;
import system.function.port.AddPort;
import system.function.port.DeletePort;
import system.function.port.SearchPort;
import system.function.port.ViewPort;
import system.models.Port;

import java.util.InputMismatchException;
import java.util.Scanner;

public class VehicleMenu {

    public VehicleMenu() {
        start();
    }

    public VehicleMenu(Port controlPort) {
        start(controlPort);
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. View Vehicle");
            System.out.println("2. Add Vehicle");
            System.out.println("3. Search Vehicle");
            System.out.println("4. Delete Vehicle");
            System.out.println("5. Update Vehicle");
            System.out.println("6. Load Container");
            System.out.println("7. Unload Container");
            System.out.println("8. Move To Port");
            System.out.println("9. Refuel Vehicle");
            System.out.println("10. Back to Menu");
            System.out.println("Your choice: ");

            try {
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        new ViewVehicle();
                        break;
                    case 2:
                        new AddVehicle();
                        break;
                    case 3:
                        new SearchVehicle();
                        break;
                    case 4:
                        new DeleteVehicle();
                        break;
                    case 5:
                        new UpdateVehicle();
                        break;
                    case 6:
                        new LoadContainer();
                        break;
                    case 7:
                        new UnloadContainer();
                        break;
                    case 8:
                        new MoveToPort();
                        break;
                    case 9:
                        new RefuelVehicle();
                        break;
                    case 10:
                        new AdminMenuBar();
                        return; // Exit the method
                    default:
                        System.out.println("Please choose a valid option!");
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid number.");
                scanner.nextLine(); // Consume the invalid input
            }
        }
    }

    public void start(Port controlPort) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. View Vehicle");
            System.out.println("2. Search Vehicle");
            System.out.println("3. Back to Menu");
            System.out.println("Your choice: ");

            try {
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        new ViewVehicle(controlPort);
                        break;
                    case 2:
                        new SearchVehicle(controlPort);
                        break;
                    case 3:
                        new ManagerMenuBar(controlPort);
                        return;
                    default:
                        System.out.println("Please choose a valid option!");
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid number.");
                scanner.nextLine(); // Consume the invalid input
            }
        }
    }
}
