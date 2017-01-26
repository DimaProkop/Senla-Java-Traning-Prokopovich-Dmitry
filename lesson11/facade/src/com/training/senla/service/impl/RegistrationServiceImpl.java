package com.training.senla.service.impl;

import com.training.senla.enums.SortType;
import com.training.senla.model.Registration;
import com.training.senla.dao.RegistrationDao;
import com.training.senla.service.RegistrationService;
import com.training.senla.util.connection.ConnectionManager;
import com.training.senla.util.db.LibraryQueries;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by prokop on 16.10.16.
 */
public class RegistrationServiceImpl implements RegistrationService {

    private static final Logger LOG = LogManager.getLogger(RegistrationServiceImpl.class);

    private RegistrationDao registrationDao;
    private LibraryQueries library;

    public RegistrationServiceImpl() {
        library = new LibraryQueries();
    }

    @Override
    public void addRecord(Registration registration) {
        Connection connection = ConnectionManager.getInstance().getConnection();
        try {
            PreparedStatement statement = library.set(connection, registration);
            registrationDao.set(statement);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }

    }

    @Override
    public void update(Registration registration) {
        Connection connection = ConnectionManager.getInstance().getConnection();
        boolean status = false;
        try {
            connection.setAutoCommit(false);
            PreparedStatement statement = library.update(connection, registration);
            status = registrationDao.update(statement);
            if (status) {
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
    public Registration getRegistration(int id) {
        Connection connection = ConnectionManager.getInstance().getConnection();
        Registration registration = null;
        try {
            PreparedStatement statement = library.get(library.GET_REGISTRATION, connection, id);
            registration = registrationDao.get(statement);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return registration;
    }

    @Override
    public List<Registration> getAll(SortType type) {
        Connection connection = ConnectionManager.getInstance().getConnection();
        List<Registration> registrations = null;
        try {
            PreparedStatement statement = library.getAll(library.GET_SORT_REGISTRATION, connection, type);
            registrations = registrationDao.getAll(statement);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return registrations;
    }

    public void setRegistrationRepository(RegistrationDao registrationDao) {
        this.registrationDao = registrationDao;
    }
}
