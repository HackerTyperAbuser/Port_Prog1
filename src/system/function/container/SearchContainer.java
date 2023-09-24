package system.function.container;

import system.PortSystem;
import system.models.Container;
import system.models.Port;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SearchContainer {

    public SearchContainer() {
        start();
    }

    public SearchContainer(Port controlPort) {
        start(controlPort);
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            PortSystem.getContainerList().forEach(System.out::println);
            System.out.println("Enter container ID to view (or 'q' to exit)");
            String containerID = scanner.nextLine();

            if (containerID.equalsIgnoreCase("q")) {
                System.out.println("View container exited!");
                break; // Exit the loop and return to the Container Menu
            }

            boolean found = false;

            for (Container container : PortSystem.getContainerList()) {
                if (container.getId().equalsIgnoreCase(containerID)) {
                    System.out.println(container);
                    found = true;
                    break; // Stop searching after finding and displaying the container
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
        List<Container> containerList = new ArrayList<>();
        PortSystem.getPortList().forEach(port -> {
            if (port.equals(controlPort)) {
                containerList.addAll(port.getListOfContainers());
            }
        });
        containerList.forEach(System.out::println);

        while (true) {

            System.out.println("Enter container ID to view (or 'q' to exit)");
            String containerID = scanner.nextLine();

            if (containerID.equalsIgnoreCase("q")) {
                System.out.println("View container exited!");
                break; // Exit the loop and return to the Container Menu
            }

            boolean found = false;

            for (Container container : containerList) {
                if (container.getId().equalsIgnoreCase(containerID)) {
                    System.out.println("Container Found: ");
                    System.out.println(container);
                    found = true;
                    break; // Stop searching after finding and displaying the container
                }
            }

            if (!found) {
                System.out.println("Container not found");
            }
        }

        new ContainerMenu(controlPort);
    }

}
