// Model/personnel/roles/ResourceAnalystRole.java
package Model.Personnel;

import Model.Employee.Employee;
import Model.Person.ContactInfo;
import Model.PublicData.HealthStatistics;
import Model.PublicData.RegionData;
import Model.PublicData.SymptomData;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

public class PublicDataManager extends Employee {
    private String analysisArea;
    private HealthStatistics currentHealthStatistics;
    private Map<String, HealthStatistics> timeSpanHealthData;
    private List<String> standardOrganizationList;

    public PublicDataManager(String analysisArea,
                           String id, String name, String gender, int age, String dateOfBirth,
                           String position, String department, ContactInfo contactInfo) {
        super(id, name, gender, age, dateOfBirth, position, department, contactInfo);
        this.analysisArea = analysisArea;
        this.timeSpanHealthData = new HashMap<>();
        this.standardOrganizationList = new ArrayList<>();
    }

    public PublicDataManager() {
        super();
        this.timeSpanHealthData = new HashMap<>();
        this.standardOrganizationList = new ArrayList<>();
    }

    // 分析区域相关
    public String getAnalysisArea() {
        return analysisArea;
    }

    public void setAnalysisArea(String analysisArea) {
        this.analysisArea = analysisArea;
    }

    // 健康统计数据管理
    public HealthStatistics getCurrentHealthStatistics() {
        return currentHealthStatistics;
    }

    public void setCurrentHealthStatistics(HealthStatistics currentHealthStatistics) {
        this.currentHealthStatistics = currentHealthStatistics;
    }

    public Map<String, HealthStatistics> getTimeSpanHealthData() {
        return timeSpanHealthData;
    }

    public void setTimeSpanHealthData(Map<String, HealthStatistics> timeSpanHealthData) {
        this.timeSpanHealthData = timeSpanHealthData;
    }

    public void addTimeSpanData(String timeSpan, HealthStatistics data) {
        this.timeSpanHealthData.put(timeSpan, data);
    }

    public HealthStatistics getDataByTimeSpan(String timeSpan) {
        return timeSpanHealthData.get(timeSpan);
    }

    // 组织列表管理
    public List<String> getStandardOrganizationList() {
        return standardOrganizationList;
    }

    public void setStandardOrganizationList(List<String> standardOrganizationList) {
        this.standardOrganizationList = standardOrganizationList;
    }

    public void addOrganization(String organization) {
        if (!standardOrganizationList.contains(organization)) {
            standardOrganizationList.add(organization);
        }
    }

    // 业务方法
    public HealthStatistics filterHealthStatisticsByTimeSpan(String timeSpan) {
        HealthStatistics data = timeSpanHealthData.get(timeSpan);
        return data != null ? data : currentHealthStatistics;
    }

    public void generateHealthReport(String timeSpan) {
        HealthStatistics stats = filterHealthStatisticsByTimeSpan(timeSpan);
        // 生成报告逻辑
    }

    @Override
    public String toString() {
        return getName() + " - Public Data Manager (" + analysisArea + ")";
    }
}
