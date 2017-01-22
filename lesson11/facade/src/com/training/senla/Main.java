package com.training.senla;

import com.training.senla.di.DependencyInjection;
import com.training.senla.facade.Facade;
import com.training.senla.model.GuestModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by prokop on 12.10.16.
 */


public class Main {

    public static Connection connection;
    public static String username = "root";
    public static String password = "root";
    public static String URL = "jdbc:mysql://localhost:3306/hotel";

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");


        connection = DriverManager.getConnection(URL, username, password);
        Statement statement = connection.createStatement();

        GuestModel guestModel = new GuestModel("testMethod");
        setGuest(guestModel, statement);


        ResultSet set = statement.executeQuery("SELECT * FROM guest WHERE id = "+2+"");
        int countColumn = set.getMetaData().getColumnCount();

        while (set.next()) {
            System.out.println(set.getInt(1));
            System.out.println(set.getString(2));
            System.out.println(set.getInt(3));
        }

        int countRow = 0;
        while (set.next()) {
            countRow++;
        }
    }

    private static void setGuest(GuestModel guest, Statement statement) throws SQLException {
        String room = "null";
        if(guest.getRoomModel() != null) {
            room = String.valueOf(guest.getRoomModel().getId());
        }
        statement.executeUpdate("UPDATE guest SET name = 'new Name' WHERE id = 4");
    }
}
