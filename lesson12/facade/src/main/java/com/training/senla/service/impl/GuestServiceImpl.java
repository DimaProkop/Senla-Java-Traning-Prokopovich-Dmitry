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
import org.hibernate.Session;
import org.hibernate.Transaction;

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
        Session session = SessionManager.getInstance().getSession();
        try {
            guestDao.add(session, guest);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }finally {
            SessionManager.getInstance().closeSession(session);
        }
    }

    @Override
    public Guest getGuest(int id) {
        Session session = SessionManager.getInstance().getSession();
        Guest guest = null;
        try {
            guest = guestDao.getById(session, id);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }finally {
            SessionManager.getInstance().closeSession(session);
        }
        return guest;
    }

    @Override
    public void update(Guest guest) {
        Session session = SessionManager.getInstance().getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            guestDao.update(session, guest);
            transaction.commit();
        }catch (Exception e) {
            LOG.error(e.getMessage());
        }finally {
            SessionManager.getInstance().closeSession(session);
        }
    }

    @Override
    public void delete(Guest guest) {
        Session session = SessionManager.getInstance().getSession();
        try {
            guestDao.delete(session, guest);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }finally {
            SessionManager.getInstance().closeSession(session);
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
        try {
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return services;
    }

    @Override
    public List<Guest> getAll(SortType type) {
        Session session = SessionManager.getInstance().getSession();
        List<Guest> guests = null;
        try {
            guests = guestDao.getAll(session, SortType.id, null);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }finally {
            SessionManager.getInstance().closeSession(session);
        }
        return guests;
    }

    @Override
    public double getSumByRoom(Room room, Guest guest) {
        double sum = 0;
        try {
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return sum;
    }

    @Override
    public int getCount() {
        int count = 0;
        try {
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return count;
    }

    public void setGuestDao(GuestDao guestDao) {
        this.guestDao = guestDao;
    }
}
