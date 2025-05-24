package Model;

import Model.Enterprise.Enterprise;
import Model.Network.Network;
import Model.Network.NetworkDirectory;
import Model.Organization.ClinicalServicesUnit;
import Model.Organization.EmergencyResponseUnit;
import Model.Organization.ResourceDispatchUnit;
import Model.Organization.InventoryManager;
import Model.Organization.AdministrationUnit;
import Model.Organization.DonationManagementUnit;
import Model.Organization.OperationsSupportUnit;

import Model.Personnel.Admin;
import Model.Personnel.DeliveryStaff;
import Model.Personnel.Doctor;
import Model.Personnel.EmergencyDispatcher;
import Model.Personnel.EmergencyResponder;
import Model.Personnel.Employee;
import Model.Personnel.LogisticsManager;
import Model.Personnel.Nurse;
import Model.Role.Role;
import Model.Personnel.Donor; // Import Donor

import Model.Person.ContactInfo;
import Model.Patient.Patient; // Import Patient
import Model.Supplies.SupplyItem; // Import SupplyItem
import Model.Supplies.Equipments; // Import Equipments
import Model.Supplies.Vehicle; // Import Vehicle
import Model.Supplies.Donation; // Import Donation
import Model.Supplies.Donation.DonatedItem; // Import DonatedItem

import Model.WorkQueue.Appointment; // Import Appointment
import Model.WorkQueue.EmergencyWorkRequest; // Import EmergencyWorkRequest
import Model.WorkQueue.SupplyWorkRequest; // Import SupplyWorkRequest
import Model.WorkQueue.DonationRequest; // Import DonationRequest
import Model.WorkQueue.DeliveryAssignmentRequest; // Import DeliveryAssignmentRequest
import Model.WorkQueue.PayrollRequest; // Import PayrollRequest
import Model.WorkQueue.ResourceAnalysisRequest; // Import ResourceAnalysisRequest
import Model.WorkQueue.CostReport; // Import CostReport
import Model.WorkQueue.MissionStats; // Import MissionStats
import Model.WorkQueue.ICURequest; // Import ICURequest

import Model.User.UserAccount;

import com.github.javafaker.Faker; // Import Faker library
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit; // For date manipulation
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


public class ConfigureASystem {

