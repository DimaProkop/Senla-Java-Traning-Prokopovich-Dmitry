package com.training.senla.service;

import com.training.senla.enums.SortType;
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

    List<Guest> getAll(SortType type);

    int getCount();

    void setGuestDao(GuestDao guestDao);
}
