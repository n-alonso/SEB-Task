package persistence.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Employee {
    @JsonProperty("EmployeeID")
    private int EmployeeID;
    @JsonProperty("Name")
    private String Name;
    @JsonProperty("DepartmentID")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer DepartmentID;

    public int getEmployeeID() {
        return EmployeeID;
    }

    public void setEmployeeID(int id) {
        EmployeeID = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public Integer getDepartmentID() {
        return DepartmentID;
    }

    public void setDepartmentID(Integer id) {
        DepartmentID = id;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeID=" + EmployeeID +
                ", name='" + Name + '\'' +
                ", departmentID='" + DepartmentID + '\'' +
                '}';
    }
}
