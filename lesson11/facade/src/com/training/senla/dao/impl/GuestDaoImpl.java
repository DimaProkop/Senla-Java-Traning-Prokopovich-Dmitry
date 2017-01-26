package com.training.senla.dao.impl;

import com.training.senla.model.Guest;
import com.training.senla.model.Room;
import com.training.senla.model.Service;
import com.training.senla.dao.GuestDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.training.senla.util.db.ParserResultSet.parseGuest;
import static com.training.senla.util.db.ParserResultSet.parseService;


/**
 * Created by prokop on 13.10.16.
 */
public class GuestDaoImpl extends BaseModelDaoImpl<Guest> implements GuestDao{

    public GuestDaoImpl() {
    }

    @Override
    public Guest assignParser(ResultSet set) {
        return parseGuest(set);
    }

    @Override
    public List<Service> getServicesByPrice(Connection connection, Guest guest) {
        PreparedStatement statement = null;
        List<Service> services = new ArrayList<>();
        try {
            statement = connection.prepareStatement("SELECT * FROM service WHERE guestId = ? ORDER BY price");
            statement.setInt(1, guest.getId());
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                services.add(parseService(set));
            }
            set.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return services;
    }

    @Override
    public List<Service> getServicesByDate(Connection connection, Guest guest) {
        PreparedStatement statement = null;
        List<Service> services = new ArrayList<>();
        try {
            statement = connection.prepareStatement("SELECT * FROM service WHERE guestId = ? ORDER BY finalDate");
            statement.setInt(1, guest.getId());
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                services.add(parseService(set));
            }
            set.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
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
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
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
            }        } catch (SQLException e) {
            e.printStackTrace();
        }

        return count;
    }
}
