package system.function.vehicle;

import system.PortSystem;
import system.models.Vehicle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DeleteVehicle {

    public DeleteVehicle() {
        start();
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        PortSystem.getVehicleList().forEach(System.out::println);
        while (true) {
            System.out.println("Enter vehicle ID to delete (or 'q' to exit)");
            String vehicleID = scanner.nextLine();

            if (vehicleID.equalsIgnoreCase("q")) {
                System.out.println("Delete vehicle exited!");
                return; // Exit the method
            }

            boolean found = false;

            List<Vehicle> vehicleList = new ArrayList<>(PortSystem.getVehicleList());
            for (Vehicle vehicle : vehicleList) {
                if (vehicle.getId().equalsIgnoreCase(vehicleID)) {
                    found = true;

                    if (!vehicle.getListOfContainers().isEmpty()) {
                        System.out.println("There are still containers in this vehicle!");
                    } else {
                        int indexPort = PortSystem.getPortList().indexOf(vehicle.getPort());
                        PortSystem.getPortList().get(indexPort).getListOfVehicles().remove(vehicle);
                        PortSystem.getVehicleList().remove(vehicle);
                        try {
                            PortSystem.writeContainerToFile();
                            PortSystem.writePortsToFile();
                        } catch (IOException e) {
                            System.out.println("Write containers fail!");
                            throw new RuntimeException(e);
                        }
                        System.out.println("Vehicle successfully deleted.");
                    }
                    break; // Found the vehicle, no need to continue searching.
                }
            }

            if (!found) {
                System.out.println("Vehicle not found or has containers inside. Please try again.");
            }
        }
    }
}
