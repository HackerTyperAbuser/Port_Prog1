package system;

import system.function.HomePage;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            // Load data into the system
            PortSystem.readPortsFromFile();
            PortSystem.readContainersFromFile();
            PortSystem.readVehiclesFromFile();
            PortSystem.readManagersFromFile();
            PortSystem.readTripsFromFile();
            // Code for delete trip history, commented this out if don't want to use
            PortSystem.deleteTripAfter7Days();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        new HomePage();

        
    }
}
