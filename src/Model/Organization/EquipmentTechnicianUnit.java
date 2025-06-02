// Model/Organization/EquipmentTechnicianUnit.java
package Model.Organization;

import Model.Employee.Employee;
import Model.Personnel.Admin;
import Model.Personnel.EquipmentTechnician;
import Model.Supplies.Equipments;
import Model.Supplies.EquipmentsCatalog;
import Model.WorkQueue.RepairRequest;
import Model.WorkQueue.IssueReport;
import Model.WorkQueue.WorkRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class EquipmentTechnicianUnit extends Organization {
    
    private List<EquipmentTechnician> technicians;
    private List<RepairRequest> repairRequests;
    private List<IssueReport> issueReports;
    private String specialization; 
    EquipmentsCatalog equipmentsCatalog;
    
    public EquipmentTechnicianUnit() {
        super("Equipment Technician Unit");
        this.technicians = new ArrayList<>();
        this.repairRequests = new ArrayList<>();
        this.issueReports = new ArrayList<>();
        this.specialization = "General Equipment";
        initializeDefaultEquipments();
    }
    
    public EquipmentTechnicianUnit(String unitName) {
        super(unitName);
        this.technicians = new ArrayList<>();
        this.repairRequests = new ArrayList<>();
        this.issueReports = new ArrayList<>();
        this.specialization = "General Equipment";
        initializeDefaultEquipments();
    }
    
    public EquipmentTechnicianUnit(String unitName, Admin admin, String specialization) {
        super(unitName, admin);
        this.technicians = new ArrayList<>();
        this.repairRequests = new ArrayList<>();
        this.issueReports = new ArrayList<>();
        this.specialization = specialization;
        initializeDefaultEquipments();
    }

    // Getters and setters
    public List<EquipmentTechnician> getTechnicians() {
        return technicians;
    }

    public List<RepairRequest> getRepairRequests() {
        return repairRequests;
    }

    public List<IssueReport> getIssueReports() {
        return issueReports;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    // 初始化默认设备
    private void initializeDefaultEquipments() {
        if (equipmentsCatalog == null) {
            equipmentsCatalog = new EquipmentsCatalog();
        }
        
        // 根据专业领域添加不同的设备
        if ("Medical Equipment".equals(specialization)) {
            addMedicalEquipments();
        } else if ("IT Equipment".equals(specialization)) {
            addITEquipments();
        } else {
            addGeneralEquipments();
        }
    }
    
    private void addMedicalEquipments() {
        Equipments mriMachine = new Equipments("MRI Machine", "Magnetic Resonance Imaging", 
                                             "Medical Equipment", 1, 500000.0,
                                             "Siemens Magnetom", new Date(), "Radiology");
        Equipments xrayMachine = new Equipments("X-Ray Machine", "Digital X-Ray System", 
                                               "Medical Equipment", 2, 150000.0,
                                               "GE Healthcare", new Date(), "Radiology");
        Equipments ultrasound = new Equipments("Ultrasound Machine", "Diagnostic Ultrasound", 
                                              "Medical Equipment", 3, 80000.0,
                                              "Philips", new Date(), "Diagnostics");
        
        equipmentsCatalog.addEquipment(mriMachine);
        equipmentsCatalog.addEquipment(xrayMachine);
        equipmentsCatalog.addEquipment(ultrasound);
    }
    
    private void addITEquipments() {
        Equipments server = new Equipments("Server", "Database Server", 
                                          "IT Equipment", 5, 15000.0,
                                          "Dell PowerEdge", new Date(), "IT");
        Equipments computer = new Equipments("Computer", "Desktop Computer", 
                                           "IT Equipment", 20, 1000.0,
                                           "Dell OptiPlex", new Date(), "IT");
        Equipments router = new Equipments("Network Router", "Enterprise Router", 
                                         "Network Equipment", 10, 2000.0,
                                         "Cisco", new Date(), "IT");
        
        equipmentsCatalog.addEquipment(server);
        equipmentsCatalog.addEquipment(computer);
        equipmentsCatalog.addEquipment(router);
    }
    
    private void addGeneralEquipments() {
        Equipments generator = new Equipments("Generator", "Emergency Generator", 
                                            "Power Equipment", 2, 25000.0,
                                            "Caterpillar", new Date(), "Maintenance");
        Equipments elevator = new Equipments("Elevator", "Passenger Elevator", 
                                           "Building Equipment", 4, 50000.0,
                                           "Otis", new Date(), "Building");
        Equipments hvac = new Equipments("HVAC System", "Heating Ventilation Air Conditioning", 
                                       "Building Equipment", 6, 20000.0,
                                       "Carrier", new Date(), "Building");
        
        equipmentsCatalog.addEquipment(generator);
        equipmentsCatalog.addEquipment(elevator);
        equipmentsCatalog.addEquipment(hvac);
    }

    // 技术员管理方法
    public void addTechnician(EquipmentTechnician technician) {
        technicians.add(technician);
        addEmployee(technician); // 也添加到通用员工目录
    }

    public void removeTechnician(EquipmentTechnician technician) {
        technicians.remove(technician);
        removeEmployee(technician);
    }

    public EquipmentTechnician findTechnicianById(String technicianId) {
        for (EquipmentTechnician technician : technicians) {
            if (technician.getTechnicianId().equals(technicianId)) {
                return technician;
            }
        }
        return null;
    }

    public List<EquipmentTechnician> findTechniciansBySpecialization(String specialization) {
        List<EquipmentTechnician> result = new ArrayList<>();
        for (EquipmentTechnician technician : technicians) {
            if (technician.getSpecialization() != null && 
                technician.getSpecialization().equalsIgnoreCase(specialization)) {
                result.add(technician);
            }
        }
        return result;
    }

    // 维修请求管理
    public void addRepairRequest(RepairRequest repairRequest) {
        repairRequests.add(repairRequest);
        addWorkRequest(repairRequest); // 也添加到通用工作队列
    }

    public void removeRepairRequest(RepairRequest repairRequest) {
        repairRequests.remove(repairRequest);
    }

    public RepairRequest findRepairRequestById(String requestId) {
        for (RepairRequest request : repairRequests) {
            if (request.getRepairRequestId().equals(requestId)) {
                return request;
            }
        }
        return null;
    }

    public List<RepairRequest> getPendingRepairRequests() {
        List<RepairRequest> pending = new ArrayList<>();
        for (RepairRequest request : repairRequests) {
            if ("Pending".equals(request.getStatus()) || 
                request.getAssignedTechnician() == null) {
                pending.add(request);
            }
        }
        return pending;
    }

    public List<RepairRequest> getActiveRepairRequests() {
        List<RepairRequest> active = new ArrayList<>();
        for (RepairRequest request : repairRequests) {
            String status = request.getStatus();
            if ("In Progress".equals(status) || "Assigned".equals(status)) {
                active.add(request);
            }
        }
        return active;
    }

    // 问题报告管理
    public void addIssueReport(IssueReport issueReport) {
        issueReports.add(issueReport);
        addWorkRequest(issueReport); // 也添加到通用工作队列
    }

    public void removeIssueReport(IssueReport issueReport) {
        issueReports.remove(issueReport);
    }

    public IssueReport findIssueReportById(String reportId) {
        for (IssueReport report : issueReports) {
            if (report.getReportId().equals(reportId)) {
                return report;
            }
        }
        return null;
    }

    // 自动分配维修请求给合适的技术员
    public boolean autoAssignRepairRequest(RepairRequest repairRequest) {
        // 查找合适的技术员
        EquipmentTechnician bestTechnician = findBestTechnician(repairRequest);
        
        if (bestTechnician != null) {
            repairRequest.setAssignedTechnician(bestTechnician);
            repairRequest.setStatus("Assigned");
            bestTechnician.assignRepair(repairRequest);
            return true;
        }
        
        return false;
    }

    private EquipmentTechnician findBestTechnician(RepairRequest repairRequest) {
        EquipmentTechnician bestTechnician = null;
        int minWorkload = Integer.MAX_VALUE;
        
        for (EquipmentTechnician technician : technicians) {
            // 检查专业领域是否匹配
            if (repairRequest.getEquipment() != null) {
                String equipmentType = repairRequest.getEquipment().getType();
                if (!isSpecializationMatch(technician.getSpecialization(), equipmentType)) {
                    continue;
                }
            }
            
            // 选择工作负载最少的技术员
            int currentWorkload = technician.getActiveRepairs().size();
            if (currentWorkload < minWorkload) {
                minWorkload = currentWorkload;
                bestTechnician = technician;
            }
        }
        
        return bestTechnician;
    }

    private boolean isSpecializationMatch(String technicianSpec, String equipmentType) {
        if (technicianSpec == null || equipmentType == null) {
            return true; // 如果信息不完整，允许分配
        }
        
        // 简单的匹配逻辑
        if (technicianSpec.toLowerCase().contains("medical") && 
            equipmentType.toLowerCase().contains("medical")) {
            return true;
        }
        if (technicianSpec.toLowerCase().contains("it") && 
            equipmentType.toLowerCase().contains("it")) {
            return true;
        }
        if (technicianSpec.toLowerCase().contains("general")) {
            return true; // 通用技术员可以处理任何设备
        }
        
        return false;
    }

    // 获取单位统计信息
    public int getTotalTechnicians() {
        return technicians.size();
    }

    public int getActiveTechnicians() {
        int active = 0;
        for (EquipmentTechnician technician : technicians) {
            if (technician.isActive() && !technician.getActiveRepairs().isEmpty()) {
                active++;
            }
        }
        return active;
    }

    public int getTotalEquipments() {
        return equipmentsCatalog.getEquipmentsList().size();
    }

    public int getEquipmentsUnderMaintenance() {
        int underMaintenance = 0;
        for (Equipments equipment : equipmentsCatalog.getEquipmentsList()) {
            if ("Under Maintenance".equals(equipment.getStatus())) {
                underMaintenance++;
            }
        }
        return underMaintenance;
    }

    @Override
    public String toString() {
        return organizationName + " [Technicians: " + technicians.size() + 
               ", Equipments: " + getTotalEquipments() + 
               ", Specialization: " + specialization + "]";
    }
}