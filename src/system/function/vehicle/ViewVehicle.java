package system.function.vehicle;

import system.PortSystem;
import system.models.Port;
import system.models.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class ViewVehicle {

    public ViewVehicle() {
        start();
    }

    public ViewVehicle(Port controlPort) {
        start(controlPort);
    }

    public void start() {
        System.out.println("List of vehicles: ");
        PortSystem.getVehicleList().forEach(System.out::println);
        new VehicleMenu();
    }

    public void start(Port controlPort) {
        List<Vehicle> vehicleList = new ArrayList<>();
        PortSystem.getPortList().forEach(port -> {
            if (port.equals(controlPort)) {
                vehicleList.addAll(port.getListOfVehicles());
            }
        });
        System.out.println("List of vehicles: ");
        vehicleList.forEach(System.out::println);
        new VehicleMenu(controlPort);
    }
}
