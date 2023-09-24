package system.function.vehicle;

import system.PortSystem;
import system.models.*;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AddVehicle {

    public AddVehicle() {
        start();
        new VehicleMenu();
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("===== Adding vehicle ======");
            String vehicleType = selectVehicleType();
            if (vehicleType.equals("QUIT")) {
                break;
            } else {
                String vehicleName = selectVehicleName();
                double vehicleCapacity = selectVehicleCapacity();
                double vehicleFuel = selectVehicleFuel();
                Port port = askForPort(vehicleType);
                if (!vehicleType.equals("SHIP") && !port.isLandable()) {
                    System.out.println("You cannot add truck to this port, it's not landable");
                } else {
                    Vehicle vehicle = createVehicleOfType(vehicleType, vehicleName, vehicleCapacity, vehicleFuel, port);
                    int indexPort = PortSystem.getPortList().indexOf(vehicle.getPort());
                    PortSystem.getPortList().get(indexPort).getListOfVehicles().add(vehicle);
                    PortSystem.getPortList().get(indexPort).addVehicleToList();
                    PortSystem.getVehicleList().add(vehicle);
                    try {
                        PortSystem.writeVehiclesToFile();
                        PortSystem.writePortsToFile();
                    } catch (IOException e) {
                        System.out.println("Write vehicle successful");
                        throw new RuntimeException(e);
                    }
                }
            }
        }

    }

    public String selectVehicleName() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Enter vehicle name: ");
            String name = scanner.nextLine();
            if (name.isEmpty()) {
                System.out.println("Name cannot be empty");
            } else
                return name;
        }
    }

    public double selectVehicleCapacity() {
        Scanner scanner = new Scanner(System.in);
        double capacity;
        while (true) {
            System.out.println("Enter vehicle carrying capacity: ");
            try {
                capacity = scanner.nextDouble();
                if (capacity <= 0) {
                    System.out.println("Capacity must be greater than 0");
                } else {
                    return capacity;
                }
            } catch (InputMismatchException e) {
                System.out.println("Vehicle Capacity must be a number!");
            }
        }
    }

    public double selectVehicleFuel() {
        Scanner scanner = new Scanner(System.in);
        double fuel;
        while (true) {
            System.out.println("Enter vehicle fuel capacity: ");
            try {
                fuel = scanner.nextDouble();
                if (fuel <= 0) {
                    System.out.println("Vehicle fuel must be greater than 0");
                } else {
                    return fuel;
                }
            } catch (InputMismatchException e) {
                System.out.println("Vehicle Fuel must be a number!");
            }
        }
    }


    public String selectVehicleType() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Select what type of vehicle you want to create");
            System.out.println("1. SHIP");
            System.out.println("2. BASIC TRUCK");
            System.out.println("3. REEFER TRUCK");
            System.out.println("4. TANKER TRUCK");
            System.out.println("5. QUIT");
            System.out.println("Your choice:");
            try {
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        // Execute code for Ship
                        System.out.println("You chose Ship");
                        return "SHIP";
                    case 2:
                        // Execute code for Basic Truck
                        System.out.println("You chose Basic Truck");
                        return "BASIC TRUCK";
                    case 3:
                        // Execute code for Reefer Truck
                        System.out.println("You chose Reefer Truck");
                        return "REEFER TRUCK";
                    case 4:
                        // Execute code for Tanker Truck
                        System.out.println("You chose Tanker Truck");
                        return "TANKER TRUCK";
                    case 5:
                        // Execute code for Liquid
                        return "QUIT";
                    default:
                        System.out.println("Invalid choice. Please select a valid option (1-5).");
                }

            } catch (InputMismatchException e) {
                System.out.println("Not a valid choice. Please enter a number (1-5).");
                scanner.nextLine(); // Consume the invalid input
            }
            
        }
    }

    public Port askForPort(String vehicleType) {
        Scanner scanner = new Scanner(System.in);
        boolean portFound = false;
        PortSystem.getPortList().forEach(System.out::println);
        while (true) {
            System.out.println("Enter port ID:");
            try {
                String portID = scanner.nextLine();
                for (Port port : PortSystem.getPortList()) {
                    if (port.getPortID().equalsIgnoreCase(portID)) {
                        portFound = true;
                        if (isForTruck(port, vehicleType))
                            return port;
                    }
                }
                if (!portFound) {
                    System.out.println("This port doesn't exist!");
                }
            } catch (InputMismatchException e) {
                System.out.println("Not a valid choice. Please enter a number (1-5).");
                scanner.nextLine(); // Consume the invalid input
            }
        }
    }

    public boolean isForTruck(Port port, String vehicleType) {
        if ((!vehicleType.equals("SHIP")) && port.isLandable()) {
            return true;
        } else if (vehicleType.equals("SHIP")) {
            return true;
        } else {
            System.out.println("This port doesn't support truck");
            return false;
        }
    }

    private Vehicle createVehicleOfType(String type, String vehicleName, double vehicleCapacity, double vehicleFuel, Port currentPort) {
        switch (type) {
            case "SHIP":
                return new Ship(vehicleName, vehicleCapacity, vehicleFuel, currentPort);
            case "BASIC TRUCK":
                return new BasicTruck(vehicleName, vehicleCapacity, vehicleFuel, currentPort);
            case "REEFER TRUCK":
                return new ReeferTruck(vehicleName, vehicleCapacity, vehicleFuel, currentPort);
            case "TANKER TRUCK":
                return new TankerTruck(vehicleName, vehicleCapacity, vehicleFuel, currentPort);
            default:
                throw new IllegalArgumentException("Invalid vehicle type: " + type);
        }
    }
}
