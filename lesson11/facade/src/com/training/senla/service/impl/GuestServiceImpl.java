package com.training.senla.service.impl;

import com.training.senla.dao.impl.GuestDaoImpl;
import com.training.senla.model.Guest;
import com.training.senla.model.Room;
import com.training.senla.model.Service;
import com.training.senla.dao.GuestDao;
import com.training.senla.service.GuestService;
import com.training.senla.util.connection.ConnectionManager;
import com.training.senla.util.db.LibraryQueries;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by prokop on 13.10.16.
 */
public class GuestServiceImpl implements GuestService {

    private static final Logger LOG = LogManager.getLogger(GuestServiceImpl.class);

    private GuestDaoImpl guestDao;
    private LibraryQueries library;

    public GuestServiceImpl() {
        guestDao = new GuestDaoImpl();
    }

    @Override
    public void addGuest(Guest guest) {
        Connection connection = ConnectionManager.getInstance().getConnection();
        try {
            PreparedStatement statement = library.set(connection, guest);
            guestDao.get(statement);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }

    @Override
    public Guest getGuest(int id) {
        Guest guest = null;
        Connection connection = ConnectionManager.getInstance().getConnection();
        try {
            PreparedStatement statement = library.get(library.GET_GUEST, connection, guest.getId());
            guest = guestDao.get(statement);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return guest;
    }

    @Override
    public void update(Guest guest) {
        Connection connection = ConnectionManager.getInstance().getConnection();
        boolean status = false;
        try {
            connection.setAutoCommit(false);
            PreparedStatement statement = library.update(connection, guest);
            status = guestDao.update(statement);
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
        Connection connection = ConnectionManager.getInstance().getConnection();
        try {
            PreparedStatement statement = library.delete(connection, guest);
            guestDao.delete(statement);
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
    public List<Service> getServicesByPrice(Guest guest) {
        List<Service> services = null;
        Connection connection = ConnectionManager.getInstance().getConnection();
        try {
            services = guestDao.getServicesByPrice(connection, guest);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return services;
    }

    @Override
    public List<Service> getServicesByDate(Guest guest) {
        List<Service> services = null;
        Connection connection = ConnectionManager.getInstance().getConnection();
        try {
            services = guestDao.getServicesByDate(connection, guest);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return services;
    }

    @Override
    public List<Guest> getAll() {
        Connection connection = ConnectionManager.getInstance().getConnection();
        List<Guest> guests = null;
        try {
            guests = guestDao.getAll(connection);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return guests;
    }

    @Override
    public List<Guest> getSortedByFinalDate() {
        Connection connection = ConnectionManager.getInstance().getConnection();
        List<Guest> guests = null;
        try {
            guests = guestDao.getSortedByFinalDate(connection);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return guests;
    }

    @Override
    public List<Guest> getSortedByName() {
        List<Guest> guests = null;
        Connection connection = ConnectionManager.getInstance().getConnection();
        try {
            guests = guestDao.getSortedByName(connection);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return guests;
    }

    @Override
    public double getSumByRoom(Room room, Guest guest) {
        double sum = 0;
        Connection connection = ConnectionManager.getInstance().getConnection();
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
        Connection connection = ConnectionManager.getInstance().getConnection();
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
