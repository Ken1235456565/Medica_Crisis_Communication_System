package Model;

// Model/ConfigureASystem.java
import Model.Enterprise.Enterprise;
import Model.Network.Network;
import Model.Network.NetworkDirectory;
import Model.Organization.ClinicalServicesUnit;
import Model.Organization.EmergencyResponseUnit;
import Model.Organization.ResourceDispatchUnit;
import Model.Organization.SupplyChainManagementUnit;
import Model.Organization.AdministrationUnit;
import Model.Organization.DonationManagementUnit;
import Model.Organization.OperationsSupportUnit;

import Model.Personnel.Admin;
import Model.Personnel.DeliveryStaff;
import Model.Personnel.Doctor;
import Model.Personnel.EmergencyDispatcher;
import Model.Personnel.EmergencyResponder;
import Model.Employee.Employee;
import Model.Enterprise.LogisticsEnterprise;
import Model.Personnel.LogisticsManager;
import Model.Personnel.Nurse;
import Model.Personnel.Donor;
import Model.Personnel.PayrollStaff;
import Model.Personnel.PublicDataManager;
import Model.Personnel.DonationCoordinator;
import Model.Personnel.SupplychainManager;
import Model.Personnel.PublicDataManager;


import Model.Person.ContactInfo;
import Model.Patient.Patient;
import Model.PublicData.HealthStatistics;
import Model.PublicData.RegionData;
import Model.PublicData.SymptomData;
import Model.Role.AdminRole;
import Model.Role.DeliveryStaffRole;
import Model.Role.DoctorRole;
import Model.Role.EmergencyDispatcherRole;
import Model.Role.EmergencyResponderRole;
import Model.Role.LogisticsManagerRole;
import Model.Role.NurseRole;
import Model.Role.PayrollStaffRole;
import Model.Role.ResourceAnalystRole;
import Model.Role.DonationCoordinatorRole;
import Model.Role.SupplychainManagerRole;
import Model.Role.VisitorRole;

import Model.Supplies.SupplyItem;
import Model.Supplies.Equipments;
import Model.Supplies.Vehicle;
import Model.Supplies.Donation;
import Model.Supplies.Donation.DonatedItem;

import Model.WorkQueue.Appointment;
import Model.WorkQueue.EmergencyWorkRequest;
import Model.WorkQueue.SupplyWorkRequest;
import Model.WorkQueue.DonationRequest;
import Model.WorkQueue.DeliveryAssignmentRequest;
import Model.WorkQueue.PayrollRequest;
import Model.WorkQueue.ResourceAnalysisRequest;
import Model.WorkQueue.CostReport;
import Model.WorkQueue.MissionStats;
import Model.WorkQueue.ICURequest;

import Model.User.UserAccount;

import com.github.javafaker.Faker;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


public class ConfigureASystem {

    public static EcoSystem configure() {
        Faker faker = new Faker();

        EcoSystem system = EcoSystem.getInstance();

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
            clinicalServicesOrg = new ClinicalServicesUnit("Clinical Services", null, true); // Admin will be added later
            bostonHospital.addOrganization(clinicalServicesOrg);
        }

