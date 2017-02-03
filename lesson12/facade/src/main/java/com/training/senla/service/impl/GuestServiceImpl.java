package com.training.senla.service.impl;

import com.training.senla.enums.SortType;
import com.training.senla.model.Guest;
import com.training.senla.model.Registration;
import com.training.senla.model.Room;
import com.training.senla.model.Service;
import com.training.senla.dao.GuestDao;
import com.training.senla.service.GuestService;
import com.training.senla.util.connection.SessionManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.ZoneId;
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
            if (transaction != null) {
                transaction.rollback();
            }
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
    public List<Guest> getAll(SortType type) {
        Session session = SessionManager.getInstance().getSession();
        List<Guest> guests = null;
        try {
            guests = guestDao.getAll(session, type, null);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }finally {
            SessionManager.getInstance().closeSession(session);
        }
        return guests;
    }

    @Override
    public int getCount() {
        Session session = SessionManager.getInstance().getSession();
        int count = 0;
        try {
            count = guestDao.getCount(session);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }finally {
            SessionManager.getInstance().closeSession(session);
        }
        return count;
    }

    public void setGuestDao(GuestDao guestDao) {
        this.guestDao = guestDao;
    }
}