    public static NetworkDirectory configure() {
        Faker faker = new Faker(); // Create a Faker instance

        NetworkDirectory system = NetworkDirectory.getInstance();

        // Configure Boston Network with Faker-generated data
        String networkName = faker.address().city() + " Network";
        Network bostonNetwork = system.createAndAddNetwork(networkName);
        bostonNetwork.setType("Healthcare");
        bostonNetwork.setDescription(faker.company().catchPhrase());
        bostonNetwork.setManager(faker.name().fullName());
        ContactInfo networkContact = new ContactInfo(faker.address().fullAddress(), faker.phoneNumber().phoneNumber(), faker.internet().emailAddress());
        bostonNetwork.setContactInfo(networkContact);

        // Configure Hospital Enterprise with Faker-generated data
        String hospitalName = faker.company().name() + " Hospital";
        String hospitalDescription = "General Hospital in " + faker.address().city();
        String hospitalManager = faker.name().fullName();
        Enterprise bostonHospital = bostonNetwork.getEnterpriseDirectory().createAndAddEnterprise(
            "HOSP" + faker.number().digits(3), hospitalName, Enterprise.EnterpriseType.Hospital, hospitalDescription, hospitalManager);
        ContactInfo hospitalContact = new ContactInfo(faker.address().fullAddress(), faker.phoneNumber().phoneNumber(), faker.internet().emailAddress());
        bostonHospital.setContactInfo(hospitalContact);

        // Hospital Organizations
        ClinicalServicesUnit clinicalServicesOrg = (ClinicalServicesUnit) bostonHospital.findOrganizationByName("Clinical Services");
        if (clinicalServicesOrg == null) {
            clinicalServicesOrg = new ClinicalServicesUnit("Clinical Services", null, true); // Added admin placeholder and emergencyReady
            bostonHospital.addOrganization(clinicalServicesOrg);
        }
        Employee hospitalAdminEmployeePlaceholder = new Employee(faker.name().fullName(), faker.name().username(), faker.internet().password(), new Admin(), "Administration", "Hospital Management");
        hospitalAdminEmployeePlaceholder.setContactInfo(new ContactInfo(faker.address().fullAddress(), faker.phoneNumber().phoneNumber(), faker.internet().emailAddress()));
        AdministrationUnit hospitalAdminOrg = (AdministrationUnit) bostonHospital.findOrganizationByName("Hospital Admin");
        if (hospitalAdminOrg == null) {
            hospitalAdminOrg = new AdministrationUnit("Hospital Admin", hospitalAdminEmployeePlaceholder);
            bostonHospital.addOrganization(hospitalAdminOrg);
        }

        // Configure Emergency Enterprise with Faker-generated data
        String emergencyName = faker.company().name() + " Emergency Services";
        String emergencyDescription = "Manages emergency and disaster response in " + faker.address().city();
        String emergencyManager = faker.name().fullName();
        Enterprise bostonEmergency = bostonNetwork.getEnterpriseDirectory().createAndAddEnterprise(
            "EMERG" + faker.number().digits(3), emergencyName, Enterprise.EnterpriseType.Emergency, emergencyDescription, emergencyManager);
        ContactInfo emergencyContact = new ContactInfo(faker.address().fullAddress(), faker.phoneNumber().phoneNumber(), faker.internet().emailAddress());
        bostonEmergency.setContactInfo(emergencyContact);

        // Emergency Organizations
        EmergencyResponseUnit emergencyResponseOrg = (EmergencyResponseUnit) bostonEmergency.findOrganizationByName("Field Operations");
        if (emergencyResponseOrg == null) {
            emergencyResponseOrg = new EmergencyResponseUnit("Field Operations", null); // Added admin placeholder
            bostonEmergency.addOrganization(emergencyResponseOrg);
        }
        ResourceDispatchUnit emergencyDispatchOrg = (ResourceDispatchUnit) bostonEmergency.findOrganizationByName("911 Dispatch");
        if (emergencyDispatchOrg == null) {
            emergencyDispatchOrg = new ResourceDispatchUnit("911 Dispatch", null); // Added admin placeholder
            bostonEmergency.addOrganization(emergencyDispatchOrg);
        }

        // Configure Logistics Enterprise with Faker-generated data
        String logisticsName = faker.company().name() + " Logistics & Supply";
        String logisticsDescription = "Handles transportation and supply chain for " + faker.address().city();
        String logisticsManager = faker.name().fullName();
        Enterprise bostonLogistics = bostonNetwork.getEnterpriseDirectory().createAndAddEnterprise(
            "LOGS" + faker.number().digits(3), logisticsName, Enterprise.EnterpriseType.Logistics, logisticsDescription, logisticsManager);
        ContactInfo logisticsContact = new ContactInfo(faker.address().fullAddress(), faker.phoneNumber().phoneNumber(), faker.internet().emailAddress());
        bostonLogistics.setContactInfo(logisticsContact);

        // Logistics Organizations
        InventoryManager inventoryManagerOrg = (InventoryManager) bostonLogistics.findOrganizationByName("Main Warehouse");
        if (inventoryManagerOrg == null) {
            inventoryManagerOrg = new InventoryManager("Main Warehouse");
            bostonLogistics.addOrganization(inventoryManagerOrg);
        }
        ResourceDispatchUnit deliveryUnitOrg = (ResourceDispatchUnit) bostonLogistics.findOrganizationByName("Transport");
        if (deliveryUnitOrg == null) {
            deliveryUnitOrg = new ResourceDispatchUnit("Transport", null); // Added admin placeholder
            bostonLogistics.addOrganization(deliveryUnitOrg);
        }
        
        // Configure Public Health Enterprise (Added)
        String publicHealthName = faker.company().name() + " Public Health Department";
        String publicHealthDescription = "Manages public health initiatives and donations in " + faker.address().city();
        String publicHealthManager = faker.name().fullName();
        Enterprise bostonPublicHealth = bostonNetwork.getEnterpriseDirectory().createAndAddEnterprise(
            "PH" + faker.number().digits(3), publicHealthName, Enterprise.EnterpriseType.PublicHealth, publicHealthDescription, publicHealthManager);
        ContactInfo publicHealthContact = new ContactInfo(faker.address().fullAddress(), faker.phoneNumber().phoneNumber(), faker.internet().emailAddress());
        bostonPublicHealth.setContactInfo(publicHealthContact);

        // Public Health Organizations (Added)
        DonationManagementUnit donationManagementOrg = (DonationManagementUnit) bostonPublicHealth.findOrganizationByName("Donation Management");
        if (donationManagementOrg == null) {
            // Need a placeholder employee for the admin of DonationManagementUnit
            Employee donationAdminPlaceholder = new Employee(faker.name().fullName(), faker.name().username(), faker.internet().password(), new Admin(), "Donation Admin", "Donation Management");
            donationAdminPlaceholder.setContactInfo(new ContactInfo(faker.address().fullAddress(), faker.phoneNumber().phoneNumber(), faker.internet().emailAddress()));
            donationManagementOrg = new DonationManagementUnit(donationAdminPlaceholder);
            bostonPublicHealth.addOrganization(donationManagementOrg);
        }
        OperationsSupportUnit operationsSupportOrg = (OperationsSupportUnit) bostonPublicHealth.findOrganizationByName("Operations Support");
        if (operationsSupportOrg == null) {
            // Need a placeholder employee for the admin of OperationsSupportUnit
            Employee opsAdminPlaceholder = new Employee(faker.name().fullName(), faker.name().username(), faker.internet().password(), new Admin(), "Operations Admin", "Operations Support");
            opsAdminPlaceholder.setContactInfo(new ContactInfo(faker.address().fullAddress(), faker.phoneNumber().phoneNumber(), faker.internet().emailAddress()));
            operationsSupportOrg = new OperationsSupportUnit("Operations Support", opsAdminPlaceholder, "2025");
            bostonPublicHealth.addOrganization(operationsSupportOrg);
        }


        // Create System Admin User with Faker-generated data
        // Create Network Admin User
        // Create System Admin User with Faker-generated data
        ContactInfo sysAdminContact = new ContactInfo(faker.address().fullAddress(), faker.phoneNumber().phoneNumber(), faker.internet().emailAddress());
        Employee sysAdminEmployee = new Employee(faker.name().fullName(), "sysadmin", "sysadmin", new Admin(), "System Administrator", "IT");
        sysAdminEmployee.setContactInfo(sysAdminContact);
        system.getUserAccountDirectory().createUserAccount(sysAdminEmployee.getUsername(), sysAdminEmployee.getPassword(), sysAdminEmployee, sysAdminEmployee.getRole());

        // --- 创建网络管理员 ---
        ContactInfo networkAdminContact = new ContactInfo(faker.address().fullAddress(), faker.phoneNumber().phoneNumber(), faker.internet().emailAddress());
        Employee networkAdminEmployee = new Employee(faker.name().fullName(), "netadmin", "netadmin", new Admin(), "Network Administrator", "Network Management");
        networkAdminEmployee.setContactInfo(networkAdminContact);
        UserAccount networkAdminAccount = bostonNetwork.getUserAccountDirectory().createUserAccount(networkAdminEmployee.getUsername(), networkAdminEmployee.getPassword(), networkAdminEmployee, networkAdminEmployee.getRole());
        bostonNetwork.setAdmin(networkAdminEmployee); // 设置网络的管理员

        // --- 创建企业管理员 (根据企业类型) ---

        // 医院企业管理员
        ContactInfo hospitalEnterpriseAdminContact = new ContactInfo(faker.address().fullAddress(), faker.phoneNumber().phoneNumber(), faker.internet().emailAddress());
        Employee hospitalEnterpriseAdminEmployee = new Employee(faker.name().fullName(), "hospadmin", "hospadmin", new Admin(), "Hospital Enterprise Administrator", "Hospital Administration");
        hospitalEnterpriseAdminEmployee.setContactInfo(hospitalEnterpriseAdminContact);
        UserAccount hospitalEnterpriseAdminAccount = bostonHospital.getUserAccountDirectory().createUserAccount(hospitalEnterpriseAdminEmployee.getUsername(), hospitalEnterpriseAdminEmployee.getPassword(), hospitalEnterpriseAdminEmployee, hospitalEnterpriseAdminEmployee.getRole());
        bostonHospital.setAdmin(hospitalEnterpriseAdminEmployee); // 设置医院企业的管理员

        // 应急企业管理员
        ContactInfo emergencyEnterpriseAdminContact = new ContactInfo(faker.address().fullAddress(), faker.phoneNumber().phoneNumber(), faker.internet().emailAddress());
        Employee emergencyEnterpriseAdminEmployee = new Employee(faker.name().fullName(), "emergadmin", "emergadmin", new Admin(), "Emergency Enterprise Administrator", "Emergency Services Administration");
        emergencyEnterpriseAdminEmployee.setContactInfo(emergencyEnterpriseAdminContact);
        UserAccount emergencyEnterpriseAdminAccount = bostonEmergency.getUserAccountDirectory().createUserAccount(emergencyEnterpriseAdminEmployee.getUsername(), emergencyEnterpriseAdminEmployee.getPassword(), emergencyEnterpriseAdminEmployee, emergencyEnterpriseAdminEmployee.getRole());
        bostonEmergency.setAdmin(emergencyEnterpriseAdminEmployee); // 设置应急企业的管理员

        // 物流企业管理员
        ContactInfo logisticsEnterpriseAdminContact = new ContactInfo(faker.address().fullAddress(), faker.phoneNumber().phoneNumber(), faker.internet().emailAddress());
        Employee logisticsEnterpriseAdminEmployee = new Employee(faker.name().fullName(), "logadmin", "logadmin", new Admin(), "Logistics Enterprise Administrator", "Logistics Administration");
        logisticsEnterpriseAdminEmployee.setContactInfo(logisticsEnterpriseAdminContact);
        UserAccount logisticsEnterpriseAdminAccount = bostonLogistics.getUserAccountDirectory().createUserAccount(logisticsEnterpriseAdminEmployee.getUsername(), logisticsEnterpriseAdminEmployee.getPassword(), logisticsEnterpriseAdminEmployee, logisticsEnterpriseAdminEmployee.getRole());
        bostonLogistics.setAdmin(logisticsEnterpriseAdminEmployee); // 设置物流企业的管理员
        
        // 公共卫生企业管理员
        ContactInfo publicHealthEnterpriseAdminContact = new ContactInfo(faker.address().fullAddress(), faker.phoneNumber().phoneNumber(), faker.internet().emailAddress());
        Employee publicHealthEnterpriseAdminEmployee = new Employee(faker.name().fullName(), "phadmin", "phadmin", new Admin(), "Public Health Enterprise Administrator", "Public Health Administration");
        publicHealthEnterpriseAdminEmployee.setContactInfo(publicHealthEnterpriseAdminContact);
        UserAccount publicHealthEnterpriseAdminAccount = bostonPublicHealth.getUserAccountDirectory().createUserAccount(publicHealthEnterpriseAdminEmployee.getUsername(), publicHealthEnterpriseAdminEmployee.getPassword(), publicHealthEnterpriseAdminEmployee, publicHealthEnterpriseAdminEmployee.getRole());
        bostonPublicHealth.setAdmin(publicHealthEnterpriseAdminEmployee); // 设置公共卫生企业的管理员


        // --- 创建组织管理员 (根据组织类型) ---

        // 临床服务单位管理员
        ContactInfo clinicalOrgAdminContact = new ContactInfo(faker.address().fullAddress(), faker.phoneNumber().phoneNumber(), faker.internet().emailAddress());
        Employee clinicalOrgAdminEmployee = new Employee(faker.name().fullName(), "clinicaladmin", "clinicaladmin", new Admin(), "Clinical Services Administrator", "Clinical Services");
        clinicalOrgAdminEmployee.setContactInfo(clinicalOrgAdminContact);
        UserAccount clinicalOrgAdminAccount = clinicalServicesOrg.getUserAccountDirectory().createUserAccount(clinicalOrgAdminEmployee.getUsername(), clinicalOrgAdminEmployee.getPassword(), clinicalOrgAdminEmployee, clinicalOrgAdminEmployee.getRole());
        clinicalServicesOrg.setAdmin(clinicalOrgAdminEmployee); // 设置临床服务组织的管理员

        // 捐赠管理单位管理员
        ContactInfo donationOrgAdminContact = new ContactInfo(faker.address().fullAddress(), faker.phoneNumber().phoneNumber(), faker.internet().emailAddress());
        Employee donationOrgAdminEmployee = new Employee(faker.name().fullName(), "donateadmin", "donateadmin", new Admin(), "Donation Management Administrator", "Donation Management");
        donationOrgAdminEmployee.setContactInfo(donationOrgAdminContact);
        UserAccount donationOrgAdminAccount = donationManagementOrg.getUserAccountDirectory().createUserAccount(donationOrgAdminEmployee.getUsername(), donationOrgAdminEmployee.getPassword(), donationOrgAdminEmployee, donationOrgAdminEmployee.getRole());
        donationManagementOrg.setAdmin(donationOrgAdminEmployee); // 设置捐赠管理组织的管理员

        // 应急响应单位管理员
        ContactInfo emergencyResponseOrgAdminContact = new ContactInfo(faker.address().fullAddress(), faker.phoneNumber().phoneNumber(), faker.internet().emailAddress());
        Employee emergencyResponseOrgAdminEmployee = new Employee(faker.name().fullName(), "erespadmin", "erespadmin", new Admin(), "Emergency Response Administrator", "Field Operations");
        emergencyResponseOrgAdminEmployee.setContactInfo(emergencyResponseOrgAdminContact);
        UserAccount emergencyResponseOrgAdminAccount = emergencyResponseOrg.getUserAccountDirectory().createUserAccount(emergencyResponseOrgAdminEmployee.getUsername(), emergencyResponseOrgAdminEmployee.getPassword(), emergencyResponseOrgAdminEmployee, emergencyResponseOrgAdminEmployee.getRole());
        emergencyResponseOrg.setAdmin(emergencyResponseOrgAdminEmployee); // 设置应急响应组织的管理员

        // 仓库管理组织管理员
        ContactInfo inventoryOrgAdminContact = new ContactInfo(faker.address().fullAddress(), faker.phoneNumber().phoneNumber(), faker.internet().emailAddress());
        Employee inventoryOrgAdminEmployee = new Employee(faker.name().fullName(), "invtadmin", "invtadmin", new Admin(), "Inventory Manager Administrator", "Main Warehouse");
        inventoryOrgAdminEmployee.setContactInfo(inventoryOrgAdminContact);
        UserAccount inventoryOrgAdminAccount = inventoryManagerOrg.getUserAccountDirectory().createUserAccount(inventoryOrgAdminEmployee.getUsername(), inventoryOrgAdminEmployee.getPassword(), inventoryOrgAdminEmployee, inventoryOrgAdminEmployee.getRole());
        inventoryManagerOrg.setAdmin(inventoryOrgAdminEmployee); // 设置仓库管理组织的管理员

        // 后台支持组织管理员
        ContactInfo opsSupportOrgAdminContact = new ContactInfo(faker.address().fullAddress(), faker.phoneNumber().phoneNumber(), faker.internet().emailAddress());
        Employee opsSupportOrgAdminEmployee = new Employee(faker.name().fullName(), "opsadmin", "opsadmin", new Admin(), "Operations Support Administrator", "Operations Support");
        opsSupportOrgAdminEmployee.setContactInfo(opsSupportOrgAdminContact);
        UserAccount opsSupportOrgAdminAccount = operationsSupportOrg.getUserAccountDirectory().createUserAccount(opsSupportOrgAdminEmployee.getUsername(), opsSupportOrgAdminEmployee.getPassword(), opsSupportOrgAdminEmployee, opsSupportOrgAdminEmployee.getRole());
        operationsSupportOrg.setAdmin(opsSupportOrgAdminEmployee); // 设置后台支持组织的管理员

        // 调度单位管理员
        ContactInfo resourceDispatchOrgAdminContact = new ContactInfo(faker.address().fullAddress(), faker.phoneNumber().phoneNumber(), faker.internet().emailAddress());
        Employee resourceDispatchOrgAdminEmployee = new Employee(faker.name().fullName(), "dispatchadmin", "dispatchadmin", new Admin(), "Resource Dispatch Administrator", "911 Dispatch");
        resourceDispatchOrgAdminEmployee.setContactInfo(resourceDispatchOrgAdminContact);
        UserAccount resourceDispatchOrgAdminAccount = emergencyDispatchOrg.getUserAccountDirectory().createUserAccount(resourceDispatchOrgAdminEmployee.getUsername(), resourceDispatchOrgAdminEmployee.getPassword(), resourceDispatchOrgAdminEmployee, resourceDispatchOrgAdminEmployee.getRole());
        emergencyDispatchOrg.setAdmin(resourceDispatchOrgAdminEmployee); // 设置调度单位组织的管理员

        //users
        // Create Doctor User and add to Clinical Services Org with Faker-generated data
        ContactInfo doctorContact = new ContactInfo(faker.address().fullAddress(), faker.phoneNumber().phoneNumber(), faker.internet().emailAddress());
        Employee doctorEmployee = new Employee(faker.name().fullName(), "doctor", "doctor", new Doctor(), "Physician", "Medical");
        doctorEmployee.setContactInfo(doctorContact);
        system.getUserAccountDirectory().createUserAccount(doctorEmployee.getUsername(), doctorEmployee.getPassword(), doctorEmployee, doctorEmployee.getRole());
        clinicalServicesOrg.addEmployee(doctorEmployee);

        // Create Nurse User and add to Clinical Services Org with Faker-generated data
        ContactInfo nurseContact = new ContactInfo(faker.address().fullAddress(), faker.phoneNumber().phoneNumber(), faker.internet().emailAddress());
        Employee nurseEmployee = new Employee(faker.name().fullName(), "nurse", "nurse", new Nurse(), "Registered Nurse", "Nursing");
        nurseEmployee.setContactInfo(nurseContact);
        system.getUserAccountDirectory().createUserAccount(nurseEmployee.getUsername(), nurseEmployee.getPassword(), nurseEmployee, nurseEmployee.getRole());
        clinicalServicesOrg.addEmployee(nurseEmployee);

        // Create Emergency Dispatcher User and add to Emergency Dispatch Org with Faker-generated data
        ContactInfo dispatcherContact = new ContactInfo(faker.address().fullAddress(), faker.phoneNumber().phoneNumber(), faker.internet().emailAddress());
        Employee dispatcherEmployee = new Employee(faker.name().fullName(), "dispatcher", "dispatcher", new EmergencyDispatcher(), "Dispatcher", "Dispatch");
        dispatcherEmployee.setContactInfo(dispatcherContact);
        UserAccount dispatcherAccount = system.getUserAccountDirectory().createUserAccount(dispatcherEmployee.getUsername(), dispatcherEmployee.getPassword(), dispatcherEmployee, dispatcherEmployee.getRole());
        emergencyDispatchOrg.addEmployee(dispatcherEmployee);

        // Create Emergency Responder User and add to Emergency Response Org with Faker-generated data
        ContactInfo responderContact = new ContactInfo(faker.address().fullAddress(), faker.phoneNumber().phoneNumber(), faker.internet().emailAddress());
        Employee responderEmployee = new Employee(faker.name().fullName(), "responder", "responder", new EmergencyResponder(), "Responder", "Response");
        responderEmployee.setContactInfo(responderContact);
        UserAccount responderAccount = system.getUserAccountDirectory().createUserAccount(responderEmployee.getUsername(), responderEmployee.getPassword(), responderEmployee, responderEmployee.getRole());
        emergencyResponseOrg.addEmployee(responderEmployee);

        // Create Logistics Manager User and add to Inventory Manager Org with Faker-generated data
        ContactInfo logisticsManagerContact = new ContactInfo(faker.address().fullAddress(), faker.phoneNumber().phoneNumber(), faker.internet().emailAddress());
        Employee logisticsManagerEmployee = new Employee(faker.name().fullName(), "logisticsmanager", "logisticsmanager", new LogisticsManager(), "Manager", "Logistics");
        logisticsManagerEmployee.setContactInfo(logisticsManagerContact);
        UserAccount logisticsManagerAccount = system.getUserAccountDirectory().createUserAccount(logisticsManagerEmployee.getUsername(), logisticsManagerEmployee.getPassword(), logisticsManagerEmployee, logisticsManagerEmployee.getRole());
        inventoryManagerOrg.addEmployee(logisticsManagerEmployee);

        // Create Delivery Staff User and add to Delivery Unit Org with Faker-generated data
        ContactInfo deliveryStaffContact = new ContactInfo(faker.address().fullAddress(), faker.phoneNumber().phoneNumber(), faker.internet().emailAddress());
        Employee deliveryStaffEmployee = new Employee(faker.name().fullName(), "deliverystaff", "deliverystaff", new DeliveryStaff(), "Driver", "Delivery");
        deliveryStaffEmployee.setContactInfo(deliveryStaffContact);
        UserAccount deliveryStaffAccount = system.getUserAccountDirectory().createUserAccount(deliveryStaffEmployee.getUsername(), deliveryStaffEmployee.getPassword(), deliveryStaffEmployee, deliveryStaffEmployee.getRole());
        deliveryUnitOrg.addEmployee(deliveryStaffEmployee);
        
        // --- 虚拟生成更多数据 ---

        // 1. 患者 (Patients) 和医疗记录 (Medical Records)
        generatePatientsAndAppointments(clinicalServicesOrg, doctorEmployee, nurseEmployee, faker);

        // 2. 医疗用品 (Supply Items) 和设备 (Equipments)
        generateInventory(inventoryManagerOrg, faker);

        // 3. 捐赠 (Donations) 和捐赠者 (Donors)
        generateDonations(donationManagementOrg, faker);

        // 4. 车辆 (Vehicles)
        generateVehicles(deliveryUnitOrg, faker); // Associate with ResourceDispatchUnit for logistics
        generateVehicles(emergencyResponseOrg, faker); // Associate with EmergencyResponseUnit

        // 5. 工作请求 (Work Requests)
        generateWorkRequests(clinicalServicesOrg, emergencyResponseOrg, inventoryManagerOrg, deliveryUnitOrg, operationsSupportOrg, donationManagementOrg, faker, sysAdminEmployee, doctorEmployee, dispatcherEmployee, responderEmployee, logisticsManagerEmployee, deliveryStaffEmployee);

        // 6. 报告 (Reports) 和任务统计 (Mission Stats)
        generateReportsAndMissionStats(operationsSupportOrg, emergencyResponseOrg, faker, sysAdminEmployee);

        return system;
    }

