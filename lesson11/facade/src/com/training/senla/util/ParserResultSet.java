package com.training.senla.util;

import com.training.senla.enums.RoomStatus;
import com.training.senla.enums.RoomsSection;
import com.training.senla.enums.ServicesSection;
import com.training.senla.model.GuestModel;
import com.training.senla.model.RoomModel;
import com.training.senla.model.ServiceModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by dmitry on 23.1.17.
 */
public class ParserResultSet {

    public static GuestModel parseGuest(Statement statement, ResultSet set) {
        GuestModel guest = null;
        try {
            guest.setId(set.getInt(1));
            guest.setName(set.getString(2));
            if(set.getInt(3) != 0) {
                StringBuilder builder = new StringBuilder();
                builder.append("SELECT * FROM room WHERE id = ");
                builder.append(String.valueOf(set.getInt(3)));
                ResultSet setRoom = statement.executeQuery(builder.toString());
                RoomModel room = new RoomModel();
                room.setId(setRoom.getInt(1));
                room.setPrice(setRoom.getDouble(2));
                room.setCapacity(setRoom.getInt(3));
                room.setStatus(RoomStatus.isExist(setRoom.getString(4)));
                room.setSection(RoomsSection.isExist(setRoom.getString(5)));
                room.setRating(setRoom.getInt(6));
                guest.setRoomModel(room);
            }else {
                guest.setRoomModel(null);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return guest;
    }

    public static ServiceModel parseService(Statement statement, ResultSet set) {
        ServiceModel service = null;
        try {
            service.setId(set.getInt(1));
            service.setName(set.getString(2));
            service.setPrice(set.getDouble(3));
            service.setSection(ServicesSection.isExist(set.getString(4)));
            service.setStartDate(set.getDate(5));
            service.setFinalDate(set.getDate(6));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return service;
    }

    public static RoomModel parseRoom(Statement statement, ResultSet set) {
        RoomModel room = null;
        try {
            room.setId(set.getInt(1));
            room.setPrice(set.getDouble(2));
            room.setCapacity(set.getInt(3));
            room.setStatus(RoomStatus.isExist(set.getString(4)));
            room.setSection(RoomsSection.isExist(set.getString(5)));
            room.setRating(set.getInt(6));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return room;
    }
}
