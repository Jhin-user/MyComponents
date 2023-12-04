package Main;

import ExcelIO.ExcelIE;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author Jhin
 */
public class MainRun {

    public static void main(String[] args) throws IOException {
        int activity = 1;
        switch (activity) {
            case 0 ->
                ConnectDB();
            case 1 ->
                Excel();
            default ->
                throw new AssertionError();
        }
    }

    private static void ConnectDB() {
        /* -------------------- Config the info in MainRun first -------------------- */
        try {
            Connection connection = DBConnection.ConnectDatabase.GetConnection();
            connection.close();
        } catch (SQLException e) {
        }
    }

    private static void Excel() {
        if (ExcelIE.Export()) {
            System.out.println("Exported");
        } else {
            System.out.println("Unexported");
        }
    }
}
