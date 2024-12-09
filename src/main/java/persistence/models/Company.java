package persistence.models;

import java.util.List;

public class Company {
    public List<Employee> employees;
    public List<Department> departments;

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }
    @Override
    public String toString() {
        return "Company{" +
                "employees=" + employees +
                ", departments=" + departments +
                '}';
    }
}
