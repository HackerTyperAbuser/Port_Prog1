package system.function.trip;

import system.PortSystem;
import system.models.Port;

public class ViewTrip {

    public ViewTrip() {
        start();
    }

    public ViewTrip(Port controlPort) {
        start(controlPort);
    }

    public void start() {
        System.out.println("List of trips: ");
        PortSystem.getTripList().forEach(System.out::println);
        new TripMenu();
    }

    public void start(Port controlPort) {
        System.out.println("List of trips: ");
        PortSystem.getTripList().forEach(trip -> {
            if (trip.getDestinatedPort().equals(controlPort))
                System.out.println(trip);
        });
        new TripMenu(controlPort);
    }
}
