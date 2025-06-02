/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author tiankaining
 */
import Model.EcoSystem;
import Model.Employee.Employee;
import Model.Enterprise.Enterprise;
import Model.Network.Network;
import Model.Organization.*;
import Model.Patient.Patient;
import Model.Person.ContactInfo;
import Model.Personnel.*;
import Model.PublicData.*;
import Model.Role.*;
import Model.Supplies.*;
import Model.User.UserAccount;
import Model.WorkQueue.Appointment;
import Model.WorkQueue.EmergencyWorkRequest;
import Model.WorkQueue.MissionStats;
import Model.WorkQueue.PayrollRequest;
import Model.WorkQueue.SupplyWorkRequest;
import com.github.javafaker.Faker;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;


public class ComponentFactory {
    
    private final Faker faker;
    private final ConfigurationContext context;
    private final Random random;
    
    public ComponentFactory(Faker faker, ConfigurationContext context) {
        this.faker = faker;
        this.context = context;
        this.random = new Random();
    }
    
    // =================== 网络和企业创建 ===================
    
    public Network createNetwork(EcoSystem system) {
        String networkName = faker.address().city() + " Healthcare Network";
        Network network = system.createAndAddNetwork(networkName);
        network.setType("Healthcare");
        network.setDescription(faker.company().catchPhrase());
        network.setManager(faker.name().fullName());
        network.setContactInfo(generateContactInfo());
        return network;
    }
    
    public Enterprise createHospitalEnterprise(Network network) {
        String name = faker.company().name() + " Hospital";
        String description = "General Hospital in " + faker.address().city();
        String manager = faker.name().fullName();
        Enterprise enterprise = network.getEnterpriseDirectory().createAndAddEnterprise(
            "HOSP" + faker.number().digits(3), name, Enterprise.EnterpriseType.Hospital, description, manager);
        enterprise.setContactInfo(generateContactInfo());
        return enterprise;
    }
    
    public Enterprise createEmergencyEnterprise(Network network) {
        String name = faker.company().name() + " Emergency Services";
        String description = "Emergency and disaster response services";
        String manager = faker.name().fullName();
        Enterprise enterprise = network.getEnterpriseDirectory().createAndAddEnterprise(
            "EMERG" + faker.number().digits(3), name, Enterprise.EnterpriseType.Emergency, description, manager);
        enterprise.setContactInfo(generateContactInfo());
        return enterprise;
    }
    
    public Enterprise createLogisticsEnterprise(Network network) {
        String name = faker.company().name() + " Logistics";
        String description = "Transportation and supply chain management";
        String manager = faker.name().fullName();
        Enterprise enterprise = network.getEnterpriseDirectory().createAndAddEnterprise(
            "LOGS" + faker.number().digits(3), name, Enterprise.EnterpriseType.Logistics, description, manager);
        enterprise.setContactInfo(generateContactInfo());
        return enterprise;
    }
    
    public Enterprise createPublicHealthEnterprise(Network network) {
        String name = faker.company().name() + " Public Health";
        String description = "Public health initiatives and donations";
        String manager = faker.name().fullName();
        Enterprise enterprise = network.getEnterpriseDirectory().createAndAddEnterprise(
            "PH" + faker.number().digits(3), name, Enterprise.EnterpriseType.PublicHealth, description, manager);
        enterprise.setContactInfo(generateContactInfo());
        return enterprise;
    }
    
    // =================== 组织创建 ===================
    
