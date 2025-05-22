package Model.Network;

import Model.User.UserAccountDirectory;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author rrheg
 */

public class NetworkDirectory {
    private static NetworkDirectory instance; // Singleton instance
    private List<Network> networkList;
    private UserAccountDirectory userAccountDirectory; // Added UserAccountDirectory

    private NetworkDirectory() { // Made constructor private for Singleton pattern
        this.networkList = new ArrayList<>();
        this.userAccountDirectory = new UserAccountDirectory(); // Initialize UserAccountDirectory
    }

    public static synchronized NetworkDirectory getInstance() { // Singleton getInstance method
        if (instance == null) {
            instance = new NetworkDirectory();
        }
        return instance;
    }

    public List<Network> getNetworkList() {
        return networkList;
    }

    public void setNetworkList(List<Network> networkList) {
        this.networkList = networkList;
    }

    public void addNetwork(Network network) {
        this.networkList.add(network);
    }

    public void removeNetwork(Network network) {
        this.networkList.remove(network);
    }

    // New method to create and add a network
    public Network createAndAddNetwork() {
        Network network = new Network(); // Create a new Network
        this.networkList.add(network);
        return network;
    }
    
    // New method to create and add a named network
    public Network createAndAddNetwork(String name) {
        Network network = new Network(); // Create a new Network
        network.setName(name); // Set the name
        this.networkList.add(network);
        return network;
    }

    public Network findNetworkById(String id) {
        for (Network net : networkList) {
            if (net.getId().equals(id)) {
                return net;
            }
        }
        return null;
    }

    // Getter for UserAccountDirectory
    public UserAccountDirectory getUserAccountDirectory() {
        return userAccountDirectory;
    }

    public void setUserAccountDirectory(UserAccountDirectory userAccountDirectory) {
        this.userAccountDirectory = userAccountDirectory;
    }
}