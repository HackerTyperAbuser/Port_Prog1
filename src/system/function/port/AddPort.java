package system.function.port;

import system.PortSystem;
import system.models.Port;

import java.io.IOException;
import java.util.Scanner;

public class AddPort {

    public AddPort() {
        start();
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("===== Adding Port ======");
        String portName = enterPortName();
        double portLatitude = enterPortLatitude();
        double portLongitude = enterPortLongitude();
        double portStoring = enterPortStoringCapacity();
        boolean portLanding = enterPortLandingAbility();

        Port port = new Port(portName, portLatitude, portLongitude, portStoring, portLanding);
        PortSystem.getPortList().add(port);

        try {
            PortSystem.writePortsToFile();
        } catch (IOException e) {
            System.out.println("Write port fail");
            throw new RuntimeException(e);
        }
        System.out.println("Port successfully created");
    }

    public String enterPortName() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Enter port name: ");
            String portName = scanner.nextLine();
            if (portName.isEmpty()) {
                System.out.println("Name cannot be empty");
            } else {
                return portName;
            }
        }
    }

    public double enterPortLatitude() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Enter port latitude: ");
            try {
                double portLatitude = Double.parseDouble(scanner.nextLine());
                if (portLatitude > 90 || portLatitude < -90) {
                    System.out.println("Invalid number for latitude");
                } else {
                    return portLatitude;
                }
            } catch (NumberFormatException e) {
                System.out.println("Latitude must be a valid number");
            }
        }
    }

    public double enterPortLongitude() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Enter port longitude: ");
            try {
                double portLongitude = Double.parseDouble(scanner.nextLine());
                if (portLongitude > 180 || portLongitude < -180) {
                    System.out.println("Invalid number for longitude");
                } else {
                    return portLongitude;
                }
            } catch (NumberFormatException e) {
                System.out.println("Longitude must be a valid number");
            }
        }
    }

    public double enterPortStoringCapacity() {
        Scanner scanner = new Scanner(System.in);
        double portStoring;
        while (true) {
            System.out.println("Enter port storing capacity: ");
            try {
                portStoring = Double.parseDouble(scanner.nextLine());
                if (portStoring <= 0) {
                    System.out.println("Port storing must be greater than 0");
                } else {
                    return portStoring;
                }
            } catch (NumberFormatException e) {
                System.out.println("Storing capacity must be a valid number");
            }
        }
    }

    public boolean enterPortLandingAbility() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Enter port landing ability (TRUE/FALSE): ");
            String portLanding = scanner.nextLine();
            if (portLanding.equalsIgnoreCase("true")) {
                return true;
            } else if (portLanding.equalsIgnoreCase("false")) {
                return false;
            } else {
                System.out.println("Port landing ability must be 'true' or 'false'");
            }
        }
    }
}
