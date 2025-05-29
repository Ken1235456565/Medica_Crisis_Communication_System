/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Network;

import Model.Enterprise.EnterpriseDirectory;
import Model.Person.ContactInfo;
import Model.Personnel.Admin;



/**
 *
 * @author MyPC1
 */
// Model/core/Network.java

public class Network {
    private String id;
    private String name;
    private String type; // 可考虑改 enum
    private String description;
    private String manager;
    private ContactInfo contactInfo;
    private EnterpriseDirectory enterpriseDirectory;
    private Admin admin; // 网络管理员账号

    // 默认构造函数
    public Network() {
        this.contactInfo = new ContactInfo();
        this.enterpriseDirectory = new EnterpriseDirectory();
    }

    // 带参数构造函数
    public Network(String id, String name, String type, String description, String manager) {
        this();
        this.id = id;
        this.name = name;
        this.type = type;
        this.description = description;
        this.manager = manager;
    }

    // Getter/Setter
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public ContactInfo getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(ContactInfo contactInfo) {
        this.contactInfo = contactInfo;
    }

    public EnterpriseDirectory getEnterpriseDirectory() {
        return enterpriseDirectory;
    }

    public void setEnterpriseDirectory(EnterpriseDirectory enterpriseDirectory) {
        this.enterpriseDirectory = enterpriseDirectory;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    // 登录认证方法（可选）
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
        return name; // 用于 ComboBox 等显示
    }
}

