package system.function.vehicle;

import system.PortSystem;
import system.models.Port;
import system.models.Vehicle;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SearchVehicle {

    public SearchVehicle() {
        start();
    }

    public SearchVehicle(Port controlPort) {
        start(controlPort);
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Enter vehicle ID to view (or 'q' to exit)");
            String vehicleID = scanner.nextLine();

            if (vehicleID.equalsIgnoreCase("q")) {
                System.out.println("View vehicle exited!");
                break; // Exit the loop and return to the Vehicle Menu
            }

            boolean found = false;

            for (Vehicle vehicle : PortSystem.getVehicleList()) {
                if (vehicle.getId().equalsIgnoreCase(vehicleID)) {
                    System.out.println(vehicle);
                    found = true;
                    break; // Stop searching after finding and displaying the vehicle
                }
            }

            if (!found) {
                System.out.println("Vehicle not found");
            }
        }

        new VehicleMenu();
    }

    public void start(Port controlPort) {
        Scanner scanner = new Scanner(System.in);
        List<Vehicle> vehicleList = new ArrayList<>();
        PortSystem.getPortList().forEach(port -> {
            if (port.equals(controlPort)) {
                vehicleList.addAll(port.getListOfVehicles());
            }
        });
        vehicleList.forEach(System.out::println);

        while (true) {
            System.out.println("Enter vehicle ID to view (or 'q' to exit)");
            String vehicleID = scanner.nextLine();

            if (vehicleID.equalsIgnoreCase("q")) {
                System.out.println("View vehicle exited!");
                break; // Exit the loop and return to the Vehicle Menu
            }

            boolean found = false;

            for (Vehicle vehicle : vehicleList) {
                if (vehicle.getId().equalsIgnoreCase(vehicleID)) {
                    System.out.println("Vehicle found:");
                    System.out.println(vehicle);
                    found = true;
                    break; // Stop searching after finding and displaying the vehicle
                }
            }

            if (!found) {
                System.out.println("Vehicle not found");
            }
        }

        new VehicleMenu(controlPort);
    }

}
