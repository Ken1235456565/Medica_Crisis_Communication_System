// Model/Personnel/EquipmentTechnician.java
package Model.Personnel;

import Model.Employee.Employee;
import Model.Person.ContactInfo;
import Model.Supplies.Equipments;
import Model.WorkQueue.RepairRequest;
import Model.WorkQueue.IssueReport;
import java.util.ArrayList;
import java.util.List;


public class EquipmentTechnician extends Employee {
    private String technicianId;
    private String specialization; // 专业领域 e.g., "Medical Equipment", "IT Equipment"
    private List<String> certifications; // 认证列表
    private List<RepairRequest> assignedRepairs; // 分配的维修任务
    private List<IssueReport> reportedIssues; // 报告的问题
    private int completedRepairs; // 完成的维修数量
    private double skillLevel; // 技能等级 1-10
    
    private static int counter = 1;

    public EquipmentTechnician() {
        super();
        this.technicianId = "TECH" + counter++;
        this.certifications = new ArrayList<>();
        this.assignedRepairs = new ArrayList<>();
        this.reportedIssues = new ArrayList<>();
        this.completedRepairs = 0;
        this.skillLevel = 5.0; // 默认技能等级
        this.setPosition("Equipment Technician");
    }

    public EquipmentTechnician(String name, String gender, int age, String dateOfBirth,
                              String department, ContactInfo contactInfo, String specialization) {
        super(null, name, gender, age, dateOfBirth, "Equipment Technician", department, contactInfo);
        this.technicianId = "TECH" + counter++;
        this.specialization = specialization;
        this.certifications = new ArrayList<>();
        this.assignedRepairs = new ArrayList<>();
        this.reportedIssues = new ArrayList<>();
        this.completedRepairs = 0;
        this.skillLevel = 5.0;
    }

    // Getters and setters
    public String getTechnicianId() { return technicianId; }
    public void setTechnicianId(String technicianId) { this.technicianId = technicianId; }

    public String getSpecialization() { return specialization; }
    public void setSpecialization(String specialization) { this.specialization = specialization; }

    public List<String> getCertifications() { return certifications; }
    public void setCertifications(List<String> certifications) { this.certifications = certifications; }

    public List<RepairRequest> getAssignedRepairs() { return assignedRepairs; }
    public void setAssignedRepairs(List<RepairRequest> assignedRepairs) { this.assignedRepairs = assignedRepairs; }

    public List<IssueReport> getReportedIssues() { return reportedIssues; }
    public void setReportedIssues(List<IssueReport> reportedIssues) { this.reportedIssues = reportedIssues; }

    public int getCompletedRepairs() { return completedRepairs; }
    public void setCompletedRepairs(int completedRepairs) { this.completedRepairs = completedRepairs; }

    public double getSkillLevel() { return skillLevel; }
    public void setSkillLevel(double skillLevel) { this.skillLevel = skillLevel; }

    // 业务方法
    public void addCertification(String certification) {
        if (!certifications.contains(certification)) {
            certifications.add(certification);
        }
    }

    public void assignRepair(RepairRequest repairRequest) {
        assignedRepairs.add(repairRequest);
        repairRequest.setAssignedTechnician(this);
    }

    public void completeRepair(RepairRequest repairRequest) {
        if (assignedRepairs.contains(repairRequest)) {
            repairRequest.completeRepair();
            completedRepairs++;
            // 技能等级可能根据完成的维修数量提升
            updateSkillLevel();
        }
    }

    public void reportIssue(IssueReport issueReport) {
        reportedIssues.add(issueReport);
        issueReport.setSender(this.getUserAccount());
    }

    private void updateSkillLevel() {
        // 简单的技能等级计算：每完成10个维修任务提升0.1
        this.skillLevel = Math.min(10.0, 5.0 + (completedRepairs / 10.0) * 0.1);
    }

    public List<RepairRequest> getActiveRepairs() {
        List<RepairRequest> activeRepairs = new ArrayList<>();
        for (RepairRequest repair : assignedRepairs) {
            if ("Pending".equals(repair.getStatus()) || "In Progress".equals(repair.getStatus())) {
                activeRepairs.add(repair);
            }
        }
        return activeRepairs;
    }

    public List<RepairRequest> getCompletedRepairsList() {
        List<RepairRequest> completedRepairsList = new ArrayList<>();
        for (RepairRequest repair : assignedRepairs) {
            if ("Completed".equals(repair.getStatus())) {
                completedRepairsList.add(repair);
            }
        }
        return completedRepairsList;
    }

    @Override
    public String toString() {
        return getName() + " (" + specialization + " Technician, Level: " + skillLevel + ")";
    }
}