    public void createOrganizations() {
        // 医院组织
        context.clinicalServicesOrg = findOrCreateOrganization(
            context.hospitalEnterprise, "Clinical Services", ClinicalServicesUnit.class);
        context.hospitalAdminOrg = findOrCreateOrganization(
            context.hospitalEnterprise, "Hospital Admin", AdministrationUnit.class);
        
        // 应急组织
        context.emergencyResponseOrg = findOrCreateOrganization(
            context.emergencyEnterprise, "Field Operations", EmergencyResponseUnit.class);
        context.emergencyDispatchOrg = findOrCreateOrganization(
            context.emergencyEnterprise, "911 Dispatch", ResourceDispatchUnit.class);
        
        // 物流组织
        context.inventoryManagerOrg = findOrCreateOrganization(
            context.logisticsEnterprise, "Main Warehouse", SupplyChainManagementUnit.class);
        context.deliveryUnitOrg = findOrCreateOrganization(
            context.logisticsEnterprise, "Transport", ResourceDispatchUnit.class);
        
        // 公共健康组织
        context.donationManagementOrg = findOrCreateOrganization(
            context.publicHealthEnterprise, "Donation Management", DonationManagementUnit.class);
        context.operationsSupportOrg = findOrCreateOrganization(
            context.publicHealthEnterprise, "Operations Support", OperationsSupportUnit.class);
    }
    
@SuppressWarnings("unchecked")
private <T extends Organization> T findOrCreateOrganization(Enterprise enterprise, String name, Class<T> type) {
    Organization existing = enterprise.findOrganizationByName(name);
    if (existing != null && type.isInstance(existing)) {
        return (T) existing;
    }

    if (type == ClinicalServicesUnit.class) {
        ICUbedCatalog icuBedCatalog = new ICUbedCatalog(); // ✅ 创建 ICU 床位目录
        ClinicalServicesUnit org = new ClinicalServicesUnit(name, icuBedCatalog, true);
        enterprise.addOrganization(org);
        return (T) org;
    }

    if (type == AdministrationUnit.class) {
        AdministrationUnit org = new AdministrationUnit(name, null);
        enterprise.addOrganization(org);
        return (T) org;
    }

    if (type == EmergencyResponseUnit.class) {
        EmergencyResponseUnit org = new EmergencyResponseUnit(name, null);
        enterprise.addOrganization(org);
        return (T) org;
    }

    if (type == ResourceDispatchUnit.class) {
        ResourceDispatchUnit org = new ResourceDispatchUnit(name, null);
        enterprise.addOrganization(org);
        return (T) org;
    }

    if (type == SupplyChainManagementUnit.class) {
        SupplyItemCatalog itemCatalog = new SupplyItemCatalog();   // ✅ 创建物品目录
        DeliveryCatalog deliveryCatalog = new DeliveryCatalog();   // ✅ 创建配送目录
        SupplyChainManagementUnit org = new SupplyChainManagementUnit(name, itemCatalog, deliveryCatalog);
        enterprise.addOrganization(org);
        return (T) org;
    }

    if (type == DonationManagementUnit.class) {
        DonationManagementUnit org = new DonationManagementUnit();
        org.setOrganizationName(name); // ✅ 设置组织名称
        enterprise.addOrganization(org);
        return (T) org;
    }

    if (type == OperationsSupportUnit.class) {
        OperationsSupportUnit org = new OperationsSupportUnit(name, new ArrayList<>(), "2025"); // ✅ 修复构造函数参数
        enterprise.addOrganization(org);
        return (T) org;
    }

    throw new IllegalArgumentException("Unsupported organization type: " + type);
}

    
    // =================== 用户创建 ===================
    
    public Employee createSystemAdmin() {
        ContactInfo contact = generateContactInfo();
        Employee admin = new Employee("EMP" + faker.number().digits(4), faker.name().fullName(), 
            faker.options().option("Male", "Female"), faker.number().numberBetween(25, 60), 
            generateDateOfBirth(), "System Administrator", "IT", contact);
        
        UserAccount account = EcoSystem.getInstance().getUserAccountDirectory()
            .createUserAccount("sysadmin", "sysadmin", admin, new AdminRole());
        admin.setUserAccount(account);
        
        context.sysAdmin = admin;
        return admin;
    }
    
