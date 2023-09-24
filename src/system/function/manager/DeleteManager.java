package system.function.manager;

import system.PortSystem;
import system.models.Manager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DeleteManager {

    public DeleteManager() {
        start();
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        boolean found = false;

        while (true) {
            PortSystem.getManagerList().forEach(System.out::println);
            System.out.println("Enter manager ID to delete (or 'q' to exit):");
            String managerID = scanner.nextLine();

            if (managerID.equalsIgnoreCase("q")) {
                System.out.println("Delete manager exited!");
                return; // Exit the method
            }

            List<Manager> managerList = new ArrayList<>(PortSystem.getManagerList());
            for (Manager manager : managerList) {
                if (manager.getManagerID().equalsIgnoreCase(managerID)) {
                    found = true;
                    int indexPort = PortSystem.getPortList().indexOf(manager.getControlPort());
                    PortSystem.getPortList().get(indexPort).setManager(null);
                    PortSystem.getManagerList().remove(manager);
                    System.out.println("Manager removed!");
                    try {
                        PortSystem.writeManagersToFile();
                        PortSystem.writePortsToFile();
                        break;
                    } catch (IOException e) {
                        System.out.println("Write port fail!");
                        throw new RuntimeException(e);
                    }

                }
            }

            if (!found) {
                System.out.println("Manager not found");
            }
        }
    }
}