        AdministrationUnit hospitalAdminOrg = (AdministrationUnit) bostonHospital.findOrganizationByName("Hospital Admin");
        if (hospitalAdminOrg == null) {
            hospitalAdminOrg = new AdministrationUnit();
            hospitalAdminOrg.setOrganizationName("Hospital Admin");
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
            emergencyResponseOrg = new EmergencyResponseUnit("Field Operations", null);
            bostonEmergency.addOrganization(emergencyResponseOrg);
        }
        ResourceDispatchUnit emergencyDispatchOrg = (ResourceDispatchUnit) bostonEmergency.findOrganizationByName("911 Dispatch");
        if (emergencyDispatchOrg == null) {
            emergencyDispatchOrg = new ResourceDispatchUnit("911 Dispatch", null);
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
        SupplyChainManagementUnit inventoryManagerOrg = (SupplyChainManagementUnit) bostonLogistics.findOrganizationByName("Main Warehouse");
        if (inventoryManagerOrg == null) {
            inventoryManagerOrg = new SupplyChainManagementUnit("Main Warehouse");
            bostonLogistics.addOrganization(inventoryManagerOrg);
        }
        ResourceDispatchUnit deliveryUnitOrg = (ResourceDispatchUnit) bostonLogistics.findOrganizationByName("Transport");
        if (deliveryUnitOrg == null) {
            deliveryUnitOrg = new ResourceDispatchUnit("Transport", null);
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
            donationManagementOrg = new DonationManagementUnit();
            bostonPublicHealth.addOrganization(donationManagementOrg);
        }
        OperationsSupportUnit operationsSupportOrg = (OperationsSupportUnit) bostonPublicHealth.findOrganizationByName("Operations Support");
        if (operationsSupportOrg == null) {
            operationsSupportOrg = new OperationsSupportUnit("Operations Support",null, "2025");
            bostonPublicHealth.addOrganization(operationsSupportOrg);
        }


        // Create System Admin User with Faker-generated data
        ContactInfo sysAdminContact = new ContactInfo(faker.address().fullAddress(), faker.phoneNumber().phoneNumber(), faker.internet().emailAddress());
        Employee sysAdminEmployee = new Employee("EMP" + faker.number().digits(4), faker.name().fullName(), faker.options().option("Male", "Female"), faker.number().numberBetween(25, 60), new SimpleDateFormat("yyyy-MM-dd").format(faker.date().birthday()), "System Administrator", "IT", sysAdminContact);
        UserAccount sysAdminAccount = system.getUserAccountDirectory().createUserAccount("sysadmin", "sysadmin", sysAdminEmployee, new AdminRole());
        sysAdminEmployee.setUserAccount(sysAdminAccount);


        // Create Doctor User and add to Clinical Services Org with Faker-generated data
        ContactInfo doctorContact = new ContactInfo(faker.address().fullAddress(), faker.phoneNumber().phoneNumber(), faker.internet().emailAddress());
        Employee doctorEmployee = new Employee("EMP" + faker.number().digits(4), faker.name().fullName(), faker.options().option("Male", "Female"), faker.number().numberBetween(30, 65), new SimpleDateFormat("yyyy-MM-dd").format(faker.date().birthday()), "Physician", "Medical", doctorContact);
        UserAccount doctorAccount = clinicalServicesOrg.getUserAccountDirectory().createUserAccount("doctor", "doctor", doctorEmployee, new DoctorRole()); // Changed to organization's user account directory
        doctorEmployee.setUserAccount(doctorAccount);
        clinicalServicesOrg.addEmployee(doctorEmployee);

        // Create Nurse User and add to Clinical Services Org with Faker-generated data
        ContactInfo nurseContact = new ContactInfo(faker.address().fullAddress(), faker.phoneNumber().phoneNumber(), faker.internet().emailAddress());
        Employee nurseEmployee = new Employee("EMP" + faker.number().digits(4), faker.name().fullName(), faker.options().option("Male", "Female"), faker.number().numberBetween(22, 55), new SimpleDateFormat("yyyy-MM-dd").format(faker.date().birthday()), "Registered Nurse", "Nursing", nurseContact);
        UserAccount nurseAccount = clinicalServicesOrg.getUserAccountDirectory().createUserAccount("nurse", "nurse", nurseEmployee, new NurseRole()); // Changed to organization's user account directory
        nurseEmployee.setUserAccount(nurseAccount);
        clinicalServicesOrg.addEmployee(nurseEmployee);

        // Create Payroll Staff User and add to Administration Unit (New)
        ContactInfo payrollStaffContact = new ContactInfo(faker.address().fullAddress(), faker.phoneNumber().phoneNumber(), faker.internet().emailAddress());
        Employee payrollStaffEmployee = new Employee("EMP" + faker.number().digits(4), faker.name().fullName(), faker.options().option("Male", "Female"), faker.number().numberBetween(25, 50), new SimpleDateFormat("yyyy-MM-dd").format(faker.date().birthday()), "Payroll Staff", "Finance", payrollStaffContact);
        UserAccount payrollStaffAccount = hospitalAdminOrg.getUserAccountDirectory().createUserAccount("payrollstaff", "payrollstaff", payrollStaffEmployee, new PayrollStaffRole()); // Changed to organization's user account directory
        payrollStaffEmployee.setUserAccount(payrollStaffAccount);
        hospitalAdminOrg.addEmployee(payrollStaffEmployee);

        // Create Resource Analyst User and add to Administration Unit (New)
        ContactInfo resourceAnalystContact = new ContactInfo(faker.address().fullAddress(), faker.phoneNumber().phoneNumber(), faker.internet().emailAddress());
        Employee resourceAnalystEmployee = new Employee("EMP" + faker.number().digits(4), faker.name().fullName(), faker.options().option("Male", "Female"), faker.number().numberBetween(25, 50), new SimpleDateFormat("yyyy-MM-dd").format(faker.date().birthday()), "Resource Analyst", "Administration", resourceAnalystContact);
        UserAccount resourceAnalystAccount = hospitalAdminOrg.getUserAccountDirectory().createUserAccount("resourceanalyst", "resourceanalyst", resourceAnalystEmployee, new ResourceAnalystRole()); // Changed to organization's user account directory
        resourceAnalystEmployee.setUserAccount(resourceAnalystAccount);
        hospitalAdminOrg.addEmployee(resourceAnalystEmployee);

        // Create Emergency Dispatcher User and add to Emergency Dispatch Org with Faker-generated data
        // 明确传入账号信息
        String dispatcherUsername = "dispatcher";
        String dispatcherPassword = "dispatcher";
        // 先创建 Employee
        ContactInfo dispatcherContact = new ContactInfo(
            faker.address().fullAddress(),
            faker.phoneNumber().phoneNumber(),
            faker.internet().emailAddress()
        );
        Employee dispatcherEmployee = new Employee(
            "EMP" + faker.number().digits(4),
            faker.name().fullName(),
            faker.options().option("Male", "Female"),
            faker.number().numberBetween(28, 55),
            new SimpleDateFormat("yyyy-MM-dd").format(faker.date().birthday()),
            "Dispatcher",
            "Dispatch",
            dispatcherContact
        );
        // 然后创建账号
        UserAccount dispatcherAccount = emergencyDispatchOrg.getUserAccountDirectory().createUserAccount( // Changed to organization's user account directory
            dispatcherUsername,
            dispatcherPassword,
            dispatcherEmployee,
            new EmergencyDispatcherRole()
        );
        // 绑定账号
        dispatcherEmployee.setUserAccount(dispatcherAccount);
        // 加入组织
        emergencyDispatchOrg.addEmployee(dispatcherEmployee);

        // Create Emergency Responder User and add to Emergency Response Org with Faker-generated data
        String responderUsername = "responder";
        String responderPassword = "responder";
        ContactInfo responderContact = new ContactInfo(
            faker.address().fullAddress(),
            faker.phoneNumber().phoneNumber(),
            faker.internet().emailAddress()
        );
        Employee responderEmployee = new Employee(
            "EMP" + faker.number().digits(4),
            faker.name().fullName(),
            faker.options().option("Male", "Female"),
            faker.number().numberBetween(20, 45),
            new SimpleDateFormat("yyyy-MM-dd").format(faker.date().birthday()),
            "Responder",
            "Response",
            responderContact
        );
        UserAccount responderAccount = emergencyResponseOrg.getUserAccountDirectory().createUserAccount( // Changed to organization's user account directory
            responderUsername,
            responderPassword,
            responderEmployee,
            new EmergencyResponderRole()
        );

        responderEmployee.setUserAccount(responderAccount);
        emergencyResponseOrg.addEmployee(responderEmployee);

        // Create Logistics Manager User and add to Inventory Manager Org with Faker-generated data
        ContactInfo logisticsManagerContact = new ContactInfo(
            faker.address().fullAddress(),
            faker.phoneNumber().phoneNumber(),
            faker.internet().emailAddress()
        );
        Employee logisticsManagerEmployee = new Employee(
            "EMP" + faker.number().digits(4),
            faker.name().fullName(),
            faker.options().option("Male", "Female"),
            faker.number().numberBetween(30, 60),
            new SimpleDateFormat("yyyy-MM-dd").format(faker.date().birthday()),
            "Manager",
            "Logistics",
            logisticsManagerContact
        );
        String logisticsUsername = "logisticsmanager";
        String logisticsPassword = "logisticsmanager";
        UserAccount logisticsManagerAccount = inventoryManagerOrg.getUserAccountDirectory().createUserAccount( // Changed to organization's user account directory
            logisticsUsername,
            logisticsPassword,
            logisticsManagerEmployee,
            new LogisticsManagerRole()
        );
        logisticsManagerEmployee.setUserAccount(logisticsManagerAccount);
        inventoryManagerOrg.addEmployee(logisticsManagerEmployee);


        // Create Delivery Staff User and add to Delivery Unit Org with Faker-generated data
        ContactInfo deliveryStaffContact = new ContactInfo(
            faker.address().fullAddress(),
            faker.phoneNumber().phoneNumber(),
            faker.internet().emailAddress()
        );
        Employee deliveryStaffEmployee = new Employee(
            "EMP" + faker.number().digits(4),
            faker.name().fullName(),
            faker.options().option("Male", "Female"),
            faker.number().numberBetween(20, 50),
            new SimpleDateFormat("yyyy-MM-dd").format(faker.date().birthday()),
            "Driver",
            "Delivery",
            deliveryStaffContact
        );
        String deliveryUsername = "deliverystaff";
        String deliveryPassword = "deliverystaff";
        UserAccount deliveryStaffAccount = deliveryUnitOrg.getUserAccountDirectory().createUserAccount( // Changed to organization's user account directory
            deliveryUsername,
            deliveryPassword,
            deliveryStaffEmployee,
            new DeliveryStaffRole()
        );
        deliveryStaffEmployee.setUserAccount(deliveryStaffAccount);
        deliveryUnitOrg.addEmployee(deliveryStaffEmployee);


        // Create Donation Coordinator User and add to Donation Management Unit (New)
        ContactInfo donationCoordinatorContact = new ContactInfo(faker.address().fullAddress(), faker.phoneNumber().phoneNumber(), faker.internet().emailAddress());
        Employee donationCoordinatorEmployee = new Employee("EMP" + faker.number().digits(4), faker.name().fullName(), faker.options().option("Male", "Female"), faker.number().numberBetween(25, 55), new SimpleDateFormat("yyyy-MM-dd").format(faker.date().birthday()), "Donation Coordinator", "Donation Management", donationCoordinatorContact);
        UserAccount donationCoordinatorAccount = donationManagementOrg.getUserAccountDirectory().createUserAccount("donationcoordinator", "donationcoordinator", donationCoordinatorEmployee, new DonationCoordinatorRole()); // Changed to organization's user account directory
        donationCoordinatorEmployee.setUserAccount(donationCoordinatorAccount);
        donationManagementOrg.addEmployee(donationCoordinatorEmployee);

    // Create Supply Chain Manager User and add to Supply Chain Management Unit (Corrected)
    LogisticsEnterprise logisticsEnterprise = new LogisticsEnterprise(
        "ENT001",
        "Logistics Corp",
        "Handles logistics operations",
        "Alice Manager"
    );
    //Step 2: 创建 Supply Chain Management Unit
    SupplyChainManagementUnit supplyChainOrg = new SupplyChainManagementUnit("Main Warehouse");
    logisticsEnterprise.getOrganizations().addOrganization(supplyChainOrg);  // ✅ 使用实例调用
    //Step 3: 创建联系人信息
    ContactInfo supplychainManagerContact = new ContactInfo(
        faker.address().fullAddress(),
        faker.phoneNumber().phoneNumber(),
        faker.internet().emailAddress()
    );
    // Step 4: 创建员工对象
    Employee supplychainManagerEmployee = new Employee(
        "EMP" + faker.number().digits(4),
        faker.name().fullName(),
        faker.options().option("Male", "Female"),
        faker.number().numberBetween(30, 60),
        new SimpleDateFormat("yyyy-MM-dd").format(faker.date().birthday()),
        "Supply Chain Manager",
        "Supply Chain Unit",
        supplychainManagerContact
    );

    system.getNetworkDirectory().getNetworkList().get(0).getEnterpriseDirectory().addEnterprise(logisticsEnterprise);
    UserAccount supplychainManagerAccount = supplyChainOrg.getUserAccountDirectory()
        .createUserAccount("supplymanager", "supplymanager", supplychainManagerEmployee, new SupplychainManagerRole());

    supplychainManagerEmployee.setUserAccount(supplychainManagerAccount);
    supplyChainOrg.addEmployee(supplychainManagerEmployee);

        // Create Patient (External User)
        // Patients are added directly to ClinicalServicesUnit, no UserAccount needed unless they log in
        // A Patient object is created in generatePatientsAndAppointments helper method.

        // Create Donor (External User)
        // Donors are created in generateDonations helper method.

        // --- Generate more virtual data ---

        // 1. Patients and Medical Records
        generatePatientsAndAppointments(clinicalServicesOrg, doctorEmployee, nurseEmployee, faker);

        // 2. Medical Supplies and Equipments
        generateInventory(inventoryManagerOrg, faker);

        // 3. Donations and Donors
        generateDonations(donationManagementOrg, faker);

        // 4. Vehicles
        generateVehicles(deliveryUnitOrg, faker); // Associate with ResourceDispatchUnit for logistics
        generateVehicles(emergencyResponseOrg, faker); // Associate with EmergencyResponseUnit

        // 5. Work Requests
        generateWorkRequests(clinicalServicesOrg, emergencyResponseOrg, inventoryManagerOrg, deliveryUnitOrg, operationsSupportOrg, donationManagementOrg, faker, sysAdminEmployee, doctorEmployee, dispatcherEmployee, responderEmployee, logisticsManagerEmployee, deliveryStaffEmployee, payrollStaffEmployee, resourceAnalystEmployee, donationCoordinatorEmployee, supplychainManagerEmployee);

        // 6. Reports and Mission Stats
        generateReportsAndMissionStats(operationsSupportOrg, emergencyResponseOrg, faker, sysAdminEmployee, supplychainManagerEmployee);
        
        // 7. Create Public Data Manager
        createPublicDataManager(system, faker);
        
        // 8. Generate Public Health Data
        generatePublicHealthData(system, faker);
        
        // 9. Generate Organization Reference Data
        generateOrganizationReferenceData(system);        

        return system;
    }