    public void createHospitalUsers() {
        // 创建医生
    // 1. 创建基础 Employee（但只是临时中转）
    Employee temp = createUser("doctor", "doctor", "Physician", "Medical", 
        context.clinicalServicesOrg, new DoctorRole());

    // 2. 用 temp 的数据构造 Doctor 子类实例（复制字段）
        Doctor doctor = new Doctor(
            "Cardiology", // 你可以自己换成别的专业
            "DOC" + System.currentTimeMillis(), // license number（模拟唯一）
            temp.getId(),
            temp.getName(),
            temp.getGender(),
            temp.getAge(),
            temp.getDateOfBirth(),
            temp.getPosition(),
            temp.getDepartment(),
            temp.getContactInfo()
        );

        // 3. 更新 UserAccount 中的 employee 引用
        doctor.setUserAccount(temp.getUserAccount());
        temp.getUserAccount().setEmployee(doctor);

        // 4. 替换原组织里的 Employee（删旧加新）
        context.clinicalServicesOrg.getEmployeeDirectory().getEmployeeList().remove(temp);
        context.clinicalServicesOrg.addEmployee(doctor);

        // 5. 存到 context
        context.doctor = doctor;

            // 创建护士
            context.nurse = createUser("nurse", "nurse", "Registered Nurse", "Nursing", 
                context.clinicalServicesOrg, new NurseRole());

            // 创建财务人员
            context.payrollStaff = createUser("payrollstaff", "payrollstaff", "Payroll Staff", "Finance", 
                context.hospitalAdminOrg, new PayrollStaffRole());

            // 创建资源分析师
            context.resourceAnalyst = createUser("resourceanalyst", "resourceanalyst", "Resource Analyst", "Administration", 
                context.hospitalAdminOrg, new ResourceAnalystRole());
        }
    
    public void createEmergencyUsers() {
        // 创建调度员
        context.dispatcher = createUser("dispatcher", "dispatcher", "Dispatcher", "Dispatch", 
            context.emergencyDispatchOrg, new EmergencyDispatcherRole());
        
        // 创建应急响应员
        context.responder = createUser("responder", "responder", "Responder", "Response", 
            context.emergencyResponseOrg, new EmergencyResponderRole());
    }
    
    public void createLogisticsUsers() {
        // 创建物流经理
        context.logisticsManager = createUser("logisticsmanager", "logisticsmanager", "Manager", "Logistics", 
            context.inventoryManagerOrg, new LogisticsManagerRole());
        
        // 创建配送人员
        context.deliveryStaff = createUser("deliverystaff", "deliverystaff", "Driver", "Delivery", 
            context.deliveryUnitOrg, new DeliveryStaffRole());
    }
    
    public void createPublicHealthUsers() {
        // 创建捐赠协调员
        context.donationCoordinator = createUser("donationcoordinator", "donationcoordinator", 
            "Donation Coordinator", "Donation Management", context.donationManagementOrg, new DonationCoordinatorRole());
        
        // 创建供应链经理
        context.supplychainManager = createUser("supplymanager", "supplymanager", 
            "Supply Chain Manager", "Supply Chain", context.inventoryManagerOrg, new SupplychainManagerRole());
    }
    
    private Employee createUser(String username, String password, String position, String department, 
                               Organization organization, Role role) {
        ContactInfo contact = generateContactInfo();
        Employee employee = new Employee("EMP" + faker.number().digits(4), faker.name().fullName(), 
            faker.options().option("Male", "Female"), faker.number().numberBetween(22, 60), 
            generateDateOfBirth(), position, department, contact);
        
        UserAccount account = organization.getUserAccountDirectory()
            .createUserAccount(username, password, employee, role);
        employee.setUserAccount(account);
        organization.addEmployee(employee);
        
        return employee;
    }
    
    // =================== 数据生成方法 ===================
    
