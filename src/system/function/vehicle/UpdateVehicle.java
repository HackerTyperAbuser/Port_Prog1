package system.function.vehicle;

import system.PortSystem;
import system.function.container.ContainerMenu;
import system.models.Container;
import system.models.Vehicle;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UpdateVehicle {

    public UpdateVehicle() {
        start();
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        PortSystem.getVehicleList().forEach(System.out::println);
        Vehicle selectedVehicle = null;
        while (true) {
            try {
                System.out.println("Enter vehicle ID to modify: ('q' to exit)");
                String vehicleID = scanner.nextLine();
                if (vehicleID.equalsIgnoreCase("q")) {
                    break;
                } else {
                    boolean found = false;

                    for (Vehicle vehicle : PortSystem.getVehicleList()) {
                        if (vehicle.getId().equalsIgnoreCase(vehicleID)) {
                            selectedVehicle = vehicle;
                            found = true;
                            break; // Stop searching after finding and displaying the container
                        }
                    }

                    if (!found) {
                        System.out.println("Vehicle not found");
                    } else {

                        while (true) {
                            System.out.println("Choose what to update");
                            System.out.println("1. Vehicle Name");
                            System.out.println("2. Vehicle Carry Capacity");
                            System.out.println("3. Vehicle Fuel Capacity");
                            System.out.println("4. Exit");
                            System.out.println("Your choice: ");

                            try {
                                int choice = scanner.nextInt();

                                switch (choice) {
                                    case 1:
                                        updateName(selectedVehicle);
                                        break;
                                    case 2:
                                        updateCarryCapacity(selectedVehicle);
                                        break;
                                    case 3:
                                        updateFuelCapacity(selectedVehicle);
                                        break;
                                    case 4:
                                        new VehicleMenu();
                                        return;
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
            } catch (InputMismatchException e) {
                System.out.println("Please pick a number");
                scanner.nextLine(); // Consume the invalid input
            }
        }
    }

    public void updateName(Vehicle vehicle) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter new vehicle name: ");
        String name = scanner.nextLine();
        vehicle.setVehicleName(name);
        PortSystem.updateVehicleInfo(vehicle);
    }

    public void updateCarryCapacity(Vehicle vehicle) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Vehicle Carrying Capacity: ");
            double carryCapacity;
            try {
                carryCapacity = scanner.nextDouble();
                if (carryCapacity <= 0) {
                    System.out.println("Carry capacity must be greater than 0");
                } else {
                    vehicle.calculateRemainingCapacity(vehicle.getCarryCapacity() - carryCapacity);
                    vehicle.setCarryCapacity(carryCapacity);
                    PortSystem.updateVehicleInfo(vehicle);
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Vehicle Carry Capacity must be a number!");
                // Clear the invalid input from the scanner buffer
                scanner.nextLine();
            }
        }
    }

    public void updateFuelCapacity(Vehicle vehicle) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Vehicle Fuel Capacity: ");
            double fuelCapacity;
            try {
                fuelCapacity = scanner.nextDouble();
                if (fuelCapacity <= 0) {
                    System.out.println("Fuel capacity must be greater than 0");
                } else {
                    if (vehicle.getFuelCapacity() > fuelCapacity) {
                        vehicle.setRemainFuel(fuelCapacity);
                    }
                    vehicle.setFuelCapacity(fuelCapacity);
                    PortSystem.updateVehicleInfo(vehicle);
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Vehicle Fuel Capacity must be a number!");
                // Clear the invalid input from the scanner buffer
                scanner.nextLine();
            }
        }
    }


}
