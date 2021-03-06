package com.training.senla.service.impl;

import com.training.senla.enums.SortType;
import com.training.senla.model.Guest;
import com.training.senla.model.Registration;
import com.training.senla.dao.RegistrationDao;
import com.training.senla.model.Room;
import com.training.senla.service.RegistrationService;
import com.training.senla.util.connection.SessionManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by prokop on 16.10.16.
 */
public class RegistrationServiceImpl implements RegistrationService {

    private static final Logger LOG = LogManager.getLogger(RegistrationServiceImpl.class);

    private RegistrationDao registrationDao;

    public RegistrationServiceImpl() {
    }

    @Override
    public void addRecord(Registration registration) {
        Session session = SessionManager.getInstance().getSession();
        try {
            registrationDao.add(session, registration);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }finally {
            SessionManager.getInstance().closeSession(session);
        }

    }

    @Override
    public void update(Registration registration) {
        Session session = SessionManager.getInstance().getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            registrationDao.update(session, registration);
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
    public double getSumByRoom(Room room, Guest guest) {
        Session session = SessionManager.getInstance().getSession();
        double sum = 0;
        try {
            sum = registrationDao.getSumByRoom(session, room, guest);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }finally {
            SessionManager.getInstance().closeSession(session);
        }
        return sum;
    }

    @Override
    public List<Guest> getSortedByFinalDate() {
        Session session = SessionManager.getInstance().getSession();
        List<Guest> guests = null;
        try {
            guests = registrationDao.getSortedByFinalDate(session);
        }catch (Exception e) {
            LOG.error(e.getMessage());
        }finally {
            SessionManager.getInstance().closeSession(session);
        }
        return guests;
    }

    @Override
    public Registration getRegistration(int id) {
        Session session = SessionManager.getInstance().getSession();
        Registration registration = null;
        try {
            registration = registrationDao.getById(session, id);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }finally {
            SessionManager.getInstance().closeSession(session);
        }
        return registration;
    }

    @Override
    public List<Registration> getAll(SortType type) {
        Session session = SessionManager.getInstance().getSession();
        List<Registration> registrations = null;
        try {
            registrations = registrationDao.getAll(session, type, null);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }finally {
            SessionManager.getInstance().closeSession(session);
        }
        return registrations;
    }

    public void setRegistrationRepository(RegistrationDao registrationDao) {
        this.registrationDao = registrationDao;
    }
}
