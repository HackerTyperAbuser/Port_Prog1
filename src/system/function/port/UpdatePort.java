package system.function.port;

import system.PortSystem;
import system.models.Port;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UpdatePort {

    public UpdatePort() {
        start();
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        PortSystem.getPortList().forEach(System.out::println);
        Port selectedPort = null;
        while (true) {
            try {
                System.out.println("Enter port ID to modify: ('q' to exit)");
                String portID = scanner.nextLine();
                if (portID.equalsIgnoreCase("q")) {
                    break;
                } else {
                    boolean found = false;

                    for (Port port : PortSystem.getPortList()) {
                        if (port.getPortID().equalsIgnoreCase(portID)) {
                            selectedPort = port;
                            found = true;
                            break; // Stop searching after finding and displaying the container
                        }
                    }

                    if (!found) {
                        System.out.println("Port not found");
                    } else {

                        while (true) {
                            System.out.println("Choose what to update");
                            System.out.println("1. Port Name");
                            System.out.println("2. Port Latitude");
                            System.out.println("3. Port Longitude");
                            System.out.println("4. Port Store Capacity");
                            System.out.println("5. Exit");
                            System.out.println("Your choice: ");

                            try {
                                int choice = scanner.nextInt();

                                switch (choice) {
                                    case 1:
                                        updateName(selectedPort);
                                        break;
                                    case 2:
                                        updateLatitude(selectedPort);
                                        break;
                                    case 3:
                                        updateLongitude(selectedPort);
                                        break;
                                    case 4:
                                        updateStoreCapacity(selectedPort);
                                        break;
                                    case 5:
                                        new PortMenu();
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

    public void updateName(Port port) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter new port name: ");
        String name = scanner.nextLine();
        port.setPortName(name);
        PortSystem.updatePortInfo(port);
    }

    public void updateLatitude(Port port) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Enter new port latitude: ");
            double latitude;
            try {
                latitude = scanner.nextDouble();
                if (latitude > 90 || latitude < -90) {
                    System.out.println("Invalid latitude value");
                } else {
                    port.setLatitude(latitude);
                    PortSystem.updatePortInfo(port);
                    break;
                }

            } catch (InputMismatchException e) {
                System.out.println("Port latitude must be a number!");
                // Clear the invalid input from the scanner buffer
                scanner.nextLine();
            }
        }
    }

    public void updateLongitude(Port port) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Enter new port longitude: ");
            double longitude;
            try {
                longitude = scanner.nextDouble();
                if (longitude > 180 || longitude < -180) {
                    System.out.println("Invalid longitude value");
                } else {
                    port.setLongitude(longitude);
                    PortSystem.updatePortInfo(port);
                    break;
                }

            } catch (InputMismatchException e) {
                System.out.println("Port longitude must be a number!");
                // Clear the invalid input from the scanner buffer
                scanner.nextLine();
            }
        }
    }

    public void updateStoreCapacity(Port port) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Enter new port storing capacity: ");
            double storeCapacity;
            try {
                storeCapacity = scanner.nextDouble();
                if (storeCapacity <= 0) {
                    System.out.println("Store capacity must be greater than 0");
                } else {
                    if (port.getStoreCapacity() > storeCapacity) {
                        port.setRemainCapacity(storeCapacity);
                    } else {
                        port.calculateRemainingCapacity(port.getStoreCapacity() - storeCapacity);
                    }
                    port.setStoreCapacity(storeCapacity);
                    PortSystem.updatePortInfo(port);
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Port Store Capacity must be a number!");
                // Clear the invalid input from the scanner buffer
                scanner.nextLine();
            }
        }
    }

}
