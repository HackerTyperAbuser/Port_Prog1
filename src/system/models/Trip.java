package system.models;

import java.io.*;
import java.time.LocalDate;

public class Trip implements Serializable{
    @Serial
    private static final long serialVersionUID = 4L;
    private static final String COUNT_ID_FILE = "src/data_storage/tripIDCount.txt";
    private static int counter = loadCounter();
    private String id;
    private Vehicle vehicle;
    private LocalDate departureDate;
    private LocalDate arriveDate;
    private Port departurePort;
    private Port destinatedPort;
    private double fuelConsumption;


    public Trip(Vehicle vehicle, LocalDate departureDate, Port departurePort, Port destinatedPort, double fuelConsumption) {
        this.id = generateTripID();
        this.vehicle = vehicle;
        this.departureDate = departureDate;
        this.arriveDate = departureDate.plusDays(3);
        this.departurePort = departurePort;
        this.destinatedPort = destinatedPort;
        this.fuelConsumption = fuelConsumption;
        saveCounter();
    }

    private static String generateTripID() {
        return "TRIP-" + counter++;
    }

    private static int loadCounter() {
        try (BufferedReader reader = new BufferedReader(new FileReader(COUNT_ID_FILE))) {
            return Integer.parseInt(reader.readLine());
        } catch (FileNotFoundException e) {
            // Counter file not found, start from 1
            return 1;
        } catch (IOException e) {
            e.printStackTrace();
            return 1;
        }
    }

    public static void saveCounter() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(COUNT_ID_FILE))) {
            writer.write(String.valueOf(counter));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getId() {
        return id;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public Port getDeparturePort() {
        return departurePort;
    }

    public Port getDestinatedPort() {
        return destinatedPort;
    }

    public double getFuelConsumption() {
        return fuelConsumption;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    public LocalDate getArriveDate() {
        return arriveDate;
    }

    public void setArriveDate(LocalDate arriveDate) {
        this.arriveDate = arriveDate;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Trip trip = (Trip) obj;
        return id.equals(trip.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return String.format("ID: %s, Vehicle: %s, departureDate: %s, arriveDate: %s, departurePort: %s, destinatedPort: %s, fuelConsumption: %.2f",
                getId(),
                getVehicle().getId(),
                getDepartureDate(),
                getArriveDate(),
                getDeparturePort().getPortID(),
                getDestinatedPort().getPortID(),
                getFuelConsumption());
    }
}
