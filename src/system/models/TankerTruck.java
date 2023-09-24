package system.models;

import java.io.Serial;
import java.io.Serializable;

public class TankerTruck extends Truck implements Serializable {
    @Serial
    private static final long serialVersionUID = 8L;
    public TankerTruck(String name, double carryingCapacity, double fuelCapacity, Port currentPort) {
        super(name, carryingCapacity, fuelCapacity, currentPort);
        setVehicleType("TANKER TRUCK");
    }

    @Override
    public boolean isCompatible(Container container) {
        return container.getType() == Container.ContainerType.LIQUID;
    }

}
