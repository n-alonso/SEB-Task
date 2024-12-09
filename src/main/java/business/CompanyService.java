package business;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import persistence.repositories.DatabaseConfig;
import utils.FileHandler;
import utils.JsonParser;
import java.io.File;
import java.sql.*;
import java.util.List;

import persistence.models.Department;
import persistence.models.Employee;
import persistence.models.Company;

public class CompanyService {
    static Logger logger = LoggerFactory.getLogger(CompanyService.class);

    public static Company readCompanyData(String filePath) {
        try {
            File file = FileHandler.createFile(filePath);

            JsonParser<Company> parser = new JsonParser<>();
            return parser.parseJsonFile(file, Company.class);
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Error reading Company Data: " + e.getMessage());
        }
    }
    public static void persistCompanyData(Company company) {
        DatabaseConfig config = DatabaseConfig.getInstance();
        String INSERT_DEPARTMENT_SQL = "INSERT INTO departments (department_id, department_name) VALUES (?, ?)";
        String INSERT_EMPLOYEE_SQL = "INSERT INTO employees (employee_id, name, department_id) VALUES (?, ?, ?)";
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(config.getDB_URL(), config.getDB_USER(), config.getDB_PASSWORD());
            connection.setAutoCommit(false);

            try (PreparedStatement deptStmt = connection.prepareStatement(INSERT_DEPARTMENT_SQL)) {
                for (Department department : company.getDepartments()) {
                    deptStmt.setInt(1, department.getDepartmentID());
                    deptStmt.setString(2, department.getDepartmentName());
                    deptStmt.addBatch();
                }
                deptStmt.executeBatch();
            }

            try (PreparedStatement empStmt = connection.prepareStatement(INSERT_EMPLOYEE_SQL)) {
                for (Employee employee : company.getEmployees()) {
                    empStmt.setInt(1, employee.getEmployeeID());
                    empStmt.setString(2, employee.getName());

                    if (employee.getDepartmentID() != null) {
                        empStmt.setInt(3, employee.getDepartmentID());
                    } else {
                        empStmt.setNull(3, java.sql.Types.INTEGER);
                    }

                    empStmt.addBatch();
                }
                empStmt.executeBatch();
            }

            connection.commit();
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    logger.error("Error occurred, rolling back transaction");
                    connection.rollback();
                } catch (SQLException ex) {
                    logger.error("Rollback failed: " + ex.getMessage());
                }
            }
            throw new RuntimeException("Database operation failed: " + e.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    logger.error("Error closing connection: " + e.getMessage());
                }
            }
        }
    }
}
