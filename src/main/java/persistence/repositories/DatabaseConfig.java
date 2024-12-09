package persistence.repositories;

public class DatabaseConfig {
    private String DB_URL;
    private String DB_USER;
    private String DB_PASSWORD;

    public String getDB_URL() {
        return DB_URL;
    }

    public String getDB_USER() {
        return DB_USER;
    }

    public String getDB_PASSWORD() {
        return DB_PASSWORD;
    }

    private static DatabaseConfig instance;

    private DatabaseConfig(String url, String user, String password) {
        this.DB_URL = url;
        this.DB_USER = user;
        this.DB_PASSWORD = password;
    }

    public static void initialize(String url, String user, String password) {
        if (DatabaseConfig.instance == null) {
            DatabaseConfig.instance = new DatabaseConfig(url, user, password);
        }
    }

    public static DatabaseConfig getInstance() {
        return DatabaseConfig.instance;
    }
}
