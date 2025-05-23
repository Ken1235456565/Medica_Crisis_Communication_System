/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import Model.Network.Network;
import Model.Network.NetworkDirectory;

/**
 *
 * @author tiankaining
 */
public class EcoSystem {
    private static EcoSystem business; // 单例
    private NetworkDirectory networkDirectory;

    // 构造器私有化，防止直接 new
    private EcoSystem() {
        networkDirectory = new NetworkDirectory();
    }

    // 获取单例实例
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

    // 查找网络
    public Network findNetworkByName(String name) {
        for (Network net : networkDirectory.getNetworkList()) {
            if (net.getName().equalsIgnoreCase(name)) {
                return net;
            }
        }
        return null;
    }

    // 后续可添加：角色、审计、全局配置等
}
