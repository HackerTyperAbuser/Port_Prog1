package system.function.manager;

import system.PortSystem;
import system.models.Manager;
import system.models.Port;

import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public class AddManager {

    public AddManager() {
        start();
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String username = getUniqueUsername(scanner);

            if (username == null) {
                System.out.println("Exiting port control.");
                break;
            }

            System.out.println("Enter Manager Password: ");
            String password = scanner.nextLine();

            boolean portFound = false;
            Port controlPort = null;
            while (true) {
                System.out.println("Choose a port for the manager");
                PortSystem.getPortList().forEach(System.out::println);
                System.out.println("Enter Port ID: ");
                String portID = scanner.nextLine();

                for (Port port : PortSystem.getPortList()) {
                    if (port.getPortID().equalsIgnoreCase(portID)) {
                        controlPort = port;
                        portFound = true;
                        break;
                    }
                }

                if (portFound) {
                    if (Objects.isNull(controlPort.getManager())) {
                        Manager manager = new Manager(username, password, controlPort);
                        int indexPort = PortSystem.getPortList().indexOf(controlPort);
                        PortSystem.getPortList().get(indexPort).setManager(manager);
                        PortSystem.getManagerList().add(manager);
                        try {
                            PortSystem.writeManagersToFile();
                            PortSystem.writePortsToFile();
                            System.out.println("Manager added!");
                            break;
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    } else {
                        System.out.println("This port already has a manager");
                    }
                } else {
                    System.out.println("Port doesn't exist");
                }

            }

        }
    }

    private String getUniqueUsername(Scanner scanner) {
        while (true) {
            System.out.println("Enter Manager Username (or 'q' to exit): ");
            String username = scanner.nextLine();

            if (username.equalsIgnoreCase("q")) {
                return null;
            }

            boolean isUsernameTaken = PortSystem.getManagerList().stream()
                    .anyMatch(manager -> manager.getUsername().equals(username));

            if (isUsernameTaken) {
                System.out.println("This username is already taken. Please choose another one.");
            } else {
                return username;
            }
        }
    }

}
