// Model/configuration/ConfigurationContext.java
package Model;

import Model.Employee.Employee;
import Model.Enterprise.Enterprise;
import Model.Network.Network;
import Model.Organization.*;
import Model.Personnel.PublicDataManager;


public class ConfigurationContext {
    
    // 网络和企业
    public Network network;
    public Enterprise hospitalEnterprise;
    public Enterprise emergencyEnterprise;
    public Enterprise logisticsEnterprise;
    public Enterprise publicHealthEnterprise;
    
    // 核心组织
    public ClinicalServicesUnit clinicalServicesOrg;
    public AdministrationUnit hospitalAdminOrg;
    public EmergencyResponseUnit emergencyResponseOrg;
    public ResourceDispatchUnit emergencyDispatchOrg;
    public SupplyChainManagementUnit inventoryManagerOrg;
    public ResourceDispatchUnit deliveryUnitOrg;
    public DonationManagementUnit donationManagementOrg;
    public OperationsSupportUnit operationsSupportOrg;
    
    // 关键用户
    public Employee sysAdmin;
    public Employee doctor;
    public Employee nurse;
    public Employee payrollStaff;
    public Employee resourceAnalyst;
    public Employee dispatcher;
    public Employee responder;
    public Employee logisticsManager;
    public Employee deliveryStaff;
    public Employee donationCoordinator;
    public Employee supplychainManager;
    
    // 公共数据服务
    public PublicDataManager publicDataManager;
    
    
    public void validate() {
        if (network == null) {
            throw new IllegalStateException("Network not configured");
        }
        if (clinicalServicesOrg == null) {
            throw new IllegalStateException("Clinical services not configured");
        }
        if (sysAdmin == null) {
            throw new IllegalStateException("System admin not configured");
        }
    }
    
    
    public Enterprise[] getAllEnterprises() {
        return new Enterprise[] {
            hospitalEnterprise,
            emergencyEnterprise,
            logisticsEnterprise,
            publicHealthEnterprise
        };
    }
    
    
    public Employee[] getAllCoreUsers() {
        return new Employee[] {
            sysAdmin, doctor, nurse, payrollStaff, resourceAnalyst,
            dispatcher, responder, logisticsManager, deliveryStaff,
            donationCoordinator, supplychainManager
        };
    }
}