    // --- Helper methods start ---

    private static void generatePatientsAndAppointments(ClinicalServicesUnit clinicalServicesOrg, Employee doctor, Employee nurse, Faker faker) {
        Random rand = new Random();

        // Simulate diagnoses and medical procedures (manually set)
        String[] diagnoses = {"Flu", "Hypertension", "Diabetes", "Allergy", "Anemia"};
        String[] procedures = {"Blood Test", "MRI", "Vaccination", "Physical Therapy", "Surgery"};

        for (int i = 0; i < 10; i++) {
            // ➤ Generate Patient
            String patientName = faker.name().fullName();
            String gender = faker.options().option("Male", "Female", "Other");
            int age = faker.number().numberBetween(1, 90);
            String dob = new SimpleDateFormat("yyyy-MM-dd").format(faker.date().birthday());

            Patient patient = new Patient(patientName, gender, age, dob);
            ContactInfo contact = new ContactInfo(
                faker.address().fullAddress(),
                faker.phoneNumber().phoneNumber(),
                faker.internet().emailAddress()
            );
            patient.setContactInfo(contact);
            clinicalServicesOrg.addPatient(patient);

            // ➤ Generate Medical Record Entries (random 1~2)
            int numMedicalEntries = faker.number().numberBetween(1, 3);
            for (int j = 0; j < numMedicalEntries; j++) {
                String diagnosis = diagnoses[rand.nextInt(diagnoses.length)];
                String procedure = procedures[rand.nextInt(procedures.length)];
                patient.getMedicalRecord().addMedicalEntry(diagnosis, procedure);
            }

            // ➤ Create an appointment for this patient (arranged by nurse for doctor)
            if (doctor != null) {
                Date appointmentDate = faker.date().future(30, TimeUnit.DAYS);
                Date appointmentTimeRaw = faker.date().future(60, TimeUnit.MINUTES, new Date());
                String appointmentTime = new SimpleDateFormat("HH:mm").format(appointmentTimeRaw);

                Appointment appointment = new Appointment(patient, doctor, appointmentDate, appointmentTime);
                appointment.setAppointmentType(faker.options().option("Regular Checkup", "Follow-up", "Emergency"));
                appointment.setLocation(faker.address().streetAddress());
                appointment.setSender(nurse.getUserAccount());    // Arranger
                appointment.setSender(doctor.getUserAccount()); // Receiver

                clinicalServicesOrg.scheduleAppointment(appointment);
            }
        }
    }

