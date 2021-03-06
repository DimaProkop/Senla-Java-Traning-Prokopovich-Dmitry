package com.training.senla.repository.impl;

import com.training.senla.model.RegistrationModel;
import com.training.senla.model.RoomModel;
import com.training.senla.repository.RegistrationModelRepository;
import com.training.senla.storage.Storage;

import java.util.List;

/**
 * Created by prokop on 16.10.16.
 */
public class RegistrationModelRepositoryImpl implements RegistrationModelRepository {

    private int currentId=1;

    public void calcCurrentId() {
        int maxId = 0;
        for (RegistrationModel registration : Storage.registrations) {
            if(registration.getId() > maxId) {
                maxId = registration.getId();
            }
        }
        currentId = maxId + 1;
    }

    private int getRegistrationIndexById(int id) {
        for (int i = 0; i < Storage.registrations.size(); i++) {
            if(Storage.registrations.get(i).getGuestId() == id) {
                return i;
            }
        }
        return -1;
    }

    public RegistrationModelRepositoryImpl(List<RegistrationModel> registrationModelList) {
        Storage.registrations = registrationModelList;
        calcCurrentId();
    }

    @Override
    public void addRecord(RegistrationModel registrationModel) {
        registrationModel.setId(currentId++);
        Storage.registrations.add(registrationModel);
    }

    @Override
    public RegistrationModel getRegistration(int id) {
        return Storage.registrations.get(getRegistrationIndexById(id));
    }

    @Override
    public List<RegistrationModel> getAll() {
        return Storage.registrations;
    }
}
