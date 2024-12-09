package persistence.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Department {
    @JsonProperty("DepartmentID")
    private int DepartmentID;
    @JsonProperty("DepartmentName")
    private String DepartmentName;

    public int getDepartmentID() {
        return DepartmentID;
    }

    public void setDepartmentID(int id) {
        DepartmentID = id;
    }

    public String getDepartmentName() {
        return DepartmentName;
    }

    public void setDepartmentName(String id) {
        DepartmentName = id;
    }

    @Override
    public String toString() {
        return "Department{" +
                "departmentID=" + DepartmentID +
                ", departmentName='" + DepartmentName + '\'' +
                '}';
    }
}
