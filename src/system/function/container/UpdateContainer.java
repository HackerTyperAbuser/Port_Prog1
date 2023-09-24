package system.function.container;

import system.PortSystem;
import system.models.Container;
import system.models.Port;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UpdateContainer {

    public UpdateContainer() {
        start();
    }

    public UpdateContainer(Port controlPort) {
        start(controlPort);
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        PortSystem.getContainerList().forEach(System.out::println);
        Container selectedContainer = null;
        while (true) {
            try {
                System.out.println("Enter container ID to modify: ('q' to exit)");
                String containerID = scanner.nextLine();
                if (containerID.equalsIgnoreCase("q")) {
                    break;
                } else {
                    boolean found = false;

                    for (Container container : PortSystem.getContainerList()) {
                        if (container.getId().equalsIgnoreCase(containerID)) {
                            selectedContainer = container;
                            found = true;
                            break; // Stop searching after finding and displaying the container
                        }
                    }

                    if (!found) {
                        System.out.println("Container not found");
                    } else {

                        while (true) {
                            System.out.println("Choose what to update");
                            System.out.println("1. Container Weight");
                            System.out.println("2. Container Type");
                            System.out.println("3. Exit");
                            System.out.println("Your choice: ");

                            try {
                                int choice = scanner.nextInt();

                                switch (choice) {
                                    case 1:
                                        updateWeight(selectedContainer);
                                        break;
                                    case 2:
                                        updateContainerType(selectedContainer);
                                        break;
                                    case 3:
                                        new ContainerMenu();
                                        break;
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

    public void start(Port controlPort) {
        Scanner scanner = new Scanner(System.in);
        controlPort.getListOfContainers().forEach(System.out::println);
        Container selectedContainer = null;
        while (true) {
            try {
                System.out.println("Enter container ID to modify: ('q' to exit)");
                String containerID = scanner.nextLine();
                if (containerID.equalsIgnoreCase("q")) {
                    break;
                } else {
                    boolean found = false;

                    for (Container container : PortSystem.getContainerList()) {
                        if (container.getId().equalsIgnoreCase(containerID)) {
                            selectedContainer = container;
                            found = true;
                            break; // Stop searching after finding and displaying the container
                        }
                    }

                    if (!found) {
                        System.out.println("Container not found");
                    } else {

                        while (true) {
                            System.out.println("Choose what to update");
                            System.out.println("1. Container Weight");
                            System.out.println("2. Container Type");
                            System.out.println("3. Exit");
                            System.out.println("Your choice: ");

                            try {
                                int choice = scanner.nextInt();

                                switch (choice) {
                                    case 1:
                                        updateWeight(selectedContainer);
                                        break;
                                    case 2:
                                        updateContainerType(selectedContainer);
                                        break;
                                    case 3:
                                        new ContainerMenu();
                                        break;
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


    public void updateContainerType(Container container) {
        Scanner scanner = new Scanner(System.in);
        boolean exitLoop = false; // Add a flag to control the loop

        while (!exitLoop) { // Use the flag to control the loop
            System.out.println("Choose a container type");
            System.out.println("1. Dry Storage");
            System.out.println("2. Open Top");
            System.out.println("3. Open Side");
            System.out.println("4. Refrigerate");
            System.out.println("5. Liquid");
            System.out.println("6. Exit"); // Add an option to exit

            try {
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        // Execute code for Dry Storage
                        System.out.println("You chose Dry Storage");
                        container.setType(Container.ContainerType.DRY_STORAGE);
                        PortSystem.updateContainerInfo(container);
                        break;
                    case 2:
                        // Execute code for Open Top
                        System.out.println("You chose Open Top");
                        container.setType(Container.ContainerType.OPEN_TOP);
                        PortSystem.updateContainerInfo(container);
                        break;
                    case 3:
                        // Execute code for Open Side
                        System.out.println("You chose Open Side");
                        container.setType(Container.ContainerType.OPEN_SIDE);
                        PortSystem.updateContainerInfo(container);
                        break;
                    case 4:
                        // Execute code for Refrigerate
                        System.out.println("You chose Refrigerate");
                        container.setType(Container.ContainerType.REFRIGERATED);
                        PortSystem.updateContainerInfo(container);
                        break;
                    case 5:
                        // Execute code for Liquid
                        System.out.println("You chose Liquid");
                        container.setType(Container.ContainerType.LIQUID);
                        PortSystem.updateContainerInfo(container);
                        break;
                    case 6:
                        // Exit the loop
                        exitLoop = true;
                        break;
                    default:
                        System.out.println("Invalid choice. Please select a valid option (1-6).");
                }
            } catch (InputMismatchException e) {
                System.out.println("Not a valid choice. Please enter a number (1-6).");
                scanner.nextLine(); // Consume the invalid input
            }
        }
    }


    public void updateWeight(Container container) {
        Scanner scanner = new Scanner(System.in);
        double weight;
        while (true) {
            System.out.println("Enter new container weight: ");
            try {
                weight = scanner.nextDouble();
                if (weight <= 0) {
                    System.out.println("Container weight must be greater than 0");
                } else {
                    container.setWeight(weight);
                    PortSystem.updateContainerInfo(container);
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Container weight must be a number!");
            }
        }
    }

}
