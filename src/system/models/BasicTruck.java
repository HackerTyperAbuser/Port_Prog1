package system.models;

import java.io.Serial;
import java.io.Serializable;

public class BasicTruck extends Truck implements Serializable{
    @Serial
    private static final long serialVersionUID = 6L;
    public BasicTruck(String name, double carryingCapacity, double fuelCapacity, Port currentPort) {
        super(name, carryingCapacity, fuelCapacity, currentPort);
        setVehicleType("BASIC TRUCK");
    }

    @Override
    public boolean isCompatible(Container container) {
        return container.getType() == Container.ContainerType.OPEN_SIDE ||
                container.getType() == Container.ContainerType.OPEN_TOP ||
                container.getType() == Container.ContainerType.DRY_STORAGE;
    }
}
