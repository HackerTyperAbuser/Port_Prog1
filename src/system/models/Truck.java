package system.models;

import system.PortSystem;

import java.io.*;

public abstract class Truck extends Vehicle implements Serializable, VehicleAction{
    @Serial
    private static final long serialVersionUID = 10L;
    private static final String COUNT_ID_FILE = "src/data_storage/truckIDCount.txt";
    private static int counter = loadCounter();

    public Truck(String name, double carryingCapacity, double fuelCapacity, Port currentPort) {
        super(name, carryingCapacity, fuelCapacity, currentPort);
        this.id = generateTruckId();
        Truck.saveCounter();
    }

    private static String generateTruckId() {
        return "TR-" + counter++;
    }

    private static int loadCounter() {
        try (BufferedReader reader = new BufferedReader(new FileReader(COUNT_ID_FILE))) {
            return Integer.parseInt(reader.readLine());
        } catch (FileNotFoundException e) {
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
    public boolean loadContainer(Container container) {
        if (this.getRemainCapacity() >= container.getWeight()) {
            if (isCompatible(container)) {
                try {
                    int indexVehicle = PortSystem.getVehicleList().indexOf(this);
                    int indexContainer = PortSystem.getContainerList().indexOf(container);
                    int indexPort = PortSystem.getPortList().indexOf(this.getPort());
                    // Calculate remaining capacity and add to number of containers
                    this.calculateRemainingCapacity(container.getWeight());
                    this.addContainerToList();
                    // Add container to vehicle listOfContainers and containerCount
                    PortSystem.getVehicleList().get(indexVehicle).getListOfContainers().add(container);
                    PortSystem.getVehicleList().get(indexVehicle).getContainerOfEachType().put(container.getType(), this.getContainerOfEachType().getOrDefault(container.getType(), 0) + 1);
                    // Update the status of the container
                    PortSystem.getContainerList().get(indexContainer).setCurrentPort(null);
                    // Remove the container from the port and from number of containers
                    PortSystem.getPortList().get(indexPort).getListOfContainers().remove(container);
                    PortSystem.getPortList().get(indexPort).removeContainerFromList();

                    PortSystem.writePortsToFile();
                    PortSystem.writeContainerToFile();
                    PortSystem.writeVehiclesToFile();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                PortSystem.updateVehicleInfo(this);
                System.out.println("Container loaded!");
                return true;
            }
            else {
                System.out.println("Container type is not compatible");
                return false;
            }
        } else {
            System.out.println("Not enough storage on the vehicle");
        }
        return false;
    }

    public boolean isCompatible(Container container) {
        return true;
    }

    @Override
    public double calculateRequiredFuel(Port destinationPort) {
        double totalFuelNeed = 0;
        double distanceBetween2Port = Port.calculateDistance(this.getPort(), destinationPort);
        double fuelForDistance = distanceBetween2Port * 2;

        for (Container c : getListOfContainers()) {
            totalFuelNeed += c.getFuelConsumption(Container.VehicleType.TRUCK) * distanceBetween2Port;
        }
        totalFuelNeed += fuelForDistance;

        System.out.println("Required Fuel: " + totalFuelNeed);
        return totalFuelNeed;
    }


    @Override
    public boolean moveToPort(Port destinatedPort) {
        if (destinatedPort.isLandable()) {
            super.moveToPort(destinatedPort);
        } else {
            System.out.println("Cannot move to this port because it doesn't support truck");

        }
        return false;
    }
}
