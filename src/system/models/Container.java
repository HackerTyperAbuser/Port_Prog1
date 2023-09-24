package system.models;

import java.io.*;
import java.util.Map;
import java.util.Objects;

import static java.util.Map.entry;

public class Container implements Serializable {
    @Serial
    private static final long serialVersionUID = 2L;
    private static final String COUNT_ID_FILE = "src/data_storage/containerIDCount.txt";
    private static int counter = loadCounter();
    private String id;
    private double weight;
    private ContainerType type;
    private Port currentPort;

    private static final Map<ContainerType, Double> SHIP_CONSUMPTION_RATES = Map.ofEntries(
            entry(ContainerType.DRY_STORAGE, 3.5),
            entry(ContainerType.OPEN_TOP, 2.8),
            entry(ContainerType.OPEN_SIDE, 2.7),
            entry(ContainerType.REFRIGERATED, 4.5),
            entry(ContainerType.LIQUID, 4.8)
    );
    private static final Map<ContainerType, Double> TRUCK_CONSUMPTION_RATES = Map.ofEntries(
            entry(ContainerType.DRY_STORAGE, 4.6),
            entry(ContainerType.OPEN_TOP, 3.2),
            entry(ContainerType.OPEN_SIDE, 3.2),
            entry(ContainerType.REFRIGERATED, 5.4),
            entry(ContainerType.LIQUID, 5.3)
    );

    public enum ContainerType {
        DRY_STORAGE,
        OPEN_TOP,
        OPEN_SIDE,
        REFRIGERATED,
        LIQUID
    }

    public enum VehicleType {
        SHIP,
        TRUCK
    }

    public Container(double weight, ContainerType type, Port currentPort) {
        this.id = generateContainerId();
        this.weight = weight;
        this.type = type;
        this.currentPort = currentPort;
        saveCounter();
    }

    private static String generateContainerId() {
        return "C-" + counter++;
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


    public double getConsumptionRate(VehicleType vehicleType) {
        if (vehicleType.equals(VehicleType.TRUCK))
            return TRUCK_CONSUMPTION_RATES.get(this.getType());
        else
            return SHIP_CONSUMPTION_RATES.get(this.getType());
    }

    public double getFuelConsumption(VehicleType vehicleType) {
        return getConsumptionRate(vehicleType) * this.weight;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public ContainerType getType() {
        return type;
    }

    public void setType(ContainerType type) {
        this.type = type;
    }

    public Port getCurrentPort() {
        return currentPort;
    }

    public void setCurrentPort(Port currentPort) {
        this.currentPort = currentPort;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Container container = (Container) o;
        return Objects.equals(id, container.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        if (Objects.isNull(getCurrentPort())) {
            return String.format("ID: %s, Weight: %.2f, Type: %s, On Vehicle\n", getId(), getWeight(), getType());
        } else {
            return String.format("ID: %s, Weight: %.2f, Type: %s, At Port: [ID: %s, Name: %s]\n", getId(), getWeight(), getType(), getCurrentPort().getPortID(), getCurrentPort().getPortName());
        }
    }
}