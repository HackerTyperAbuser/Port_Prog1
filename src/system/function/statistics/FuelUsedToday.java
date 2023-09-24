package system.function.statistics;

import system.PortSystem;

import java.time.LocalDate;
import java.util.Map;

public class FuelUsedToday {

    public FuelUsedToday() {
        start();
    }

    public void start() {
        System.out.println("Fuel usage for today: " + PortSystem.getFuelForToday());
//        for (Map.Entry<LocalDate, Double> entry : PortSystem.getFuelForToday().entrySet()) {
//            LocalDate date = entry.getKey();
//            double fuelConsumption = entry.getValue();
//            String displayString = String.format("Date: %s, Fuel Consumed: %.2f", date, fuelConsumption);
//            System.out.println(displayString);
//        }
    }
}