    public void generatePatientData() {
        for (int i = 0; i < 15; i++) {
            Patient patient = new Patient(faker.name().fullName(), 
                faker.options().option("Male", "Female", "Other"), 
                faker.number().numberBetween(1, 90), generateDateOfBirth());
            patient.setContactInfo(generateContactInfo());
            
            // 添加医疗记录
            int numEntries = faker.number().numberBetween(1, 3);
            String[] diagnoses = {"Flu", "Hypertension", "Diabetes", "Allergy", "Anemia"};
            String[] treatments = {"Blood Test", "MRI", "Vaccination", "Physical Therapy", "Surgery"};
            
            for (int j = 0; j < numEntries; j++) {
                patient.getMedicalRecord().addMedicalEntry(
                    diagnoses[random.nextInt(diagnoses.length)],
                    treatments[random.nextInt(treatments.length)]
                );
            }
            
            context.clinicalServicesOrg.addPatient(patient);
            
            // 创建预约
            if (context.doctor != null) {
                Date appointmentDate = faker.date().future(30, TimeUnit.DAYS);
                String appointmentTime = faker.number().numberBetween(8, 17) + ":" + 
                    faker.options().option("00", "30");
                
                Appointment appointment = new Appointment(patient, context.doctor, appointmentDate, appointmentTime);
                appointment.setAppointmentType(faker.options().option("Regular Checkup", "Follow-up", "Emergency"));
                appointment.setLocation(faker.address().streetAddress());
                appointment.setSender(context.nurse.getUserAccount());
                appointment.setReceiver(context.doctor.getUserAccount());
                
                context.clinicalServicesOrg.scheduleAppointment(appointment);
            }
        }
    }
    
    public void generateInventoryData() {
        // 生成供应品
        for (int i = 0; i < 20; i++) {
            String name = faker.commerce().productName();
            String description = faker.lorem().sentence();
            String type = faker.options().option("Medical Supplies", "Office Supplies", "Cleaning Supplies", "Food");
            int quantity = faker.number().numberBetween(10, 500);
            double unitPrice = faker.number().randomDouble(2, 1, 100);
            
            SupplyItem item = new SupplyItem(name, description, type, quantity, unitPrice);
            item.setExpirationDate(faker.date().future(365, TimeUnit.DAYS));
            item.setManufacturer(faker.company().name());
            item.setVendor(faker.company().name());
            
            context.inventoryManagerOrg.addItem(item);
            
            // 设置重订阈值
            if (i % 3 == 0) {
                context.inventoryManagerOrg.setReorderThreshold(name, quantity * 0.2);
            }
        }
        
        // 生成设备
        String[] equipmentNames = {"X-Ray Machine", "Ultrasound Scanner", "Ventilator", "Defibrillator", "Surgical Robot"};
        for (String equipmentName : equipmentNames) {
            Equipments equipment = new Equipments(equipmentName, faker.lorem().sentence(), 
                "Medical Equipment", faker.number().numberBetween(1, 5), 
                faker.number().randomDouble(2, 1000, 100000), 
                faker.bothify("###-???-####"), 
                faker.date().past(365 * 5, TimeUnit.DAYS), 
                faker.options().option("Radiology", "Cardiology", "Emergency", "Surgery"));
            
            equipment.setStatus(faker.options().option("Available", "In Use", "Under Maintenance"));
            if (equipment.getStatus().equals("Under Maintenance")) {
                equipment.scheduleMaintenance(faker.date().future(30, TimeUnit.DAYS));
            }
            
            context.inventoryManagerOrg.addItem(equipment);
        }
    }
    
