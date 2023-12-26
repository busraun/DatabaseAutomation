import lombok.SneakyThrows;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class DatabaseTestAutomation {

    @SneakyThrows
    public static void main(String[] args) {

        Properties properties = new Properties();

        try (InputStream input = new FileInputStream("configuration.properties")) {
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String url = properties.getProperty("db.url");
        String user = properties.getProperty("db.user");
        String password = properties.getProperty("db.password");

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");


            try (Connection connection = DriverManager.getConnection(url, user, password)) {
                System.out.println("Connected to the database!");


                try (Statement statement = connection.createStatement()) {

                    ResultSet resultSet = statement.executeQuery("SELECT * FROM customers");

                    while (resultSet.next()) {
                        System.out.println(resultSet.getString("customername"));

                    }
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();

        } catch (SQLException e) {
            e.printStackTrace();

        }
    }
}