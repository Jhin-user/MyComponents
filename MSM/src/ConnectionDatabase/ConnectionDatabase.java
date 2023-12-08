package ConnectionDatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Jhin
 */
public class ConnectionDatabase {

    public static Connection GetConnection() {
        Connection connection = null;
        String URL = "jdbc:sqlserver://localhost:1433;databaseName=MSM;encrypt=true;trustServerCertificate=true;username=sa;password=jhin";

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(URL);
//            System.out.println("Connected.");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Can't connect.");
            System.out.println("Error: " + e);
        }

        return connection;
    }
}