    public void generateDonationData() {
        List<Donor> donors = new ArrayList<>();
        
        // 创建捐赠者
        for (int i = 0; i < 12; i++) {
            String donorName = faker.name().fullName();
            String gender = faker.options().option("Male", "Female", "Other");
            int age = faker.number().numberBetween(18, 80);
            String dob = generateDateOfBirth();
            ContactInfo contact = generateContactInfo();
            
            Donor donor = new Donor(donorName, gender, age, dob, contact);
            
            // 创建登录账号
            String username = "donor"; //
            UserAccount account = new UserAccount(username, "password", new VisitorRole(), "Public Donors", donor);
            donor.setUserAccount(account);
            context.donationManagementOrg.getUserAccountDirectory().addUserAccount(account);
            
            context.donationManagementOrg.addDonor(donor);
            donors.add(donor);
        }
        
        // 创建捐赠记录
        for (int i = 0; i < 25; i++) {
            Donor donor = donors.get(faker.number().numberBetween(0, donors.size()));
            String purpose = faker.options().option("Medical Research", "Disaster Relief", "Community Support", "General Fund");
            
            Donation donation;
            if (faker.bool().bool()) {
                // 现金捐赠
                double amount = faker.number().randomDouble(2, 50, 5000);
                donation = context.donationManagementOrg.recordMonetaryDonation(donor, amount, purpose);
            } else {
                // 物品捐赠
                List<DonatedItem> items = new ArrayList<>();
                int numItems = faker.number().numberBetween(1, 4);
                for (int j = 0; j < numItems; j++) {
                    items.add(new DonatedItem(faker.commerce().productName(), 
                        faker.number().numberBetween(1, 10), 
                        faker.number().randomDouble(2, 5, 200)));
                }
                donation = context.donationManagementOrg.recordItemDonation(donor, items, purpose);
            }
            
            donation.setNotes(faker.lorem().sentence());
            if (faker.bool().bool()) {
                donation.processDonation();
            }
        }
    }
    
    public void generateVehicleData() {
        // 为物流单位生成车辆
        for (int i = 0; i < 8; i++) {
            String vehicleType = faker.options().option("Ambulance", "Cargo Truck", "Van", "Emergency Car");
            String vehicleId = "VEH" + faker.number().digits(4);
            String status = faker.options().option("Available", "In Use", "Under Maintenance");
            double fuelLevel = faker.number().randomDouble(2, 20, 100);
            
            Vehicle vehicle = new Vehicle(vehicleType, vehicleId, status, fuelLevel);
            context.deliveryUnitOrg.addVehicle(vehicle);
        }
        
        // 为应急单位生成车辆
        for (int i = 0; i < 5; i++) {
            String vehicleId = "EM_VEH" + faker.number().digits(3);
            boolean isAvailable = faker.bool().bool();
            context.emergencyResponseOrg.addEmergencyVehicle(vehicleId, isAvailable);
        }
    }
    
    public void generateWorkRequests() {
        // 供应请求
        for (int i = 0; i < 6; i++) {
            if (!context.inventoryManagerOrg.getInventoryList().isEmpty()) {
                SupplyItem item = context.inventoryManagerOrg.getInventoryList()
                    .get(faker.number().numberBetween(0, context.inventoryManagerOrg.getInventoryList().size()));
                
                SupplyWorkRequest request = new SupplyWorkRequest(item, 
                    faker.number().numberBetween(10, 100), 
                    faker.options().option("Emergency", "Clinical", "Logistics"));
                request.setUrgency(faker.options().option("High", "Medium", "Low"));
                request.setSender(context.sysAdmin.getUserAccount());
                request.setReceiver(context.logisticsManager.getUserAccount());
                request.setStatus(faker.options().option("Requested", "Approved", "Fulfilled"));
                
                context.inventoryManagerOrg.addWorkRequest(request);
            }
        }
        
        // 应急请求
        for (int i = 0; i < 5; i++) {
            String emergencyType = faker.options().option("Medical Emergency", "Fire", "Accident", "Natural Disaster");
            String location = faker.address().fullAddress();
            
            EmergencyWorkRequest request = new EmergencyWorkRequest(emergencyType, location, 
                faker.number().numberBetween(1, 5), faker.name().fullName());
            request.setSender(context.sysAdmin.getUserAccount());
            request.setReceiver(context.dispatcher.getUserAccount());
            request.setStatus(faker.options().option("Reported", "Dispatched", "Completed"));
            
            if (request.getStatus().equals("Dispatched") || request.getStatus().equals("Completed")) {
                request.setAssignedVehicle(faker.options().option("AMB" + faker.number().digits(2), 
                    "TRUCK" + faker.number().digits(2)));
            }
            
            context.emergencyResponseOrg.addWorkRequest(request);
        }
        
        // 薪资请求
        if (context.operationsSupportOrg.getStaffList().isEmpty()) {
            context.operationsSupportOrg.addStaff(context.payrollStaff);
            context.operationsSupportOrg.addStaff(context.resourceAnalyst);
            context.operationsSupportOrg.addStaff(context.doctor);
        }
        
        for (int i = 0; i < 3; i++) {
            Employee emp = context.operationsSupportOrg.getStaffList()
                .get(faker.number().numberBetween(0, context.operationsSupportOrg.getStaffList().size()));
            
            Date payStart = faker.date().past(30, TimeUnit.DAYS);
            Date payEnd = new Date();
            Date payDate = faker.date().future(7, TimeUnit.DAYS);
            
            PayrollRequest request = new PayrollRequest(emp, payStart, payEnd, payDate);
            request.setHoursWorked(faker.number().randomDouble(2, 120, 160));
            request.setOvertimeHours(faker.number().randomDouble(2, 0, 20));
            request.setSender(context.sysAdmin.getUserAccount());
            request.setReceiver(context.payrollStaff.getUserAccount());
            request.setStatus(faker.options().option("Submitted", "Processed"));
            
            context.operationsSupportOrg.addWorkRequest(request);
        }
    }
    
