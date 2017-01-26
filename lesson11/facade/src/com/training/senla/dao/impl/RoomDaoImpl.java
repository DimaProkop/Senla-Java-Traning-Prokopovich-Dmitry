package com.training.senla.dao.impl;

import com.training.senla.dao.RoomDao;
import com.training.senla.enums.RoomsSection;
import com.training.senla.model.Room;
import com.training.senla.util.db.LibraryQueries;

import java.sql.*;
import java.util.*;
import java.util.Date;

import static com.training.senla.util.db.ParserResultSet.parseRoom;

/**
 * Created by prokop on 13.10.16.
 */
public class RoomDaoImpl extends BaseModelDaoImpl<Room> implements RoomDao {

    private LibraryQueries library;
    public RoomDaoImpl() {
        library = new LibraryQueries();
    }

    @Override
    public Room assignParser(ResultSet set) {
        return parseRoom(set);
    }

    @Override
    public int getCountFreeRooms(Connection connection) {
        Statement statement = null;
        int count = 0;
        try {
            statement = connection.createStatement();
            ResultSet set = statement.executeQuery("SELECT COUNT(*) FROM room WHERE status = 'free'");
            while (set.next()) {
                count = set.getInt(1);
            }
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
            String currentDate = library.formatter.format(date);
            statement = connection.prepareStatement("SELECT * FROM room JOIN registration ON room.id = registration.roomId WHERE finalDate < " + currentDate);
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                rooms.add(parseRoom(set));
            }
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
