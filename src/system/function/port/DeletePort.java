package system.function.port;

import system.PortSystem;
import system.models.Port;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DeletePort {

    public DeletePort() {
        start();
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        boolean found = false;

        while (true) {
            PortSystem.getPortList().forEach(System.out::println);
            System.out.println("Enter port ID to delete (or 'q' to exit):");
            String portID = scanner.nextLine();

            if (portID.equalsIgnoreCase("q")) {
                System.out.println("Delete port exited!");
                return; // Exit the method
            }

            List<Port> portListCopy = new ArrayList<>(PortSystem.getPortList());
            for (Port port : portListCopy) {
                if (port.getPortID().equalsIgnoreCase(portID)) {
                    found = true;
                    if (!port.getListOfContainers().isEmpty()) {
                        System.out.println("You can't delete this port, there are still vehicles inside");
                        break;
                    } else if (!port.getListOfVehicles().isEmpty()) {
                        System.out.println("You can't delete this port, there are still containers inside");
                        break;
                    } else {
                        PortSystem.getManagerList().remove(port.getManager());
                        PortSystem.getPortList().remove(port);
                        System.out.println("Port deleted!");
                        try {
                            PortSystem.writePortsToFile();
                            PortSystem.writeManagersToFile();
                        } catch (IOException e) {
                            System.out.println("Write port fail!");
                            throw new RuntimeException(e);
                        }
                        break;
                    }
                }
            }

            if (!found) {
                System.out.println("Port not found");
            }
        }
    }
}