    // --- 辅助方法开始 ---

    private static void generatePatientsAndAppointments(ClinicalServicesUnit clinicalServicesOrg, Employee doctor, Employee nurse, Faker faker) {
    Random rand = new Random();

    // 模拟诊断和医疗操作（手动设定）
    String[] diagnoses = {"Flu", "Hypertension", "Diabetes", "Allergy", "Anemia"};
    String[] procedures = {"Blood Test", "MRI", "Vaccination", "Physical Therapy", "Surgery"};

    for (int i = 0; i < 10; i++) {
        // ➤ 生成患者
        String patientName = faker.name().fullName();
        String gender = faker.options().option("Male", "Female", "Other");
        int age = faker.number().numberBetween(1, 90);
        String dob = faker.date().birthday().toString();

        Patient patient = new Patient(patientName, gender, age, dob);
        ContactInfo contact = new ContactInfo(
            faker.address().fullAddress(),
            faker.phoneNumber().phoneNumber(),
            faker.internet().emailAddress()
        );
        patient.setContactInfo(contact);
        clinicalServicesOrg.addPatient(patient);

        // ➤ 生成病历条目（随机1~2条）
        int numMedicalEntries = faker.number().numberBetween(1, 3);
        for (int j = 0; j < numMedicalEntries; j++) {
            String diagnosis = diagnoses[rand.nextInt(diagnoses.length)];
            String procedure = procedures[rand.nextInt(procedures.length)];
            patient.getMedicalRecord().addMedicalEntry(diagnosis, procedure);
        }

        // ➤ 为该患者创建一个预约（由 nurse 为 doctor 安排）
        if (doctor != null) {
            Date appointmentDate = faker.date().future(30, TimeUnit.DAYS);
            Date appointmentTimeRaw = faker.date().future(60, TimeUnit.MINUTES, new Date());
            String appointmentTime = new SimpleDateFormat("HH:mm").format(appointmentTimeRaw);

            Appointment appointment = new Appointment(patient, doctor, appointmentDate, appointmentTime);
            appointment.setAppointmentType(faker.options().option("Regular Checkup", "Follow-up", "Emergency"));
            appointment.setLocation(faker.address().streetAddress());
            appointment.setSender(nurse);    // 安排者
            appointment.setReceiver(doctor); // 接诊者

            clinicalServicesOrg.scheduleAppointment(appointment);
        }
    }
}

