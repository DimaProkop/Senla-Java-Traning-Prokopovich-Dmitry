package com.training.senla.repository.impl;

import com.training.senla.model.GuestModel;
import com.training.senla.model.RegistrationModel;
import com.training.senla.repository.RegistrationModelRepository;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by prokop on 16.10.16.
 */
public class RegistrationModelRepositoryImpl implements RegistrationModelRepository {

    private List<RegistrationModel> registrationModelList;
    public static int currentId=1;

    public RegistrationModelRepositoryImpl(List<RegistrationModel> registrationModelList) {
        this.registrationModelList = registrationModelList;
    }

    @Override
    public void addRecord(RegistrationModel registrationModel) {
        registrationModel.setRegistrationId(currentId++);
        registrationModelList.add(registrationModel);
    }

    @Override
    public void setFinalDate(GuestModel guestModel) {
        registrationModelList.get(guestModel.getGuestId()).setFinalDate(guestModel.getFinalDate());
    }
}
