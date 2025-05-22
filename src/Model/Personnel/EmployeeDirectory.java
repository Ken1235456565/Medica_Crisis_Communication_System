// Model/personnel/EmployeeDirectory.java
package Model.Personnel;

import java.util.ArrayList;
import java.util.List;

public class EmployeeDirectory {
    private List<Employee> employeeList;

    public EmployeeDirectory() {
        this.employeeList = new ArrayList<>();
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    public void addEmployee(Employee employee) {
        this.employeeList.add(employee);
    }

    public void removeEmployee(Employee employee) {
        this.employeeList.remove(employee);
    }

    public Employee findEmployeeById(String id) {
        for (Employee emp : employeeList) {
            if (emp.getId().equals(id)) {
                return emp;
            }
        }
        return null;
    }

    public Employee findEmployeeByName(String name) {
        for (Employee emp : employeeList) {
            if (emp.getName().equals(name)) {
                return emp;
            }
        }
        return null;
    }

    public List<Employee> findEmployeesByRole(String roleName) {
        List<Employee> result = new ArrayList<>();
        for (Employee employee : employeeList) {
            if (employee.getRole() != null && employee.getRole().getName().equals(roleName)) {
                result.add(employee);
            }
        }
        return result;
    }
}