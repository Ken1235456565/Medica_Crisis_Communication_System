/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import Model.Network.Network;
import Model.Network.NetworkDirectory;
import Model.Personnel.Admin;
import Model.User.UserAccountDirectory;

/**
 *
 * @author tiankaining
 */
public class EcoSystem {
    private static EcoSystem business; // 单例
    private NetworkDirectory networkDirectory;
    private UserAccountDirectory UserAccountDirectory;
    private Admin admin; // 系统管理员账号

    // 构造器私有化
    public EcoSystem() {
        networkDirectory = new NetworkDirectory();
    }

    // 获取 EcoSystem 单例
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
        return UserAccountDirectory;
    }

    public void setUserAccountDirectory(UserAccountDirectory UserAccountDirectory) {
        this.UserAccountDirectory = UserAccountDirectory;
    }
    
    

    // 登录验证：如果匹配系统管理员
    public Admin authenticateAdmin(String username, String password) {
        if (admin != null &&
            admin.getUsername().equals(username) &&
            admin.getPassword().equals(password)) {
            return admin;
        }
        return null;
    }

    @Override
    public String toString() {
        return "EcoSystem{" +
                "networks=" + networkDirectory.getNetworkList().size() +
                ", admin=" + (admin != null ? admin.getUsername() : "none") +
                '}';
    }
}

