package com.training.senla.util.db;

import com.training.senla.enums.SortType;
import com.training.senla.model.Guest;
import com.training.senla.model.Registration;
import com.training.senla.model.Room;
import com.training.senla.model.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by dmitry on 26.1.17.
 */
public class LibraryQueries {

    //update
    private final String UPDATE_GUEST = "UPDATE guest SET name = ?, roomId = ? WHERE id = ?";
    private final String UPDATE_ROOM = "UPDATE room SET price = ?, capacity = ?, status = ?, section = ?, rating = ? WHERE id = ?";
    private final String UPDATE_SERVICE = "UPDATE guest SET name = ?, price = ?, section = ?, startDate = ?, finalDate = ? WHERE id = ?";
    private final String UPDATE_REGISTRATION = "UPDATE guest SET guestId = ?, roomId = ?, startDate = ?, finalDate = ? WHERE id = ?";
    //insert
    private final String SET_GUEST = "INSERT guest(name) VALUES (?) ";
    private final String SET_ROOM = "INSERT room(price, capacity, status, section, rating) VALUES (?,?,?,?,?) ";
    private final String SET_SERVICE = "INSERT service(name, price, section, startDate, finalDate) VALUES (?,?,?,?,?) ";
    private final String SET_REGISTRATION = "INSERT registration(guestId, roomId, startDate, finalDate) VALUES (?,?,?,?) ";
    //get
    public final String GET_GUEST = "SELECT * FROM guest WHERE id = ?";
    public final String GET_ROOM = "SELECT * FROM room WHERE id = ?";
    public final String GET_SERVICE = "SELECT * FROM service WHERE id = ?";
    public final String GET_REGISTRATION = "SELECT * FROM registration WHERE id = ?";
    //delete
    private final String DELETE_GUEST = "DELETE * FROM guest WHERE id = ?";
    private final String DELETE_ROOM = "DELETE * FROM room WHERE id = ?";
    private final String DELETE_SERVICE = "DELETE * FROM service WHERE id = ?";
    private final String DELETE_REGISTRATION = "DELETE * FROM registration WHERE id = ?";
    //get by sort
    public final String GET_SORT_GUEST = "SELECT * FROM guest ORDER BY ?";
    public final String GET_SORT_ROOM = "SELECT * FROM room ORDER BY ?";
    public final String GET_SORT_SERVICE = "SELECT * FROM service ORDER BY ?";

    public PreparedStatement update(Connection connection, Guest guest) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(UPDATE_GUEST);
            statement.setString(1, guest.getName());
            statement.setInt(2, guest.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statement;
    }

    public PreparedStatement update(Connection connection, Room room) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(UPDATE_ROOM);
            statement.setDouble(1, room.getPrice());
            statement.setInt(2, room.getCapacity());
            statement.setString(3, room.getStatus().toString());
            statement.setString(4, room.getSection().toString());
            statement.setInt(5, room.getRating());
            statement.setInt(6, room.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statement;
    }

    public PreparedStatement update(Connection connection, Service service) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(UPDATE_SERVICE);
            statement.setString(1, service.getName());
            statement.setDouble(2, service.getPrice());
            statement.setString(3, service.getSection().toString());
            statement.setString(4, service.getStartDate().toString());
            statement.setString(5, service.getFinalDate().toString());
            statement.setInt(6, service.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statement;
    }

    public PreparedStatement update(Connection connection, Registration registration) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(UPDATE_REGISTRATION);
            statement.setInt(1, registration.getGuestId());
            statement.setInt(2, registration.getRoomId());
            statement.setString(3, registration.getStartDate().toString());
            statement.setString(4, registration.getFinalDate().toString());
            statement.setInt(5, registration.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statement;
    }

    public PreparedStatement set(Connection connection, Guest guest) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SET_GUEST);
            statement.setString(1, guest.getName());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statement;
    }

    public PreparedStatement set(Connection connection, Room room) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SET_ROOM);
            statement.setDouble(1, room.getPrice());
            statement.setInt(2, room.getCapacity());
            statement.setString(3, room.getStatus().toString());
            statement.setString(4, room.getSection().toString());
            statement.setInt(5, room.getRating());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statement;
    }

    public PreparedStatement set(Connection connection, Service service) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SET_SERVICE);
            statement.setString(1, service.getName());
            statement.setDouble(2, service.getPrice());
            statement.setString(3, service.getSection().toString());
            statement.setString(4, service.getStartDate().toString());
            statement.setString(5, service.getFinalDate().toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statement;
    }

    public PreparedStatement set(Connection connection, Registration registration) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SET_REGISTRATION);
            statement.setInt(1, registration.getGuestId());
            statement.setInt(2, registration.getRoomId());
            statement.setString(3, registration.getStartDate().toString());
            statement.setString(4, registration.getFinalDate().toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statement;
    }

    public PreparedStatement delete(Connection connection, Guest guest) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(DELETE_GUEST);
            statement.setInt(1, guest.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statement;
    }

    public PreparedStatement delete(Connection connection, Room room) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(DELETE_ROOM);
            statement.setInt(1, room.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statement;
    }

    public PreparedStatement delete(Connection connection, Registration registration) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(DELETE_REGISTRATION);
            statement.setInt(1, registration.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statement;
    }

    public PreparedStatement delete(Connection connection, Service service) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(DELETE_SERVICE);
            statement.setInt(1, service.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statement;
    }

    public PreparedStatement get(String query, Connection connection, Integer id) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statement;
    }

    public PreparedStatement getAll(String query, Connection connection, SortType type) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(query);
            statement.setString(1, type.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statement;
    }




}
