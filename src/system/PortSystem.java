package system;

import system.models.*;

import java.io.*;
import java.time.LocalDate;
import java.util.*;
import java.util.List;

public class PortSystem {
    private static List<Container> containerList = new ArrayList<>();
    private static List<Port> portList = new ArrayList<>();
    private static List<Vehicle> vehicleList = new ArrayList<>();
    private static List<Trip> tripList = new ArrayList<>();
    private static List<Manager> managerList = new ArrayList<>();

    public static void writeContainerToFile() throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream("src/data_storage/containerData.obj");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(PortSystem.getContainerList());
        objectOutputStream.close();
    }

    public static void readContainersFromFile() throws IOException {
        List<Container> containersToRead;
        File file = new File("src/data_storage/containerData.obj");
        if (!file.exists()) {
            // File does not exist
            return;
        }
        FileInputStream fileInputStream = new FileInputStream("src/data_storage/containerData.obj");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        try {
            containersToRead = (List<Container>) objectInputStream.readObject();
            System.out.println(containersToRead);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        // Overwrite data_storage of ContainerList
        PortSystem.getContainerList().clear();
        PortSystem.getContainerList().addAll(containersToRead);
    }

    public static void writePortsToFile() throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream("src/data_storage/portData.obj");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(PortSystem.getPortList());
        objectOutputStream.close();
    }

    public static void readPortsFromFile() throws IOException {
        List<Port> portsToRead;
        File file = new File("src/data_storage/portData.obj");
        if (!file.exists()) {
            // File does not exist
            return;
        }
        FileInputStream fileInputStream = new FileInputStream("src/data_storage/portData.obj");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        try {
            portsToRead = (List<Port>) objectInputStream.readObject();
            System.out.println(portsToRead);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        // Overwrite data_storage of ContainerList
        PortSystem.getPortList().clear();
        PortSystem.getPortList().addAll(portsToRead);
    }

    public static void writeVehiclesToFile() throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream("src/data_storage/vehicleData.obj");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(PortSystem.getVehicleList());
        objectOutputStream.close();
    }

    public static void readVehiclesFromFile() throws IOException {
        List<Vehicle> vehiclesToRead;
        File file = new File("src/data_storage/vehicleData.obj");
        if (!file.exists()) {
            // File does not exist
            return;
        }
        FileInputStream fileInputStream = new FileInputStream("src/data_storage/vehicleData.obj");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        try {
            vehiclesToRead = (List<Vehicle>) objectInputStream.readObject();
            System.out.println(vehiclesToRead);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        // Overwrite data_storage of ContainerList
        PortSystem.getVehicleList().clear();
        PortSystem.getVehicleList().addAll(vehiclesToRead);
    }



    public static void writeTripsToFile() throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream("src/data_storage/tripData.obj");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(PortSystem.getTripList());
        objectOutputStream.close();
    }

    public static void readTripsFromFile() throws IOException {
        List<Trip> tripsToRead;
        File file = new File("src/data_storage/tripData.obj");
        if (!file.exists()) {
            // File does not exist
            return;
        }
        FileInputStream fileInputStream = new FileInputStream("src/data_storage/tripData.obj");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        try {
            tripsToRead = (List<Trip>) objectInputStream.readObject();
            System.out.println(tripsToRead);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        // Overwrite data_storage of ContainerList
        PortSystem.getTripList().clear();
        PortSystem.getTripList().addAll(tripsToRead);
    }

    public static void writeManagersToFile() throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream("src/data_storage/managerData.obj");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(PortSystem.getManagerList());
        objectOutputStream.close();
    }

    public static void readManagersFromFile() throws IOException {
        List<Manager> managersToRead;
        File file = new File("src/data_storage/managerData.obj");
        if (!file.exists()) {
            // File does not exist
            return;
        }
        FileInputStream fileInputStream = new FileInputStream("src/data_storage/managerData.obj");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        try {
            managersToRead = (List<Manager>) objectInputStream.readObject();
            System.out.println(managersToRead);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        // Overwrite data_storage of ContainerList
        PortSystem.getManagerList().clear();
        PortSystem.getManagerList().addAll(managersToRead);
    }

    public static double getFuelForToday() {
        double fuelConsumptionForToday = 0.0;
        LocalDate today = LocalDate.now();

        for (Trip trip : getTripList()) {
            LocalDate arriveDate = trip.getArriveDate();
            double fuel = trip.getFuelConsumption();
            if (arriveDate.equals(today)) {
                fuelConsumptionForToday += fuel;
            }
        }

        return fuelConsumptionForToday;
    }

    public static void deleteTripAfter7Days() {
        try {
            LocalDate currentDate = LocalDate.now();
            Iterator<Trip> iterator = PortSystem.getTripList().iterator();

            while (iterator.hasNext()) {
                Trip trip = iterator.next();
                LocalDate arriveDate = trip.getArriveDate();
                int daysBetweenTwoDate = Math.abs(currentDate.until(arriveDate).getDays());

                if (daysBetweenTwoDate > 7) {
                    iterator.remove(); // Remove the current trip using the iterator
                }
            }

            PortSystem.writeTripsToFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateContainerInfo(Container container) {
        int index = PortSystem.getContainerList().indexOf(container);
        PortSystem.getContainerList().set(index, container);
        try {
            PortSystem.writeContainerToFile();
            System.out.println("Container updated!");
        } catch (IOException e) {
            System.out.println("Container updated!");
            throw new RuntimeException(e);
        }
    }

    public static void updateVehicleInfo(Vehicle vehicle) {
        int index = PortSystem.getVehicleList().indexOf(vehicle);
        PortSystem.getVehicleList().set(index, vehicle);
        try {
            PortSystem.writeVehiclesToFile();
            System.out.println("Vehicle update!");
        } catch (IOException e) {
            System.out.println("Vehicle update!");
            throw new RuntimeException(e);
        }
    }

    public static void updatePortInfo(Port port) {
        int index = PortSystem.getPortList().indexOf(port);
        PortSystem.getPortList().set(index, port);
        try {
            PortSystem.writePortsToFile();
            System.out.println("Port update!");
        } catch (IOException e) {
            System.out.println("Port update!");
            throw new RuntimeException(e);
        }
    }

    public static void updateTripInfo(Trip trip) {
        int index = PortSystem.getTripList().indexOf(trip);
        PortSystem.getTripList().set(index, trip);
        try {
            PortSystem.writeTripsToFile();
            System.out.println("Trip update!");
        } catch (IOException e) {
            System.out.println("Trip update!");
            throw new RuntimeException(e);
        }
    }

    public static void updateManagerInfo(Manager manager) {
        int index = PortSystem.getManagerList().indexOf(manager);
        PortSystem.getManagerList().set(index, manager);
        try {
            PortSystem.writeTripsToFile();
            System.out.println("Manager update!");
        } catch (IOException e) {
            System.out.println("Manager update!");
            throw new RuntimeException(e);
        }
    }

    public static List<Container> getContainerList() {
        return containerList;
    }

    public static List<Port> getPortList() {
        return portList;
    }

    public static List<Vehicle> getVehicleList() {
        return vehicleList;
    }

    public static List<Trip> getTripList() {
        return tripList;
    }

    public static List<Manager> getManagerList() {
        return managerList;
    }
}