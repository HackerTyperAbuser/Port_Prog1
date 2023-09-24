package system.function;

import system.PortSystem;
import system.models.Manager;

import java.io.IOException;
import java.util.Scanner;

public class LoginPage {

    public LoginPage() {
        try {
            PortSystem.readManagersFromFile();
            start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            boolean accountFound = false;
            System.out.println("Enter your username: ");
            String username = scanner.nextLine();
            System.out.println("Enter your password: ");
            String password = scanner.nextLine();
            if (username.equals("admin") && password.equals("123456")) {
                accountFound = true;
                new AdminMenuBar();
            } else {
                for (Manager manager : PortSystem.getManagerList()) {
                    if (manager.getUsername().equals(username) && manager.getPassword().equals(password)) {
                        new ManagerMenuBar(manager.getControlPort());
                        accountFound = true;
                        break;
                    }
                }

                System.out.println("This account doesn't exist!");
                start();
            }

            if (!accountFound) {
                System.out.println("Account doesn't exist");
            }
        }

    }
}
