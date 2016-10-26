package com.training.senla.service.impl;

import com.training.senla.model.GuestModel;
import com.training.senla.model.RegistrationModel;
import com.training.senla.repository.RegistrationModelRepository;
import com.training.senla.service.RegistrationModelService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by prokop on 16.10.16.
 */
public class RegistrationModelServiceImpl implements RegistrationModelService{

    private static final Logger LOG = LogManager.getLogger(RegistrationModelServiceImpl.class);

    private RegistrationModelRepository registrationModelRepository;

    public RegistrationModelServiceImpl(RegistrationModelRepository registrationModelRepository) {
        this.registrationModelRepository = registrationModelRepository;
    }

    @Override
    public void addRecord(RegistrationModel registrationModel) {
        try {
            registrationModelRepository.addRecord(registrationModel);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }

    }

    @Override
    public void setFinalDate(GuestModel guestModel) {
        try {
            registrationModelRepository.setFinalDate(guestModel);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }

    @Override
    public RegistrationModel getRegistration(int id) {
        RegistrationModel registration = null;
        try {
            registration = registrationModelRepository.getRegistration(id);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return registration;
    }

    @Override
    public List<RegistrationModel> getAll() {
        List<RegistrationModel> registrations = null;
        try {
            registrations = registrationModelRepository.getAll();
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return registrations;
    }

}
