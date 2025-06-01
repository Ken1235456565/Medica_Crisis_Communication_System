// Model/personnel/roles/NurseRole.java
package Model.Personnel;

import Model.Employee.Employee;
import Model.Employee.PayrollRecord;
import Model.Patient.ShiftNote;
import Model.Person.ContactInfo;
import Model.Role.NurseRole;
import Model.Role.Role;
import Model.User.UserAccount;
import java.util.List;

public class Nurse extends Employee {
    private String nurseType;
    private String licenseNumber;
    private List<ShiftNote> shiftNotes;

    public Nurse(String nurseType, String licenseNumber,
                 String id, String name, String gender, int age, String dateOfBirth,
                 String position, String department, ContactInfo contactInfo) {
        super(id, name, gender, age, dateOfBirth, position, department, contactInfo);
        this.nurseType = nurseType;
        this.licenseNumber = licenseNumber;
    }
    
    public Nurse(String nurseType, String licenseNumber,
                 String id, String name, String gender, int age, String dateOfBirth,
                 String position, String department, ContactInfo contactInfo,
                 UserAccount userAccount, PayrollRecord payrollRecord, Role role) {
        super(id != null ? id : generateEmployeeId(), name, gender, age, dateOfBirth,
              position, department, contactInfo, userAccount, payrollRecord, role);
        this.nurseType = nurseType;
        this.licenseNumber = licenseNumber;
    }

    public Nurse() {
        super();
    }

    public String getNurseType() {
        return nurseType;
    }

    public void setNurseType(String nurseType) {
        this.nurseType = nurseType;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public List<ShiftNote> getShiftNotes() {
        return shiftNotes;
    }

    public void setShiftNotes(List<ShiftNote> shiftNotes) {
        this.shiftNotes = shiftNotes;
    }
    
    public void addShiftNote(ShiftNote note) {
        this.shiftNotes.add(note);
    }

    public void removeShiftNote(ShiftNote note) {
        this.shiftNotes.remove(note);
    }
    
    public String getEmployeeId() {
        return employeeId;
    }

    public String getName() {
        return name;
    }
}
