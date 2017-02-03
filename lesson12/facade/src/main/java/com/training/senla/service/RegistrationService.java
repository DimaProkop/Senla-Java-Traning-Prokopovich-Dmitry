package com.training.senla.service;

import com.training.senla.enums.SortType;
import com.training.senla.model.Guest;
import com.training.senla.model.Registration;
import com.training.senla.dao.RegistrationDao;
import com.training.senla.model.Room;

import java.util.List;

/**
 * Created by prokop on 16.10.16.
 */
public interface RegistrationService {
    void addRecord(Registration registration);

    void update(Registration registration);

    List<Guest> getSortedByFinalDate();

    double getSumByRoom(Room room, Guest guest);

    Registration getRegistration(int id);

    List<Registration> getAll(SortType type);

    void setRegistrationRepository(RegistrationDao registrationRepository);
}
