package system.function.manager;

import system.PortSystem;

public class ViewManager {

    public ViewManager() {
        start();
    }

    public void start() {
        System.out.println("List of managers: ");
        PortSystem.getManagerList().forEach(System.out::println);
        new ManagerMenu();
    }
}
