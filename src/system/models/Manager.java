package system.models;

import java.io.*;

public class Manager implements Serializable {
    @Serial
    private static final long serialVersionUID = 5L;
    private static final String COUNT_ID_FILE = "src/data_storage/managerIDCount.txt";
    private static int counter = loadCounter();
    private String managerID;
    private String username;
    private String password;
    private Port controlPort;

    public Manager(String username, String password, Port controlPort) {
        this.managerID = generateManagerID();
        this.username = username;
        this.password = password;
        this.controlPort = controlPort;
        Manager.saveCounter();
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

    private static String generateManagerID() {
        return "M-" + counter++;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Port getControlPort() {
        return controlPort;
    }

    public void setControlPort(Port controlPort) {
        this.controlPort = controlPort;
    }

    public String getManagerID() {
        return managerID;
    }

    @Override
    public String toString() {
        return String.format("ID: %s, Username: %s, Password: %s, Control Port: [ID: %s, Name: %s]\n", getManagerID(), getUsername(), getPassword(), getControlPort().getPortID(), getControlPort().getPortName());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Manager manager = (Manager) obj;
        return managerID.equals(manager.managerID);
    }

    @Override
    public int hashCode() {
        return managerID.hashCode();
    }
}
