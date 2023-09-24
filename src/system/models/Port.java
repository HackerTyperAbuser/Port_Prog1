package system.models;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Port implements Serializable{
    @Serial
    private static final long serialVersionUID = 1L;
    private static final String COUNT_ID_FILE = "src/data_storage/portIDCount.txt";
    private String portID;
    private String portName;
    private double latitude;
    private double longitude;
    private double storeCapacity;
    private double remainCapacity;
    private boolean landable;
    private int numberOfContainers = 0;
    private int numberOfVehicles = 0;
    private List<Container> listOfContainers;
    private List<Vehicle> listOfVehicles;
    private Manager manager;
    private static int counter = loadCounter();


    public Port(String portName, double latitude, double longitude, double storeCapacity, boolean landable) {
        this.portID = generatePortId();
        this.portName = portName;
        this.latitude = latitude;
        this.longitude = longitude;
        this.storeCapacity = storeCapacity;
        this.landable = landable;
        this.listOfContainers = new ArrayList<>();
        this.listOfVehicles = new ArrayList<>();
        this.remainCapacity = storeCapacity;
        Port.saveCounter();
    }

    public boolean canLoadContainers() {
        return true;
    }

    private static String generatePortId() {
        return "P-" + counter++;
    }

    public static double calculateDistance(Port firstPort, Port secondPort) {
        double latitude1 = Math.toRadians(firstPort.getLatitude());
        double longitude1 = Math.toRadians(firstPort.getLongitude());
        double latitude2 = Math.toRadians(secondPort.getLatitude());
        double longitude2 = Math.toRadians(secondPort.getLongitude());

        double latitudeDif = latitude2 - latitude1;
        double longitudeDif = longitude2 - longitude1;

        double a = Math.sin(latitudeDif / 2) * Math.sin(latitudeDif / 2) +
                Math.cos(latitude1) * Math.cos(latitude2) * Math.sin(longitudeDif / 2) * Math.sin(longitudeDif / 2);

        double b = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return 6371 * b;
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Port port = (Port) obj;
        return portID.equals(port.portID);
    }

    @Override
    public int hashCode() {
        return portID.hashCode();
    }

    public String getPortID() {
        return portID;
    }

    public String getPortName() {
        return portName;
    }

    public void setPortName(String portName) {
        this.portName = portName;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getStoreCapacity() {
        return storeCapacity;
    }

    public void setStoreCapacity(double storeCapacity) {
        this.storeCapacity = storeCapacity;
    }

    public boolean isLandable() {
        return landable;
    }

    public void setLandable(boolean landable) {
        this.landable = landable;
    }

    public int getNumberOfContainers() {
        return numberOfContainers;
    }

    public void setNumberOfContainers(int numberOfContainers) {
        this.numberOfContainers = numberOfContainers;
    }

    public int getNumberOfVehicles() {
        return numberOfVehicles;
    }

    public void setNumberOfVehicles(int numberOfVehicles) {
        this.numberOfVehicles = numberOfVehicles;
    }

    public List<Container> getListOfContainers() {
        return listOfContainers;
    }

    public List<Vehicle> getListOfVehicles() {
        return listOfVehicles;
    }

    public void addContainerToList() {
        this.setNumberOfContainers(this.getNumberOfContainers() + 1);
    }

    public void addVehicleToList() {
        this.setNumberOfVehicles(this.getNumberOfVehicles() + 1);
    }

    public void removeContainerFromList() {
        this.setNumberOfContainers(this.getNumberOfContainers() - 1);
    }

    public void removeVehicleFromList() {
        this.setNumberOfVehicles(this.getNumberOfVehicles() - 1);
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public double getRemainCapacity() {
        return remainCapacity;
    }

    public void setRemainCapacity(double remainCapacity) {
        this.remainCapacity = remainCapacity;
    }

    public void calculateRemainingCapacity (double amount) {
        setRemainCapacity(getRemainCapacity() - amount);
    }

    public String getContainersAsString() {
        StringBuilder result = new StringBuilder();
        for (Container container : listOfContainers) {
            result.append(container.getId()).append(", "); // Append container ID and a comma
        }
        // Remove the trailing comma and space, if there are containers
        if (result.length() > 0) {
            result.delete(result.length() - 2, result.length());
        }
        return result.toString();
    }

    public String getVehiclesAsString() {
        StringBuilder result = new StringBuilder();
        for (Vehicle vehicle : listOfVehicles) {
            result.append(vehicle.getId()).append(", "); // Append container ID and a comma
        }
        // Remove the trailing comma and space, if there are containers
        if (result.length() > 0) {
            result.delete(result.length() - 2, result.length());
        }
        return result.toString();
    }

    public String getManagerIDAsString() {
        if (Objects.isNull(getManager())) {
            return "none";
        } else {
            return String.format("{%s}", getManager().getManagerID());
        }
    }

    @Override
    public String toString() {
        return String.format("ID: %s, Name: %s, Latitude: %s, Longitude: %s, StoringCap: %.2f, RemainCap: %.2f, " +
                        "Landing: %s, Number of containers: %s, Number of vehicles: %s," +
                        "Containers: {%s}, Vehicle: {%s}, Manager: %s",
                getPortID(),
                getPortName(),
                getLatitude(),
                getLongitude(),
                getStoreCapacity(),
                getRemainCapacity(),
                isLandable(),
                getNumberOfContainers(),
                getNumberOfVehicles(),
                getContainersAsString(),
                getVehiclesAsString(),
                getManagerIDAsString());
    }
}
