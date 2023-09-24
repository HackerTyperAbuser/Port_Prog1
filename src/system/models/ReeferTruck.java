package system.models;

import java.io.Serial;
import java.io.Serializable;

public class ReeferTruck extends Truck implements Serializable {
    @Serial
    private static final long serialVersionUID = 7L;
    public ReeferTruck(String name, double carryingCapacity, double fuelCapacity, Port currentPort) {
        super(name, carryingCapacity, fuelCapacity, currentPort);
        setVehicleType("REEFER TRUCK");
    }

    @Override
    public boolean isCompatible(Container container) {
        return container.getType() == Container.ContainerType.REFRIGERATED;
    }

}