    private static void generateInventory(SupplyChainManagementUnit inventoryManagerOrg, Faker faker) {
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
    List<Donor> donors = new ArrayList<>();

    // ➤ 创建 Donor
    for (int i = 0; i < 10; i++) {
    String personId = "PER" + faker.number().digits(4);
    String donorName = faker.name().fullName();
    String gender = faker.options().option("Male", "Female", "Other");
    int age = faker.number().numberBetween(18, 80);
    String dob = new SimpleDateFormat("yyyy-MM-dd").format(faker.date().birthday());
    String username = "donor" + faker.number().digits(3);
    String password = "password";
    String orgName = "Public Donors";
    ContactInfo contact = new ContactInfo(faker.address().fullAddress(), faker.phoneNumber().phoneNumber(), faker.internet().emailAddress());

    // ✔ 创建 Donor 本体
    Donor donor = new Donor(personId, donorName, gender, age, dob, contact);

    // ✔ 绑定账号（可登录）
    UserAccount ua = new UserAccount(username, password, new VisitorRole(), orgName, donor);
    donor.setUserAccount(ua);
    // Add donor's user account to the *Donation Management Unit's* UserAccountDirectory
    donationManagementOrg.getUserAccountDirectory().addUserAccount(ua);
    
    donationManagementOrg.addDonor(donor);
    donors.add(donor);
    }

    // ➤ 创建 Donation（随机是现金或实物）
    for (int i = 0; i < 20; i++) {
        Donor randomDonor = donors.get(faker.number().numberBetween(0, donors.size()));
        String purpose = faker.options().option("Medical Research", "Disaster Relief", "Community Support", "General Fund");

        Donation donation;
        if (faker.bool().bool()) {
            double amount = faker.number().randomDouble(2, 50, 5000);
            donation = donationManagementOrg.recordMonetaryDonation(randomDonor, amount, purpose);
        } else {
            List<DonatedItem> donatedItems = new ArrayList<>();
            int numItems = faker.number().numberBetween(1, 4);
            for (int j = 0; j < numItems; j++) {
                String itemName = faker.commerce().productName();
                int quantity = faker.number().numberBetween(1, 10);
                double unitPrice = faker.number().randomDouble(2, 5, 200);
                donatedItems.add(new DonatedItem(itemName, quantity, unitPrice));
            }
            donation = donationManagementOrg.recordItemDonation(randomDonor, donatedItems, purpose);
        }

        donation.setNotes(faker.lorem().sentence());

        // 随机处理一部分
        if (faker.bool().bool()) {
            donation.processDonation();
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
                                             SupplyChainManagementUnit inventoryManagerOrg, ResourceDispatchUnit deliveryUnitOrg,
                                             OperationsSupportUnit operationsSupportOrg, DonationManagementUnit donationManagementOrg,
                                             Faker faker, Employee sysAdmin, Employee doctor, Employee dispatcher,
                                             Employee responder, Employee logisticsManager, Employee deliveryStaff,
                                             Employee payrollStaff, Employee resourceAnalyst, Employee donationCoordinator, Employee supplychainManager) { // Added new employee parameters

        // SupplyWorkRequest
        for (int i = 0; i < 5; i++) {
            SupplyItem requestedSupply = inventoryManagerOrg.getInventoryList().get(faker.number().numberBetween(0, inventoryManagerOrg.getInventoryList().size() - 1));
            int quantity = faker.number().numberBetween(10, 100);
            SupplyWorkRequest supplyRequest = new SupplyWorkRequest(requestedSupply, quantity, faker.options().option("Emergency", "Clinical", "Logistics"));
            supplyRequest.setUrgency(faker.options().option("High", "Medium", "Low"));
            supplyRequest.setSender(sysAdmin.getUserAccount()); // Example sender
            supplyRequest.setReceiver(logisticsManager.getUserAccount()); // Example receiver
            supplyRequest.setStatus(faker.options().option("Requested", "Approved", "Fulfilled"));
            inventoryManagerOrg.addWorkRequest(supplyRequest); // Add to InventoryManager's queue
        }

        // DonationRequest
        for (int i = 0; i < 3; i++) {
            List<Donation> donationsForRequest = new ArrayList<>();
            donationsForRequest.add(donationManagementOrg.getDonationRecords().get(faker.number().numberBetween(0, donationManagementOrg.getDonationRecords().size() - 1)));
            String requestType = faker.options().option("Process", "Distribute", "Acknowledge");
            DonationRequest donationRequest = new DonationRequest(requestType, donationsForRequest);
            donationRequest.setSender(sysAdmin.getUserAccount()); // Example sender
            donationRequest.setReceiver(donationCoordinator.getUserAccount()); // Changed receiver to DonationCoordinator
            donationRequest.setStatus(faker.options().option("Pending", "Processed", "Distributed"));
            donationManagementOrg.addWorkRequest(donationRequest);
        }

        // PayrollRequest
        // Ensure staff list is not empty by adding the new payrollStaff and resourceAnalyst
        if (operationsSupportOrg.getStaffList().isEmpty()) {
            operationsSupportOrg.addStaff(payrollStaff);
            operationsSupportOrg.addStaff(resourceAnalyst);
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
            payrollRequest.setSender(sysAdmin.getUserAccount());
            payrollRequest.setReceiver(payrollStaff.getUserAccount()); // Changed receiver to PayrollStaff
            payrollRequest.setStatus(faker.options().option("Submitted", "Processed"));
            operationsSupportOrg.addWorkRequest(payrollRequest);
        }

        // EmergencyWorkRequest
        for (int i = 0; i < 4; i++) {
            String emergencyType = faker.options().option("Medical Emergency", "Fire", "Accident", "Natural Disaster");
            String location = faker.address().fullAddress();
            EmergencyWorkRequest emergencyRequest = new EmergencyWorkRequest(emergencyType, location, faker.number().numberBetween(1, 5), faker.name().fullName());
            emergencyRequest.setSender(sysAdmin.getUserAccount());
            emergencyRequest.setReceiver(dispatcher.getUserAccount());
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
            icuRequest.setSender(doctor.getUserAccount());
            icuRequest.setReceiver(doctor.getUserAccount());
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
            deliveryRequest.setSender(logisticsManager.getUserAccount());
            deliveryRequest.setReceiver(deliveryStaff.getUserAccount());
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
            analysisRequest.setSender(sysAdmin.getUserAccount());
            analysisRequest.setReceiver(resourceAnalyst.getUserAccount());
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
            costReport.setSender(sysAdmin.getUserAccount());
            costReport.setReceiver(payrollStaff.getUserAccount());
            costReport.setStatus(faker.options().option("Pending", "Generated"));
            operationsSupportOrg.addWorkRequest(costReport);
        }
    }

    private static void generateReportsAndMissionStats(OperationsSupportUnit operationsSupportOrg, EmergencyResponseUnit emergencyResponseOrg, Faker faker, Employee sysAdmin, Employee supplychainManager) {
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
            missionStats.setSender(sysAdmin.getUserAccount());
            missionStats.setReceiver(supplychainManager.getUserAccount());
            missionStats.setStatus("Finalized");
            operationsSupportOrg.addWorkRequest(missionStats);
            emergencyResponseOrg.addWorkRequest(missionStats);
        }
    }
    
    // 创建公共数据管理员
    private static void createPublicDataManager(EcoSystem system, Faker faker) {
        ContactInfo pdmContact = new ContactInfo(
            faker.address().fullAddress(),
            faker.phoneNumber().phoneNumber(),
            faker.internet().emailAddress()
        );
        
        PublicDataManager pdm = new PublicDataManager(
            "Healthcare Analytics",
            "EMP" + faker.number().digits(4),
            faker.name().fullName(),
            faker.options().option("Male", "Female"),
            faker.number().numberBetween(30, 55),
            new SimpleDateFormat("yyyy-MM-dd").format(faker.date().birthday()),
            "Public Data Manager",
            "Analytics Department",
            pdmContact
        );
        
        system.setPublicDataManager(pdm);
    }

    // 生成公共健康数据
    private static void generatePublicHealthData(EcoSystem system, Faker faker) {
        PublicDataManager pdm = system.getPublicDataManager();
        Random random = new Random();
        
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
        pdm.setCurrentHealthStatistics(healthStats);
        
        generateHealthDataForTimeSpans(pdm, regionDataList, faker);
    }
    
    private static void generateHealthDataForTimeSpans(PublicDataManager pdm, List<RegionData> baseData, Faker faker) {
        String[] timeSpans = {"3 days", "7 days", "30 days"};
        
        for (String timeSpan : timeSpans) {
            List<RegionData> adjustedData = new ArrayList<>();
            for (RegionData region : baseData) {
                double multiplier = getTimeSpanMultiplier(timeSpan);
                
                int adjustedTotal = (int) (region.getTotalPatients() * multiplier);
                int adjustedDischarged = (int) (region.getDischargedCount() * multiplier);
                int adjustedHospitalized = (int) (region.getHospitalizedCount() * multiplier);
                int adjustedDeaths = (int) (region.getDeathsCount() * multiplier);
                
                SymptomData originalSymptoms = region.getSymptomSummary();
                SymptomData adjustedSymptoms = new SymptomData(
                    region.getRegionName(),
                    (int) (originalSymptoms.getFeverCount() * multiplier),
                    (int) (originalSymptoms.getCoughCount() * multiplier),
                    (int) (originalSymptoms.getChestTightnessCount() * multiplier),
                    (int) (originalSymptoms.getComaCount() * multiplier)
                );
                
                RegionData adjustedRegion = new RegionData(
                    region.getRegionName(),
                    adjustedTotal,
                    adjustedDischarged,
                    adjustedHospitalized,
                    adjustedDeaths,
                    region.getAverageLengthOfStay(),
                    adjustedSymptoms
                );
                
                adjustedData.add(adjustedRegion);
            }
            
            HealthStatistics timeSpanStats = new HealthStatistics(adjustedData, timeSpan, new Date());
            pdm.addTimeSpanData(timeSpan, timeSpanStats);
        }
    }
    
    private static double getTimeSpanMultiplier(String timeSpan) {
        switch (timeSpan) {
            case "3 days": return 0.4;
            case "7 days": return 1.0;
            case "30 days": return 4.2;
            default: return 1.0;
        }
    }
    
    private static void generateOrganizationReferenceData(EcoSystem system) {
        PublicDataManager pdm = system.getPublicDataManager();
        
        List<String> standardOrganizations = Arrays.asList(
            "Emergency Response Unit",
            "Clinical Services Unit", 
            "Supply Chain Management",
            "Operations Support Unit",
            "Disaster Relief Team",
            "Community Health Center",
            "Medical Research Division",
            "Public Health Department"
        );
        
        pdm.setStandardOrganizationList(standardOrganizations);
    }


}