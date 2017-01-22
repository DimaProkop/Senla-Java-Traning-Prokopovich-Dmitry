package com.training.senla.util.connection;

import com.training.senla.ClassSetting;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by dmitry on 22.1.17.
 */
public class ConnectionManager {

    private static final Logger LOG = LogManager.getLogger(ConnectionManager.class);


    private static Connection connection;
    private static String URL = ClassSetting.getProps().getUrlToDB();
    private static String DRIVER = ClassSetting.getProps().getPathToDriverJDBC();
    private static String USERNAME = ClassSetting.getProps().getUsernameToDB();
    private static String PASSWORD = ClassSetting.getProps().getPasswordToDB();

    public static Connection getConnection() {
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            LOG.error(e.getMessage());
        }
        return connection;
    }

    public static void closeConnection(Connection connection) {
        if(connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                LOG.error(e.getMessage());
            }
        }
    }
}
