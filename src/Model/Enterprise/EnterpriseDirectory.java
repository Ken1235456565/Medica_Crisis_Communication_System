// Model/enterprise/EnterpriseDirectory.java
package Model.Enterprise;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MyPC1
 */
public class EnterpriseDirectory {
    private List<Enterprise> enterpriseList;

    public List<Enterprise> getEnterpriseList() {
        return enterpriseList;
    }

    public void setEnterpriseList(List<Enterprise> enterpriseList) {
        this.enterpriseList = enterpriseList;
    }

    public EnterpriseDirectory(){
        enterpriseList=new ArrayList<Enterprise>();
    }

    // Create enterprise
    public Enterprise createAndAddEnterprise(String id, String name, Enterprise.EnterpriseType type, String description, String manager){
        Enterprise enterprise = null;
        switch (type) {
            case Hospital:
                enterprise = new HospitalEnterprise(id, name, description, manager);
                break;
            case Emergency:
                enterprise = new EmergencyEnterprise(id, name, description, manager);
                break;
            case Logistics:
                enterprise = new LogisticsEnterprise(id, name, description, manager);
                break;
            case PublicHealth:
                enterprise = new PublicHealthEnterprise(id, name, description, manager);
                break;
        }
        if (enterprise != null) {
            enterpriseList.add(enterprise);
        }
        return enterprise;
    }

    public void addEnterprise(Enterprise enterprise) {
        this.enterpriseList.add(enterprise);
    }

    public void removeEnterprise(Enterprise enterprise) {
        this.enterpriseList.remove(enterprise);
    }

    public Enterprise findEnterpriseByName(String name) {
        for (Enterprise ent : enterpriseList) {
            if (ent.getName().equals(name)) {
                return ent;
            }
        }
        return null;
    }
}