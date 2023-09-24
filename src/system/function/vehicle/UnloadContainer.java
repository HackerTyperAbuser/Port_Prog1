package system.function.vehicle;

import system.PortSystem;
import system.models.Vehicle;

import java.util.Scanner;

public class UnloadContainer {

    public UnloadContainer() {
        start();
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            boolean found = false;
            System.out.println("===== Vehicle List =====");
            PortSystem.getVehicleList().forEach(System.out::println);
            System.out.println("Enter vehicle ID to unload (or 'q' to exit)");
            String vehicleID = scanner.nextLine();

            if (vehicleID.equalsIgnoreCase("q")) {
                System.out.println("View vehicle exited!");
                break; // Exit the loop and return to the Vehicle Menu
            }

            for (Vehicle vehicle : PortSystem.getVehicleList()) {
                if (vehicle.getId().equalsIgnoreCase(vehicleID)) {
                    found = true;
                    vehicle.unloadContainers();
                    break; // Stop searching after finding and displaying the vehicle
                }
            }

            if (!found) {
                System.out.println("Vehicle not found");
            }
        }
    }
}