    private static void generateInventory(InventoryManager inventoryManagerOrg, Faker faker) {
        // Generate Supply Items
        for (int i = 0; i < 15; i++) {
            String supplyName = faker.commerce().productName();
            String description = faker.lorem().sentence();
            String type = faker.options().option("Medical Supplies", "Office Supplies", "Cleaning Supplies", "Food");
            int quantity = faker.number().numberBetween(10, 500);
            double unitPrice = faker.number().randomDouble(2, 1, 100);
            Date expirationDate = faker.date().future(365, TimeUnit.DAYS);
            String manufacturer = faker.company().name();
            String vendor = faker.company().name();

            SupplyItem supplyItem = new SupplyItem(supplyName, description, type, quantity, unitPrice);
            supplyItem.setExpirationDate(expirationDate);
            supplyItem.setManufacturer(manufacturer);
            supplyItem.setVendor(vendor);
            inventoryManagerOrg.addItem(supplyItem);

            // Set some reorder thresholds
            if (i % 3 == 0) {
                inventoryManagerOrg.setReorderThreshold(supplyName, quantity * 0.2);
            }
        }

        // Generate Equipments
        for (int i = 0; i < 7; i++) {
            String equipmentName = faker.options().option("X-Ray Machine", "Ultrasound Scanner", "Ventilator", "Defibrillator", "Surgical Robot");
            String description = faker.lorem().sentence();
            String type = "Medical Equipment";
            int quantity = faker.number().numberBetween(1, 5);
            double unitPrice = faker.number().randomDouble(2, 1000, 100000);
            String model = faker.bothify("###-???-####");
            Date purchaseDate = faker.date().past(365 * 5, TimeUnit.DAYS);
            String department = faker.options().option("Radiology", "Cardiology", "Emergency", "Surgery");

            Equipments equipment = new Equipments(equipmentName, description, type, quantity, unitPrice, model, purchaseDate, department);
            equipment.setStatus(faker.options().option("Available", "In Use", "Under Maintenance"));
            if (equipment.getStatus().equals("Under Maintenance")) {
                equipment.scheduleMaintenance(faker.date().future(30, TimeUnit.DAYS));
            }
            inventoryManagerOrg.addItem(equipment); // Equipments extends SupplyItem
        }
    }

