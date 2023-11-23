package Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Jhin
 */
public class ConnectionDatabase {

    public static Connection GetConnection() throws SQLException {
        Connection connection = null;
        String URL = "jdbc:sqlserver://localhost:1433;databaseName=spendMng;encrypt=true;trustServerCertificate=true;user=sa;password=jhin";

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(URL);
            System.out.println("Connected!");
        } catch (ClassNotFoundException e) {
            System.out.println("Not connected!");
        }

        return connection;
    }
}
