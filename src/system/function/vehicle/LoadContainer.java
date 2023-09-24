package system.function.vehicle;

import system.PortSystem;
import system.models.Container;
import system.models.Vehicle;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LoadContainer {

    public LoadContainer() {
        start();
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        Vehicle selectedVehicle = null;
        boolean vehicleFound = false;
        System.out.println("===== Vehicle List ======");
        PortSystem.getVehicleList().forEach(System.out::println);
        System.out.println("Enter vehicle ID (or enter 'q' to exit");
        String vehicleID = scanner.nextLine();
        if (vehicleID.equalsIgnoreCase("q")) {
            System.out.println("Loading container exited!");
            new VehicleMenu();
        } else {
            for (Vehicle vehicle : PortSystem.getVehicleList()) {
                if (vehicle.getId().equalsIgnoreCase(vehicleID)) {
                    selectedVehicle = vehicle;
                    vehicleFound = true;
                    break;
                }
            }
            if (vehicleFound) {
                loadContainerIntoVehicle(selectedVehicle);
            } else {
                System.out.println("Vehicle not found!");
            }
        }
    }

    public void loadContainerIntoVehicle(Vehicle vehicle) {
        Scanner scanner = new Scanner(System.in);
        boolean continueLoading = true;
        List<Container> containerList = new ArrayList<>();
        PortSystem.getPortList().forEach(port -> {
            if (port.equals(vehicle.getPort()))
                containerList.addAll(port.getListOfContainers());
        });

        while (true) {
            containerList.forEach(System.out::println);
            System.out.println("Choose a container ID to start (or enter 'q' to exit):");
            String containerID = scanner.nextLine();
            boolean containerFound = false;
            if (containerID.equalsIgnoreCase("q")) {
                break; // Exit the loop
            } else {

                for (Container container : containerList) {
                    if (container.getId().equalsIgnoreCase(containerID)) {
                        containerFound = true;
                        if (vehicle.loadContainer(container)) {
                            containerList.remove(container);
                        }
                        break;
                    }
                }

                if (!containerFound) {
                    System.out.println("Vehicle not found");
                }
            }
        }
    }
}
