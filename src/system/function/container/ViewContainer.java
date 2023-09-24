package system.function.container;

import system.PortSystem;
import system.models.Container;
import system.models.Port;

import java.util.ArrayList;
import java.util.List;

public class ViewContainer {

    public ViewContainer() {
        start();
    }

    public ViewContainer(Port controlPort) {
        start(controlPort);
    }


    public void start() {
        System.out.println("List of containers: ");
        PortSystem.getContainerList().forEach(System.out::println);
        new ContainerMenu();
    }

    public void start(Port controlPort) {
        List<Container> containerList = new ArrayList<>();
        PortSystem.getPortList().forEach(port -> {
            if (port.equals(controlPort)) {
                containerList.addAll(port.getListOfContainers());
            }
        });
        System.out.println("List of containers: ");
        containerList.forEach(System.out::println);
        new ContainerMenu(controlPort);
    }
}
