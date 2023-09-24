package system.function.vehicle;

import system.PortSystem;
import system.models.Port;
import system.models.Vehicle;

import java.util.Scanner;

public class MoveToPort {

    public MoveToPort() {
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
                System.out.println("Move to port exited!");
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
                    moveVehicleToPort(selectedVehicle);
                } else {
                    System.out.println("Vehicle not found!");
                }
            }
        }
    }


    public void moveVehicleToPort(Vehicle vehicle) {
        Scanner scanner = new Scanner(System.in);
        Port selectedPort = null;
        boolean portFound = false;

        while (true) {
            PortSystem.getPortList().forEach(System.out::println);
            System.out.println("Enter the port ID that you want to move the vehicle to (or enter 'q' to exit): ");
            String portID = scanner.nextLine();

            if (portID.equalsIgnoreCase("q")) {
                System.out.println("Move vehicle to port exited!");
                break; // Exit the loop and return to the previous menu
            }

            for (Port port : PortSystem.getPortList()) {
                if (port.getPortID().equalsIgnoreCase(portID)) {
                    selectedPort = port;
                    portFound = true;
                    break;
                }
            }

            if (!portFound) {
                System.out.println("Port with ID " + portID + " not found. Please enter a valid port ID.");
            } else {
                if (vehicle.moveToPort(selectedPort))
                    break;
            }
        }
    }

}
