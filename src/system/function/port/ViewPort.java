package system.function.port;

import system.PortSystem;
import system.function.container.ContainerMenu;
import system.models.Port;

public class ViewPort {

    public ViewPort() {
        start();
    }

    public ViewPort(Port controlPort) {
        start(controlPort);
    }

    public void start() {
        System.out.println("List of ports: ");
        PortSystem.getPortList().forEach(System.out::println);
        new PortMenu();
    }

    public void start(Port controlPort) {
        System.out.println("===== Port Information =====");
        System.out.println("Port name: " + controlPort.getPortName());
        System.out.println("Port latitude: " + controlPort.getLatitude());
        System.out.println("Port longitude: " + controlPort.getLongitude());
        System.out.println("Port storing capacity: " + controlPort.getStoreCapacity());
        System.out.println("Port number of containers: " + controlPort.getNumberOfContainers());
        System.out.println("Port number of vehicles: " + controlPort.getNumberOfVehicles());
        System.out.println("===== END =====");
        new ContainerMenu(controlPort);
    }
}
