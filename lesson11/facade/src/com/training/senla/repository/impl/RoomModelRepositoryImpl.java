package com.training.senla.repository.impl;

import com.training.senla.enums.RoomStatus;
import com.training.senla.enums.RoomsSection;
import com.training.senla.model.RoomModel;
import com.training.senla.repository.RoomModelRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.training.senla.util.ParserResultSet.parseRoom;

/**
 * Created by prokop on 13.10.16.
 */
public class RoomModelRepositoryImpl implements RoomModelRepository {

    public RoomModelRepositoryImpl() {
    }

    @Override
    public List<RoomModel> getSortedByPrice(Connection connection) {
        Statement statement = null;
        List<RoomModel> rooms = new ArrayList<>();
        try {
            statement = connection.createStatement();
            ResultSet set = statement.executeQuery("SELECT * FROM room ORDER BY price");
            while (set.next()) {
                rooms.add(parseRoom(statement, set));
            }
            set.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rooms;
    }

    @Override
    public List<RoomModel> getSortedByCapacity(Connection connection) {
        Statement statement = null;
        List<RoomModel> rooms = new ArrayList<>();
        try {
            statement = connection.createStatement();
            ResultSet set = statement.executeQuery("SELECT * FROM room ORDER BY capacity");
            while (set.next()) {
                rooms.add(parseRoom(statement, set));
            }
            set.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rooms;
    }

    @Override
    public List<RoomModel> getSortedByRating(Connection connection) {
        Statement statement = null;
        List<RoomModel> rooms = new ArrayList<>();
        try {
            statement = connection.createStatement();
            ResultSet set = statement.executeQuery("SELECT * FROM room ORDER BY rating");
            while (set.next()) {
                rooms.add(parseRoom(statement, set));
            }
            set.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rooms;
    }

    @Override
    public List<RoomModel> getAll(Connection connection, RoomStatus status) {
        Statement statement = null;
        List<RoomModel> rooms = new ArrayList<>();
        try {
            statement = connection.createStatement();
            StringBuilder builder = new StringBuilder();
            builder.append("SELECT * FROM room WHERE status = ");
            builder.append(status.toString());
            builder.append("ORDER BY id");
            ResultSet set = statement.executeQuery(builder.toString());
            while (set.next()) {
                rooms.add(parseRoom(statement, set));
            }
            set.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rooms;
    }

    @Override
    public List<RoomModel> getSortedByPrice(Connection connection, RoomStatus status) {
        Statement statement = null;
        List<RoomModel> rooms = new ArrayList<>();
        try {
            statement = connection.createStatement();
            StringBuilder builder = new StringBuilder();
            builder.append("SELECT * FROM room WHERE status = ");
            builder.append(status.toString());
            builder.append("ORDER BY price");
            ResultSet set = statement.executeQuery(builder.toString());
            while (set.next()) {
                rooms.add(parseRoom(statement, set));
            }
            set.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rooms;
    }

    @Override
    public List<RoomModel> getSortedByCapacity(Connection connection, RoomStatus status) {
        Statement statement = null;
        List<RoomModel> rooms = new ArrayList<>();
        try {
            statement = connection.createStatement();
            StringBuilder builder = new StringBuilder();
            builder.append("SELECT * FROM room WHERE status = ");
            builder.append(status.toString());
            builder.append("ORDER BY capacity");
            ResultSet set = statement.executeQuery(builder.toString());
            while (set.next()) {
                rooms.add(parseRoom(statement, set));
            }
            set.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rooms;
    }

    @Override
    public List<RoomModel> getSortedByRating(Connection connection, RoomStatus status) {
        Statement statement = null;
        List<RoomModel> rooms = new ArrayList<>();
        try {
            statement = connection.createStatement();
            StringBuilder builder = new StringBuilder();
            builder.append("SELECT * FROM room WHERE status = ");
            builder.append(status.toString());
            builder.append("ORDER BY rating");
            ResultSet set = statement.executeQuery(builder.toString());
            while (set.next()) {
                rooms.add(parseRoom(statement, set));
            }
            set.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rooms;
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
    public List<RoomModel> getLatestGuests(Connection connection, int count) {
        return null;
    }

    @Override
    public List<Double> getPriceBySection(Connection connection, RoomsSection section) {
        return null;
    }

    @Override
    public void update(Connection connection, RoomModel entity) {
        PreparedStatement preparedStatement = null;
        try {
            StringBuilder builder = new StringBuilder();
            builder.append("UPDATE room SET price = ?, capacity = ?, status = ?, section = ?, rating = ? WHERE id = ");
            builder.append(String.valueOf(entity.getId()));
            preparedStatement = connection.prepareStatement(builder.toString());
            preparedStatement.setDouble(1, entity.getPrice());
            preparedStatement.setInt(2, entity.getCapacity());
            preparedStatement.setString(3, entity.getStatus().toString());
            preparedStatement.setString(4, entity.getSection().toString());
            preparedStatement.setInt(5, entity.getRating());
            preparedStatement.executeUpdate();

            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public RoomModel get(Connection connection, int id) {
        Statement statement = null;
        RoomModel room = null;
        try {
            statement = connection.createStatement();
            StringBuilder builder = new StringBuilder();
            builder.append("SELECT * FROM room WHERE id = ");
            builder.append(String.valueOf(id));
            ResultSet set = statement.executeQuery(builder.toString());
            room = parseRoom(statement, set);
            set.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return room;
    }

    @Override
    public void set(Connection connection, RoomModel entity) {
        Statement statement = null;
        try {
            StringBuilder builder = new StringBuilder();
            statement = connection.createStatement();
            builder.append("INSERT room(price, capacity, status, section, rating) VALUES (");
            builder.append(String.valueOf(entity.getPrice()));
            builder.append(String.valueOf(entity.getCapacity()));
            builder.append(String.valueOf(entity.getStatus()));
            builder.append(String.valueOf(entity.getSection()));
            builder.append(String.valueOf(entity.getRating()));
            builder.append(")");
            statement.executeUpdate(builder.toString());

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<RoomModel> getAll(Connection connection) {
        Statement statement = null;
        List<RoomModel> rooms = new ArrayList<>();
        try {
            statement = connection.createStatement();
            ResultSet set = statement.executeQuery("SELECT * FROM room ORDER BY id");
            while (set.next()) {
                rooms.add(parseRoom(statement, set));
            }
            set.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rooms;
    }

    @Override
    public void delete(Connection connection, RoomModel entity) {
        Statement statement = null;
        try {
            statement = connection.createStatement();
            StringBuilder builder = new StringBuilder();
            builder.append("DELETE * FROM room WHERE id = ");
            builder.append(String.valueOf(entity.getId()));
            statement.executeUpdate(builder.toString());
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
