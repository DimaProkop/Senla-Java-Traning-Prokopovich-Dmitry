package com.training.senla.service.impl;

import com.training.senla.model.RegistrationModel;
import com.training.senla.repository.RegistrationModelRepository;
import com.training.senla.service.RegistrationModelService;
import com.training.senla.util.connection.ConnectionManager;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.util.List;

/**
 * Created by prokop on 16.10.16.
 */
public class RegistrationModelServiceImpl implements RegistrationModelService{

    private static final Logger LOG = LogManager.getLogger(RegistrationModelServiceImpl.class);

    private RegistrationModelRepository registrationModelRepository;

    public RegistrationModelServiceImpl() {
    }

    public RegistrationModelServiceImpl(RegistrationModelRepository registrationModelRepository) {
        this.registrationModelRepository = registrationModelRepository;
    }

    @Override
    public void addRecord(RegistrationModel registrationModel) {
        Connection connection = ConnectionManager.getConnection();
        try {
            registrationModelRepository.set(connection, registrationModel);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }

    }

    @Override
    public void update(RegistrationModel registrationModel) {
        Connection connection = ConnectionManager.getConnection();
        try {
            registrationModelRepository.update(connection, registrationModel);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }

    @Override
    public RegistrationModel getRegistration(int id) {
        Connection connection = ConnectionManager.getConnection();
        RegistrationModel registration = null;
        try {
            registration = registrationModelRepository.get(connection, id);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return registration;
    }

    @Override
    public List<RegistrationModel> getAll() {
        Connection connection = ConnectionManager.getConnection();
        List<RegistrationModel> registrations = null;
        try {
            registrations = registrationModelRepository.getAll(connection);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return registrations;
    }

    public void setRegistrationModelRepository(RegistrationModelRepository registrationModelRepository) {
        this.registrationModelRepository = registrationModelRepository;
    }
}
