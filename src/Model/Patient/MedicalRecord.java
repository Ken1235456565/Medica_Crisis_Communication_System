// Model/patient/MedicalRecord.java
package Model.Patient;

import java.util.ArrayList;
import java.util.List;

public class MedicalRecord {
    private String recordId;
    private List<MedicalEntry> medicalHistory;

    public MedicalRecord() {
        this.medicalHistory = new ArrayList<>();
    }

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public List<MedicalEntry> getMedicalHistory() {
        return medicalHistory;
    }

    public void setMedicalHistory(List<MedicalEntry> medicalHistory) {
        this.medicalHistory = medicalHistory;
    }

    // 原始添加方法：处理对象
    public void addMedicalEntry(MedicalEntry entry) {
        this.medicalHistory.add(entry);
    }

    // 传入两个字符串
    public void addMedicalEntry(String diagnosis, String treatment) {
        MedicalEntry entry = new MedicalEntry(diagnosis, treatment);
        this.addMedicalEntry(entry); // ✅ 现在可以正常调用
    }

    public void removeMedicalEntry(MedicalEntry entry) {
        this.medicalHistory.remove(entry);
    }
}