package com.training.senla.repository;

import com.training.senla.model.RegistrationModel;

import java.util.List;

/**
 * Created by prokop on 16.10.16.
 */
public interface RegistrationModelRepository {
    void addRecord(RegistrationModel registrationModel);

    void update(RegistrationModel registrationModel);

    RegistrationModel getRegistration(int id);

    List<RegistrationModel> getAll();

    void setRegistrations(List<RegistrationModel> registrations);
}
