package system.function.manager;

import system.PortSystem;
import system.models.Manager;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UpdateManager {

    public UpdateManager() {
        start();
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        PortSystem.getManagerList().forEach(System.out::println);
        Manager selectedManager = null;
        while (true) {
            try {
                System.out.println("Enter manager ID to modify: ('q' to exit)");
                String managerID = scanner.nextLine();
                if (managerID.equalsIgnoreCase("q")) {
                    break;
                } else {
                    boolean found = false;

                    for (Manager manager : PortSystem.getManagerList()) {
                        if (manager.getManagerID().equalsIgnoreCase(managerID)) {
                            selectedManager = manager;
                            found = true;
                            break; // Stop searching after finding and displaying the container
                        }
                    }

                    if (!found) {
                        System.out.println("Manager not found");
                    } else {

                        while (true) {
                            System.out.println("Choose what to update");
                            System.out.println("1. Username");
                            System.out.println("2. Password");
                            System.out.println("3. Exit");
                            System.out.println("Your choice: ");

                            try {
                                int choice = scanner.nextInt();

                                switch (choice) {
                                    case 1:
                                        updateUsername(selectedManager);
                                        break;
                                    case 2:
                                        updatePassword(selectedManager);
                                        break;
                                    case 3:
                                        new ManagerMenu();
                                        return;
                                    default:
                                        System.out.println("Please choose the correct option!");
                                }
                            } catch (InputMismatchException e) {
                                System.out.println("Please pick a number");
                                scanner.nextLine(); // Consume the invalid input
                            }
                        }
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println("Please pick a number");
                scanner.nextLine(); // Consume the invalid input
            }
        }
    }

    public void updateUsername(Manager selectedManager) {
        Scanner scanner = new Scanner(System.in);
        boolean usernameTaken = false;
        while (true) {
            System.out.println("Enter new username: ");
            String username = scanner.nextLine();

            for (Manager manager : PortSystem.getManagerList()) {
                if (manager.getUsername().equals(username)) {
                    usernameTaken = true;
                }
            }

            if (usernameTaken) {
                System.out.println("This username is already taken. Please choose another one.");
            } else {
                selectedManager.setUsername(username);
                PortSystem.updateManagerInfo(selectedManager);
                break;
            }
        }
    }

    public void updatePassword(Manager selectedManager) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter new password: ");
        String password = scanner.nextLine();
        selectedManager.setPassword(password);
        PortSystem.updateManagerInfo(selectedManager);
    }

}
