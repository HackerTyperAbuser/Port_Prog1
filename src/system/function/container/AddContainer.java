package system.function.container;

import system.PortSystem;
import system.models.Container;
import system.models.Port;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AddContainer {

    public AddContainer() {
        start();
    }

    public AddContainer(Port controlPort) {
        start(controlPort);
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.println("Enter container weight: ");
            double containerWeight;
            try {
                containerWeight = scanner.nextDouble();
                if (containerWeight <= 0) {
                    System.out.println("Container weight must be greater than 0");
                } else {
                    Container.ContainerType containerType = askForContainerType();
                    while (true) {
                        Port port = askForPort();
                        if (containerWeight > port.getRemainCapacity()) {
                            System.out.println("This port doesn't have enough storage");
                        } else {
                            Container container = new Container(containerWeight, containerType, port);
                            PortSystem.getContainerList().add(container);
                            int indexPort = PortSystem.getPortList().indexOf(container.getCurrentPort());
                            PortSystem.getPortList().get(indexPort).getListOfContainers().add(container);
                            PortSystem.getPortList().get(indexPort).calculateRemainingCapacity(containerWeight);
                            PortSystem.getPortList().get(indexPort).addContainerToList();
                            PortSystem.writeContainerToFile();
                            PortSystem.writePortsToFile();
                            new ContainerMenu();
                        }
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println("Container weight must be a number!");
                start();
            } catch (IOException e) {
                System.out.println("Write container fail");
                throw new RuntimeException(e);
            }
        }
    }

    public void start(Port controlPort) {
        System.out.println("THIS IS MANAGER VERSION");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Enter container weight: ");
            double containerWeight;
            try {
                containerWeight = scanner.nextDouble();
                if (containerWeight <= 0) {
                    System.out.println("Container weight must be greater than 0");
                } else {
                    if (containerWeight > controlPort.getRemainCapacity()) {
                        System.out.println("Cannot add this container to the port, there are not enough storage!");
                    } else {
                        Container.ContainerType containerType = askForContainerType();
                        Container container = new Container(containerWeight, containerType, controlPort);
                        PortSystem.getContainerList().add(container);
                        int indexPort = PortSystem.getPortList().indexOf(controlPort);
                        PortSystem.getPortList().get(indexPort).getListOfContainers().add(container);
                        PortSystem.getPortList().get(indexPort).calculateRemainingCapacity(containerWeight);
                        PortSystem.getPortList().get(indexPort).addContainerToList();
                        PortSystem.writeContainerToFile();
                        PortSystem.writePortsToFile();
                        new ContainerMenu(controlPort);
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println("Container weight must be a number!");
                start();
            } catch (IOException e) {
                System.out.println("Write container fail");
                throw new RuntimeException(e);
            }
        }

    }

    public Container.ContainerType askForContainerType() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Choose a container type");
            System.out.println("1. Dry Storage");
            System.out.println("2. Open Top");
            System.out.println("3. Open Side");
            System.out.println("4. Refrigerate");
            System.out.println("5. Liquid");

            try {
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        // Execute code for Dry Storage
                        System.out.println("You chose Dry Storage");
                        return Container.ContainerType.DRY_STORAGE;
                    case 2:
                        // Execute code for Open Top
                        System.out.println("You chose Open Top");
                        return Container.ContainerType.OPEN_TOP;
                    case 3:
                        // Execute code for Open Side
                        System.out.println("You chose Open Side");
                        return Container.ContainerType.OPEN_SIDE;
                    case 4:
                        // Execute code for Refrigerate
                        System.out.println("You chose Refrigerate");
                        return Container.ContainerType.REFRIGERATED;
                    case 5:
                        // Execute code for Liquid
                        System.out.println("You chose Liquid");
                        return Container.ContainerType.LIQUID;
                    default:
                        System.out.println("Invalid choice. Please select a valid option (1-5).");
                }

            } catch (InputMismatchException e) {
                System.out.println("Not a valid choice. Please enter a number (1-5).");
                scanner.nextLine(); // Consume the invalid input
            }
        }
    }

    public Port askForPort() {
        Scanner scanner = new Scanner(System.in);
        boolean portFound = false;
        while (true) {
            System.out.println("===== Port List =====");
            PortSystem.getPortList().forEach(System.out::println);
            System.out.println("Enter port ID:");
            try {
                String portID = scanner.nextLine();
                for (Port port : PortSystem.getPortList()) {
                    if (port.getPortID().equalsIgnoreCase(portID)) {
                        portFound = true;
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
}
