package com.training.senla.dao.impl;

import com.training.senla.dao.RoomDao;
import com.training.senla.enums.RoomStatus;
import com.training.senla.enums.RoomsSection;
import com.training.senla.model.Room;

import java.sql.*;
import java.util.*;
import java.util.Date;

import static com.training.senla.util.ParserResultSet.parseRoom;

/**
 * Created by prokop on 13.10.16.
 */
public class RoomDaoImpl implements RoomDao {

    public RoomDaoImpl() {
    }


    @Override
    public int getCountFreeRooms(Connection connection) {
        Statement statement = null;
        int count = 0;
        try {
            statement = connection.createStatement();
            ResultSet set = statement.executeQuery("SELECT COUNT(id) FROM room WHERE status = 'free'");
            set.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return count;
    }

    @Override
    public List<Room> getLatestGuests(Connection connection, int count) {
        return null;
    }

    @Override
    public List<Room> getReleasedInFuture(Connection connection, Date date) {
        PreparedStatement statement = null;
        List<Room> rooms = new ArrayList<>();
        try {
            statement = connection.prepareStatement("SELECT * FROM room JOIN registration ON room.id = registration.roomId WHERE finalDate < ?");
            statement.setString(1, date.toLocaleString());
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                rooms.add(parseRoom(set));
            }
            set.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rooms;
    }

    @Override
    public List<Double> getPriceBySection(Connection connection, RoomsSection section) {
        return null;
    }


}