    public void generateReportsAndStats() {
        // 生成性能报告
        for (int i = 0; i < 3; i++) {
            String title = faker.lorem().words(4).toString() + " Performance Report";
            String type = faker.options().option("Staffing", "Financial", "Operational");
            Date startDate = faker.date().past(90, TimeUnit.DAYS);
            Date endDate = new Date();
            
            Map<String, Object> metrics = new HashMap<>();
            metrics.put("EfficiencyScore", faker.number().randomDouble(2, 50, 100) / 100.0);
            metrics.put("ComplianceRate", faker.number().randomDouble(2, 80, 99) / 100.0);
            
            context.operationsSupportOrg.generatePerformanceReport(title, type, startDate, endDate, metrics);
        }
        
        // 生成任务统计
        for (int i = 0; i < 3; i++) {
            String missionName = faker.lorem().words(2).toString() + " Mission";
            Date missionStart = faker.date().past(30, TimeUnit.DAYS);
            Date missionEnd = faker.date().between(missionStart, faker.date().future(7, TimeUnit.DAYS, missionStart));
            String missionType = faker.options().option("Emergency Response", "Logistics Operation", "Public Health Campaign");
            String location = faker.address().cityName();
            
            MissionStats missionStats = new MissionStats(missionName, missionStart, missionEnd, missionType, location);
            missionStats.addStat("ResourcesDeployed", faker.number().numberBetween(5, 20));
            missionStats.addStat("PersonnelInvolved", faker.number().numberBetween(10, 50));
            missionStats.setSender(context.sysAdmin.getUserAccount());
            missionStats.setReceiver(context.supplychainManager.getUserAccount());
            missionStats.setStatus("Finalized");
            
            context.operationsSupportOrg.addWorkRequest(missionStats);
            context.emergencyResponseOrg.addWorkRequest(missionStats);
        }
    }
    
    // =================== 公共数据服务 ===================
    
    public PublicDataManager createPublicDataManager() {
        ContactInfo contact = generateContactInfo();
        PublicDataManager pdm = new PublicDataManager("Healthcare Analytics",
            "EMP" + faker.number().digits(4), faker.name().fullName(),
            faker.options().option("Male", "Female"), faker.number().numberBetween(30, 55),
            generateDateOfBirth(), "Public Data Manager", "Analytics Department", contact);
        
        context.publicDataManager = pdm;
        return pdm;
    }
    
