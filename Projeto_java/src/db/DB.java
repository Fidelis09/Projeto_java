package db;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.util.Properties;

public class DB {

    private static Connection conn = null;

    public static Connection getConnection() {
        if (conn == null) {
            try {
                Properties props = loadProperties();
                String url = props.getProperty("dburl");
                conn = java.sql.DriverManager.getConnection(url, props);
            } catch (Exception e) {
                throw new DbExeption("Error connecting to database: " + e.getMessage(), e);
            }
        }
        return conn;
    }

    public static void closeConnection() {
        if (conn != null) {
            try {
                conn.close();
            } catch (Exception e) {
                throw new DbExeption("Error closing connection: " + e.getMessage(), e);
            }
        }
    }

    private static Properties loadProperties() {
        try (InputStream fs = Files.newInputStream(Paths.get("db.properties"))) {
            Properties props = new Properties();
            props.load(fs);
            return props;
        } catch (IOException e) {
            throw new DbExeption("Error loading properties file: " + e.getMessage(), e);
        }
    }
}
