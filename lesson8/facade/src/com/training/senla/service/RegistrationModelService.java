package com.training.senla.service;

import com.training.senla.model.RegistrationModel;
import com.training.senla.repository.RegistrationModelRepository;

import java.util.List;

/**
 * Created by prokop on 16.10.16.
 */
public interface RegistrationModelService {
    void addRecord(RegistrationModel registrationModel);

    void update(RegistrationModel registrationModel);

    RegistrationModel getRegistration(int id);

    List<RegistrationModel> getAll();

    void setRegistrationModelRepository(RegistrationModelRepository registrationModelRepository);
}
