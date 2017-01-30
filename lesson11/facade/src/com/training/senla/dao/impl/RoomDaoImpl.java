package com.training.senla.dao.impl;

import com.training.senla.dao.RoomDao;
import com.training.senla.enums.RoomStatus;
import com.training.senla.enums.RoomsSection;
import com.training.senla.enums.SortType;
import com.training.senla.model.Room;
import com.training.senla.util.connection.ConnectionManager;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

import static com.training.senla.util.db.ParserResultSet.parseRoom;

/**
 * Created by prokop on 13.10.16.
 */
public class RoomDaoImpl extends BaseModelDaoImpl<Room> implements RoomDao {

    private final String UPDATE_ROOM = "UPDATE room SET price = ?, capacity = ?, status = ?, section = ?, rating = ? WHERE id = ?";
    private final String SET_ROOM = "INSERT room(price, capacity, status, section, rating) VALUES (?,?,?,?,?) ";
    private final String GET_ROOM = "SELECT * FROM room WHERE id = ?";
    private final String DELETE_ROOM = "DELETE * FROM room WHERE id = ?";
    private final String GET_SORT_ROOM_FREE = "SELECT * FROM room WHERE status = 'free' ORDER BY ";
    private final String GET_SORT_ROOM = "SELECT * FROM room ORDER BY ";
    private final SimpleDateFormat formatter = new SimpleDateFormat("YYYY-dd-MM");

    public RoomDaoImpl() {

    }

    @Override
    public Room assignParser(ResultSet set) {
        return parseRoom(set);
    }

    @Override
    protected String getInsertQuery() {
        return SET_ROOM;
    }

    @Override
    protected String getUpdateQuery() {
        return UPDATE_ROOM;
    }

    @Override
    protected String getDeleteQuery() {
        return DELETE_ROOM;
    }

    @Override
    protected String getGetByIdQuery() {
        return GET_ROOM;
    }

    @Override
    protected String getGetAllQuery(SortType type, RoomStatus status) {
        if(status != null) {
            return GET_SORT_ROOM_FREE  + type.toString();
        }else {
            return GET_SORT_ROOM  + type.toString();
        }
    }

    @Override
    protected void setPreparedStatementForInsert(PreparedStatement statement, Room entity) throws SQLException {
        statement.setDouble(1, entity.getPrice());
        statement.setInt(2, entity.getCapacity());
        statement.setString(3, entity.getStatus().toString());
        statement.setString(4, entity.getSection().toString());
        statement.setInt(5, entity.getRating());
    }

    @Override
    protected void setPreparedStatementForUpdate(PreparedStatement statement, Room entity) throws SQLException {
        statement.setDouble(1, entity.getPrice());
        statement.setInt(2, entity.getCapacity());
        statement.setString(3, entity.getStatus().toString());
        statement.setString(4, entity.getSection().toString());
        statement.setInt(5, entity.getRating());
        statement.setInt(6, entity.getId());
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
            ConnectionManager.getInstance().closeStatement(statement);
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
            ConnectionManager.getInstance().closeStatement(statement);
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
            ConnectionManager.getInstance().closeStatement(statement);
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
            ConnectionManager.getInstance().closeStatement(statement);
        }

        return prices;
    }

}
