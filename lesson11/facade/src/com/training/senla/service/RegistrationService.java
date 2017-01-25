package com.training.senla.service;

import com.training.senla.model.Registration;
import com.training.senla.dao.RegistrationDao;

import java.util.List;

/**
 * Created by prokop on 16.10.16.
 */
public interface RegistrationService {
    void addRecord(Registration registration);

    void update(Registration registration);

    Registration getRegistration(int id);

    List<Registration> getAll();

    void setRegistrationRepository(RegistrationDao registrationRepository);
}
