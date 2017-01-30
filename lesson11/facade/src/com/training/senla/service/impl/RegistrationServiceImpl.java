package com.training.senla.service.impl;

import com.training.senla.enums.SortType;
import com.training.senla.model.Registration;
import com.training.senla.dao.RegistrationDao;
import com.training.senla.service.RegistrationService;
import com.training.senla.util.connection.ConnectionManager;
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

    public RegistrationServiceImpl() {
    }

    @Override
    public void addRecord(Registration registration) {
        Connection connection = ConnectionManager.getInstance().getConnection();
        try {
            registrationDao.add(connection, registration);
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
            status = registrationDao.update(connection, registration);
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
            registration = registrationDao.getById(connection, id);
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
            registrations = registrationDao.getAll(connection, type, null);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return registrations;
    }

    public void setRegistrationRepository(RegistrationDao registrationDao) {
        this.registrationDao = registrationDao;
    }
}
