package PropertiesConfig;

import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author Jhin
 */
public class ReadConfig {

    private static Properties p = new Properties();

    private static String url;
    private static String ip;
    private static String port;
    private static String databaseName;
    private static String encrypt;
    private static String trustServerCertificate;
    private static String username;
    private static String password;

    public static void ReadProperties() {
        try {
            p.load(ReadConfig.class.getClassLoader().getResourceAsStream("PropertiesConfig/config.properties"));
        } catch (IOException e) {
            System.out.println("null: " + e);
        }

        url = p.getProperty("URL");
        ip = p.getProperty("IP");
        port = p.getProperty("PORT");
        databaseName = p.getProperty("DATABASENAME");
        encrypt = p.getProperty("ENCRYPT");
        trustServerCertificate = p.getProperty("TRUSTSERVERCERTIFICATE");
        username = p.getProperty("USERNAME");
        password = p.getProperty("PASSWORD");

        boolean show = false;
        showInfo(show);
    }

    private static void showInfo(boolean show) {
        if (show) {
            System.out.println("url: " + url);
            System.out.println("ip: " + ip);
            System.out.println("port: " + port);
            System.out.println("databaseName: " + databaseName);
            System.out.println("encrypt: " + encrypt);
            System.out.println("trustServerCertificate: " + trustServerCertificate);
            System.out.println("username: " + username);
            System.out.println("password: " + password);
        }
    }

    public static String getUrl() {
        return url;
    }

    public static String getIp() {
        return ip;
    }

    public static String getPort() {
        return port;
    }

    public static String getDatabaseName() {
        return databaseName;
    }

    public static String getEncrypt() {
        return encrypt;
    }

    public static String getTrustServerCertificate() {
        return trustServerCertificate;
    }

    public static String getUsername() {
        return username;
    }

    public static String getPassword() {
        return password;
    }

}
