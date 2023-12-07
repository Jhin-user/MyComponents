package PropertiesConfig;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author Jhin
 */
public class ReadProperties {

    private static String url;
    private static String database;
    private static String username;
    private static String password;

    public static Properties readProperties() {
        Properties properties = new Properties();

        try {
            properties.load(new FileInputStream("config.properties"));
            url = properties.getProperty("url");
            database = properties.getProperty("database");
            username = properties.getProperty("username");
            password = properties.getProperty("password");
        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {
        }

        return properties;
    }

    public static String getUrl() {
        return url;
    }

    public static String getDatabase() {
        return database;
    }

    public static String getUsername() {
        return username;
    }

    public static String getPassword() {
        return password;
    }

}
