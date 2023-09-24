package system.function.manager;

import system.PortSystem;
import system.models.Manager;

import java.util.Scanner;

public class SearchManager {

    public SearchManager() {
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        boolean found = false; // Initialize found as false

        while (true) {
            System.out.println("Enter manager ID (or 'q' to exit): ");
            String managerID = scanner.nextLine();

            if (managerID.equalsIgnoreCase("q")) {
                System.out.println("Exiting search for port.");
                break; // Exit the loop and return to the Port Menu
            }

            for (Manager manager : PortSystem.getManagerList()) {
                if (manager.getManagerID().equalsIgnoreCase(managerID)) {
                    System.out.println(managerID);
                    found = true; // Port found
                    break;
                }
            }

            if (!found) {
                System.out.println("Manager not found.");
            }
        }
        new ManagerMenu();
    }
}
