package Model;

// Model/ConfigureASystem.java

import Model.EcoSystem;
import com.github.javafaker.Faker;


public class ConfigureASystem {
    private final Faker faker;
    private final ConfigurationContext context;
    private final ComponentFactory factory;
    
    public ConfigureASystem() {
        this.faker = new Faker();
        this.context = new ConfigurationContext();
        this.factory = new ComponentFactory(faker, context);
    }
    
    
    public static EcoSystem configure() {
        ConfigureASystem configurator = new ConfigureASystem();
        return configurator.buildSystem();
    }
    
    private EcoSystem buildSystem() {
        EcoSystem system = EcoSystem.getInstance();
        
        // 1. 创建基础网络架构
        setupNetworkInfrastructure(system);
        
        // 2. 创建用户和权限
        setupUsersAndPermissions();
        
        // 3. 生成业务数据
        generateBusinessData();
        
        // 4. 设置公共数据服务
        setupPublicDataServices(system);
        
        return system;
    }
    
    private void setupNetworkInfrastructure(EcoSystem system) {
        // 创建网络
        context.network = factory.createNetwork(system);
        
        // 创建企业
        context.hospitalEnterprise = factory.createHospitalEnterprise(context.network);
        context.emergencyEnterprise = factory.createEmergencyEnterprise(context.network);
        context.logisticsEnterprise = factory.createLogisticsEnterprise(context.network);
        context.publicHealthEnterprise = factory.createPublicHealthEnterprise(context.network);
        
        // 创建组织单位
        factory.createOrganizations();
    }
    
    private void setupUsersAndPermissions() {
        // 创建系统管理员
        context.sysAdmin = factory.createSystemAdmin();
        
        // 创建各部门用户
        factory.createHospitalUsers();
        factory.createEmergencyUsers();
        factory.createLogisticsUsers();
        factory.createPublicHealthUsers();
    }
    
    private void generateBusinessData() {
        // 生成患者和医疗数据
        factory.generatePatientData();
        
        // 生成库存和设备
        factory.generateInventoryData();
        
        // 生成捐赠数据
        factory.generateDonationData();
        
        // 生成车辆数据
        factory.generateVehicleData();
        
        // 生成工作请求
        factory.generateWorkRequests();
        
        // 生成报告和统计
        factory.generateReportsAndStats();
    }
    
    private void setupPublicDataServices(EcoSystem system) {
        // 创建公共数据管理器
        context.publicDataManager = factory.createPublicDataManager();
        system.setPublicDataManager(context.publicDataManager);
        
        // 生成健康统计数据
        factory.generateHealthStatistics();
        
        // 设置组织参考数据
        factory.generateOrganizationReferenceData();
    }
}