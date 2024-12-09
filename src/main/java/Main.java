import business.CompanyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import persistence.models.Company;
import persistence.repositories.DatabaseConfig;

public class Main {
    static Logger logger = LoggerFactory.getLogger(Main.class);
    static String filePath = "data.json";
    static String DB_URL = "jdbc:postgresql://localhost:5432/testdb";
    static String DB_USER = "testuser";
    static String DB_PASSWORD = "testpass";
    public static void main(String[] args) {
        // TODO: Read file path from args
        // TODO: Initialise DataSource with provided config from args
        DatabaseConfig.initialize(DB_URL, DB_USER, DB_PASSWORD);

        Company data = CompanyService.readCompanyData(filePath);
        CompanyService.persistCompanyData(data);
    }
}