    public void generateHealthStatistics() {
        List<RegionData> regionDataList = new ArrayList<>();
        String[] regions = {"North District", "South District", "East District", "West District", "Central District"};
        
        for (String regionName : regions) {
            int totalPatients = faker.number().numberBetween(50, 200);
            int discharged = (int) (totalPatients * (0.6 + random.nextDouble() * 0.3));
            int hospitalized = (int) (totalPatients * (0.1 + random.nextDouble() * 0.2));
            int deaths = (int) (totalPatients * (0.01 + random.nextDouble() * 0.04));
            double averageLengthOfStay = 3.0 + random.nextDouble() * 7.0;
            
            int feverCount = (int) (totalPatients * (0.3 + random.nextDouble() * 0.4));
            int coughCount = (int) (totalPatients * (0.2 + random.nextDouble() * 0.3));
            int chestTightnessCount = (int) (totalPatients * (0.1 + random.nextDouble() * 0.2));
            int comaCount = (int) (totalPatients * (0.01 + random.nextDouble() * 0.04));
            
            SymptomData symptomData = new SymptomData(regionName, feverCount, coughCount, 
                chestTightnessCount, comaCount);
            
            RegionData regionData = new RegionData(regionName, totalPatients, discharged, 
                hospitalized, deaths, averageLengthOfStay, symptomData);
            
            regionDataList.add(regionData);
        }
        
        HealthStatistics healthStats = new HealthStatistics(regionDataList, "7 days", new Date());
        context.publicDataManager.setCurrentHealthStatistics(healthStats);
        
        // 生成不同时间跨度的数据
        generateHealthDataForTimeSpans(regionDataList);
    }
    
    private void generateHealthDataForTimeSpans(List<RegionData> baseData) {
        String[] timeSpans = {"3 days", "7 days", "30 days"};
        
        for (String timeSpan : timeSpans) {
            List<RegionData> adjustedData = new ArrayList<>();
            double multiplier = getTimeSpanMultiplier(timeSpan);
            
            for (RegionData region : baseData) {
                int adjustedTotal = (int) (region.getTotalPatients() * multiplier);
                int adjustedDischarged = (int) (region.getDischargedCount() * multiplier);
                int adjustedHospitalized = (int) (region.getHospitalizedCount() * multiplier);
                int adjustedDeaths = (int) (region.getDeathsCount() * multiplier);
                
                SymptomData originalSymptoms = region.getSymptomSummary();
                SymptomData adjustedSymptoms = new SymptomData(region.getRegionName(),
                    (int) (originalSymptoms.getFeverCount() * multiplier),
                    (int) (originalSymptoms.getCoughCount() * multiplier),
                    (int) (originalSymptoms.getChestTightnessCount() * multiplier),
                    (int) (originalSymptoms.getComaCount() * multiplier));
                
                RegionData adjustedRegion = new RegionData(region.getRegionName(),
                    adjustedTotal, adjustedDischarged, adjustedHospitalized, adjustedDeaths,
                    region.getAverageLengthOfStay(), adjustedSymptoms);
                
                adjustedData.add(adjustedRegion);
            }
            
            HealthStatistics timeSpanStats = new HealthStatistics(adjustedData, timeSpan, new Date());
            context.publicDataManager.addTimeSpanData(timeSpan, timeSpanStats);
        }
    }
    
    private double getTimeSpanMultiplier(String timeSpan) {
        switch (timeSpan) {
            case "3 days": return 0.4;
            case "7 days": return 1.0;
            case "30 days": return 4.2;
            default: return 1.0;
        }
    }
    
    public void generateOrganizationReferenceData() {
        List<String> standardOrganizations = Arrays.asList(
            "Emergency Response Unit", "Clinical Services Unit", "Supply Chain Management",
            "Operations Support Unit", "Disaster Relief Team", "Community Health Center",
            "Medical Research Division", "Public Health Department");
        
        context.publicDataManager.setStandardOrganizationList(standardOrganizations);
    }
    
    // =================== 工具方法 ===================
    
    private ContactInfo generateContactInfo() {
        return new ContactInfo(faker.address().fullAddress(), 
            faker.phoneNumber().phoneNumber(), faker.internet().emailAddress());
    }
    
    private String generateDateOfBirth() {
        return new SimpleDateFormat("yyyy-MM-dd").format(faker.date().birthday());
    }
}