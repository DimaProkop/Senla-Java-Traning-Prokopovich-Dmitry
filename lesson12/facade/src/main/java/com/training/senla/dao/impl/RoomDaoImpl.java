package com.training.senla.dao.impl;

import com.training.senla.dao.RoomDao;
import com.training.senla.enums.RoomStatus;
import com.training.senla.enums.RoomsSection;
import com.training.senla.enums.SortType;
import com.training.senla.model.Room;
import com.training.senla.util.connection.SessionManager;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

import static com.training.senla.util.db.ParserResultSet.parseRoom;

/**
 * Created by prokop on 13.10.16.
 */
public class RoomDaoImpl extends BaseModelDaoImpl<Room> implements RoomDao {
    private final SimpleDateFormat formatter = new SimpleDateFormat("YYYY-dd-MM");

    public RoomDaoImpl() {

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
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
        }

        return count;
    }

    @Override
    public List<Room> getLatestGuests(Connection connection, int count) {
        PreparedStatement statement = null;
        List<Room> rooms = new ArrayList<>();
        try {
            List<Integer> list = new ArrayList<>();
            statement = connection.prepareStatement("SELECT DISTINCT roomId FROM registration JOIN room ON registration.roomId = room.id ORDER BY id DESC LIMIT ?");
            statement.setInt(1, count);
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                list.add(set.getInt(1));
            }
            for (Integer aList : list) {
                set = statement.executeQuery("SELECT * FROM room WHERE id = ?");
                statement.setInt(1, aList);
                statement.executeQuery();
                rooms.add(parseRoom(set));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
        }
        return rooms;
    }

    @Override
    public List<Room> getReleasedInFuture(Connection connection, Date date) {
        PreparedStatement statement = null;
        List<Room> rooms = new ArrayList<>();
        try {
            String currentDate = formatter.format(date);
            statement = connection.prepareStatement("SELECT * FROM room JOIN registration ON room.id = registration.roomId WHERE finalDate < " + currentDate);
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                rooms.add(parseRoom(set));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
        }

        return rooms;
    }

    @Override
    public List<Double> getPriceBySection(Connection connection, RoomsSection section) {
        PreparedStatement statement = null;
        List<Double> prices = new ArrayList<>();
        try {
            statement = connection.prepareStatement("SELECT price FROM room ORDER BY ?");
            statement.setString(1, section.toString());
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                prices.add(set.getDouble(1));
            }
            set.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
        }

        return prices;
    }

    @Override
    protected Class assignClass() throws SQLException {
        return Room.class;
    }
}
