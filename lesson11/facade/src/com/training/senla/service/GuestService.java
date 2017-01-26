package com.training.senla.service;

import com.training.senla.model.Guest;
import com.training.senla.model.Room;
import com.training.senla.model.Service;
import com.training.senla.dao.GuestDao;

import java.util.List;

/**
 * Created by prokop on 13.10.16.
 */
public interface GuestService {
    void addGuest(Guest guest);

    Guest getGuest(int id);

    void update(Guest guest);

    void delete(Guest guest);

    List<Service> getServicesByPrice(Guest guest);

    List<Service> getServicesByDate(Guest guest);

    List<Guest> getAll();

    List<Guest> getSortedByFinalDate();

    List<Guest> getSortedByName();

    double getSumByRoom(Room room, Guest guest);

    int getCount();

    void setGuestDao(GuestDao guestDao);
}
