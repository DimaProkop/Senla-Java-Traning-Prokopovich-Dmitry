package com.training.senla.service.impl;

import com.training.senla.enums.SortType;
import com.training.senla.model.Guest;
import com.training.senla.model.Room;
import com.training.senla.model.Service;
import com.training.senla.dao.GuestDao;
import com.training.senla.service.GuestService;
import com.training.senla.util.connection.SessionManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by prokop on 13.10.16.
 */
public class GuestServiceImpl implements GuestService {

    private static final Logger LOG = LogManager.getLogger(GuestServiceImpl.class);

    private GuestDao guestDao;

    public GuestServiceImpl() {
    }

    @Override
    public void addGuest(Guest guest) {
        Connection connection = SessionManager.getInstance().getConnection();
        try {
            guestDao.add(connection, guest);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }

    @Override
    public Guest getGuest(int id) {
        Guest guest = null;
        Connection connection = SessionManager.getInstance().getConnection();
        try {
            guest = guestDao.getById(connection, id);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return guest;
    }

    @Override
    public void update(Guest guest) {
        Connection connection = SessionManager.getInstance().getConnection();
        boolean status = false;
        try {
            connection.setAutoCommit(false);
            status = guestDao.update(connection ,guest);
            if (status) {
                connection.commit();
            }
        } catch (Exception e) {
            try {
                connection.rollback();
            } catch (SQLException sql) {
                sql.printStackTrace();
            }
            LOG.error(e.getMessage());
        }
    }

    @Override
    public void delete(Guest guest) {
        Connection connection = SessionManager.getInstance().getConnection();
        try {
            guestDao.delete(connection, guest);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }

    @Override
    public void addService(Guest guest, Service service) {
    }

    @Override
    public void removeService(Guest guest, Service service) {
    }

    @Override
    public List<Service> getServices(Guest guest, SortType type) {
        List<Service> services = null;
        Connection connection = SessionManager.getInstance().getConnection();
        try {
            services = guestDao.getServices(connection, guest, type);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return services;
    }

    @Override
    public List<Guest> getAll(SortType type) {
        Connection connection = SessionManager.getInstance().getConnection();
        List<Guest> guests = null;
        try {
            guests = guestDao.getAll(connection, type, null);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return guests;
    }

    @Override
    public double getSumByRoom(Room room, Guest guest) {
        double sum = 0;
        Connection connection = SessionManager.getInstance().getConnection();
        try {
            sum = guestDao.getSumByRoom(connection, room, guest);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return sum;
    }

    @Override
    public int getCount() {
        int count = 0;
        Connection connection = SessionManager.getInstance().getConnection();
        try {
            count = guestDao.getCount(connection);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return count;
    }

    public void setGuestDao(GuestDao guestDao) {
        this.guestDao = guestDao;
    }
}
