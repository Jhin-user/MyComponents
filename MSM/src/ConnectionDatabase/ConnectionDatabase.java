package ConnectionDatabase;

import PropertiesConfig.ReadConfig;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Jhin
 */
public class ConnectionDatabase {

    public static Connection GetConnection() {
        ReadConfig.ReadProperties();
        String URL = ReadConfig.getUrl();
        String IP = ReadConfig.getIp();
        String PORT = ReadConfig.getPort();
        String DATABASENAME = ReadConfig.getDatabaseName();
        String ENCRYPT = ReadConfig.getEncrypt();
        String TSC = ReadConfig.getTrustServerCertificate();
        String USERNAME = ReadConfig.getUsername();
        String PASSWORD = ReadConfig.getPassword();
        
        String url = String.format("%s%s:%s; databaseName=%s; encrypt=%s; trustServerCertificate=%s; username=%s; password=%s", URL, IP, PORT, DATABASENAME, ENCRYPT, TSC, USERNAME, PASSWORD);
        Connection connection = null;

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(url);
            System.out.println("Connected to:");
            System.out.println(String.format("[URL]: %s\n[Ip]: %s\n[Port]: %s\n[Database]: %s\n[Encrypt]: %s\n[TrustServerCertificate]: %s\n[Username]: %s\n[Password]: %s", URL, IP, PORT, DATABASENAME, ENCRYPT, TSC, USERNAME, PASSWORD));
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Can't connect.");
            System.out.println("Error: " + e);
        }

        return connection;
    }
}
