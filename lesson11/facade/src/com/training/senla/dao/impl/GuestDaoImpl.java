package com.training.senla.dao.impl;

import com.training.senla.enums.RoomStatus;
import com.training.senla.enums.SortType;
import com.training.senla.model.Guest;
import com.training.senla.model.Room;
import com.training.senla.model.Service;
import com.training.senla.dao.GuestDao;
import com.training.senla.util.connection.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.training.senla.util.db.ParserResultSet.parseGuest;
import static com.training.senla.util.db.ParserResultSet.parseService;


/**
 * Created by prokop on 13.10.16.
 */
public class GuestDaoImpl extends BaseModelDaoImpl<Guest> implements GuestDao{

    private final String UPDATE_GUEST = "UPDATE guest SET name = ?, roomId = ? WHERE id = ?";
    private final String SET_GUEST = "INSERT guest(name) VALUES (?) ";
    private final String GET_GUEST = "SELECT * FROM guest WHERE id = ?";
    private final String DELETE_GUEST = "DELETE * FROM guest WHERE id = ?";
    private final String GET_SORT_GUEST = "SELECT * FROM guest ORDER BY ";

    public GuestDaoImpl() {
    }

    @Override
    public Guest assignParser(ResultSet set) {
        return parseGuest(set);
    }

    @Override
    protected String getInsertQuery() {
        return SET_GUEST;
    }

    @Override
    protected String getUpdateQuery() {
        return UPDATE_GUEST;
    }

    @Override
    protected String getDeleteQuery() {
        return DELETE_GUEST;
    }

    @Override
    protected String getGetByIdQuery() {
        return GET_GUEST;
    }

    @Override
    protected String getGetAllQuery(SortType type, RoomStatus status) {
        return GET_SORT_GUEST + type.toString();
    }

    @Override
    protected void setPreparedStatementForInsert(PreparedStatement statement, Guest entity) throws SQLException {
        statement.setString(1, entity.getName());
    }

    @Override
    protected void setPreparedStatementForUpdate(PreparedStatement statement, Guest entity) throws SQLException {
            statement.setString(1, entity.getName());
            if(entity.getRoom() == null) {
                statement.setString(2, null);
            }else {
                statement.setInt(2, entity.getRoom().getId());
            }
            statement.setInt(3, entity.getId());
    }

    @Override
    public List<Service> getServices(Connection connection, Guest guest, SortType type) {
        PreparedStatement statement = null;
        List<Service> services = new ArrayList<>();
        try {
            statement = connection.prepareStatement("SELECT * FROM service WHERE guestId = ? ORDER BY ?");
            statement.setInt(1, guest.getId());
            statement.setString(2, type.toString());
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                services.add(parseService(set));
            }
            set.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            ConnectionManager.getInstance().closeStatement(statement);
        }

        return services;
    }


    @Override
    public List<Guest> getSortedByFinalDate(Connection connection) {
        Statement statement = null;
        List<Guest> guests = new ArrayList<>();
        try {
            statement = connection.createStatement();
            ResultSet set = statement.executeQuery("SELECT * FROM guest JOIN registration ON guest.id = registration.guestId ORDER BY registration.finalDate ");
            while (set.next()) {
                guests.add(parseGuest(set));
            }
            set.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            ConnectionManager.getInstance().closeStatement(statement);
        }

        return guests;
    }

    @SuppressWarnings("deprecation")
    @Override
    public double getSumByRoom(Connection connection, Room room, Guest guest) {
        PreparedStatement statement = null;
        double sum = 0;
        try {
            statement = connection.prepareStatement("SELECT startDate, finalDate FROM registration WHERE guestId = ? AND roomId = ?");
            statement.setInt(1, guest.getId());
            statement.setInt(2, room.getId());
            ResultSet set = statement.executeQuery();
            int count = 0;
            while (set.next()) {
                count = set.getDate(2).toLocalDate().getDayOfYear() - set.getDate(1).toLocalDate().getDayOfYear();
            }
            sum = count * room.getPrice();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            ConnectionManager.getInstance().closeStatement(statement);
        }
        return sum;
    }

    @Override
    public int getCount(Connection connection) {
        Statement statement = null;
        int count = 0;
        try {
            statement = connection.createStatement();
            ResultSet set = statement.executeQuery("SELECT COUNT(id) FROM guest");
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
}
