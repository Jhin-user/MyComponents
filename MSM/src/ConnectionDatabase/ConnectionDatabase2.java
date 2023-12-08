package ConnectionDatabase;

import PropertiesConfig.ReadProperties;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Jhin
 */
public class ConnectionDatabase2 {

    public static Connection GetConnection() {
        Connection connection = null;

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(ReadProperties.getUrl(), ReadProperties.getUsername(), ReadProperties.getPassword());
//            System.out.println("Connected.");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Can't connect.");
            System.out.println("Error: " + e);
        }

        return connection;
    }
}
