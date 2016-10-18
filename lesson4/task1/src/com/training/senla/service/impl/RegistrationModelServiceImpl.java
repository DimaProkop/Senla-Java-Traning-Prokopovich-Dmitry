package com.training.senla.service.impl;

import com.training.senla.model.GuestModel;
import com.training.senla.model.RegistrationModel;
import com.training.senla.repository.RegistrationModelRepository;
import com.training.senla.service.RegistrationModelService;

import java.util.List;

/**
 * Created by prokop on 16.10.16.
 */
public class RegistrationModelServiceImpl implements RegistrationModelService{

    private RegistrationModelRepository registrationModelRepository;

    public RegistrationModelServiceImpl(RegistrationModelRepository registrationModelRepository) {
        this.registrationModelRepository = registrationModelRepository;
    }

    @Override
    public void addRecord(RegistrationModel registrationModel) {
        registrationModelRepository.addRecord(registrationModel);
    }

    @Override
    public void setFinalDate(GuestModel guestModel) {
        registrationModelRepository.setFinalDate(guestModel);
    }

    @Override
    public RegistrationModel getRegistration(int id) {
        return registrationModelRepository.getRegistration(id);
    }

    @Override
    public List<RegistrationModel> getAll() {
        return null;
    }

}
