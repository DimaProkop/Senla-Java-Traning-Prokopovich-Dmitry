package com.training.senla.service.impl;

import com.training.senla.model.Guest;
import com.training.senla.model.Room;
import com.training.senla.model.Service;
import com.training.senla.dao.GuestDao;
import com.training.senla.service.GuestService;
import com.training.senla.util.connection.ConnectionManager;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by prokop on 13.10.16.
 */
public class GuestServiceImpl implements GuestService {

    private static final Logger LOG = LogManager.getLogger(GuestServiceImpl.class);

    private GuestDao guestRepository;

    public GuestServiceImpl() {
    }

    @Override
    public void addGuest(Guest guest) {
        Connection connection = null;
        try {
            connection = ConnectionManager.getInstance().getConnection();
            guestRepository.set(connection, guest);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }

    @Override
    public Guest getGuest(int id) {
        Guest guest = null;
        Connection connection = null;
        try {
            connection = ConnectionManager.getInstance().getConnection();
            guest = guestRepository.get(connection, id);
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
            status = guestRepository.update(connection, guest);
            if(status) {
                connection.commit();
            }
        } catch (Exception e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            LOG.error(e.getMessage());
        }
    }

    @Override
    public void delete(Guest guest) {
        Connection connection = ConnectionManager.getInstance().getConnection();
        try {
            guestRepository.delete(connection, guest);
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
            services = guestRepository.getServicesByPrice(connection, guest);
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
            services = guestRepository.getServicesByDate(connection, guest);
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
            guests = guestRepository.getAll(connection);
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
            guests = guestRepository.getSortedByFinalDate(connection);
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
            guests = guestRepository.getSortedByName(connection);
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
            sum = guestRepository.getSumByRoom(connection, room, guest);
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
            count = guestRepository.getCount(connection);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return count;
    }

    public void setGuestRepository(GuestDao guestRepository) {
        this.guestRepository = guestRepository;
    }
}
