package hva.employee;

import java.io.Serializable;

public abstract class Employee implements Serializable{
    private String _employeeId;
    private String _name;

    public Employee(String employeeId, String name) {
        _employeeId = employeeId;
        _name = name;
    }

    public String getEmployeeId() {
        return _employeeId;
    }

    public String getEmployeeName() {
        return _name;
    }

    public abstract double calculateSatisfaction();
}
