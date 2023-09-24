package system.function.vehicle;

import system.PortSystem;
import system.models.Vehicle;

import java.util.Scanner;

public class RefuelVehicle {

    public RefuelVehicle() {
        start();
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            Vehicle selectedVehicle = null;
            boolean vehicleFound = false;
            System.out.println("===== Vehicle List ======");
            PortSystem.getVehicleList().forEach(System.out::println);
            System.out.println("Enter vehicle ID (or enter 'q' to exit)");
            String vehicleID = scanner.nextLine();

            if (vehicleID.equalsIgnoreCase("q")) {
                break; // Exit the loop and return to the Vehicle Menu
            } else {
                for (Vehicle vehicle : PortSystem.getVehicleList()) {
                    if (vehicle.getId().equalsIgnoreCase(vehicleID)) {
                        selectedVehicle = vehicle;
                        vehicleFound = true;
                        break;
                    }
                }
                if (vehicleFound) {
                    selectedVehicle.refuel();
                    System.out.println("Vehicle has been refueled");
                } else {
                    System.out.println("Vehicle not found!");
                }
            }
        }
    }
}
