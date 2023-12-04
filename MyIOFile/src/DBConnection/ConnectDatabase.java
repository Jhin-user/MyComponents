package DBConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Jhin
 */
public class ConnectDatabase {

    /* -------------------- LOCAL HOST WAY 1 -------------------- */
    public static Connection GetConnection() {
        Connection connection = null;
        /* ------------------------------ CONFIG ------------------------------ */
        String databaseName = "";
        String username = "";
        String password = "";
        /* -------------------------------------------------------------------- */
        String URL = "jdbc:sqlserver://localhost:1433;databaseName=" + databaseName + ";encrypt=true;trustServerCertificate=true";

        try {
            DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
            connection = DriverManager.getConnection(URL, username, password);
            System.out.println("Connected with: Database: %s; User: %s; Pass: %s".formatted(databaseName, username, password));
        } catch (SQLException e) {
            System.out.println("Can't connect.");
        }

        return connection;
    }
    
    /* -------------------- LOCAL HOST WAY 2 -------------------- */
//    public static Connection getConnection() {
//        Connection connection = null;
//        /* ------------------------------ CONFIG ------------------------------ */
//        String databaseName = "";
//        String username = "";
//        String password = "";
//        /* -------------------------------------------------------------------- */
//        String URL = "jdbc:sqlserver://localhost:1433;databaseName=" + databaseName + ";encrypt=true;trustServerCertificate=true";
//
//        try {
//            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//            connection = DriverManager.getConnection(URL, username, password);
//            System.out.println("Connected.");
//        } catch (ClassNotFoundException | SQLException e) {
//            System.out.println("Can't connect.");
//            System.out.println("Error: " + e);
//        }
//
//        return connection;
//    }
}
