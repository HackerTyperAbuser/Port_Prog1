package system.function.statistics;

import system.PortSystem;
import system.models.Container;

import java.util.HashMap;
import java.util.Map;

public class TotalContainerWeight {

    public TotalContainerWeight() {
        start();
    }

    public void start() {
        System.out.println("===== Total Container Weight By Type =====");
        Map <Container.ContainerType, Double> totalContainerWeight = new HashMap<>();

        // Iterate through the list of containers and calculate the total weight for each type
        for (Container container : PortSystem.getContainerList()) {
            Container.ContainerType containerType = container.getType();
            double containerWeight = container.getWeight();

            // If the container type is already in the HashMap, add the weight to the existing total
            // Otherwise, create a new entry for the container type
            totalContainerWeight.put(containerType, totalContainerWeight.getOrDefault(containerType, 0.0) + containerWeight);
        }


//        List<String> containerWeightStrings = new ArrayList<>();

        // Add content to the display string
        for (Map.Entry<Container.ContainerType, Double> entry : totalContainerWeight.entrySet()) {
            Container.ContainerType containerType = entry.getKey();
            double totalWeight = entry.getValue();
            System.out.println(containerType + ": " + totalWeight);
//            String displayString = containerType + ": " + totalWeight;
//            containerWeightStrings.add(displayString);
        }
    }
}