    private static void generateDonations(DonationManagementUnit donationManagementOrg, Faker faker) {
        // Generate Donors
        List<Donor> donors = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            String donorName = faker.name().fullName();
            String donorId = "DONOR" + faker.number().digits(4);
            ContactInfo donorContact = new ContactInfo(faker.address().fullAddress(), faker.phoneNumber().phoneNumber(), faker.internet().emailAddress());
            Donor donor = new Donor(donorId, donorName, donorContact);
            donationManagementOrg.addDonor(donor);
            donors.add(donor);
        }

        // Generate Donations
        for (int i = 0; i < 20; i++) {
            Donor randomDonor = donors.get(faker.number().numberBetween(0, donors.size() - 1));
            String purpose = faker.options().option("Medical Research", "Disaster Relief", "Community Support", "General Fund");

            if (faker.bool().bool()) { // Monetary donation
                double amount = faker.number().randomDouble(2, 50, 5000);
                Donation donation = donationManagementOrg.recordMonetaryDonation(randomDonor, amount, purpose);
                donation.setNotes(faker.lorem().sentence());
            } else { // Item donation
                List<DonatedItem> donatedItems = new ArrayList<>();
                int numItems = faker.number().numberBetween(1, 3);
                for (int j = 0; j < numItems; j++) {
                    String itemName = faker.commerce().productName();
                    int quantity = faker.number().numberBetween(1, 10);
                    double unitPrice = faker.number().randomDouble(2, 5, 200);
                    donatedItems.add(new DonatedItem(itemName, quantity, unitPrice));
                }
                Donation donation = donationManagementOrg.recordItemDonation(randomDonor, donatedItems, purpose);
                donation.setNotes(faker.lorem().sentence());
            }

            // Simulate processing some donations
            if (faker.bool().bool()) {
                donationManagementOrg.getDonationRecords().get(donationManagementOrg.getDonationRecords().size() - 1).processDonation();
            }
        }
    }

    private static void generateVehicles(ResourceDispatchUnit resourceDispatchUnit, Faker faker) {
        for (int i = 0; i < 10; i++) {
            String vehicleType = faker.options().option("Ambulance", "Cargo Truck", "Van", "Emergency Car");
            String vehicleId = "VEH" + faker.number().digits(4);
            String status = faker.options().option("Available", "In Use", "Under Maintenance");
            double fuelLevel = faker.number().randomDouble(2, 20, 100);
            Vehicle vehicle = new Vehicle(vehicleType, vehicleId, status, fuelLevel);
            resourceDispatchUnit.addVehicle(vehicle);
        }
    }

    private static void generateVehicles(EmergencyResponseUnit emergencyResponseUnit, Faker faker) {
        for (int i = 0; i < 5; i++) {
            String vehicleId = "EM_VEH" + faker.number().digits(3);
            boolean isAvailable = faker.bool().bool();
            emergencyResponseUnit.addEmergencyVehicle(vehicleId, isAvailable);
        }
    }


    private static void generateWorkRequests(ClinicalServicesUnit clinicalServicesOrg, EmergencyResponseUnit emergencyResponseOrg,
                                             InventoryManager inventoryManagerOrg, ResourceDispatchUnit deliveryUnitOrg,
                                             OperationsSupportUnit operationsSupportOrg, DonationManagementUnit donationManagementOrg,
                                             Faker faker, Employee sysAdmin, Employee doctor, Employee dispatcher,
                                             Employee responder, Employee logisticsManager, Employee deliveryStaff) {

        // SupplyWorkRequest
        for (int i = 0; i < 5; i++) {
            SupplyItem requestedSupply = inventoryManagerOrg.getInventoryList().get(faker.number().numberBetween(0, inventoryManagerOrg.getInventoryList().size() - 1));
            int quantity = faker.number().numberBetween(10, 100);
            SupplyWorkRequest supplyRequest = new SupplyWorkRequest(requestedSupply, quantity, faker.options().option("Emergency", "Clinical", "Logistics"));
            supplyRequest.setUrgency(faker.options().option("High", "Medium", "Low"));
            supplyRequest.setSender(sysAdmin); // Example sender
            supplyRequest.setReceiver(logisticsManager); // Example receiver
            supplyRequest.setStatus(faker.options().option("Requested", "Approved", "Fulfilled"));
            inventoryManagerOrg.addWorkRequest(supplyRequest); // Add to InventoryManager's queue
        }

        // DonationRequest
        for (int i = 0; i < 3; i++) {
            List<Donation> donationsForRequest = new ArrayList<>();
            donationsForRequest.add(donationManagementOrg.getDonationRecords().get(faker.number().numberBetween(0, donationManagementOrg.getDonationRecords().size() - 1)));
            String requestType = faker.options().option("Process", "Distribute", "Acknowledge");
            DonationRequest donationRequest = new DonationRequest(requestType, donationsForRequest);
            donationRequest.setSender(sysAdmin); // Example sender
            donationRequest.setReceiver(donationManagementOrg.getAdmin()); // Donation Coordinator
            donationRequest.setStatus(faker.options().option("Pending", "Processed", "Distributed"));
            donationManagementOrg.addWorkRequest(donationRequest);
        }

        // PayrollRequest
        if (operationsSupportOrg.getStaffList().isEmpty()) { // Ensure staff list is not empty
            operationsSupportOrg.addStaff(logisticsManager); // Add some staff for payroll
            operationsSupportOrg.addStaff(doctor);
        }
        for (int i = 0; i < 2; i++) {
            Employee emp = operationsSupportOrg.getStaffList().get(faker.number().numberBetween(0, operationsSupportOrg.getStaffList().size() - 1));
            Date payStart = faker.date().past(30, TimeUnit.DAYS);
            Date payEnd = new Date();
            Date payDate = faker.date().future(7, TimeUnit.DAYS);
            PayrollRequest payrollRequest = new PayrollRequest(emp, payStart, payEnd, payDate);
            payrollRequest.setHoursWorked(faker.number().randomDouble(2, 120, 160));
            payrollRequest.setOvertimeHours(faker.number().randomDouble(2, 0, 20));
            payrollRequest.setSender(sysAdmin);
            payrollRequest.setReceiver(operationsSupportOrg.getAdmin());
            payrollRequest.setStatus(faker.options().option("Submitted", "Processed"));
            operationsSupportOrg.addWorkRequest(payrollRequest);
        }

        // EmergencyWorkRequest
        for (int i = 0; i < 4; i++) {
            String emergencyType = faker.options().option("Medical Emergency", "Fire", "Accident", "Natural Disaster");
            String location = faker.address().fullAddress();
            EmergencyWorkRequest emergencyRequest = new EmergencyWorkRequest(emergencyType, location, faker.number().numberBetween(1, 5), faker.name().fullName());
            emergencyRequest.setSender(sysAdmin);
            emergencyRequest.setReceiver(dispatcher);
            emergencyRequest.setStatus(faker.options().option("Reported", "Dispatched", "Completed"));
            emergencyResponseOrg.addWorkRequest(emergencyRequest);
            if (emergencyRequest.getStatus().equals("Dispatched") || emergencyRequest.getStatus().equals("Completed")) {
                 emergencyRequest.setAssignedVehicle(faker.options().option("AMB" + faker.number().digits(2), "TRUCK" + faker.number().digits(2)));
            }
        }

        // ICURequest
        for (int i = 0; i < 3; i++) {
            Patient patient = clinicalServicesOrg.getPatientList().get(faker.number().numberBetween(0, clinicalServicesOrg.getPatientList().size() - 1));
            String icuReason = faker.lorem().sentence();
            ICURequest icuRequest = new ICURequest(patient, icuReason);
            icuRequest.setSender(doctor);
            icuRequest.setReceiver(clinicalServicesOrg.getAdmin()); // Or specific ICU bed manager
            icuRequest.setStatus(faker.options().option("Requested", "Bed Assigned", "Completed"));
            clinicalServicesOrg.addWorkRequest(icuRequest);
            if (icuRequest.getStatus().equals("Bed Assigned") || icuRequest.getStatus().equals("Completed")) {
                icuRequest.setBedAssignedDate(faker.date().past(7, TimeUnit.DAYS));
                icuRequest.setBedId("ICU" + faker.number().digits(3));
                icuRequest.setAttendingPhysician(doctor.getName());
            }
        }
        
        // DeliveryAssignmentRequest
        for (int i = 0; i < 3; i++) {
            Model.Supplies.Delivery delivery = new Model.Supplies.Delivery(faker.address().fullAddress(), faker.date().future(7, TimeUnit.DAYS));
            delivery.addItem(new SupplyItem(faker.commerce().productName(), "Description", "Type", faker.number().numberBetween(1, 50), faker.number().randomDouble(2, 5, 100)));
            DeliveryAssignmentRequest deliveryRequest = new DeliveryAssignmentRequest(delivery);
            deliveryRequest.setSender(logisticsManager);
            deliveryRequest.setReceiver(deliveryStaff);
            deliveryRequest.setStatus(faker.options().option("Pending", "Assigned", "Completed"));
            deliveryUnitOrg.addWorkRequest(deliveryRequest);
        }
        
        // ResourceAnalysisRequest
        for (int i = 0; i < 2; i++) {
            String analysisType = faker.options().option("Staffing", "Supply Usage", "Budget");
            Date periodStart = faker.date().past(90, TimeUnit.DAYS);
            Date periodEnd = new Date();
            String department = faker.options().option("Clinical", "Logistics", "Emergency");
            ResourceAnalysisRequest analysisRequest = new ResourceAnalysisRequest(analysisType, periodStart, periodEnd, department);
            analysisRequest.setPriority(faker.options().option("High", "Medium", "Low"));
            analysisRequest.setSender(sysAdmin);
            analysisRequest.setReceiver(operationsSupportOrg.getAdmin());
            analysisRequest.setStatus(faker.options().option("Requested", "In Progress", "Completed"));
            operationsSupportOrg.addWorkRequest(analysisRequest);
        }
        
        // CostReport
        for (int i = 0; i < 2; i++) {
            String reportTitle = faker.lorem().words(3).toString() + " Cost Report";
            Date startDate = faker.date().past(180, TimeUnit.DAYS);
            Date endDate = new Date();
            String department = faker.options().option("Clinical", "Logistics", "Administration");
            String reportType = faker.options().option("Monthly", "Quarterly", "Annual");
            CostReport costReport = new CostReport(reportTitle, startDate, endDate, department, reportType);
            costReport.addCost("Salaries", faker.number().randomDouble(2, 10000, 50000));
            costReport.addCost("Supplies", faker.number().randomDouble(2, 1000, 10000));
            costReport.setSender(sysAdmin);
            costReport.setReceiver(operationsSupportOrg.getAdmin());
            costReport.setStatus(faker.options().option("Pending", "Generated"));
            operationsSupportOrg.addWorkRequest(costReport);
        }
    }

    private static void generateReportsAndMissionStats(OperationsSupportUnit operationsSupportOrg, EmergencyResponseUnit emergencyResponseOrg, Faker faker, Employee sysAdmin) {
        // Reports (internal to OperationsSupportUnit)
        for (int i = 0; i < 3; i++) {
            String reportTitle = faker.lorem().words(4).toString() + " Performance Report";
            String reportType = faker.options().option("Staffing", "Financial", "Operational");
            Date startDate = faker.date().past(90, TimeUnit.DAYS);
            Date endDate = new Date();
            Map<String, Object> metrics = new HashMap<>();
            metrics.put("EfficiencyScore",faker.number().randomDouble(2, 50, 100) / 100.0);
            metrics.put("ComplianceRate",faker.number().randomDouble(2, 80, 99) / 100.0);
            operationsSupportOrg.generatePerformanceReport(reportTitle, reportType, startDate, endDate, metrics);
        }

        // MissionStats
        for (int i = 0; i < 3; i++) {
            String missionName = faker.lorem().words(2).toString() + " Mission";
            Date missionStart = faker.date().past(30, TimeUnit.DAYS);
            Date missionEnd = faker.date().between(missionStart, faker.date().future(7, TimeUnit.DAYS, missionStart));
            String missionType = faker.options().option("Emergency Response", "Logistics Operation", "Public Health Campaign");
            String location = faker.address().cityName();
            MissionStats missionStats = new MissionStats(missionName, missionStart, missionEnd, missionType, location);
            missionStats.addStat("ResourcesDeployed", faker.number().numberBetween(5, 20));
            missionStats.addStat("PersonnelInvolved", faker.number().numberBetween(10, 50));
            missionStats.setSender(sysAdmin); // Example sender
            missionStats.setReceiver(operationsSupportOrg.getAdmin()); // Operations admin might review
            missionStats.setStatus("Finalized");
            operationsSupportOrg.addWorkRequest(missionStats); // Add to ops unit work queue
            emergencyResponseOrg.addWorkRequest(missionStats); // Also add to emergency unit work queue if relevant
        }
    }
    
}