package com.training.senla;

import com.training.senla.di.DependencyInjection;
import com.training.senla.enums.RoomsSection;
import com.training.senla.facade.Facade;
import com.training.senla.model.GuestModel;
import com.training.senla.model.RoomModel;

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
        Facade facade = (Facade) DependencyInjection.getInstance(Facade.class);
        facade.init();

        facade.addRoom(new RoomModel(200, 4, RoomsSection.SINGLE, 9));


//        Class.forName("com.mysql.cj.jdbc.Driver");
//
//
//        connection = DriverManager.getConnection(URL, username, password);
//        Statement statement = connection.createStatement();
//
//        GuestModel guestModel = new GuestModel("testMethod");
//        setGuest(guestModel, statement);
//        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE guest SET name = ?  WHERE id = '4'");
//        preparedStatement.setString(1, "sdsds");
//        preparedStatement.executeUpdate();
//
//        ResultSet set = statement.executeQuery("SELECT price FROM service ORDER BY section");
//        int countColumn = set.getMetaData().getColumnCount();
//
//        while (set.next()) {
//            System.out.println(set.getInt(1));
//        }
//
//        int countRow = 0;
//        while (set.next()) {
//            countRow++;
//        }
    }

    private static void setGuest(GuestModel guest, Statement statement) throws SQLException {
        String room = "null";
        if(guest.getRoomModel() != null) {
            room = String.valueOf(guest.getRoomModel().getId());
        }
        statement.executeUpdate("UPDATE guest SET name = 'new Name' WHERE id = 4");
    }
}
