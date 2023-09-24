package system.function.container;

import system.PortSystem;
import system.models.Container;
import system.models.Port;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DeleteContainer {

    public DeleteContainer() {
        start();
    }

    public DeleteContainer(Port controlPort) {
        start(controlPort);
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        boolean found = false;
        PortSystem.getContainerList().forEach(System.out::println);

        while (true) {
            System.out.println("Enter container ID to delete (or 'q' to exit)");
            String containerID = scanner.nextLine();

            if (containerID.equalsIgnoreCase("q")) {
                System.out.println("Delete container exited!");
                break; // Exit the loop and return to the Container Menu
            }

            List<Container> containerList = new ArrayList<>(PortSystem.getContainerList());
            for (Container container : containerList) {
                if (container.getId().equalsIgnoreCase(containerID)) {
                    int indexPort = PortSystem.getPortList().indexOf(container.getCurrentPort());
                    PortSystem.getPortList().get(indexPort).getListOfContainers().remove(container);
                    PortSystem.getContainerList().remove(container);
                    found = true;

                    try {
                        PortSystem.writeContainerToFile();
                        PortSystem.writePortsToFile();
                        System.out.println("Container deleted!");
                    } catch (IOException e) {
                        System.out.println("Write containers fail!");
                        throw new RuntimeException(e);
                    }
                }
            }

            if (!found) {
                System.out.println("Container not found");
            }
        }

        new ContainerMenu();
    }

    public void start(Port controlPort) {
        Scanner scanner = new Scanner(System.in);
        boolean found = false;

        List<Container> containerList = new ArrayList<>();
        PortSystem.getPortList().forEach(port -> {
            if (port.equals(controlPort)) {
                containerList.addAll(port.getListOfContainers());
            }
        });

        System.out.println("===== Container List =====");
        containerList.forEach(System.out::println);
        System.out.println("===== End =====");

        while (true) {
            System.out.println("Enter container ID to delete (or 'q' to exit)");
            String containerID = scanner.nextLine();

            if (containerID.equalsIgnoreCase("q")) {
                System.out.println("Delete container exited!");
                break; // Exit the loop and return to the Container Menu
            }


            for (Container container : containerList) {
                if (container.getId().equalsIgnoreCase(containerID)) {
                    int indexPort = PortSystem.getPortList().indexOf(container.getCurrentPort());
                    PortSystem.getPortList().get(indexPort).getListOfContainers().remove(container);
                    PortSystem.getContainerList().remove(container);
                    System.out.println("Container deleted!");
                    found = true;

                    try {
                        PortSystem.writeContainerToFile();
                        PortSystem.writePortsToFile();
                    } catch (IOException e) {
                        System.out.println("Write containers fail!");
                        throw new RuntimeException(e);
                    }
                }
            }

            if (!found) {
                System.out.println("Container not found");
            }
        }

        new ContainerMenu(controlPort);
    }

}
