package system.function.statistics;

import system.PortSystem;
import system.models.Port;
import system.models.Ship;
import system.models.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class ListShipInPort {

    public ListShipInPort() {
        start();
    }

    public void start() {
        List<String> displayList = new ArrayList<>();
        for (Port port : PortSystem.getPortList()) {
            StringBuilder shipsInPort = new StringBuilder();
            shipsInPort.append("Port ").append(port.getPortID()).append(": ");

            boolean isFirstShip = true;

            for (Vehicle vehicle : port.getListOfVehicles()) {
                // Check if the vehicle is a ship
                if (vehicle instanceof Ship) {
                    if (!isFirstShip) {
                        shipsInPort.append(", ");
                    }
                    shipsInPort.append(vehicle.getId());
                    isFirstShip = false;
                }
            }

            displayList.add(shipsInPort.toString());
        }

        displayList.forEach(System.out::println);
    }
}
