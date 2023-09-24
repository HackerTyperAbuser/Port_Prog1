package system.models;

import system.PortSystem;

import java.io.IOException;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Vehicle implements Serializable, VehicleAction{
    @Serial
    private static final long serialVersionUID = 3L;
    protected String id;
    private String vehicleType;
    private String vehicleName;
    private double fuelCapacity;
    private double remainFuel;
    private double carryCapacity;
    private double remainCapacity;
    private Port port;
    private int numberOfContainers;
    private List<Container> listOfContainers;
    private Map<Container.ContainerType, Integer> containerOfEachType;

    public Vehicle(String vehicleName, double carryCapacity, double fuelCapacity, Port port) {
        this.vehicleName = vehicleName;
        this.remainFuel = fuelCapacity;
        this.carryCapacity = carryCapacity;
        this.remainCapacity = carryCapacity;
        this.fuelCapacity = fuelCapacity;
        this.port = port;
        this.listOfContainers = new ArrayList<>();
        this.containerOfEachType = new HashMap<>();
    }

    public boolean moveToPort(Port destinatedPort) {
        double fuelNeed = this.calculateRequiredFuel(destinatedPort);
        if (this.getPort().equals(destinatedPort)) {
            System.out.println("The vehicle is already at destination!");
            return false;
        } else if (fuelNeed < this.getRemainFuel()) {
            this.setRemainFuel(this.getRemainFuel() - fuelNeed);
            // update port data
            try {
                Trip trip = new Trip(this, LocalDate.now(), this.port, destinatedPort, fuelNeed);
                PortSystem.getTripList().add(trip);

                // remove vehicle from old port
                int indexOldPort = PortSystem.getPortList().indexOf(this.getPort());
                PortSystem.getPortList().get(indexOldPort).getListOfVehicles().remove(this);
                PortSystem.getPortList().get(indexOldPort).removeVehicleFromList();

                this.setPort(destinatedPort); // set vehicle port to new port

                // add vehicle to new port
                int indexNewPort = PortSystem.getPortList().indexOf(destinatedPort);
                PortSystem.getPortList().get(indexNewPort).getListOfVehicles().add(this);
                PortSystem.getPortList().get(indexNewPort).addVehicleToList();
                PortSystem.updateVehicleInfo(this);
                PortSystem.writePortsToFile();
                PortSystem.writeTripsToFile();
                System.out.println("Vehicle is moving to port");
                return true;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("Vehicle doesn't have enough fuel");
            return false;
        }
    }

    public void refuel() {
        this.setRemainFuel(this.getFuelCapacity());
        System.out.println("Vehicle has been refueled!");
    }

    public void unloadContainers() {
        if (getListOfContainers().isEmpty()) {
            System.out.println("There are no container on the vehicle");
        } else {
            int indexVehicle = PortSystem.getVehicleList().indexOf(this);
            int indexPort = PortSystem.getPortList().indexOf(this.getPort());

            for (Container container : PortSystem.getContainerList()) {
                if (PortSystem.getVehicleList().get(indexVehicle).getListOfContainers().contains(container)) {
                    if (PortSystem.getPortList().get(indexPort).getRemainCapacity() >= container.getWeight()) {
                        container.setCurrentPort(this.getPort()); // reset the current Port of the container
                        this.removeContainerFromList(); // Remove from number of containers
                        this.refillRamainingCapacity(container.getWeight()); // Remove from number of containers
                        PortSystem.getPortList().get(indexPort).getListOfContainers().add(container); // add container into port
                        PortSystem.getPortList().get(indexPort).addContainerToList(); // add container into port
                        PortSystem.getPortList().get(indexPort).calculateRemainingCapacity(container.getWeight());
                        System.out.println("Container " + container.getId() + " port set to " + this.getPort());
                        getListOfContainers().remove(container);
                        getContainerOfEachType().put(container.getType(), getContainerOfEachType().get(container.getType()) - 1);
                    }

                }
            }

            try {
                PortSystem.writeContainerToFile();
                PortSystem.writePortsToFile();
                PortSystem.updateVehicleInfo(this);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }

    public abstract double calculateRequiredFuel(Port destinatedPort);

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public double getRemainFuel() {
        return remainFuel;
    }

    public void setRemainFuel(double remainFuel) {
        this.remainFuel = remainFuel;
    }

    public double getCarryCapacity() {
        return carryCapacity;
    }

    public void setCarryCapacity(double carryCapacity) {
        this.carryCapacity = carryCapacity;
    }

    public double getFuelCapacity() {
        return fuelCapacity;
    }

    public void setFuelCapacity(double fuelCapacity) {
        this.fuelCapacity = fuelCapacity;
    }

    public List<Container> getListOfContainers() {
        return listOfContainers;
    }

    public Map<Container.ContainerType, Integer> getContainerOfEachType() {
        return containerOfEachType;
    }

    public Port getPort() {
        return port;
    }

    public void setPort(Port port) {
        this.port = port;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getId() {
        return id;
    }

    public double getRemainCapacity() {
        return remainCapacity;
    }

    public void setRemainCapacity(double remainCapacity) {
        this.remainCapacity = remainCapacity;
    }

    public void calculateRemainingCapacity(double amount) {
        setRemainCapacity(getRemainCapacity() - amount);
    }

    public void refillRamainingCapacity(double amount) {
        setRemainCapacity(getRemainCapacity() + amount);
    }

    public int getNumberOfContainers() {
        return numberOfContainers;
    }

    public void setNumberOfContainers(int numberOfContainers) {
        this.numberOfContainers = numberOfContainers;
    }

    public void addContainerToList() {
        this.setNumberOfContainers(this.getNumberOfContainers() + 1);
    }

    public void removeContainerFromList() {
        this.setNumberOfContainers(this.getNumberOfContainers() - 1);
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

    @Override
    public String toString() {
        return String.format("ID: %s, Type: %s, Name: %s, Current Fuel: %.2f, " +
                        "Fuel Capacity: %.2f, Carry Capacity: %.2f, Remain Capacity: %.2f, " +
                        "At Port: [ID: %s, Name: %s], Number of container: %s, Containers: [%s], " +
                        "Container of each type: %s",
                        getId(), getVehicleType(), getVehicleName(), getRemainFuel(),
                        getFuelCapacity(), getCarryCapacity(), getRemainCapacity(),
                        getPort().getPortID(),
                        getPort().getPortName(), getNumberOfContainers(),
                        getContainersAsString(), getContainerOfEachType());
    }



    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Vehicle vehicle = (Vehicle) obj;
        return id.equals(vehicle.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
