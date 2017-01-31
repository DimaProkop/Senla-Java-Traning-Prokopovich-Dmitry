package com.training.senla.util.connection;

import com.training.senla.ClassSetting;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

/**
 * Created by dmitry on 22.1.17.
 */
public class SessionManager {

    private static final Logger LOG = LogManager.getLogger(SessionManager.class);


    private static SessionManager sessionManager;
    private static Connection connection;
    private static String URL = ClassSetting.getProps().getUrlToDB();
    private static String DRIVER = ClassSetting.getProps().getPathToDriverJDBC();
    private static String USERNAME = ClassSetting.getProps().getUsernameToDB();
    private static String PASSWORD = ClassSetting.getProps().getPasswordToDB();


    public static SessionManager getInstance() {
        if (sessionManager == null) {
            sessionManager = new SessionManager();
        }
        return sessionManager;
    }

    public Connection getConnection() {
        if(connection != null) {
            return connection;
        }else {
            try {
                Class.forName(DRIVER);
                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            } catch (ClassNotFoundException | SQLException e) {
                LOG.error(e.getMessage());
            }
        }
        return connection;
    }

    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                LOG.error(e.getMessage());
            }
        }
    }

    public void closeStatement(Statement statement) {
        try {
            statement.close();
        } catch (SQLException e) {
            LOG.error(e.getMessage());
        }
    }
}
