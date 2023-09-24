package system.function.port;

import system.PortSystem;
import system.models.Port;

import java.util.Scanner;

public class SearchPort {

    public SearchPort() {
        start();
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        boolean found = false; // Initialize found as false
        PortSystem.getPortList().forEach(System.out::println);

        while (true) {
            System.out.println("Enter port ID (or 'q' to exit): ");
            String portID = scanner.nextLine();

            if (portID.equalsIgnoreCase("q")) {
                System.out.println("Exiting search for port.");
                break; // Exit the loop and return to the Port Menu
            }

            for (Port port : PortSystem.getPortList()) {
                if (port.getPortID().equalsIgnoreCase(portID)) {
                    System.out.println("Port found: ");
                    System.out.println(port);
                    found = true; // Port found
                }
            }

            if (!found) {
                System.out.println("Port not found.");
            }
        }
        new PortMenu();
    }


}
