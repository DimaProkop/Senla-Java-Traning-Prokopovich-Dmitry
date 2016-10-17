package com.training.senla.service;

import com.training.senla.model.GuestModel;
import com.training.senla.model.RegistrationModel;

import java.time.LocalDate;

/**
 * Created by prokop on 16.10.16.
 */
public interface RegistrationModelService {
    void addRecord(RegistrationModel registrationModel);

    void setFinalDate(GuestModel guestModel);

    RegistrationModel getRegistration(int id);
}
