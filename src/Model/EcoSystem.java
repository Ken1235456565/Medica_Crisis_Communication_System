/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import Model.Network.Network;
import Model.Network.NetworkDirectory;
import Model.Personnel.Admin;
import Model.Personnel.PublicDataManager;
import Model.PublicData.HealthStatistics;
import Model.User.UserAccountDirectory;
import java.util.List;
import java.util.Map;

/**
 *
 * @author tiankaining
 */
public class EcoSystem {
    private static EcoSystem business;
    private NetworkDirectory networkDirectory;
    private UserAccountDirectory userAccountDirectory;
    private Admin admin;
    private PublicDataManager publicDataManager; // 改为使用PublicDataManager

    public EcoSystem() {
        networkDirectory = new NetworkDirectory();
        this.userAccountDirectory = new UserAccountDirectory();
    }

    public static EcoSystem getInstance() {
        if (business == null) {
            business = new EcoSystem();
        }
        return business;
    }

    // 获取网络目录
    public NetworkDirectory getNetworkDirectory() {
        return networkDirectory;
    }

    // 添加新网络
    public Network createAndAddNetwork(String name) {
        Network network = new Network();
        network.setName(name);
        networkDirectory.getNetworkList().add(network);
        return network;
    }

    // 移除网络
    public void removeNetwork(Network network) {
        networkDirectory.getNetworkList().remove(network);
    }

    // 按名称查找网络
    public Network findNetworkByName(String name) {
        for (Network net : networkDirectory.getNetworkList()) {
            if (net.getName().equalsIgnoreCase(name)) {
                return net;
            }
        }
        return null;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public UserAccountDirectory getUserAccountDirectory() {
        return userAccountDirectory;
    }

    public void setUserAccountDirectory(UserAccountDirectory UserAccountDirectory) {
        this.userAccountDirectory = UserAccountDirectory;
    }

    public PublicDataManager getPublicDataManager() {
        return publicDataManager;
    }

    public void setPublicDataManager(PublicDataManager publicDataManager) {
        this.publicDataManager = publicDataManager;
    }
    
    

    // 登录验证：如果匹配系统管理员
    public Admin authenticateAdmin(String username, String password) {
    if (admin != null &&
        admin.getUserAccount() != null &&
        admin.getUserAccount().getUsername().equals(username) &&
        admin.getUserAccount().getPassword().equals(password)) {
        return admin;
    }
    return null;
}


    @Override
    public String toString() {
        return "EcoSystem{" +
                "networks=" + networkDirectory.getNetworkList().size() +
                ", admin=" + (admin != null && admin.getUserAccount() != null
                              ? admin.getUserAccount().getUsername()
                              : "none") +
                '}';
    }

}

